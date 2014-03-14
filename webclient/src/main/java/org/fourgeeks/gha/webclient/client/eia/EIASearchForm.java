package org.fourgeeks.gha.webclient.client.eia;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.ess.WorkingArea;
import org.fourgeeks.gha.domain.ess.auth.Role;
import org.fourgeeks.gha.domain.gar.Facility;
import org.fourgeeks.gha.domain.gar.Obu;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.mix.Bpi;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.GHAUtil;
import org.fourgeeks.gha.webclient.client.UI.ResultSetContainerType;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHABpiSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAEiaStateSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAFacilitySelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAObuSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHARoleSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAWorkingAreaSelectItem;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACancelButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACleanButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASearchButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm.FormType;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASearchForm;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.events.KeyUpEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyUpHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret, emiliot, jfuentes
 * 
 */
public class EIASearchForm extends GHASearchForm<Eia> implements
EIASelectionListener, EiaSelectionProducer {

	private GHATextItem serialNumber;
	private GHATextItem fixedAssetIdentifier;
	private GHAEiaStateSelectItem stateSelectItem;
	private GHAObuSelectItem obuSelectItem;
	private GHABpiSelectItem bpiObuSelectItem;
	private GHARoleSelectItem baseRoleSelectItem;
	private GHAWorkingAreaSelectItem workingAreaLocationSelectItem;
	private GHAFacilitySelectItem facilityLocationSelectItem;

	protected final EiaResultSet resultSet = new EiaResultSet(
			ResultSetContainerType.SEARCH_FORM);
	private GHADynamicForm form;

	{
		serialNumber = new GHATextItem(GHAStrings.get("serial"));
		fixedAssetIdentifier = new GHATextItem(
				GHAStrings.get("fixed-asset-identifier"));
		stateSelectItem = new GHAEiaStateSelectItem();
		workingAreaLocationSelectItem = new GHAWorkingAreaSelectItem();
		facilityLocationSelectItem = new GHAFacilitySelectItem();
		obuSelectItem = new GHAObuSelectItem();
		bpiObuSelectItem = new GHABpiSelectItem();
		baseRoleSelectItem = new GHARoleSelectItem();

		resultSet.addEiaSelectionListener(new EIASelectionListener() {
			@Override
			public void select(Eia eia) {
				hide();
			}
		});

		form = new GHADynamicForm(4,FormType.NORMAL_FORM);
	}

	/**
	 * 
	 */
	public EIASearchForm(String title) {
		super(title);

		form.setItems(serialNumber, fixedAssetIdentifier, stateSelectItem,
				bpiObuSelectItem, workingAreaLocationSelectItem,
				facilityLocationSelectItem, obuSelectItem, baseRoleSelectItem);
		form.setAutoFocus(true);
		serialNumber.setSelectOnFocus(true);

		final KeyUpHandler searchKeyUpHandler = new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (event.getKeyName().equals("Enter")) {
					search();
				}
			}
		};
		// actualCostItem.addKeyUpHandler(searchKeyUpHandler);
		serialNumber.addKeyUpHandler(searchKeyUpHandler);
		fixedAssetIdentifier.addKeyUpHandler(searchKeyUpHandler);
		stateSelectItem.addKeyUpHandler(searchKeyUpHandler);
		bpiObuSelectItem.addKeyUpHandler(searchKeyUpHandler);
		workingAreaLocationSelectItem.addKeyUpHandler(searchKeyUpHandler);
		facilityLocationSelectItem.addKeyUpHandler(searchKeyUpHandler);
		obuSelectItem.addKeyUpHandler(searchKeyUpHandler);
		baseRoleSelectItem.addKeyUpHandler(searchKeyUpHandler);

		final VLayout sideButtons = GHAUiHelper.createBar(new GHASearchButton(
				searchClickHandler), new GHACleanButton(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						clean();
					}
				}), new GHACancelButton(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						hide();
						clean();
					}
				}));

		final HLayout formLayout = new HLayout();
		formLayout.setPadding(10);
		formLayout.setHeight(GHAUiHelper.DEFAULT_INNER_TOP_SECTION_HEIGHT
				+ "px");
		formLayout.setDefaultLayoutAlign(VerticalAlignment.CENTER);
		formLayout.addMembers(form, new LayoutSpacer(), sideButtons);

		addMembers(formLayout,
				GHAUiHelper
				.verticalGraySeparator(GHAUiHelper.V_SEPARATOR_HEIGHT
						+ "px"), resultSet);
		fill();
	}

	@Override
	public void addEiaSelectionListener(
			EIASelectionListener eiaSelectionListener) {
		resultSet.addEiaSelectionListener(eiaSelectionListener);
	}

	/**
	 * 
	 */
	public void clean() {
		form.clearValues();
		resultSet.clean();
	}

	@Override
	public void close() {
		destroy();
	}

	private void fill() {
	}

	@Override
	public void hide() {
		super.hide();
	}

	@Override
	public void notifyEia(Eia eia) {
	}

	@Override
	public void onResize(ResizeEvent event) {
		super.onResize(event);
		form.resize();
	}

	@Override
	public void open() {
		resultSet.setVisible(true);
		super.open();
	}

	@Override
	public void removeEiaSelectionListener(
			EIASelectionListener eiaSelectionListener) {
		resultSet.removeEiaSelectionListener(eiaSelectionListener);
	}

	@Override
	public void search() {
		final Obu obu = new Obu();
		final Eia eia = new Eia();
		eia.setState(null);

		if (serialNumber.getValue() != null)
			eia.setSerialNumber(serialNumber.getValueAsString());
		if (fixedAssetIdentifier != null)
			eia.setFixedAssetIdentifier(fixedAssetIdentifier.getValueAsString());
		if (obuSelectItem.getValue() != null) {
			obu.setId(Long.valueOf(obuSelectItem.getValueAsString()));
			eia.setObu(obu);
		}
		if (bpiObuSelectItem.getValue() != null) {
			final Bpi bpi = new Bpi(Long.valueOf(bpiObuSelectItem.getValueAsString()));
			obu.setBpi(bpi);
			eia.setObu(obu);
		}
		if (baseRoleSelectItem.getValue() != null) {
			final Role baseRole = new Role();
			baseRole.setId(Integer.valueOf(baseRoleSelectItem
					.getValueAsString()));
			eia.setResponsibleRole(baseRole);
		}
		if (stateSelectItem.getValue() != null) {
			eia.setState(EiaStateEnum.valueOf(stateSelectItem
					.getValueAsString()));
		}
		if (facilityLocationSelectItem.getValue() != null) {
			eia.setWorkingArea(null);
			eia.setFacility(new Facility(Integer
					.valueOf(facilityLocationSelectItem.getValueAsString())));
		}
		if (workingAreaLocationSelectItem.getValue() != null) {
			eia.setFacility(null);
			eia.setWorkingArea(new WorkingArea(Integer
					.valueOf(workingAreaLocationSelectItem.getValueAsString())));
		}
		search(eia);
	}

	protected void search(final Eia eia) {
		EIAModel.find(eia, new GHAAsyncCallback<List<Eia>>() {

			@Override
			public void onSuccess(List<Eia> result) {
				List<Eia> newList = new ArrayList<Eia>();
				if (blackList != null) {
					final List<AbstractEntity> tmpList = GHAUtil
							.binarySearchFilterEntity(result, blackList);
					final List<Eia> newTmpList = new ArrayList<Eia>();
					for (final AbstractEntity entity : tmpList)
						newTmpList.add((Eia) entity);
					newList = newTmpList;
				} else
					newList = result;
				resultSet.setRecords(newList, false);
			}
		});
	}

	@Override
	public void select(Eia eia) {
		search(eia);
	}
}
