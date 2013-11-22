package org.fourgeeks.gha.webclient.client.maintenanceplan;

import java.util.List;

import org.fourgeeks.gha.domain.enu.MaintenancePlanState;
import org.fourgeeks.gha.domain.enu.MaintenancePlanType;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHATopForm;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHAPeriodOfTimeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAMaintenancePlanStateSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAMaintenancePlanTypeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACleanButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHADeleteButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASearchButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATabSet;

import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

public class MaintenancePlanTopForm extends
		GHATopForm<MaintenancePlanResultSet, MaintenancePlan> implements
		MaintenancePlanSelectionListener {

	private GHATextItem nameItem, frequencyItem, descriptionItem;
	private GHAPeriodOfTimeSelectItem periodOfTimeItem;

	private final GHASearchButton searchButton;
	private final GHACleanButton cleanButton;
	private final GHADeleteButton deleteButton;
	private GHAMaintenancePlanTypeSelectItem typeItem;
	private GHAMaintenancePlanStateSelectItem stateItem;
	private final VLayout sideButtons;
	protected MaintenancePlan selectedMaintenancePlan;

	{
		nameItem = new GHATextItem(GHAStrings.get("name"), false);
		nameItem.setColSpan(2);
		frequencyItem = new GHATextItem(GHAStrings.get("frecuency"), false);
		periodOfTimeItem = new GHAPeriodOfTimeSelectItem();
		typeItem = new GHAMaintenancePlanTypeSelectItem();
		// typeItem.setDefaultValue(MaintenancePlanType.PREVENTIVE);
		stateItem = new GHAMaintenancePlanStateSelectItem();
		// stateItem.setDefaultValue(MaintenancePlanState.ACTIVE);
		descriptionItem = new GHATextItem(GHAStrings.get("description"), false);
		descriptionItem.setColSpan(2);
	}

	public MaintenancePlanTopForm(MaintenancePlanResultSet resultSet,
			MaintenancePlanTab tab) {
		super(resultSet, tab);

		GHADynamicForm form = new GHADynamicForm(
				GHAUiHelper.getNormalFormWidth(30), 4);
		form.setItems(nameItem, typeItem, stateItem, descriptionItem,
				frequencyItem, periodOfTimeItem);

		searchButton = new GHASearchButton(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				search();
			}
		});
		cleanButton = new GHACleanButton(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				clear();
			}
		});

		deleteButton = new GHADeleteButton(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				GHATabSet.closeTab(containerTab);
			}
		});

		sideButtons = GHAUiHelper.createBar(searchButton, cleanButton,
				deleteButton);
		addMembers(form, new LayoutSpacer(), sideButtons);
		deactivate();
	}

	@Override
	public void activate() {
		toggleForm(true);
		sideButtons.removeMember(deleteButton);
		sideButtons.addMember(searchButton, 0);
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
		if (!this.activated)
			return;
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
		sideButtons.removeMember(searchButton);
		sideButtons.addMember(deleteButton, 0);
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
			maintenancePlan.setType(MaintenancePlanType.valueOf(stateItem
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
	}

}