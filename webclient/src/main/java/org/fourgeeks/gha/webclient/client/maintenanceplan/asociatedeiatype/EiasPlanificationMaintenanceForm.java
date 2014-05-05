package org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.fourgeeks.gha.domain.enu.MaintenancePlanificationState;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.gar.Job;
import org.fourgeeks.gha.domain.glm.Bsp;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHADateItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHABspSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAJobSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAMaintenancePlanSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAMaintenancePlanStateSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAMaintenancePlanificationStateSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAPeriodOfTimeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.pmewindows.GHAErrorMessageProcessor;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm.FormType;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAForm;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eia.EIAUtil;
import org.fourgeeks.gha.webclient.client.eiamaintenanceplanification.EiaMaintenancePlanificationModel;
import org.fourgeeks.gha.webclient.client.eiamaintenanceplanification.MaintenancePlanificationSelectionListener;
import org.fourgeeks.gha.webclient.client.eiamaintenanceplanification.MaintenancePlanificationSelectionProducer;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;

/**
 * @author naramirez
 */
public class EiasPlanificationMaintenanceForm extends
		GHAForm<EiaMaintenancePlanification> implements EIASelectionListener,
		EIATypeSelectionListener, MaintenancePlanificationSelectionProducer {

	private List<MaintenancePlanificationSelectionListener> listeners;
	private Eia selectedEia;

	private GHADynamicForm form;
	private GHABspSelectItem providerSelectItem;
	private GHAJobSelectItem roleSelectItem;
	private GHADateItem beginningDateDateItem;
	private GHATextItem frequencyItem;
	private GHAPeriodOfTimeSelectItem frequencyPoTSelectItem;
	private GHAMaintenancePlanStateSelectItem planStateSelectItem;
	private GHAMaintenancePlanificationStateSelectItem planificationStateSelectItem;
	private GHAMaintenancePlanSelectItem planSelectItem;
	private MaintenancePlan selectedMplan;
	private final EiasPlanificationAddForm addForm;

	private EiaType selectedEiaType;

	{
		listeners = new ArrayList<MaintenancePlanificationSelectionListener>();

		roleSelectItem = new GHAJobSelectItem();
		roleSelectItem.addChangedHandler(changedHandler);
		planificationStateSelectItem = new GHAMaintenancePlanificationStateSelectItem(
				false, changedHandler);
		planStateSelectItem = new GHAMaintenancePlanStateSelectItem(false,
				changedHandler);
		planStateSelectItem.setDisabled(true);
		providerSelectItem = new GHABspSelectItem();
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

		providerSelectItem.addChangedHandler(new ChangedHandler() {
			@Override
			public void onChanged(ChangedEvent event) {
				changedHandler.onChanged(event);
				roleSelectItem.fill(providerSelectItem.getValueAsBsp());

				if (providerSelectItem.getValueAsString() == null) {
					roleSelectItem.clearValue();
					roleSelectItem.setDisabled(true);
				} else
					roleSelectItem.setDisabled(false);
			}
		});

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
		planSelectItem.setVisible(true);

	}

	/** */
	public EiasPlanificationMaintenanceForm(EiasPlanificationAddForm addForm) {
		this.addForm = addForm;
		final HLayout mainPanel = new HLayout();
		form.setItems(beginningDateDateItem, new GHASpacerItem(2),
				planSelectItem, new GHASpacerItem(2), frequencyItem,
				frequencyPoTSelectItem, new GHASpacerItem(2),
				providerSelectItem, roleSelectItem, new GHASpacerItem(2),
				planificationStateSelectItem, planStateSelectItem,
				new GHASpacerItem(2));

		mainPanel.addMembers(form, new LayoutSpacer());
		addMember(mainPanel);
	}

	@Override
	public void activate() {
		toggleForm(true);
	}

	@Override
	public void addMaintenancePlanificationSelectionListener(
			MaintenancePlanificationSelectionListener eiaDamageReportSelectionListener) {
		listeners.add(eiaDamageReportSelectionListener);
	}

	@Override
	public void clear() {
		super.clear();
		Window.alert("en el mensaje clear");
		beginningDateDateItem.clearValue();
		planSelectItem.clearValue();
		providerSelectItem.clearValue();
		roleSelectItem.clearValue();
		planificationStateSelectItem.clearValue();
		planStateSelectItem.clearValue();
		// selectDateItem.clearValue();
	}

	@Override
	public void deactivate() {
		toggleForm(true);
	}

	private EiaMaintenancePlanification extract() {
		// if (!hasUnCommittedChanges)
		// return null;
		List<String> violationsList = new ArrayList<String>();

		final EiaMaintenancePlanification planification = new EiaMaintenancePlanification();
		planification.setEia(selectedEia);

		if (planSelectItem.getValue() != null) {

			final EiaTypeMaintenancePlan mplan = new EiaTypeMaintenancePlan();
			mplan.setId(Long.valueOf(planSelectItem.getValueAsString()));
			planification.setPlan(mplan);

		}

		if (providerSelectItem.getValue() != null) {
			final Bsp bsp = new Bsp();
			bsp.setId(Long.valueOf(providerSelectItem.getValueAsString()));
			planification.setMaintenanceProvider(bsp);
		}

		if (roleSelectItem.getValue() != null) {

			final Job job = new Job();
			job.setId(Long.valueOf(roleSelectItem.getValueAsString()));
			planification.setJobResponsable(job);
		}

		if (beginningDateDateItem.getValueAsDate() != null)
			planification.setBeginningDate(EIAUtil
					.getLogicalDate(beginningDateDateItem.getValueAsDate()));

		if (planificationStateSelectItem.getValueAsString() != null) {
			planification.setPlanificationState(MaintenancePlanificationState
					.valueOf(planificationStateSelectItem.getValueAsString()));
		}

		// VALIDANDO LOS DATOS
		Set<ConstraintViolation<EiaMaintenancePlanification>> violations = null;
		violations = validator.validate(planification);

		if (form.validate() && violations.isEmpty())
			return planification;
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
	public void notifyMaintenancePlanification(
			EiaMaintenancePlanification preventivePlanif) {
		for (final MaintenancePlanificationSelectionListener listener : listeners)
			listener.select(preventivePlanif);
	}

	@Override
	public void onResize(ResizeEvent arg0) {
		form.resize();
	}

	@Override
	public void removeMaintenancePlanificationSelectionListener(
			MaintenancePlanificationSelectionListener eiaDamageReportSelectionListener) {
		listeners.remove(eiaDamageReportSelectionListener);
	}

	@Override
	public void save(
			final GHAAsyncCallback<EiaMaintenancePlanification> callback) {
		final EiaMaintenancePlanification entity = extract();
		if (entity == null)
			return;

		EiaMaintenancePlanificationModel.existMantenancePlanification(
				selectedEia, entity.getPlan(), new GHAAsyncCallback<Boolean>() {
					@Override
					public void onSuccess(Boolean result) {

						if (result == false) {
							EiaMaintenancePlanificationModel
									.save(entity,
											new GHAAsyncCallback<EiaMaintenancePlanification>() {
												@Override
												public void onSuccess(
														EiaMaintenancePlanification result) {
													hasUnCommittedChanges = false;
													notifyMaintenancePlanification(result);
													clear();
													if (callback != null)
														callback.onSuccess(result);
												}
											});
						} else {
							GHAErrorMessageProcessor
									.alert("exist-maintenance-planification");
						}
					}
				});
	}

	@Override
	public void select(Eia eia) {
		selectedEia = eia;
		planSelectItem.fillByEiaType(selectedEiaType);

		MaintenancePlan mPlan = addForm.getEiaTypeMplan().getMaintenancePlan();

		planStateSelectItem.setValue(mPlan.getState());
		frequencyItem.setValue(mPlan.getFrequency());
		frequencyPoTSelectItem.setValue(mPlan.getPot());

		Date dia_actual = new Date();
		DateTimeFormat dtf = DateTimeFormat.getFormat("dd/MM/yyyy");
		beginningDateDateItem.setValue(dtf.format(dia_actual));

		planificationStateSelectItem
				.setValue(MaintenancePlanificationState.ACTIVE);

		if (eia.getMaintenanceProvider() != null) {
			providerSelectItem.setValue(eia.getMaintenanceProvider().getId());
			roleSelectItem.fill(providerSelectItem.getValueAsBsp());
			roleSelectItem.setValue(eia.getResponsibleRole().getId());
		}
		if (providerSelectItem.getValueAsString() == null)
			roleSelectItem.setDisabled(true);

	}

	@Override
	public void select(EiaType eiaType) {
		this.selectedEiaType = eiaType;
	}

	@Override
	public void set(EiaMaintenancePlanification entity) {
		beginningDateDateItem.setValue(entity.getBeginningDate());
		providerSelectItem.setValue(entity.getMaintenanceProvider());
		roleSelectItem.setValue(entity.getJobResponsable());
		planificationStateSelectItem.setValue(entity.getPlanificationState());

		final MaintenancePlan plan = entity.getPlan().getMaintenancePlan();
		planStateSelectItem.setValue(plan.getState().name());
		planSelectItem.setValue(plan.getId());

	}

	private void toggleForm(boolean active) {
		beginningDateDateItem.setDisabled(!active);
		planSelectItem.setDisabled(!active);
		providerSelectItem.setDisabled(!active);
		roleSelectItem.setDisabled(!active);
		planificationStateSelectItem.setDisabled(!active);
	}

	@Override
	public void update(GHAAsyncCallback<EiaMaintenancePlanification> callback) {
		// TODO Auto-generated method stub
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
