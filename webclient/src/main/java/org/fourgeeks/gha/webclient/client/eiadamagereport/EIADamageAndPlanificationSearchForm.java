package org.fourgeeks.gha.webclient.client.eiadamagereport;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.ess.WorkingArea;
import org.fourgeeks.gha.domain.gar.Facility;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.GHAUtil;
import org.fourgeeks.gha.webclient.client.UI.ResultSetContainerType;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAFacilitySelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAWorkingAreaSelectItem;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACancelButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACleanButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASearchButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASearchForm;
import org.fourgeeks.gha.webclient.client.eia.EIAModel;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eia.EiaResultSet;
import org.fourgeeks.gha.webclient.client.eia.EiaSelectionProducer;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.events.KeyUpEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyUpHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret, emiliot
 * 
 */
public class EIADamageAndPlanificationSearchForm extends GHASearchForm<Eia> implements
		EIATypeSelectionListener, EIASelectionListener, EiaSelectionProducer {

	private GHATextItem serialNumber;
	private GHAWorkingAreaSelectItem workingAreaLocationSelectItem;
	private GHAFacilitySelectItem facilityLocationSelectItem;

	private final EiaResultSet resultSet = new EiaResultSet(
			ResultSetContainerType.SEARCH_FORM);
	private GHADynamicForm form;
	private EiaType eiaType;

	{
		serialNumber = new GHATextItem(GHAStrings.get("serialNumber-item"));
		workingAreaLocationSelectItem = new GHAWorkingAreaSelectItem();
		facilityLocationSelectItem = new GHAFacilitySelectItem();

		resultSet.addEiaSelectionListener(new EIASelectionListener() {
			@Override
			public void select(Eia eia) {
				hide();
			}
		});

		form = new GHADynamicForm(GHAUiHelper.getNormalFormWidth(30), 3);
	}

	/**
	 * 
	 */
	public EIADamageAndPlanificationSearchForm(String title) {
		super(title);

		GHAUiHelper.addGHAResizeHandler(this);

		form.setItems(serialNumber, workingAreaLocationSelectItem,
				facilityLocationSelectItem);

		form.setAutoFocus(true);
		serialNumber.setSelectOnFocus(true);

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
		workingAreaLocationSelectItem.addKeyUpHandler(searchKeyUpHandler);
		facilityLocationSelectItem.addKeyUpHandler(searchKeyUpHandler);

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
		super.onResize(event);
		form.resize(GHAUiHelper.getNormalFormWidth(30), 3);
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
		Eia eia = new Eia();
		eia.setState(null);
		eia.setEiaType(eiaType);

		if (serialNumber.getValue() != null)
			eia.setSerialNumber(serialNumber.getValueAsString());
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

	@Override
	public void select(EiaType eiaType) {
		this.eiaType = eiaType;
	}
}
