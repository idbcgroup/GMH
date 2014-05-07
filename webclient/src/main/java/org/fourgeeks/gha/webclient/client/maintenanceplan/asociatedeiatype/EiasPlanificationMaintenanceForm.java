package org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.fourgeeks.gha.domain.enu.MaintenancePlanificationState;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.gar.Job;
import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaPlanificationEntity;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHADateItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAMaintenancePlanSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAMaintenancePlanStateSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAMaintenancePlanificationStateSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAPeriodOfTimeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.pmewindows.GHAErrorMessageProcessor;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm.FormType;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAForm;
import org.fourgeeks.gha.webclient.client.eia.EIAUtil;
import org.fourgeeks.gha.webclient.client.eiamaintenanceplanification.EiaMaintenancePlanificationModel;
import org.fourgeeks.gha.webclient.client.eiamaintenanceplanification.MaintenancePlanificationSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.eialistplanification.EiaTypeMaintenancePlanProducer;
import org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.eialistplanification.EiaTypeMaintenancePlanificationListener;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;

/**
 * @author eguerere
 */
public class EiasPlanificationMaintenanceForm extends
		GHAForm<List<EiaMaintenancePlanification>> implements
		EiaTypeMaintenancePlanificationListener, EiaTypeMaintenancePlanProducer {

	private List<MaintenancePlanificationSelectionListener> listeners;
	private List<EiaPlanificationEntity> listEiaPlan;
	// private Eia selectedEia;

	private GHADynamicForm form;
	// private GHABspSelectItem providerSelectItem;
	// private GHAJobSelectItem roleSelectItem;
	private GHADateItem beginningDateDateItem;
	private GHATextItem frequencyItem;
	private GHAPeriodOfTimeSelectItem frequencyPoTSelectItem;
	private GHAMaintenancePlanStateSelectItem planStateSelectItem;
	private GHAMaintenancePlanificationStateSelectItem planificationStateSelectItem;
	private GHAMaintenancePlanSelectItem planSelectItem;
	private MaintenancePlan selectedMplan;
	private final EiasPlanificationAddForm addForm;

	private EiaTypeMaintenancePlan selectedEiatypeMplan;

	private EiaType selectedEiaType;

	{
		listeners = new ArrayList<MaintenancePlanificationSelectionListener>();

		planificationStateSelectItem = new GHAMaintenancePlanificationStateSelectItem(
				false, changedHandler);
		planStateSelectItem = new GHAMaintenancePlanStateSelectItem(false,
				changedHandler);
		planStateSelectItem.setDisabled(true);

		planSelectItem = new GHAMaintenancePlanSelectItem(true, changedHandler);
		planSelectItem.setColSpan(2);
		beginningDateDateItem = new GHADateItem("Fecha de inicio",
				changedHandler);
		beginningDateDateItem.setRequired(true);
		beginningDateDateItem.setValidators(beginningDateDateItem
				.getValidatorDateMax());
		beginningDateDateItem.setShowErrorIcon(true);
		frequencyItem = new GHATextItem(GHAStrings.get("frecuency"));
		frequencyItem.setDisabled(true);
		frequencyPoTSelectItem = new GHAPeriodOfTimeSelectItem();
		frequencyPoTSelectItem.setValue((TimePeriodEnum) null);
		frequencyPoTSelectItem.setDisabled(true);
		// selectDateItem.setDefaultValue(VAL_SOME_EIATYPES);

		form = new GHADynamicForm(4, FormType.NORMAL_FORM);

		/*
		 * planSelectItem.addChangedHandler(new ChangedHandler() {
		 * 
		 * @Override public void onChanged(ChangedEvent event) {
		 * changedHandler.onChanged(event);
		 * 
		 * final MaintenancePlan mPlan = planSelectItem
		 * .getValueAsMaintenancePlan();
		 * planStateSelectItem.setValue(mPlan.getState());
		 * frequencyItem.setValue(mPlan.getFrequency());
		 * frequencyPoTSelectItem.setValue(mPlan.getPot()); } });
		 */
		planSelectItem.setDisabled(true);
		planificationStateSelectItem.setDisabled(true);

	}

	/** */
	public EiasPlanificationMaintenanceForm(EiasPlanificationAddForm addForm) {
		this.addForm = addForm;
		final HLayout mainPanel = new HLayout();
		form.setItems(beginningDateDateItem, new GHASpacerItem(2),
				planSelectItem, new GHASpacerItem(2), frequencyItem,
				frequencyPoTSelectItem, new GHASpacerItem(2),
				planificationStateSelectItem, planStateSelectItem);

		mainPanel.addMembers(form, new LayoutSpacer());
		addMember(mainPanel);
	}

	@Override
	public void activate() {
		toggleForm(true);
	}

	@Override
	public void clear() {
		super.clear();
		beginningDateDateItem.clearValue();
		planSelectItem.clearValue();
		planificationStateSelectItem.clearValue();
		planStateSelectItem.clearValue();
	}

	@Override
	public void deactivate() {
		toggleForm(true);
	}

	private List<EiaMaintenancePlanification> extract() {

		final List<EiaMaintenancePlanification> listPlanification = new ArrayList<EiaMaintenancePlanification>();

		for (int index = 0; index < listEiaPlan.size(); index++) {

			if ((listEiaPlan.get(index).getEmp().getId() == 0)
					&& (listEiaPlan.get(index).getEmp().getPlanificationState() == MaintenancePlanificationState.INACTIVE)) {
				continue;
			}
			EiaMaintenancePlanification planification = new EiaMaintenancePlanification();
			planification.setEia(listEiaPlan.get(index).getEia());
			planification.setPlan(selectedEiatypeMplan);
			planification.setMaintenanceProvider(listEiaPlan.get(index)
					.getEia().getMaintenanceProvider());

			final Job job = new Job();
			job.setId(listEiaPlan.get(index).getEia().getResponsibleRole()
					.getId());
			planification.setJobResponsable(job);

			if (beginningDateDateItem.getValueAsDate() != null)
				planification
						.setBeginningDate(EIAUtil
								.getLogicalDate(beginningDateDateItem
										.getValueAsDate()));

			planification.setPlanificationState(listEiaPlan.get(index).getEmp()
					.getPlanificationState());
			// se verifica que la planificacion exista preguntando si el ID es
			// distinto de cero
			if (listEiaPlan.get(index).getEmp().getId() != 0)
				planification.setId(listEiaPlan.get(index).getEmp().getId());

			listPlanification.add(planification);

		}

		List<String> violationsList = new ArrayList<String>();
		// VALIDANDO LOS DATOS
		Set<ConstraintViolation<EiaMaintenancePlanification>> violations = null;

		violations = validator.validate(listPlanification.get(0));

		if (form.validate() && violations.isEmpty())
			return listPlanification;
		else {

			for (final ConstraintViolation<EiaMaintenancePlanification> violation : violations) {
				violationsList.add(violation.getMessage());
			}

			// GHAAlertManager.alert(violationsList);
			GHAErrorMessageProcessor.alert(violationsList.get(violationsList
					.size() - 1));
		}
		return null;

	}

	@Override
	public void onResize(ResizeEvent arg0) {
		form.resize();
	}

	@Override
	public void save(
			final GHAAsyncCallback<List<EiaMaintenancePlanification>> callback) {

		final List<EiaMaintenancePlanification> entity = extract();

		if (entity == null)
			return;

		EiaMaintenancePlanificationModel.save(entity,
				new GHAAsyncCallback<List<EiaMaintenancePlanification>>() {
					@Override
					public void onSuccess(
							List<EiaMaintenancePlanification> result) {
						hasUnCommittedChanges = false;
						// notifyMaintenancePlanification(result);
						clear();
						if (callback != null) {
							callback.onSuccess(result);

						}
					}
				});
	}

	@Override
	public void set(List<EiaMaintenancePlanification> entity) {

	}

	private void toggleForm(boolean active) {
		beginningDateDateItem.setDisabled(!active);
		planSelectItem.setDisabled(!active);
		planificationStateSelectItem.setDisabled(!active);
	}

	@Override
	public void update(
			GHAAsyncCallback<List<EiaMaintenancePlanification>> callback) {
	}

	@Override
	public void addEiaTypeMaintenancePlanSelectionListener(
			EiaTypeMaintenancePlanificationListener listener) {

	}

	@Override
	public void removeEiaTypeMaintenancePlanSelectionListener(
			EiaTypeMaintenancePlanificationListener listener) {

	}

	@Override
	public void notifyEiaTypeMaintenancePlan(EiaTypeMaintenancePlan plan) {

	}

	@Override
	public void notifyListEiaPlanificationEntity(
			List<EiaPlanificationEntity> eiaPlan) {

	}

	@Override
	public void select(EiaTypeMaintenancePlan plan) {
		this.selectedEiatypeMplan = plan;
		this.selectedMplan = plan.getMaintenancePlan();

		planSelectItem.clearValue();

		planStateSelectItem.setValue(this.selectedMplan.getState());
		frequencyItem.setValue(this.selectedMplan.getFrequency());
		frequencyPoTSelectItem.setValue(this.selectedMplan.getPot());

		Date diaActual = new Date();
		DateTimeFormat dtf = DateTimeFormat.getFormat("dd/MM/yyyy");
		beginningDateDateItem.setValue(dtf.format(diaActual));

		planificationStateSelectItem
				.setValue(MaintenancePlanificationState.ACTIVE);

		planSelectItem.setValue(this.selectedMplan.getName());
	}

	@Override
	public void select(List<EiaPlanificationEntity> listEiaPlan) {
		this.listEiaPlan = listEiaPlan;
	}

	/**
	 * @return the selectedMplan
	 */
	public MaintenancePlan getSelectedMplan() {
		return selectedMplan;
	}

	/**
	 * @param selectedMplan
	 *            the selectedMplan to set
	 */
	public void setSelectedMplan(MaintenancePlan selectedMplan) {
		this.selectedMplan = selectedMplan;
	}

}
