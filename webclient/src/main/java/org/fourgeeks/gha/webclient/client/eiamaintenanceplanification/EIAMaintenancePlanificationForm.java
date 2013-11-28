package org.fourgeeks.gha.webclient.client.eiamaintenanceplanification;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

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
import org.fourgeeks.gha.webclient.client.UI.formItems.GHAEiaStateSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHAExternalProviderSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHAPeriodOfTimeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHARoleSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextAreaItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATimeItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATitleTextItem;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASectionForm;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.EiaTypeMaintenancePlanModel;

import com.google.gwt.event.logical.shared.ResizeEvent;

public class EIAMaintenancePlanificationForm extends
		GHAForm<EiaMaintenancePlanification> implements
		EiaMaintenancePlanificationSelectionListener, EIATypeSelectionListener,
		EiaMaintenancePlanificationSelectionProducer {

	private List<EiaMaintenancePlanificationSelectionListener> listeners;
	private EiaMaintenancePlanification selectedMaintenance;
	private EiaPreventiveMaintenancePlanification selectedPreventiveMaintenance;
	private EiaCorrectiveMaintenancePlanification selectedCorrectiveMaintenance;

	private final GHASectionForm sectionForm;
	private final GHADynamicForm basicInfoForm;
	private final GHADynamicForm timesAndDatesForm;
	private final GHADynamicForm maintenanceTypeForm;

	private final GHATitleTextItem preventiveMaintenance_TitleItem,
			correctiveMaintenance_TitleItem;
	private GHATextItem idNumberTextItem;
	private GHATextItem requestNumberTextItem;
	private GHATextItem technicianNameTextItem;
	private GHAExternalProviderSelectItem providerSelectItem;
	private GHARoleSelectItem roleSelectItem;
	private GHADateItem beginningDateItem, finishDateItem;
	private GHATimeItem beginningTimeItem, finishTimeItem;
	private GHATextItem effectiveTimeTextItem;
	private GHAPeriodOfTimeSelectItem effectivePoTSelectItem;
	private GHAEiaStateSelectItem initialEiaStateSelectItem,
			finalEiaStateSelectItem;
	private GHASelectItem maintenanceStatusSelectItem,
			maintenacePlanSelectItem;
	private GHATextAreaItem failureDescriptionTextAreaItem;
	private GHATextItem estimatedMaintenanceTimeTextItem;
	private GHAPeriodOfTimeSelectItem estimatedMaintenancePoTSelectedItem;
	private GHADateItem deliverDateItem, acceptationDateItem;
	private GHATextItem durationPlanTextItem;
	private GHAPeriodOfTimeSelectItem durationPlanPoTSelectItem;

	{
		sectionForm = new GHASectionForm();
		listeners = new ArrayList<EiaMaintenancePlanificationSelectionListener>();

		idNumberTextItem = new GHATextItem("Numero de informe", false);
		requestNumberTextItem = new GHATextItem("Numero de solicitud", false,
				changedHandler);
		technicianNameTextItem = new GHATextItem("Técnico del mant.", false,
				changedHandler);
		beginningDateItem = new GHADateItem("Fecha de inicio del mant.",
				changedHandler);
		beginningTimeItem = new GHATimeItem("Hora de inicio del mant.",
				changedHandler);
		finishDateItem = new GHADateItem("Fecha de finalización del mant.",
				changedHandler);
		finishTimeItem = new GHATimeItem("Hora de finalización del mant.",
				changedHandler);
		effectiveTimeTextItem = new GHATextItem("Tiempo efectivo empleado",
				false, changedHandler);
		effectivePoTSelectItem = new GHAPeriodOfTimeSelectItem(false,
				changedHandler);
		initialEiaStateSelectItem = new GHAEiaStateSelectItem(
				"Estado inicial del equipo", false, changedHandler);
		finalEiaStateSelectItem = new GHAEiaStateSelectItem(
				"Estado final del equipo", false, changedHandler);
		failureDescriptionTextAreaItem = new GHATextAreaItem(
				"Descripción de daño, Motivo de falla", changedHandler);
		failureDescriptionTextAreaItem.setColSpan(2);
		estimatedMaintenanceTimeTextItem = new GHATextItem(
				"Tiempo estimado sin el equipo", false, changedHandler);
		estimatedMaintenancePoTSelectedItem = new GHAPeriodOfTimeSelectItem(
				false, changedHandler);
		deliverDateItem = new GHADateItem("Fecha de entrega", changedHandler);
		acceptationDateItem = new GHADateItem("Fecha de aceptación",
				changedHandler);
		durationPlanTextItem = new GHATextItem("Tiempo de duración", false,
				changedHandler);
		durationPlanPoTSelectItem = new GHAPeriodOfTimeSelectItem();
		roleSelectItem = new GHARoleSelectItem();
		roleSelectItem.addChangedHandler(changedHandler);
		maintenanceStatusSelectItem = new GHASelectItem("Estatus", false,
				changedHandler);
		providerSelectItem = new GHAExternalProviderSelectItem("Proveedor",
				false, changedHandler);
		maintenacePlanSelectItem = new GHASelectItem("Plan de mantenimiento",
				true, changedHandler);
		maintenacePlanSelectItem.setColSpan(2);

		preventiveMaintenance_TitleItem = new GHATitleTextItem(
				"Mantenimiento Preventivo", 4);
		correctiveMaintenance_TitleItem = new GHATitleTextItem(
				"Mantenimiento Correctivo", 4);
	}

	public EIAMaintenancePlanificationForm() {
		super();

		basicInfoForm = buildAndGetBasicInfoForm();
		timesAndDatesForm = buildAndGetTimesAndDatesForm();
		maintenanceTypeForm = buildAndGetMaintenanceTypeForm();

		sectionForm.addSection("Información basica", basicInfoForm);
		sectionForm.addSection("Horas y fechas", timesAndDatesForm);
		sectionForm.addSection("Tipo de Mant.", maintenanceTypeForm);

		addMember(sectionForm);

		fill();
		sectionForm.openFirst();
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

	private GHADynamicForm buildAndGetBasicInfoForm() {
		final GHADynamicForm form = new GHADynamicForm(
				GHAUiHelper.getNormalFormWidth(30), 4);

		form.setItems(idNumberTextItem, requestNumberTextItem,
				maintenanceStatusSelectItem, new GHASpacerItem(),
				technicianNameTextItem, providerSelectItem, roleSelectItem,
				new GHASpacerItem(), initialEiaStateSelectItem,
				finalEiaStateSelectItem, new GHASpacerItem(2));

		return form;
	}

	private GHADynamicForm buildAndGetMaintenanceTypeForm() {
		final GHADynamicForm form = new GHADynamicForm(
				GHAUiHelper.getNormalFormWidth(30), 4);

		form.setItems(preventiveMaintenance_TitleItem,
				maintenacePlanSelectItem, new GHASpacerItem(2),
				durationPlanTextItem, durationPlanPoTSelectItem,
				new GHASpacerItem(2), correctiveMaintenance_TitleItem,
				estimatedMaintenanceTimeTextItem,
				estimatedMaintenancePoTSelectedItem, new GHASpacerItem(2),
				failureDescriptionTextAreaItem, new GHASpacerItem(2));

		return form;
	}

	private GHADynamicForm buildAndGetTimesAndDatesForm() {
		final GHADynamicForm form = new GHADynamicForm(
				GHAUiHelper.getNormalFormWidth(30), 4);

		form.setItems(beginningDateItem, beginningTimeItem,
				new GHASpacerItem(2), finishDateItem, finishTimeItem,
				effectiveTimeTextItem, effectivePoTSelectItem,
				new GHASpacerItem(2), deliverDateItem, acceptationDateItem,
				new GHASpacerItem(2));

		return form;
	}

	@Override
	public void deactivate() {
		toggleForm(false);
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

	private EiaMaintenancePlanification extractMaintenance() {
		EiaMaintenancePlanification planification = null;

		planification = selectedMaintenance;

		planification.setStatus(MaintenancePlanificationStatus
				.valueOf(maintenanceStatusSelectItem.getValueAsString()));

		if (providerSelectItem.getValue() != null) {
			final ExternalProvider provider = new ExternalProvider(
					Long.valueOf(providerSelectItem.getValueAsString()));
			planification.setProvider(provider);
		}

		if (roleSelectItem.getValue() != null) {
			final Role role = new Role(Long.valueOf(roleSelectItem
					.getValueAsString()));
			planification.setRole(role);
		}

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
		// TODO cargar otros items si es necesario
	}

	@Override
	public void hide() {
		sectionForm.hide();
		super.hide();
	}

	@Override
	public void notifyEiaMaintenancePlanification(
			EiaMaintenancePlanification entity) {
		for (EiaMaintenancePlanificationSelectionListener listener : listeners)
			listener.select(entity);
	}

	@Override
	public void onResize(final ResizeEvent arg0) {
		basicInfoForm.resize(GHAUiHelper.getNormalFormWidth(30), 4);
		timesAndDatesForm.resize(GHAUiHelper.getNormalFormWidth(30), 4);
		maintenanceTypeForm.resize(GHAUiHelper.getNormalFormWidth(30), 4);
	}

	@Override
	public void removeEiaMaintenancePlanificationSelectionListener(
			EiaMaintenancePlanificationSelectionListener listener) {
		listeners.remove(listener);
	}

	@Override
	public void save(
			final GHAAsyncCallback<EiaMaintenancePlanification> callback) {
		// TODO agregarle funcionalidad cuando se vaya a utilizar
	}

	@Override
	public void select(EiaMaintenancePlanification entity) {
		selectedMaintenance = entity;

		if (entity.getType() == MaintenancePlanificationType.CORRECTIVE) {
			selectCorrectiveMaintenance(selectedMaintenance);
			maintenanceStatusSelectItem
					.setValueMap(MaintenancePlanificationStatus
							.toValueMap(MaintenancePlanificationStatus.EIA_DAMAGE));
		} else {
			selectPreventiveMaintenance(selectedMaintenance);
			maintenanceStatusSelectItem
					.setValueMap(MaintenancePlanificationStatus.toValueMap(
							MaintenancePlanificationStatus.ACCOMPLISHED,
							MaintenancePlanificationStatus.CANCELED,
							MaintenancePlanificationStatus.DEFERRED));
		}

		toogleForm(selectedMaintenance.getType());
		set(selectedMaintenance);
		sectionForm.openFirst();
	}

	@Override
	public void select(EiaType eiaType) {
		EiaTypeMaintenancePlanModel.findByEiaType(eiaType,
				new GHAAsyncCallback<List<EiaTypeMaintenancePlan>>() {
					@Override
					public void onSuccess(
							final List<EiaTypeMaintenancePlan> result) {
						final LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
						for (EiaTypeMaintenancePlan plan : result) {
							final MaintenancePlan mPlan = plan
									.getMaintenancePlan();
							map.put(String.valueOf(plan.getId()),
									mPlan.getName());
						}
						maintenacePlanSelectItem.setValueMap(map);
					}
				});
	}

	/**
	 * Find the Corrective Maintenance Planification for this Maintenance
	 * Planification and fill the corresponded fields
	 * 
	 * @param entity
	 */
	private void selectCorrectiveMaintenance(EiaMaintenancePlanification entity) {
		EiaMaintenancePlanificationModel.getCorrectiveMaintenance(entity,
				new GHAAsyncCallback<EiaCorrectiveMaintenancePlanification>() {
					@Override
					public void onSuccess(
							final EiaCorrectiveMaintenancePlanification result) {
						selectedCorrectiveMaintenance = result;
						set(selectedCorrectiveMaintenance);
					}
				});
	}

	/**
	 * Find the Preventive Maintenance Planification for this Maintenance
	 * Planification and fill the corresponded fields
	 * 
	 * @param entity
	 */
	private void selectPreventiveMaintenance(EiaMaintenancePlanification entity) {
		EiaMaintenancePlanificationModel.getPreventiveMaintenance(entity,
				new GHAAsyncCallback<EiaPreventiveMaintenancePlanification>() {
					@Override
					public void onSuccess(
							final EiaPreventiveMaintenancePlanification result) {
						selectedPreventiveMaintenance = result;
						set(selectedPreventiveMaintenance);
					}
				});
	}

	@Override
	public void set(EiaMaintenancePlanification entity) {
		maintenanceStatusSelectItem.setValue(entity.getStatus() == null ? null
				: entity.getStatus().name());

		Role role = entity.getRole();
		roleSelectItem.setValue(role == null ? null : role.getId());

		ExternalProvider provider = entity.getProvider();
		providerSelectItem.setValue(provider == null ? null : provider.getId());
	}

	private void set(EiaCorrectiveMaintenancePlanification entity) {
		// TODO faltan campos por llenar
	}

	private void set(EiaPreventiveMaintenancePlanification entity) {
		maintenacePlanSelectItem.setValue(entity.getId());
		// TODO faltan campos por llenar
	}

	@Override
	public void show() {
		sectionForm.show();
		super.show();
	}

	private void toggleForm(boolean active) {
		requestNumberTextItem.setDisabled(!active);
		technicianNameTextItem.setDisabled(!active);
		beginningDateItem.setDisabled(!active);
		finishDateItem.setDisabled(!active);
		beginningTimeItem.setDisabled(!active);
		finishTimeItem.setDisabled(!active);
		effectiveTimeTextItem.setDisabled(!active);
		effectivePoTSelectItem.setDisabled(!active);
		initialEiaStateSelectItem.setDisabled(!active);
		finalEiaStateSelectItem.setDisabled(!active);
		maintenanceStatusSelectItem.setDisabled(!active);
		failureDescriptionTextAreaItem.setDisabled(!active);
		estimatedMaintenanceTimeTextItem.setDisabled(!active);
		estimatedMaintenancePoTSelectedItem.setDisabled(!active);
		deliverDateItem.setDisabled(!active);
		acceptationDateItem.setDisabled(!active);
		durationPlanTextItem.setDisabled(!active);
		durationPlanPoTSelectItem.setDisabled(!active);
		maintenacePlanSelectItem.setDisabled(!active);
		providerSelectItem.setDisabled(!active);
		roleSelectItem.setDisabled(!active);
	}

	private void toogleForm(MaintenancePlanificationType type) {
		boolean active = type == MaintenancePlanificationType.CORRECTIVE;

		estimatedMaintenanceTimeTextItem.setDisabled(!active);
		estimatedMaintenancePoTSelectedItem.setDisabled(!active);
		failureDescriptionTextAreaItem.setDisabled(!active);
		maintenacePlanSelectItem.setDisabled(active);
		durationPlanTextItem.setDisabled(active);
		durationPlanPoTSelectItem.setDisabled(active);
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
							final EiaCorrectiveMaintenancePlanification result) {
						hasUnCommittedChanges = false;
						notifyEiaMaintenancePlanification(maintenance);
						clear();

						if (callback != null) {
							callback.onSuccess(maintenance);
						}
					}
				});
	}

	private void updatePreventiveMaintenance(
			final GHAAsyncCallback<EiaMaintenancePlanification> callback,
			final EiaMaintenancePlanification maintenance) {

		final EiaPreventiveMaintenancePlanification entity = extractPreventiveMaintenance(maintenance);
		EiaMaintenancePlanificationModel.updatePreventiveMaintenance(entity,
				new GHAAsyncCallback<EiaPreventiveMaintenancePlanification>() {
					@Override
					public void onSuccess(
							final EiaPreventiveMaintenancePlanification result) {
						hasUnCommittedChanges = false;
						notifyEiaMaintenancePlanification(maintenance);
						clear();

						if (callback != null) {
							callback.onSuccess(maintenance);
						}
					}
				});
	}
}
