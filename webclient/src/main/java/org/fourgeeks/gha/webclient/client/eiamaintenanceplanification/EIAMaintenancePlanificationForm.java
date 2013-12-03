package org.fourgeeks.gha.webclient.client.eiamaintenanceplanification;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.enu.MaintenancePlanificationStatus;
import org.fourgeeks.gha.domain.enu.MaintenancePlanificationType;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
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
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextAreaItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATimeItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATitletextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAEiaStateSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAExternalProviderSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAMaintenancePlanSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAPeriodOfTimeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHARoleSelectItem;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASectionForm;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author nelson
 * 
 */
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

	private final GHATitletextItem preventiveMaintenance_TitleItem,
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
	private GHASelectItem maintenanceStatusSelectItem;
	private GHAMaintenancePlanSelectItem maintenacePlanSelectItem;
	private GHATextAreaItem failureDescriptionTextAreaItem;
	private GHATextItem estimatedMaintenanceTimeTextItem;
	private GHAPeriodOfTimeSelectItem estimatedMaintenancePoTSelectedItem;
	private GHADateItem deliverDateItem, acceptationDateItem;
	private GHATextItem durationPlanTextItem;
	private GHAPeriodOfTimeSelectItem durationPlanPoTSelectItem;

	{
		sectionForm = new GHASectionForm();
		listeners = new ArrayList<EiaMaintenancePlanificationSelectionListener>();
		ChangedHandler mPlanChangedHandler = new ChangedHandler() {
			@Override
			public void onChanged(ChangedEvent event) {
				hasUnCommittedChanges = true;

				MaintenancePlan mPlan = maintenacePlanSelectItem
						.getValueAsMaintenancePlan();
				durationPlanTextItem.setValue(mPlan.getFrequency());
				if (mPlan.getPot() != null)
					durationPlanPoTSelectItem.setValue(mPlan.getPot().name());
			}
		};

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
		durationPlanTextItem.setDisabled(true);

		durationPlanPoTSelectItem = new GHAPeriodOfTimeSelectItem();
		durationPlanPoTSelectItem.setDisabled(true);

		roleSelectItem = new GHARoleSelectItem();
		roleSelectItem.addChangedHandler(changedHandler);
		maintenanceStatusSelectItem = new GHASelectItem("Estatus", false,
				changedHandler);
		providerSelectItem = new GHAExternalProviderSelectItem("Proveedor",
				false, changedHandler);

		maintenacePlanSelectItem = new GHAMaintenancePlanSelectItem();
		maintenacePlanSelectItem.addChangedHandler(mPlanChangedHandler);
		maintenacePlanSelectItem.setColSpan(2);

		preventiveMaintenance_TitleItem = new GHATitletextItem(
				"Mantenimiento Preventivo", 4);
		correctiveMaintenance_TitleItem = new GHATitletextItem(
				"Mantenimiento Correctivo", 4);
	}

	/**
	 * 
	 */
	public EIAMaintenancePlanificationForm() {
		super();
		GHAUiHelper.addGHAResizeHandler(this);

		basicInfoForm = buildAndGetBasicInfoForm();
		timesAndDatesForm = buildAndGetTimesAndDatesForm();
		maintenanceTypeForm = buildAndGetMaintenanceTypeForm();

		sectionForm.addSection("Información basica", basicInfoForm);
		sectionForm.addSection("Horas y fechas", timesAndDatesForm);
		sectionForm.addSection("Tipo de Mant.", maintenanceTypeForm);

		addMember(sectionForm);

		sectionForm.openFirst();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.UI.superclasses.GHAForm#activate()
	 */
	@Override
	public void activate() {
		toggleForm(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eiamaintenanceplanification.
	 * EiaMaintenancePlanificationSelectionProducer
	 * #addEiaMaintenancePlanificationSelectionListener
	 * (org.fourgeeks.gha.webclient.client.eiamaintenanceplanification.
	 * EiaMaintenancePlanificationSelectionListener)
	 */
	@Override
	public void addEiaMaintenancePlanificationSelectionListener(
			EiaMaintenancePlanificationSelectionListener listener) {
		listeners.add(listener);
	}

	/**
	 * 
	 * @return
	 */
	private GHADynamicForm buildAndGetBasicInfoForm() {
		final GHADynamicForm form = new GHADynamicForm(
				GHAUiHelper.getNormalFormWidth(30), 3);

		form.setItems(idNumberTextItem, requestNumberTextItem,
				maintenanceStatusSelectItem, technicianNameTextItem,
				providerSelectItem, roleSelectItem, initialEiaStateSelectItem,
				finalEiaStateSelectItem, new GHASpacerItem());

		return form;
	}

	/**
	 * 
	 * @return
	 */
	private GHADynamicForm buildAndGetMaintenanceTypeForm() {
		final GHADynamicForm form = new GHADynamicForm(
				GHAUiHelper.getNormalFormWidth(30), 3);

		form.setItems(preventiveMaintenance_TitleItem,
				maintenacePlanSelectItem, new GHASpacerItem(),
				durationPlanTextItem, durationPlanPoTSelectItem,
				new GHASpacerItem(), correctiveMaintenance_TitleItem,
				estimatedMaintenanceTimeTextItem,
				estimatedMaintenancePoTSelectedItem, new GHASpacerItem(),
				failureDescriptionTextAreaItem, new GHASpacerItem());

		return form;
	}

	/**
	 * 
	 * @return
	 */
	private GHADynamicForm buildAndGetTimesAndDatesForm() {
		final GHADynamicForm form = new GHADynamicForm(
				GHAUiHelper.getNormalFormWidth(30), 3);

		form.setItems(beginningDateItem, beginningTimeItem,
				new GHASpacerItem(), finishDateItem, finishTimeItem,
				new GHASpacerItem(), effectiveTimeTextItem,
				effectivePoTSelectItem, new GHASpacerItem(), deliverDateItem,
				acceptationDateItem, new GHASpacerItem());

		return form;
	}

	@Override
	public void clear() {
		super.clear();

		requestNumberTextItem.clearValue();
		technicianNameTextItem.clearValue();
		beginningDateItem.clearValue();
		finishDateItem.clearValue();
		beginningTimeItem.clearValue();
		finishTimeItem.clearValue();
		effectiveTimeTextItem.clearValue();
		effectivePoTSelectItem.clearValue();
		initialEiaStateSelectItem.clearValue();
		finalEiaStateSelectItem.clearValue();
		maintenanceStatusSelectItem.clearValue();
		deliverDateItem.clearValue();
		acceptationDateItem.clearValue();
		providerSelectItem.clearValue();
		roleSelectItem.clearValue();

		// corrective maintenance
		estimatedMaintenanceTimeTextItem.clearValue();
		estimatedMaintenancePoTSelectedItem.clearValue();
		failureDescriptionTextAreaItem.clearValue();

		// preventive maintenance
		maintenacePlanSelectItem.clearValue();
		durationPlanTextItem.clearValue();
		durationPlanPoTSelectItem.clearValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.UI.superclasses.GHAForm#deactivate()
	 */
	@Override
	public void deactivate() {
		toggleForm(false);
	}

	/**
	 * 
	 * @param planif
	 * @return
	 */
	private EiaCorrectiveMaintenancePlanification extractCorrectiveMaintenance(
			EiaMaintenancePlanification planif) {

		EiaCorrectiveMaintenancePlanification entity = null;
		if (selectedCorrectiveMaintenance == null)
			entity = new EiaCorrectiveMaintenancePlanification();
		else
			entity = selectedCorrectiveMaintenance;

		entity.setDescription(failureDescriptionTextAreaItem.getValueAsString());

		entity.setEstimatedMaintenance(Integer
				.valueOf(estimatedMaintenanceTimeTextItem.getValueAsString()));

		entity.setEstimatedMaintenancePoT(TimePeriodEnum
				.valueOf(estimatedMaintenancePoTSelectedItem.getValueAsString()));

		return entity;
	}

	/**
	 * 
	 * @return
	 */
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

		// TODO faltan atributos por obtener

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

	/**
	 * 
	 * @param planification
	 * @return
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.UI.superclasses.GHAForm#hide()
	 */
	@Override
	public void hide() {
		sectionForm.hide();
		super.hide();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eiamaintenanceplanification.
	 * EiaMaintenancePlanificationSelectionProducer
	 * #notifyEiaMaintenancePlanification
	 * (org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification)
	 */
	@Override
	public void notifyEiaMaintenancePlanification(
			EiaMaintenancePlanification entity) {
		for (EiaMaintenancePlanificationSelectionListener listener : listeners)
			listener.select(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.google.gwt.event.logical.shared.ResizeHandler#onResize(com.google
	 * .gwt.event.logical.shared.ResizeEvent)
	 */
	@Override
	public void onResize(final ResizeEvent arg0) {
		basicInfoForm.resize(GHAUiHelper.getNormalFormWidth(30), 4);
		timesAndDatesForm.resize(GHAUiHelper.getNormalFormWidth(30), 4);
		maintenanceTypeForm.resize(GHAUiHelper.getNormalFormWidth(30), 4);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eiamaintenanceplanification.
	 * EiaMaintenancePlanificationSelectionProducer
	 * #removeEiaMaintenancePlanificationSelectionListener
	 * (org.fourgeeks.gha.webclient.client.eiamaintenanceplanification.
	 * EiaMaintenancePlanificationSelectionListener)
	 */
	@Override
	public void removeEiaMaintenancePlanificationSelectionListener(
			EiaMaintenancePlanificationSelectionListener listener) {
		listeners.remove(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.UI.superclasses.GHAForm#save(org.fourgeeks
	 * .gha.webclient.client.UI.GHAAsyncCallback)
	 */
	@Override
	public void save(
			final GHAAsyncCallback<EiaMaintenancePlanification> callback) {
		// TODO agregarle funcionalidad cuando se vaya a utilizar
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eiamaintenanceplanification.
	 * EiaMaintenancePlanificationSelectionListener
	 * #select(org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification)
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener#select
	 * (org.fourgeeks.gha.domain.gmh.EiaType)
	 */
	@Override
	public void select(EiaType eiaType) {
		maintenacePlanSelectItem.fillByEiaType(eiaType);
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

	/**
	 * 
	 * @param entity
	 */
	private void set(EiaCorrectiveMaintenancePlanification entity) {
		estimatedMaintenanceTimeTextItem.setValue(entity
				.getEstimatedMaintenance());
		estimatedMaintenancePoTSelectedItem.setValue(entity
				.getEstimatedMaintenancePoT().name());
		failureDescriptionTextAreaItem.setValue(entity.getDescription());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.UI.superclasses.GHAForm#set(java.lang
	 * .Object)
	 */
	@Override
	public void set(EiaMaintenancePlanification entity) {
		idNumberTextItem.setValue(entity.getId());
		requestNumberTextItem.setValue(entity.getRequestNumber());
		technicianNameTextItem.setValue(entity.getTechnicianName());
		beginningDateItem.setValue(entity.getBeginningDate());
		beginningTimeItem.setValue(entity.getBeginningDate());
		finishDateItem.setValue(entity.getFinishDate());
		finishTimeItem.setValue(entity.getFinishDate());
		deliverDateItem.setValue(entity.getDeliverDate());
		acceptationDateItem.setValue(entity.getAcceptationDate());
		effectiveTimeTextItem.setValue(entity.getEffectiveTime());

		TimePeriodEnum effectivePoT = entity.getEffectivePoT();
		effectivePoTSelectItem.setValue(effectivePoT == null ? null
				: effectivePoT.name());

		EiaStateEnum initialEiaState = entity.getInitialEiaState();
		if (initialEiaState == null) {
			EiaStateEnum eiaState = entity.getEia().getState();
			initialEiaStateSelectItem.setValue(eiaState == null ? null
					: eiaState.name());
		} else {
			initialEiaStateSelectItem.setValue(initialEiaState.name());
		}

		EiaStateEnum finalEiaState = entity.getFinalEiaState();
		finalEiaStateSelectItem.setValue(finalEiaState == null ? null
				: finalEiaState.name());

		MaintenancePlanificationStatus status = entity.getStatus();
		maintenanceStatusSelectItem.setValue(status == null ? null : status
				.name());

		Role role = entity.getRole();
		roleSelectItem.setValue(role == null ? null : role.getId());

		ExternalProvider provider = entity.getProvider();
		providerSelectItem.setValue(provider == null ? null : provider.getId());
	}

	/**
	 * 
	 * @param entity
	 */
	private void set(EiaPreventiveMaintenancePlanification entity) {
		EiaTypeMaintenancePlan plan = entity.getPlan();

		maintenacePlanSelectItem.setValue(entity.getId());
		durationPlanTextItem.setValue(plan.getMaintenancePlan().getFrequency());
		durationPlanPoTSelectItem.setValue(plan.getMaintenancePlan().getPot());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.smartgwt.client.widgets.Canvas#show()
	 */
	@Override
	public void show() {
		sectionForm.show();
		super.show();
	}

	/**
	 * Active/Deactive the form's items
	 * 
	 * @param active
	 *            if true activate (set enabled) the form's items, if false
	 *            deactivate (set disabled) the form's items
	 */
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
		deliverDateItem.setDisabled(!active);
		acceptationDateItem.setDisabled(!active);
		providerSelectItem.setDisabled(!active);
		roleSelectItem.setDisabled(!active);
	}

	/**
	 * active/deactive the respective form's items for corrective and preventive
	 * maintenance
	 * 
	 * @param type
	 *            the type of maintenance to active its form's items
	 */
	private void toogleForm(MaintenancePlanificationType type) {
		boolean active = (type == MaintenancePlanificationType.CORRECTIVE);

		// corrective maintenance
		estimatedMaintenanceTimeTextItem.setDisabled(!active);
		estimatedMaintenancePoTSelectedItem.setDisabled(!active);
		failureDescriptionTextAreaItem.setDisabled(!active);

		// preventive maintenance
		maintenacePlanSelectItem.setDisabled(active);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.UI.superclasses.GHAForm#update(org
	 * .fourgeeks.gha.webclient.client.UI.GHAAsyncCallback)
	 */
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

	/**
	 * Update the data of the corrective maintenance planification entity in DB
	 * 
	 * @param callback
	 *            object who give the result of the trasaction back
	 * @param maintenance
	 *            the maintenance planification entity with the other data of
	 *            the corrective maintenance planification
	 */
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

	/**
	 * Update the data of the preventive maintenance planification entity in DB
	 * 
	 * @param callback
	 *            object who give the result of the trasaction back
	 * @param maintenance
	 *            the maintenance planification entity with the other data of
	 *            the preventive maintenance planification
	 */
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
