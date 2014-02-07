package org.fourgeeks.gha.webclient.client.eiamaintenance;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.enu.EiaMaintenanceState;
import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.enu.MaintenancePlanificationType;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.glm.Bsp;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaCorrectiveMaintenance;
import org.fourgeeks.gha.domain.gmh.EiaDamageReport;
import org.fourgeeks.gha.domain.gmh.EiaMaintenance;
import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaPreventiveMaintenance;
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
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm.FormType;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASectionForm;
import org.fourgeeks.gha.webclient.client.eia.EIAUtil;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.damageandplanification.EIADamageAndPlanificationUtil;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author nelson
 * 
 */
public class EIAMaintenanceForm extends GHAForm<EiaMaintenance> implements
		EiaMaintenanceSelectionListener, EIATypeSelectionListener,
		EiaMaintenanceSelectionProducer {

	private List<EiaMaintenanceSelectionListener> listeners;
	private EiaMaintenance selectedMaintenance;
	private boolean formIsActive;

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
	private EiaType selectedEiaType;

	{
		formIsActive = true;
		sectionForm = new GHASectionForm();
		listeners = new ArrayList<EiaMaintenanceSelectionListener>();
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
		providerSelectItem = new GHAExternalProviderSelectItem(false,
				changedHandler);

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
	public EIAMaintenanceForm() {
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
	public void addEiaMaintenanceSelectionListener(
			EiaMaintenanceSelectionListener listener) {
		listeners.add(listener);
	}

	/**
	 * 
	 * @return
	 */
	private GHADynamicForm buildAndGetBasicInfoForm() {
		final GHADynamicForm form = new GHADynamicForm(3,
				FormType.SECTIONFORM_FORM);

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
		final GHADynamicForm form = new GHADynamicForm(3,
				FormType.SECTIONFORM_FORM);

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
		final GHADynamicForm form = new GHADynamicForm(3,
				FormType.SECTIONFORM_FORM);

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
	private EiaCorrectiveMaintenance extractCorrectiveMaintenance(
			EiaMaintenancePlanification planif) {

		EiaCorrectiveMaintenance entity = (EiaCorrectiveMaintenance) selectedMaintenance;

		entity.setDescription(failureDescriptionTextAreaItem.getValueAsString());

		String time = estimatedMaintenanceTimeTextItem.getValueAsString();
		if (time != null)
			entity.setEstimatedMaintenance(Integer.valueOf(time));

		String pot = estimatedMaintenancePoTSelectedItem.getValueAsString();
		entity.setEstimatedMaintenancePoT(pot != null ? null : TimePeriodEnum
				.valueOf(pot));

		return entity;
	}

	/**
	 * 
	 * @return
	 */
	private EiaMaintenance extractMaintenance() {

		EiaMaintenance entity = selectedMaintenance;

		String mStatus = maintenanceStatusSelectItem.getValueAsString();
		entity.setState(mStatus == null ? null : EiaMaintenanceState
				.valueOf(mStatus));

		if (providerSelectItem.getValue() != null) {
			final Bsp provider = new Bsp();
			provider.setId(Long.valueOf(providerSelectItem.getValueAsString()));
			entity.setProvider(provider);
		}

		if (roleSelectItem.getValue() != null) {
			final Bpu technician = new Bpu();
			technician.setId(Long.valueOf(roleSelectItem.getValueAsString()));
			entity.setTechnician(technician);
		}

		String requestNumber = requestNumberTextItem.getValueAsString();
		entity.setRequestNumber(requestNumber);

		String initialState = initialEiaStateSelectItem.getValueAsString();
		entity.setInitialEiaState(initialState == null ? null : EiaStateEnum
				.valueOf(initialState));

		String finalState = finalEiaStateSelectItem.getValueAsString();
		entity.setFinalEiaState(finalState == null ? null : EiaStateEnum
				.valueOf(finalState));

		entity.setBeginningTimestamp(EIADamageAndPlanificationUtil
				.getTimestamp(beginningDateItem.getValueAsLogicalDate(),
						beginningTimeItem.getValueAsLogicalTime()));

		entity.setFinishTimestamp(EIADamageAndPlanificationUtil.getTimestamp(
				finishDateItem.getValueAsLogicalDate(),
				finishTimeItem.getValueAsLogicalTime()));

		String effectiveTime = effectiveTimeTextItem.getValueAsString();
		if (effectiveTime != null)
			entity.setEffectiveTime(Integer.valueOf(effectiveTime));

		String pot = effectivePoTSelectItem.getValueAsString();
		entity.setEffectivePoT(pot == null ? null : TimePeriodEnum.valueOf(pot));

		entity.setDeliverDate(EIAUtil.getLogicalDate(deliverDateItem
				.getValueAsDate()));

		entity.setAcceptationDate(EIAUtil.getLogicalDate(acceptationDateItem
				.getValueAsDate()));

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
	public void notifyEiaMaintenance(EiaMaintenance entity) {
		for (EiaMaintenanceSelectionListener listener : listeners)
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
		basicInfoForm.resize();
		timesAndDatesForm.resize();
		maintenanceTypeForm.resize();
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
	public void removeEiaMaintenanceSelectionListener(
			EiaMaintenanceSelectionListener listener) {
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
	public void save(final GHAAsyncCallback<EiaMaintenance> callback) {
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
	public void select(EiaMaintenance entity) {
		selectedMaintenance = entity;

		if (formIsActive)
			toogleTypeSection(selectedMaintenance.getType());

		if (selectedEiaType != null)
			maintenacePlanSelectItem.fillByEiaType(selectedEiaType);

		if (entity.getType() == MaintenancePlanificationType.CORRECTIVE) {
			maintenanceStatusSelectItem.setValueMap(EiaMaintenanceState
					.toValueMap(EiaMaintenanceState.EIA_DAMAGE));
		} else {
			maintenanceStatusSelectItem.setValueMap(EiaMaintenanceState
					.toValueMap(EiaMaintenanceState.ACCOMPLISHED,
							EiaMaintenanceState.CANCELED,
							EiaMaintenanceState.DEFERRED));
		}

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
		this.selectedEiaType = eiaType;
		maintenacePlanSelectItem.fillByEiaType(selectedEiaType);
	}

	/**
	 * 
	 * @param entity
	 */
	private void set(EiaCorrectiveMaintenance entity) {
		estimatedMaintenanceTimeTextItem.setValue(entity
				.getEstimatedMaintenance());
		estimatedMaintenancePoTSelectedItem.setValue(entity
				.getEstimatedMaintenancePoT().name());
		failureDescriptionTextAreaItem.setValue(entity.getDescription());

		EiaDamageReport damageReport = entity.getDamageReport();
		Eia eia = damageReport.getEia();

		setInitialEiaState(entity, eia);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.UI.superclasses.GHAForm#set(java.lang
	 * .Object)
	 */
	@Override
	public void set(EiaMaintenance entity) {
		idNumberTextItem.setValue(entity.getId());
		requestNumberTextItem.setValue(entity.getRequestNumber());
		beginningDateItem.setValue(entity.getBeginningTimestamp());
		beginningTimeItem.setValue(entity.getBeginningTimestamp());
		finishDateItem.setValue(entity.getFinishTimestamp());
		finishTimeItem.setValue(entity.getFinishTimestamp());
		deliverDateItem.setValue(entity.getDeliverDate());
		acceptationDateItem.setValue(entity.getAcceptationDate());
		effectiveTimeTextItem.setValue(entity.getEffectiveTime());

		TimePeriodEnum effectivePoT = entity.getEffectivePoT();
		effectivePoTSelectItem.setValue(effectivePoT == null ? null
				: effectivePoT.name());

		EiaStateEnum finalEiaState = entity.getFinalEiaState();
		finalEiaStateSelectItem.setValue(finalEiaState == null ? null
				: finalEiaState.name());

		EiaMaintenanceState status = entity.getState();
		maintenanceStatusSelectItem.setValue(status == null ? null : status
				.name());

		Bpu jobResponsable = entity.getTechnician();
		roleSelectItem.setValue(jobResponsable == null ? null : jobResponsable
				.getId());

		Bsp maintenanceProvider = entity.getProvider();
		providerSelectItem.setValue(maintenanceProvider == null ? null
				: maintenanceProvider.getId());
	}

	/**
	 * 
	 * @param entity
	 */
	private void set(EiaPreventiveMaintenance entity) {
		EiaMaintenancePlanification planification = entity.getPlanification();
		EiaTypeMaintenancePlan plan = planification.getPlan();

		maintenacePlanSelectItem.setValue(plan.getId());
		durationPlanTextItem.setValue(plan.getMaintenancePlan().getFrequency());
		durationPlanPoTSelectItem.setValue(plan.getMaintenancePlan().getPot());

		setInitialEiaState(entity, planification.getEia());
	}

	private void setInitialEiaState(EiaMaintenance entity, Eia eia) {
		EiaStateEnum initialEiaState = entity.getInitialEiaState();
		if (initialEiaState == null) {
			EiaStateEnum eiaState = eia.getState();
			initialEiaStateSelectItem.setValue(eiaState == null ? null
					: eiaState.name());
		} else {
			initialEiaStateSelectItem.setValue(initialEiaState.name());
		}
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
		formIsActive = active;

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

		// corrective maintenance
		estimatedMaintenanceTimeTextItem.setDisabled(!active);
		estimatedMaintenancePoTSelectedItem.setDisabled(!active);
		failureDescriptionTextAreaItem.setDisabled(!active);
		// preventive maintenance
		maintenacePlanSelectItem.setDisabled(!active);
	}

	/**
	 * active/deactive the respective form's items for corrective and preventive
	 * maintenance in the maintenance type section of the form
	 * 
	 * @param type
	 *            the type of maintenance to active its form's items
	 */
	private void toogleTypeSection(MaintenancePlanificationType type) {
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
	public void update(final GHAAsyncCallback<EiaMaintenance> callback) {

		EiaMaintenance maintenance = extractMaintenance();
		if (maintenance == null)
			return;

		if (maintenance instanceof EiaPreventiveMaintenance) {
			final EiaPreventiveMaintenance entity = (EiaPreventiveMaintenance) maintenance;
			update(callback, entity);
		} else {
			final EiaCorrectiveMaintenance entity = (EiaCorrectiveMaintenance) maintenance;
			update(callback, entity);
		}
	}

	private void update(final GHAAsyncCallback<EiaMaintenance> callback,
			final EiaCorrectiveMaintenance entity) {

		EiaMaintenanceModel.updateCorrectiveMaintenance(entity,
				new GHAAsyncCallback<EiaCorrectiveMaintenance>() {
					@Override
					public void onSuccess(EiaCorrectiveMaintenance result) {
						hasUnCommittedChanges = false;
						notifyEiaMaintenance(entity);
						clear();

						if (callback != null) {
							callback.onSuccess(entity);
						}
					}
				});
	}

	private void update(final GHAAsyncCallback<EiaMaintenance> callback,
			final EiaPreventiveMaintenance entity) {

		EiaMaintenanceModel.updatePreventiveMaintenance(entity,
				new GHAAsyncCallback<EiaPreventiveMaintenance>() {
					@Override
					public void onSuccess(final EiaPreventiveMaintenance result) {
						hasUnCommittedChanges = false;
						notifyEiaMaintenance(entity);
						clear();

						if (callback != null) {
							callback.onSuccess(entity);
						}
					}
				});
	}

}
