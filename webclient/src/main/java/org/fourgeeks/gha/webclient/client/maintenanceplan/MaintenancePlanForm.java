/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceplan;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.fourgeeks.gha.domain.enu.MaintenancePlanCancelationOption;
import org.fourgeeks.gha.domain.enu.MaintenancePlanState;
import org.fourgeeks.gha.domain.enu.MaintenancePlanType;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.ess.Role;
import org.fourgeeks.gha.domain.glm.ExternalProvider;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenancePlanStadisticData;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHADateItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextAreaItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATitletextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHACurrencyTypeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAExternalProviderSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAMaintenancePlanCancelationOptionSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAMaintenancePlanStateSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAMaintenancePlanTypeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAPeriodOfTimeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHARoleSelectItem;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.validation.client.impl.Validation;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;

/**
 * @author emiliot
 * 
 */
public class MaintenancePlanForm extends GHAForm<MaintenancePlan> implements
		MaintenancePlanSelectionProducer {

	private List<MaintenancePlanSelectionListener> listeners;

	private GHATitletextItem planStadistics_TitleItem;
	private GHATextItem nameItem, frequencyItem, estimatedTimeItem,
			protocolActivitiesItem, estimatedCostItem, effectuatedTimesItem,
			eiasWithThisPlanItem;
	private GHATextAreaItem descriptionItem;
	private GHAPeriodOfTimeSelectItem frecuencyPoTItem;
	private GHAPeriodOfTimeSelectItem estimatedTimePoTItem;
	private GHAMaintenancePlanTypeSelectItem typeItem;
	private GHAMaintenancePlanStateSelectItem stateItem;
	private GHAMaintenancePlanCancelationOptionSelectItem cancelationOptionItem;
	private GHARoleSelectItem roleSelectItem;
	private GHAExternalProviderSelectItem providerSelectItem;
	private GHACurrencyTypeSelectItem estimatedCostCurrencyItem;
	private GHADateItem lastEffectuatedDateItem;
	private Validator validator;

	private GHADynamicForm form;

	{
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

		estimatedTimeItem = new GHATextItem("Tiempo estimado", false);
		estimatedTimePoTItem = new GHAPeriodOfTimeSelectItem();
		estimatedTimePoTItem.setDisabled(true);
		protocolActivitiesItem = new GHATextItem("Actividades del protocolo",
				false);
		estimatedCostItem = new GHATextItem("Costo estimado", false);
		estimatedCostCurrencyItem = new GHACurrencyTypeSelectItem();
		estimatedCostCurrencyItem.setDisabled(true);
		effectuatedTimesItem = new GHATextItem("Veces efectuado", false);
		eiasWithThisPlanItem = new GHATextItem("Equipos con este plan", false);
		lastEffectuatedDateItem = new GHADateItem("Ultima fecha Efectuado",
				false);
		planStadistics_TitleItem = new GHATitletextItem(
				"Estadisticas del plan de mantenimiento", 4);

		planStadistics_TitleItem.setVisible(false);
		protocolActivitiesItem.setVisible(false);
		estimatedTimeItem.setVisible(false);
		estimatedTimePoTItem.setVisible(false);
		estimatedCostItem.setVisible(false);
		estimatedCostCurrencyItem.setVisible(false);
		effectuatedTimesItem.setVisible(false);
		eiasWithThisPlanItem.setVisible(false);
		lastEffectuatedDateItem.setVisible(false);

		validator = Validation.buildDefaultValidatorFactory().getValidator();
		listeners = new ArrayList<MaintenancePlanSelectionListener>();

		form = new GHADynamicForm(GHAUiHelper.getNormalFormWidth(30), 4);
	}

	/**
	 * 
	 */
	public MaintenancePlanForm() {
		final HLayout mainPanel = new HLayout();
		form.setItems(nameItem, frequencyItem, frecuencyPoTItem,
				new GHASpacerItem(), typeItem, stateItem,
				cancelationOptionItem, new GHASpacerItem(), descriptionItem,
				new GHASpacerItem(), providerSelectItem, roleSelectItem,
				new GHASpacerItem(2), new GHASpacerItem(4),
				planStadistics_TitleItem, protocolActivitiesItem,
				estimatedTimeItem, estimatedTimePoTItem, new GHASpacerItem(),
				estimatedCostItem, estimatedCostCurrencyItem,
				new GHASpacerItem(2), effectuatedTimesItem,
				eiasWithThisPlanItem, new GHASpacerItem(2),
				lastEffectuatedDateItem);

		mainPanel.addMembers(form, new LayoutSpacer());
		addMember(mainPanel);
	}

	@Override
	public void activate() {
		toggleForm(true);
	}

	@Override
	public void addMaintenancePlanSelectionListener(
			MaintenancePlanSelectionListener maintenancePlanSelectionListener) {
		listeners.add(maintenancePlanSelectionListener);
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
	}

	@Override
	public void deactivate() {
		toggleForm(false);
	}

	private MaintenancePlan extract(boolean update) {
		final MaintenancePlan maintenancePlan = new MaintenancePlan();
		if (update) {
			maintenancePlan.setId(this.originalEntity.getId());
		}

		if (nameItem.getValue() != null) {
			maintenancePlan.setName(nameItem.getValueAsString());
		}
		if (descriptionItem.getValue() != null) {
			maintenancePlan.setDescription(descriptionItem.getValueAsString());
		}

		// TODO: handle number format exception
		if (frequencyItem.getValue() != null) {
			maintenancePlan.setFrequency(Integer.valueOf(frequencyItem
					.getValueAsString()));
		}
		if (frecuencyPoTItem.getValue() != null)
			maintenancePlan.setPot(TimePeriodEnum.valueOf(frecuencyPoTItem
					.getValueAsString()));

		if (typeItem.getValue() != null)
			maintenancePlan.setType(MaintenancePlanType.valueOf(typeItem
					.getValueAsString()));

		if (stateItem.getValue() != null)
			maintenancePlan.setState(MaintenancePlanState.valueOf(stateItem
					.getValueAsString()));

		if (cancelationOptionItem.getValue() != null) {
			MaintenancePlanCancelationOption option = MaintenancePlanCancelationOption
					.valueOf(cancelationOptionItem.getValueAsString());
			maintenancePlan.setCancelationOption(option);
		}

		if (roleSelectItem.getValue() != null) {
			String id = roleSelectItem.getValueAsString();
			Role role = new Role(Long.valueOf(id));
			maintenancePlan.setRole(role);
		}

		if (providerSelectItem.getValue() != null) {
			String id = providerSelectItem.getValueAsString();
			ExternalProvider provider = new ExternalProvider(Long.valueOf(id));
			maintenancePlan.setProvider(provider);
		}

		Set<ConstraintViolation<MaintenancePlan>> violations = validator
				.validate(maintenancePlan);
		if (form.validate() && violations.isEmpty())
			return maintenancePlan;
		else {
			List<String> violationsList = new ArrayList<String>();
			for (Iterator<ConstraintViolation<MaintenancePlan>> it = violations
					.iterator(); it.hasNext();) {
				violationsList.add(it.next().getMessage());
			}
			GHANotification.alert(violationsList);
		}
		return null;
	}

	@Override
	public void notifyMaintenancePlan(MaintenancePlan plan) {
		for (MaintenancePlanSelectionListener listener : listeners)
			listener.select(plan);
	}

	@Override
	public void removeMaintenancePlanSelectionListener(
			MaintenancePlanSelectionListener maintenancePlanSelectionListener) {
		listeners.remove(maintenancePlanSelectionListener);
	}

	@Override
	public void save(final GHAAsyncCallback<MaintenancePlan> callback) {
		MaintenancePlan maintenancePlan = extract(false);
		if (maintenancePlan == null)
			return;

		MaintenancePlanModel.save(maintenancePlan,
				new GHAAsyncCallback<MaintenancePlan>() {

					@Override
					public void onSuccess(MaintenancePlan result) {
						hasUnCommittedChanges = false;
						notifyMaintenancePlan(result);
						clear();
						if (callback != null)
							callback.onSuccess(result);
					}
				});
	}

	@Override
	public void set(MaintenancePlan maintenancePlan) {
		this.originalEntity = maintenancePlan;
		nameItem.setValue(maintenancePlan.getName());
		descriptionItem.setValue(maintenancePlan.getDescription());
		frequencyItem.setValue(maintenancePlan.getFrequency());
		frecuencyPoTItem.setValue(maintenancePlan.getPot().name());
		typeItem.setValue(maintenancePlan.getType().name());
		stateItem.setValue(maintenancePlan.getState().name());
		cancelationOptionItem.setValue(maintenancePlan.getCancelationOption()
				.name());
		if (maintenancePlan.getRole() != null)
			roleSelectItem.setValue(maintenancePlan.getRole().getId());
		if (maintenancePlan.getProvider() != null)
			providerSelectItem.setValue(maintenancePlan.getProvider().getId());

		MaintenancePlanModel.getStadisticInfo(maintenancePlan,
				new GHAAsyncCallback<MaintenancePlanStadisticData>() {
					@Override
					public void onSuccess(MaintenancePlanStadisticData result) {
						Window.alert("I'm back!");
					}
				});

		showPlanStadisticsItems();
	}

	private void showPlanStadisticsItems() {
		planStadistics_TitleItem.show();
		protocolActivitiesItem.show();
		estimatedTimeItem.show();
		estimatedTimePoTItem.show();
		estimatedCostItem.show();
		estimatedCostCurrencyItem.show();
		effectuatedTimesItem.show();
		eiasWithThisPlanItem.show();
		lastEffectuatedDateItem.show();
	}

	private void toggleForm(boolean active) {
		nameItem.setDisabled(!active);
		frequencyItem.setDisabled(!active);
		frecuencyPoTItem.setDisabled(!active);
		descriptionItem.setDisabled(!active);
		typeItem.setDisabled(!active);
		stateItem.setDisabled(!active);
		cancelationOptionItem.setDisabled(!active);
		roleSelectItem.setDisabled(!active);
		providerSelectItem.setDisabled(!active);
	}

	@Override
	public void update(final GHAAsyncCallback<MaintenancePlan> callback) {
		MaintenancePlan maintenancePlan = extract(true);

		if (maintenancePlan == null)
			return;

		MaintenancePlanModel.update(maintenancePlan,
				new GHAAsyncCallback<MaintenancePlan>() {

					@Override
					public void onSuccess(MaintenancePlan result) {
						hasUnCommittedChanges = false;
						notifyMaintenancePlan(result);
						clear();
						if (callback != null)
							callback.onSuccess(result);
					}
				});
	}

	@Override
	public void onResize(ResizeEvent arg0) {
		form.resize(GHAUiHelper.getNormalFormWidth(30), 4);
	}

}
