package org.fourgeeks.gha.webclient.client.maintenanceplan;

import java.util.List;

import org.fourgeeks.gha.domain.enu.MaintenancePlanState;
import org.fourgeeks.gha.domain.enu.MaintenancePlanType;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHATopForm;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHANotification;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAMaintenancePlanStateSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAMaintenancePlanTypeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAPeriodOfTimeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm.FormType;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.widgets.layout.LayoutSpacer;

/**
 * @author naramirez
 */
public class MaintenancePlanTopForm extends
		GHATopForm<MaintenancePlanResultSet, MaintenancePlan> implements
		MaintenancePlanSelectionListener {

	private GHATextItem nameItem, frequencyItem, descriptionItem;
	private GHAPeriodOfTimeSelectItem periodOfTimeItem;
	private GHAMaintenancePlanTypeSelectItem typeItem;
	private GHAMaintenancePlanStateSelectItem stateItem;

	protected MaintenancePlan selectedMaintenancePlan;
	private GHADynamicForm form;

	{
		nameItem = new GHATextItem(GHAStrings.get("name"), false);
		nameItem.setColSpan(2);
		frequencyItem = new GHATextItem(GHAStrings.get("frecuency"), false);
		periodOfTimeItem = new GHAPeriodOfTimeSelectItem();
		typeItem = new GHAMaintenancePlanTypeSelectItem();
		stateItem = new GHAMaintenancePlanStateSelectItem();
		descriptionItem = new GHATextItem(GHAStrings.get("description"), false);
		descriptionItem.setColSpan(2);
		form = new GHADynamicForm(4,FormType.NORMAL_FORM);
		nameItem.addKeyUpHandler(searchKeyUpHandler);
		frequencyItem.addKeyUpHandler(searchKeyUpHandler);
		periodOfTimeItem.addKeyUpHandler(searchKeyUpHandler);
		typeItem.addKeyUpHandler(searchKeyUpHandler);
		stateItem.addKeyUpHandler(searchKeyUpHandler);
		descriptionItem.addKeyUpHandler(searchKeyUpHandler);
		periodOfTimeItem.setDefaultValue((String) null);
	}

	/**
	 * @param resultSet
	 * @param tab
	 */
	public MaintenancePlanTopForm(MaintenancePlanResultSet resultSet,
			MaintenancePlanPanel tab) {
		super(resultSet, tab);

		form.setItems(nameItem, typeItem, stateItem, descriptionItem,
				frequencyItem, periodOfTimeItem);

		form.setAutoFocus(true);
		nameItem.setSelectOnFocus(true);

		addMembers(form, new LayoutSpacer(), sideButtons);
	}

	@Override
	public void activate() {
		toggleForm(true);
		super.activate();
	}

	private void toggleForm(boolean active) {
		nameItem.setDisabled(!active);
		frequencyItem.setDisabled(!active);
		periodOfTimeItem.setDisabled(!active);
		descriptionItem.setDisabled(!active);
		stateItem.setDisabled(!active);
		typeItem.setDisabled(!active);
		activated = active;
	}

	@Override
	public boolean canBeClosen(HideCloseAction hideAction) {
		return true;
	}

	@Override
	public void clear() {
		nameItem.clearValue();
		frequencyItem.clearValue();
		periodOfTimeItem.clearValue();
		descriptionItem.clearValue();
		typeItem.clearValue();
		stateItem.clearValue();
	}

	@Override
	public void deactivate() {
		toggleForm(false);
		sideButtons.removeMembers(searchButton, cleanButton, deleteButton);
	}

	@Override
	protected void delete() {
		GHANotification.confirm(GHAStrings.get("maitenance-plan"),
				GHAStrings.get("maintenance-plan-delete-confirm"),
				new BooleanCallback() {
					@Override
					public void execute(Boolean value) {
						if (value) {
							MaintenancePlanModel.delete(
									selectedMaintenancePlan.getId(),
									new GHAAsyncCallback<Void>() {
										@Override
										public void onSuccess(Void result) {
											containerTab.search();
											clear();
											GHANotification
													.alert("maintenance-delete-success");
										}
									});
						}
					}
				});
	}

	@Override
	public void search() {
		super.search();
		MaintenancePlan maintenancePlan = new MaintenancePlan();

		if (nameItem.getValue() != null)
			maintenancePlan.setName(nameItem.getValueAsString());
		if (descriptionItem.getValue() != null)
			maintenancePlan.setDescription(descriptionItem.getValueAsString());
		if (frequencyItem.getValue() != null)
			maintenancePlan.setFrequency(Integer.parseInt(frequencyItem
					.getValueAsString()));
		if (periodOfTimeItem.getValue() != null)
			maintenancePlan.setPot(TimePeriodEnum.valueOf(periodOfTimeItem
					.getValueAsString()));
		if (stateItem.getValue() != null)
			maintenancePlan.setState(MaintenancePlanState.valueOf(stateItem
					.getValueAsString()));
		if (typeItem.getValue() != null)
			maintenancePlan.setType(MaintenancePlanType.valueOf(typeItem
					.getValueAsString()));

		search(maintenancePlan);
	}

	@Override
	public void search(MaintenancePlan maintenancePlan) {
		MaintenancePlanModel.find(maintenancePlan,
				new GHAAsyncCallback<List<MaintenancePlan>>() {
					@Override
					public void onSuccess(List<MaintenancePlan> result) {
						resultSet.setRecords(result, true);
					}
				});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceplan.
	 * MaintenancePlanSelectionListener
	 * #select(org.fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public void select(MaintenancePlan maintenancePlan) {
		selectedMaintenancePlan = maintenancePlan;
		nameItem.setValue(maintenancePlan.getName());
		descriptionItem.setValue(maintenancePlan.getDescription());
		frequencyItem.setValue(maintenancePlan.getFrequency());
		periodOfTimeItem.setValue(maintenancePlan.getPot());
		stateItem.setValue(maintenancePlan.getState());
		typeItem.setValue(maintenancePlan.getType());
		deactivate();
		sideButtons.addMember(deleteButton, 0);
	}
	
	@Override
	public void onResize(ResizeEvent event) {
		super.onResize(event);
		form.resize();
	}

}