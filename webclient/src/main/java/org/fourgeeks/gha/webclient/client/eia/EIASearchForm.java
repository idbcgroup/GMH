package org.fourgeeks.gha.webclient.client.eia;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.ess.Role;
import org.fourgeeks.gha.domain.ess.WorkingArea;
import org.fourgeeks.gha.domain.gar.Facility;
import org.fourgeeks.gha.domain.gar.Obu;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.mix.Bpi;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.GHAUtil;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHABpiSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHAEiaStateSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHAFacilitySelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHAObuSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHARoleSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHAWorkingAreaSelectItem;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACancelButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACleanButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASearchButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASearchForm;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.events.KeyUpEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyUpHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret, emiliot
 * 
 */
public class EIASearchForm extends GHASearchForm<Eia> implements
		EIASelectionListener, EiaSelectionProducer {
	private final int ITEM_WIDTH = 200;

	private GHATextItem serialNumber;
	private GHATextItem fixedAssetIdentifier;
	private GHAEiaStateSelectItem stateSelectItem;
	private GHAObuSelectItem obuSelectItem;
	private GHABpiSelectItem bpiObuSelectItem;
	private GHARoleSelectItem baseRoleSelectItem;
	private GHAWorkingAreaSelectItem workingAreaLocationSelectItem;
	private GHAFacilitySelectItem facilityLocationSelectItem;

	private EiaResultSet resultSet = new EiaResultSet();
	private final DynamicForm form = new DynamicForm();

	{
		serialNumber = new GHATextItem(GHAStrings.get("serialNumber-item"),
				ITEM_WIDTH);
		fixedAssetIdentifier = new GHATextItem(
				GHAStrings.get("fixedAssetIdentifier-item"), ITEM_WIDTH);
		stateSelectItem = new GHAEiaStateSelectItem(ITEM_WIDTH);
		workingAreaLocationSelectItem = new GHAWorkingAreaSelectItem(ITEM_WIDTH);
		facilityLocationSelectItem = new GHAFacilitySelectItem(ITEM_WIDTH);
		obuSelectItem = new GHAObuSelectItem(ITEM_WIDTH);
		bpiObuSelectItem = new GHABpiSelectItem(ITEM_WIDTH);
		baseRoleSelectItem = new GHARoleSelectItem(ITEM_WIDTH);
		resultSet.addEiaSelectionListener(new EIASelectionListener() {

			@Override
			public void select(Eia eia) {
				hide();
			}
		});
	}

	/**
	 * 
	 */
	public EIASearchForm(String title) {
		super(title);
		setTop(GHAUiHelper.DEFAULT_TOP_SECTION_HEIGHT);
		setHeight(GHAUiHelper.getTabHeight() - 5 + "px");

		GHAUiHelper.addGHAResizeHandler(this);

		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(4);
		form.setItems(serialNumber, fixedAssetIdentifier, stateSelectItem,
				bpiObuSelectItem, workingAreaLocationSelectItem,
				facilityLocationSelectItem, obuSelectItem, baseRoleSelectItem);

		KeyUpHandler searchKeyUpHandler = new KeyUpHandler() {

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

		VLayout sideButtons = GHAUiHelper.createBar(new GHASearchButton(
				searchClickHandler), new GHACleanButton(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				clean();
			}
		}), new GHACancelButton(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		}));

		HLayout formLayout = new HLayout();
		formLayout.setPadding(10);
		formLayout.setHeight(GHAUiHelper.DEFAULT_INNER_TOP_SECTION_HEIGHT
				+ "px");
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
		setHeight(GHAUiHelper.getTabHeight() - 5 + "px");
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
		Obu obu = new Obu();
		Eia eia = new Eia();
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
			Bpi bpi = new Bpi(Long.valueOf(bpiObuSelectItem.getValueAsString()));
			obu.setBpi(bpi);
			eia.setObu(obu);
		}
		if (baseRoleSelectItem.getValue() != null) {
			Role baseRole = new Role();
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

	private void search(final Eia eia) {
		EIAModel.find(eia, new GHAAsyncCallback<List<Eia>>() {

			@Override
			public void onSuccess(List<Eia> result) {
				List<Eia> newList = new ArrayList<Eia>();
				if (blackList != null) {
					List<AbstractEntity> tmpList = GHAUtil
							.binarySearchFilterEntity(result, blackList);
					List<Eia> newTmpList = new ArrayList<Eia>();
					for (AbstractEntity entity : tmpList)
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
