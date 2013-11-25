package org.fourgeeks.gha.webclient.client.eiamaintenanceplanification;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.fourgeeks.gha.domain.enu.MaintenancePlanificationState;
import org.fourgeeks.gha.domain.enu.MaintenancePlanificationStatus;
import org.fourgeeks.gha.domain.enu.MaintenancePlanificationType;
import org.fourgeeks.gha.domain.ess.Role;
import org.fourgeeks.gha.domain.glm.ExternalProvider;
import org.fourgeeks.gha.domain.gmh.EiaCorrectiveMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaPreventiveMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHADateItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHAExternalProviderSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHARoleSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAForm;
import org.fourgeeks.gha.webclient.client.eia.EIAUtil;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.EiaTypeMaintenancePlanModel;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;

public class EIAMaintenancePlanificationForm extends
		GHAForm<EiaMaintenancePlanification> implements
		EiaMaintenancePlanificationSelectionListener, EIATypeSelectionListener,
		EiaMaintenancePlanificationSelectionProducer {

	private List<EiaMaintenancePlanificationSelectionListener> listeners;
	private EiaMaintenancePlanification selectedMaintenance;
	private EiaPreventiveMaintenancePlanification selectedPreventiveMaintenance;
	private EiaCorrectiveMaintenancePlanification selectedCorrectiveMaintenance;

	private GHADynamicForm form;
	private GHAExternalProviderSelectItem providerSelectItem;
	private GHARoleSelectItem roleSelectItem;
	private GHADateItem scheduledDateDateItem, deliverDateDateItem,
			acceptationDateDateItem;
	private GHASelectItem statusSelectItem, stateSelectItem,
			maintenacePlanSelectItem;

	{
		listeners = new ArrayList<EiaMaintenancePlanificationSelectionListener>();

		roleSelectItem = new GHARoleSelectItem();
		roleSelectItem.addChangedHandler(changedHandler);
		statusSelectItem = new GHASelectItem("Estatus", false, changedHandler);
		stateSelectItem = new GHASelectItem("Estado", false, changedHandler);
		providerSelectItem = new GHAExternalProviderSelectItem("Proveedor",
				false, changedHandler);
		maintenacePlanSelectItem = new GHASelectItem("Plan de mantenimiento",
				true, changedHandler);
		maintenacePlanSelectItem.setColSpan(2);
		scheduledDateDateItem = new GHADateItem("Fecha de inicio",
				changedHandler);
		deliverDateDateItem = new GHADateItem("Fecha de entrega",
				changedHandler);
		acceptationDateDateItem = new GHADateItem("Fecha de aceptacion",
				changedHandler);

		form = new GHADynamicForm(GHAUiHelper.getNormalFormWidth(30), 4);
	}

	public EIAMaintenancePlanificationForm() {
		final HLayout mainPanel = new HLayout();
		form.setItems(scheduledDateDateItem, deliverDateDateItem,
				acceptationDateDateItem, new GHASpacerItem(),
				maintenacePlanSelectItem, new GHASpacerItem(2),
				providerSelectItem, roleSelectItem, new GHASpacerItem(2),
				statusSelectItem, stateSelectItem, new GHASpacerItem(2));

		fill();
		mainPanel.addMembers(form, new LayoutSpacer());
		addMember(mainPanel);
	}

	@Override
	public void activate() {
		toggleForm(true);
	}

	@Override
	public void addEiaMaintenancePlanificationSelectionListener(
			EiaMaintenancePlanificationSelectionListener listener) {
		listeners.add(listener);
	}

	@Override
	public void deactivate() {
		toggleForm(true);
	}

	private EiaMaintenancePlanification extractMaintenance() {
		EiaMaintenancePlanification planification = null;
		if (selectedMaintenance == null)
			planification = new EiaMaintenancePlanification();
		else
			planification = selectedMaintenance;

		planification.setStatus(MaintenancePlanificationStatus
				.valueOf(statusSelectItem.getValueAsString()));

		if (planification.getStatus() == MaintenancePlanificationStatus.EIA_DAMAGE)
			planification.setType(MaintenancePlanificationType.CORRECTIVE);
		else
			planification.setType(MaintenancePlanificationType.PREVENTIVE);

		if (providerSelectItem.getValue() != null) {
			ExternalProvider provider = new ExternalProvider(
					Long.valueOf(providerSelectItem.getValueAsString()));
			planification.setProvider(provider);
		}

		if (roleSelectItem.getValue() != null) {
			Role role = new Role(
					Long.valueOf(roleSelectItem.getValueAsString()));
			planification.setRole(role);
		}

		planification.setScheduledDate(EIAUtil
				.getLogicalDate(scheduledDateDateItem.getValueAsDate()));

		planification.setDeliverDate(EIAUtil.getLogicalDate(deliverDateDateItem
				.getValueAsDate()));

		planification.setAcceptationDate(EIAUtil
				.getLogicalDate(acceptationDateDateItem.getValueAsDate()));

		// VALIDANDO LOS DATOS
		// Set<ConstraintViolation<EiaMaintenancePlanification>> violations =
		// null;
		// violations = validator.validate(planification);
		// if (form.validate() && violations.isEmpty())
		// return planification;
		// else {
		// List<String> violationsList = new ArrayList<String>();
		// for (ConstraintViolation<EiaMaintenancePlanification> violation :
		// violations)
		// violationsList.add(violation.getMessage());
		// GHANotification.alert(violationsList);
		// }
		return null;
	}

	private EiaCorrectiveMaintenancePlanification extractCorrectiveMaintenance(
			EiaMaintenancePlanification planif) {

		EiaCorrectiveMaintenancePlanification entity = null;
		if (selectedCorrectiveMaintenance == null)
			entity = new EiaCorrectiveMaintenancePlanification();
		else
			entity = selectedCorrectiveMaintenance;

		// TODO faltan atributos por obtener

		return entity;
	}

	private EiaPreventiveMaintenancePlanification extractPreventiveMaintenance(
			EiaMaintenancePlanification planification) {

		EiaPreventiveMaintenancePlanification entity = null;
		if (selectedPreventiveMaintenance == null)
			entity = new EiaPreventiveMaintenancePlanification();
		else
			entity = selectedPreventiveMaintenance;

		if (maintenacePlanSelectItem.getValue() != null) {
			EiaTypeMaintenancePlan plan = new EiaTypeMaintenancePlan();
			plan.setId(Long.valueOf(maintenacePlanSelectItem.getValueAsString()));
			entity.setPlan(plan);
		}

		// TODO faltan atributos por obtener

		return entity;
	}

	private void fill() {
		stateSelectItem.setValueMap(MaintenancePlanificationState.toValueMap());
		statusSelectItem.setValueMap(MaintenancePlanificationStatus.toValueMap(
				MaintenancePlanificationStatus.ACCOMPLISHED,
				MaintenancePlanificationStatus.CANCELED,
				MaintenancePlanificationStatus.DEFERRED,
				MaintenancePlanificationStatus.EIA_DAMAGE));

		// TODO cargar otros items si es necesario
	}

	@Override
	public void notifyEiaMaintenancePlanification(
			EiaMaintenancePlanification entity) {
		for (EiaMaintenancePlanificationSelectionListener listener : listeners)
			listener.select(entity);
	}

	@Override
	public void onResize(ResizeEvent arg0) {
		form.resize(GHAUiHelper.getNormalFormWidth(30), 4);
	}

	@Override
	public void removeEiaMaintenancePlanificationSelectionListener(
			EiaMaintenancePlanificationSelectionListener listener) {
		listeners.remove(listener);
	}

	@Override
	public void save(
			final GHAAsyncCallback<EiaMaintenancePlanification> callback) {
	}

	@Override
	public void select(EiaMaintenancePlanification entity) {
		selectedMaintenance = entity;
		set(selectedMaintenance);

		if (selectedMaintenance.getType() == MaintenancePlanificationType.CORRECTIVE)
			EiaMaintenancePlanificationModel
					.getCorrectiveMaintenancePlanification(
							selectedMaintenance,
							new GHAAsyncCallback<EiaCorrectiveMaintenancePlanification>() {
								@Override
								public void onSuccess(
										EiaCorrectiveMaintenancePlanification result) {
									selectedCorrectiveMaintenance = result;
								}
							});
		else
			EiaMaintenancePlanificationModel
					.getPreventiveMaintenancePlanification(
							selectedMaintenance,
							new GHAAsyncCallback<EiaPreventiveMaintenancePlanification>() {
								@Override
								public void onSuccess(
										EiaPreventiveMaintenancePlanification result) {
									selectedPreventiveMaintenance = result;
								}
							});
	}

	@Override
	public void select(EiaType eiaType) {
		EiaTypeMaintenancePlanModel.findByEiaType(eiaType,
				new GHAAsyncCallback<List<EiaTypeMaintenancePlan>>() {
					@Override
					public void onSuccess(List<EiaTypeMaintenancePlan> result) {
						LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
						for (EiaTypeMaintenancePlan plan : result) {
							MaintenancePlan mPlan = plan.getMaintenancePlan();
							map.put(plan.getId() + "", mPlan.getName());
						}
						maintenacePlanSelectItem.setValueMap(map);
					}
				});
	}

	@Override
	public void set(EiaMaintenancePlanification entity) {
		scheduledDateDateItem.setValue(entity.getScheduledDate());
		deliverDateDateItem.setValue(entity.getDeliverDate());
		acceptationDateDateItem.setValue(entity.getAcceptationDate());
		providerSelectItem.setValue(entity.getProvider());
		roleSelectItem.setValue(entity.getRole());
		statusSelectItem.setValue(entity.getStatus().name());
		stateSelectItem.setValue(entity.getState().name());
	}

	private void toggleForm(boolean active) {
		scheduledDateDateItem.setDisabled(!active);
		deliverDateDateItem.setDisabled(!active);
		acceptationDateDateItem.setDisabled(!active);
		maintenacePlanSelectItem.setDisabled(!active);
		providerSelectItem.setDisabled(!active);
		roleSelectItem.setDisabled(!active);
		statusSelectItem.setDisabled(!active);
		stateSelectItem.setDisabled(!active);
	}

	@Override
	public void update(
			final GHAAsyncCallback<EiaMaintenancePlanification> callback) {

		EiaMaintenancePlanification entity = extractMaintenance();
		if (entity == null)
			return;

		if (entity.getType() == MaintenancePlanificationType.PREVENTIVE)
			updatePreventiveMaintenance(callback, entity);
		else
			updateCorrectiveMaintenance(callback, entity);
	}

	private void updateCorrectiveMaintenance(
			final GHAAsyncCallback<EiaMaintenancePlanification> callback,
			final EiaMaintenancePlanification maintenance) {

		EiaCorrectiveMaintenancePlanification entity = extractCorrectiveMaintenance(maintenance);
		EiaMaintenancePlanificationModel.updateCorrectiveMaintenance(entity,
				new GHAAsyncCallback<EiaCorrectiveMaintenancePlanification>() {
					@Override
					public void onSuccess(
							EiaCorrectiveMaintenancePlanification result) {
						hasUnCommittedChanges = false;
						notifyEiaMaintenancePlanification(maintenance);
						clear();

						if (callback != null)
							callback.onSuccess(maintenance);
					}
				});
	}

	private void updatePreventiveMaintenance(
			final GHAAsyncCallback<EiaMaintenancePlanification> callback,
			final EiaMaintenancePlanification maintenance) {

		EiaPreventiveMaintenancePlanification entity = extractPreventiveMaintenance(maintenance);
		EiaMaintenancePlanificationModel.updatePreventiveMaintenance(entity,
				new GHAAsyncCallback<EiaPreventiveMaintenancePlanification>() {
					@Override
					public void onSuccess(
							EiaPreventiveMaintenancePlanification result) {
						hasUnCommittedChanges = false;
						notifyEiaMaintenancePlanification(maintenance);
						clear();

						if (callback != null)
							callback.onSuccess(maintenance);
					}
				});
	}
}
