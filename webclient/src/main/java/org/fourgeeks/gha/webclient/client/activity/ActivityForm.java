package org.fourgeeks.gha.webclient.client.activity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHAAlertManager;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHADateItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextAreaItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATitletextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHABpiSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHACurrencyTypeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAExternalProviderSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAMaintenancePlanCancelationOptionSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAMaintenancePlanStateSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAMaintenancePlanTypeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAPeriodOfTimeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHARoleSelectItem;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm.FormType;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAForm;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivityModel;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.validation.client.impl.Validation;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;

/**
 * @author caparicio
 * 
 */
public class ActivityForm extends GHAForm<MaintenanceActivity> implements
		ActivitySelectionListener, ActivitySelectionProducer {

	private List<ActivitySelectionListener> listeners;

	private GHATitletextItem planStadistics_TitleItem;
	private GHATextItem nameItem, frequencyItem, estimatedTimeItem,
			protocolActivitiesItem, estimatedCostItem, effectuatedTimesItem,
			eiasWithThisPlanItem;
	private GHATextAreaItem descriptionItem;
	private GHAPeriodOfTimeSelectItem frecuencyPoTItem;
	private GHAPeriodOfTimeSelectItem estimatedTimePoTSelectItem;
	private GHAMaintenancePlanTypeSelectItem typeItem;
	private GHAMaintenancePlanStateSelectItem stateItem;
	private GHAMaintenancePlanCancelationOptionSelectItem cancelationOptionItem;
	private GHARoleSelectItem roleSelectItem;
	private GHAExternalProviderSelectItem providerSelectItem;
	private GHACurrencyTypeSelectItem estimatedCostCurrencyItem;
	private GHABpiSelectItem bpiSelectItem;
	private GHADateItem lastEffectuatedDateItem;
	private Validator validator;

	private GHADynamicForm form;

	{
		bpiSelectItem = new GHABpiSelectItem(false, changedHandler);
		nameItem = new GHATextItem(GHAStrings.get("name"), true, changedHandler);
		nameItem.setLength(100);
		frequencyItem = new GHATextItem(GHAStrings.get("frecuency"), true,
				changedHandler);
		frequencyItem.setLength(3);
		frecuencyPoTItem = new GHAPeriodOfTimeSelectItem(true, changedHandler);
		descriptionItem = new GHATextAreaItem(GHAStrings.get("description"),
				changedHandler);
		descriptionItem.setColSpan(3);
		typeItem = new GHAMaintenancePlanTypeSelectItem(true, changedHandler);
		stateItem = new GHAMaintenancePlanStateSelectItem(true, changedHandler);
		cancelationOptionItem = new GHAMaintenancePlanCancelationOptionSelectItem(
				true, changedHandler);
		providerSelectItem = new GHAExternalProviderSelectItem(false,
				changedHandler);
		roleSelectItem = new GHARoleSelectItem(false, changedHandler);

		estimatedTimeItem = new GHATextItem(GHAStrings.get("estimated-time"),
				false);
		estimatedTimePoTSelectItem = new GHAPeriodOfTimeSelectItem();
		estimatedTimePoTSelectItem.setDisabled(true);
		protocolActivitiesItem = new GHATextItem(
				GHAStrings.get("protocol-activities"), false);
		estimatedCostItem = new GHATextItem(GHAStrings.get("estimated-cost"),
				false);
		estimatedCostCurrencyItem = new GHACurrencyTypeSelectItem();
		estimatedCostCurrencyItem.setDisabled(true);
		effectuatedTimesItem = new GHATextItem(
				GHAStrings.get("times-effectuated"), false);
		eiasWithThisPlanItem = new GHATextItem(
				GHAStrings.get("eias-with-this-plan"), false);
		lastEffectuatedDateItem = new GHADateItem(
				GHAStrings.get("last-effectuated-date"), false);
		planStadistics_TitleItem = new GHATitletextItem(
				GHAStrings.get("maintenance-plan-stadistics"), 4);

		planStadistics_TitleItem.setVisible(false);
		protocolActivitiesItem.setVisible(false);
		estimatedTimeItem.setVisible(false);
		estimatedTimePoTSelectItem.setVisible(false);
		estimatedCostItem.setVisible(false);
		estimatedCostCurrencyItem.setVisible(false);
		effectuatedTimesItem.setVisible(false);
		eiasWithThisPlanItem.setVisible(false);
		lastEffectuatedDateItem.setVisible(false);

		validator = Validation.buildDefaultValidatorFactory().getValidator();
		listeners = new ArrayList<ActivitySelectionListener>();

		form = new GHADynamicForm(4, FormType.NORMAL_FORM);
	}

	/**
 * 
 */
	public ActivityForm() {
		final HLayout mainPanel = new HLayout();
		form.setItems(bpiSelectItem, new GHASpacerItem(3), nameItem,
				frequencyItem, frecuencyPoTItem, new GHASpacerItem(), typeItem,
				stateItem, cancelationOptionItem, new GHASpacerItem(),
				descriptionItem, new GHASpacerItem(), providerSelectItem,
				roleSelectItem, new GHASpacerItem(2), new GHASpacerItem(4),
				planStadistics_TitleItem, protocolActivitiesItem,
				estimatedTimeItem, estimatedTimePoTSelectItem,
				new GHASpacerItem(), estimatedCostItem,
				estimatedCostCurrencyItem, new GHASpacerItem(2),
				effectuatedTimesItem, eiasWithThisPlanItem,
				new GHASpacerItem(2), lastEffectuatedDateItem);

		mainPanel.addMembers(form, new LayoutSpacer());
		addMember(mainPanel);
	}

	//
	// @Override
	// public void activate() {
	// toggleForm(true);
	// }

	@Override
	public void addActivitySelectionListener(
			ActivitySelectionListener activitySelectionListener) {
		listeners.add(activitySelectionListener);
	}

	@Override
	public void clear() {
		super.clear();
		nameItem.clearValue();
		frequencyItem.clearValue();
		frecuencyPoTItem.clearValue();
		descriptionItem.clearValue();
		stateItem.clearValue();
		typeItem.clearValue();
		cancelationOptionItem.clearValue();
		roleSelectItem.clearValue();
		providerSelectItem.clearValue();
		bpiSelectItem.clearValue();
		cancelationOptionItem.clearValue();
		frecuencyPoTItem.clearValue();
	}

	//
	// @Override
	// public void deactivate() {
	// toggleForm(false);
	// }

	private MaintenanceActivity extract(boolean update) {
		final MaintenanceActivity maintenanceActivity = new MaintenanceActivity();
		if (update) {
			maintenanceActivity.setId(this.originalEntity.getId());
		}
		//
		// if (bpiSelectItem.getValue() != null) {
		// Bpi bpi = new Bpi(Long.valueOf(bpiSelectItem.getValueAsString()));
		// maintenanceActivity.setInstitution(bpi);
		// }
		if (nameItem.getValue() != null) {
			maintenanceActivity.setName(nameItem.getValueAsString());
		}
		if (descriptionItem.getValue() != null) {
			maintenanceActivity.setDescription(descriptionItem
					.getValueAsString());
		}
		//
		// // TODO: handle number format exception
		// if (frequencyItem.getValue() != null) {
		// maintenanceActivity.setFrequency(Integer.valueOf(frequencyItem
		// .getValueAsString()));
		// }
		// if (frecuencyPoTItem.getValue() != null)
		// maintenanceActivity.setPot(TimePeriodEnum.valueOf(frecuencyPoTItem
		// .getValueAsString()));
		//
		// if (typeItem.getValue() != null)
		// maintenanceActivity.setType(MaintenancePlanType.valueOf(typeItem
		// .getValueAsString()));
		//
		// if (stateItem.getValue() != null)
		// maintenanceActivity.setState(MaintenancePlanState.valueOf(stateItem
		// .getValueAsString()));
		//
		// if (cancelationOptionItem.getValue() != null) {
		// MaintenancePlanCancelationOption option =
		// MaintenancePlanCancelationOption
		// .valueOf(cancelationOptionItem.getValueAsString());
		// maintenanceActivity.setCancelationOption(option);
		// }
		//
		// if (roleSelectItem.getValue() != null) {
		// String id = roleSelectItem.getValueAsString();
		// Role role = new Role(Long.valueOf(id));
		// maintenanceActivity.setRole(role);
		// }
		//
		// if (providerSelectItem.getValue() != null) {
		// String id = providerSelectItem.getValueAsString();
		// ExternalProvider provider = new ExternalProvider(Long.valueOf(id));
		// maintenanceActivity.setProvider(provider);
		// }

		Set<ConstraintViolation<MaintenanceActivity>> violations = validator
				.validate(maintenanceActivity);
		if (form.validate() && violations.isEmpty())
			return maintenanceActivity;
		else {
			List<String> violationsList = new ArrayList<String>();
			for (Iterator<ConstraintViolation<MaintenanceActivity>> it = violations
					.iterator(); it.hasNext();) {
				violationsList.add(it.next().getMessage());
			}
			GHAAlertManager.alert(violationsList);
		}
		return null;
	}

	@Override
	public void notifyActivity(MaintenanceActivity activity) {
		for (ActivitySelectionListener listener : listeners)
			listener.select(activity);
	}

	@Override
	public void onResize(ResizeEvent event) {
		form.resize();
	}

	@Override
	public void removeActivitySelectionListener(
			ActivitySelectionListener activitySelectionListener) {
		listeners.remove(activitySelectionListener);
	}

	@Override
	public void save(final GHAAsyncCallback<MaintenanceActivity> callback) {
		MaintenanceActivity maintenanceActivity = extract(false);
		if (maintenanceActivity == null)
			return;

		MaintenanceActivityModel.save(maintenanceActivity,
				new GHAAsyncCallback<MaintenanceActivity>() {

					@Override
					public void onSuccess(MaintenanceActivity result) {
						hasUnCommittedChanges = false;
						notifyActivity(result);
						clear();
						if (callback != null)
							callback.onSuccess(result);
					}
				});
	}

	@Override
	public void select(MaintenanceActivity activity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void activate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(GHAAsyncCallback<MaintenanceActivity> callback) {
		// TODO Auto-generated method stub

	}

	@Override
	public void set(MaintenanceActivity entity) {
		// TODO Auto-generated method stub

	}

	// @Override
	// public void select(MaintenanceProtocols entity) {
	// MaintenancePlanModel.getStadisticInfo(originalEntity,
	// new GHAAsyncCallback<MaintenancePlanStadisticData>() {
	// @Override
	// public void onSuccess(MaintenancePlanStadisticData result) {
	// long numActivities = result.getNumberActivities();
	// protocolActivitiesItem.setValue(numActivities);
	//
	// long estimatedDuration = result.getEstimatedDuration();
	// estimatedTimeItem.setValue(estimatedDuration);
	//
	// String potName = result.getPot().name();
	// estimatedTimePoTSelectItem.setValue(potName);
	//
	// long numberOfEias = result.getNumberOfEias();
	// eiasWithThisPlanItem.setValue(numberOfEias);
	//
	// long timesEffectuated = result.getTimesEffectuated();
	// effectuatedTimesItem.setValue(timesEffectuated);
	//
	// BigDecimal estimatedCost = result.getEstimatedCost();
	// estimatedCostItem.setValue(estimatedCost);
	//
	// estimatedCostCurrencyItem.setValue(CurrencyTypeEnum.BS);
	//
	// Timestamp time = result.getLastTimeEffectuated();
	// lastEffectuatedDateItem.setValue(time);
	// }
	// });
	//
	// }
	//
	// @Override
	// public void set(MaintenancePlan maintenancePlan) {
	// this.originalEntity = maintenancePlan;
	// nameItem.setValue(maintenancePlan.getName());
	// descriptionItem.setValue(maintenancePlan.getDescription());
	// frequencyItem.setValue(maintenancePlan.getFrequency());
	// frecuencyPoTItem.setValue(maintenancePlan.getPot().name());
	// typeItem.setValue(maintenancePlan.getType().name());
	// stateItem.setValue(maintenancePlan.getState().name());
	// cancelationOptionItem.setValue(maintenancePlan.getCancelationOption()
	// .name());
	//
	// if (maintenancePlan.getInstitution() != null)
	// bpiSelectItem.setValue(maintenancePlan.getInstitution().getId());
	// if (maintenancePlan.getRole() != null)
	// roleSelectItem.setValue(maintenancePlan.getRole().getId());
	// if (maintenancePlan.getProvider() != null)
	// providerSelectItem.setValue(maintenancePlan.getProvider().getId());
	//
	// select(null);
	//
	// showPlanStadisticsItems();
	// }
	//
	// private void showPlanStadisticsItems() {
	// planStadistics_TitleItem.show();
	// protocolActivitiesItem.show();
	// estimatedTimeItem.show();
	// estimatedTimePoTSelectItem.show();
	// estimatedCostItem.show();
	// estimatedCostCurrencyItem.show();
	// effectuatedTimesItem.show();
	// eiasWithThisPlanItem.show();
	// lastEffectuatedDateItem.show();
	// }
	//
	// private void toggleForm(boolean active) {
	// nameItem.setDisabled(!active);
	// frequencyItem.setDisabled(!active);
	// frecuencyPoTItem.setDisabled(!active);
	// descriptionItem.setDisabled(!active);
	// typeItem.setDisabled(!active);
	// stateItem.setDisabled(!active);
	// cancelationOptionItem.setDisabled(!active);
	// roleSelectItem.setDisabled(!active);
	// providerSelectItem.setDisabled(!active);
	// bpiSelectItem.setDisabled(!active);
	// }
	//
	// @Override
	// public void update(final GHAAsyncCallback<MaintenancePlan> callback) {
	// MaintenancePlan maintenancePlan = extract(true);
	//
	// if (maintenancePlan == null)
	// return;
	//
	// MaintenancePlanModel.update(maintenancePlan,
	// new GHAAsyncCallback<MaintenancePlan>() {
	// @Override
	// public void onSuccess(MaintenancePlan result) {
	// hasUnCommittedChanges = false;
	// notifyMaintenancePlan(result);
	// if (callback != null)
	// callback.onSuccess(result);
	// }
	// });
	// }

}
