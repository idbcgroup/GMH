package org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.eialistplanification;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.ess.WorkingArea;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaPlanificationEntity;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.GHAUtil;
import org.fourgeeks.gha.webclient.client.UI.ResultSetContainerType;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAWorkingAreaSelectItem;
import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHACancelButton;
import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHACleanButton;
import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHASearchButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm.FormType;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASearchForm;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eia.EiaSelectionProducer;
import org.fourgeeks.gha.webclient.client.eiamaintenanceplanification.EiaMaintenancePlanificationModel;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionProducer;

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
 * @author eguerere
 * 
 */
public class SearchFormEiaListPlanification extends GHASearchForm<Eia>
		implements EIASelectionListener, EiaSelectionProducer,
		MaintenancePlanSelectionProducer {

	private GHATextItem serialNumber;
	private GHATextItem fixedAssetIdentifier;
	private GHAWorkingAreaSelectItem workingAreaLocationSelectItem;
	private EiaTypeMaintenancePlan eiaTypeMaintenancePlan;
	protected final ResultSetEiaListPlanification resultSet = new ResultSetEiaListPlanification(
			ResultSetContainerType.SEARCH_FORM);
	private GHADynamicForm form;

	{
		serialNumber = new GHATextItem(GHAStrings.get("serial"));
		fixedAssetIdentifier = new GHATextItem(
				GHAStrings.get("fixed-asset-identifier"));
		workingAreaLocationSelectItem = new GHAWorkingAreaSelectItem();

		resultSet.addEiaSelectionListener(new EIASelectionListener() {
			@Override
			public void select(Eia eia) {
				hide();
			}
		});

		form = new GHADynamicForm(3, FormType.NORMAL_FORM);
	}

	/**
	 * @param title
	 */
	public SearchFormEiaListPlanification(final String title) {
		super(title);
		form.setItems(serialNumber, fixedAssetIdentifier,
				workingAreaLocationSelectItem);
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
		serialNumber.addKeyUpHandler(searchKeyUpHandler);
		fixedAssetIdentifier.addKeyUpHandler(searchKeyUpHandler);
		workingAreaLocationSelectItem.addKeyUpHandler(searchKeyUpHandler);

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
		final Eia eia = new Eia();
		eia.setState(EiaStateEnum.IN_OPERATION);

		if (serialNumber.getValue() != null)
			eia.setSerialNumber(serialNumber.getValueAsString());
		if (fixedAssetIdentifier != null)
			eia.setFixedAssetIdentifier(fixedAssetIdentifier.getValueAsString());
		if (workingAreaLocationSelectItem.getValue() != null) {
			eia.setFacility(null);
			eia.setWorkingArea(new WorkingArea(Integer
					.valueOf(workingAreaLocationSelectItem.getValueAsString())));
		}
		search(eia);
	}

	protected void search(final Eia eia) {
		EiaMaintenancePlanificationModel.findEiaMaintenancePlanificationStatus(
				eia, eiaTypeMaintenancePlan,

				new GHAAsyncCallback<List<EiaPlanificationEntity>>() {
					@Override
					public void onSuccess(List<EiaPlanificationEntity> result) {
						List<EiaPlanificationEntity> newList = new ArrayList<EiaPlanificationEntity>();

						if (blackList != null) {
							final List<AbstractEntity> tmpList = GHAUtil
									.binarySearchFilterEntity(result, blackList);
							final List<EiaPlanificationEntity> newTmpList = new ArrayList<EiaPlanificationEntity>();

							for (final AbstractEntity entity : tmpList)
								newTmpList.add((EiaPlanificationEntity) entity);

							newList = newTmpList;

						} else {
							newList = result;
						}
						resultSet.setRecords(newList, false);
					}
				});
	}

	@Override
	public void select(Eia eia) {
		search(eia);
	}

	/**
	 * @return the eiaTypeMaintenancePlan
	 */
	public EiaTypeMaintenancePlan getEiaTypeMaintenancePlan() {
		return eiaTypeMaintenancePlan;
	}

	/**
	 * @param eiaTypeMaintenancePlan
	 *            the eiaTypeMaintenancePlan to set
	 */
	public void setEiaTypeMaintenancePlan(
			EiaTypeMaintenancePlan eiaTypeMaintenancePlan) {

		this.eiaTypeMaintenancePlan = eiaTypeMaintenancePlan;
		resultSet.getEiasPlanificationAddForm().select(eiaTypeMaintenancePlan);
		resultSet.setEiaTypeMaintenancePlan(eiaTypeMaintenancePlan);

	}

	@Override
	public void addMaintenancePlanSelectionListener(
			MaintenancePlanSelectionListener maintenancePlanSelectionListener) {
		resultSet
				.addMaintenancePlanSelectionListener(maintenancePlanSelectionListener);
	}

	@Override
	public void removeMaintenancePlanSelectionListener(
			MaintenancePlanSelectionListener maintenancePlanSelectionListener) {
		resultSet
				.removeMaintenancePlanSelectionListener(maintenancePlanSelectionListener);
	}

	@Override
	public void notifyMaintenancePlan(MaintenancePlan maintenancePlan) {
	}
}