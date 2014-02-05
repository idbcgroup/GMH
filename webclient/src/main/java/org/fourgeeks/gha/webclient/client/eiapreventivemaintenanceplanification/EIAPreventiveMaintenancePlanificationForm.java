package org.fourgeeks.gha.webclient.client.eiapreventivemaintenanceplanification;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.fourgeeks.gha.domain.enu.MaintenancePlanificationState;
import org.fourgeeks.gha.domain.gar.Job;
import org.fourgeeks.gha.domain.glm.Bsp;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHAAlertManager;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHADateItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAExternalProviderSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAMaintenancePlanSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAMaintenancePlanStateSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAMaintenancePlanificationStateSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHARoleSelectItem;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm.FormType;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAForm;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eia.EIAUtil;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;

/**
 * @author naramirez
 */
public class EIAPreventiveMaintenancePlanificationForm extends
		GHAForm<EiaMaintenancePlanification> implements EIASelectionListener,
		EIATypeSelectionListener, MaintenancePlanificationSelectionProducer {

	private List<MaintenancePlanificationSelectionListener> listeners;
	private Eia selectedEia;

	private GHADynamicForm form;
	private GHAExternalProviderSelectItem providerSelectItem;
	private GHARoleSelectItem roleSelectItem;
	private GHADateItem scheduledDateDateItem;
	private GHAMaintenancePlanStateSelectItem planStateSelectItem;
	private GHAMaintenancePlanificationStateSelectItem planificationStateSelectItem;
	private GHAMaintenancePlanSelectItem maintenacePlanSelectItem;
	private EiaType selectedEiaType;

	{
		listeners = new ArrayList<MaintenancePlanificationSelectionListener>();

		roleSelectItem = new GHARoleSelectItem();
		roleSelectItem.addChangedHandler(changedHandler);
		planificationStateSelectItem = new GHAMaintenancePlanificationStateSelectItem(
				false, changedHandler);
		planStateSelectItem = new GHAMaintenancePlanStateSelectItem(false,
				changedHandler);
		planStateSelectItem.setDisabled(true);
		providerSelectItem = new GHAExternalProviderSelectItem(false,
				changedHandler);
		maintenacePlanSelectItem = new GHAMaintenancePlanSelectItem(true,
				changedHandler);
		maintenacePlanSelectItem.setColSpan(2);
		scheduledDateDateItem = new GHADateItem("Fecha de inicio",
				changedHandler);

		form = new GHADynamicForm(4, FormType.NORMAL_FORM);
	}

	/** */
	public EIAPreventiveMaintenancePlanificationForm() {
		final HLayout mainPanel = new HLayout();
		form.setItems(scheduledDateDateItem, new GHASpacerItem(3),
				maintenacePlanSelectItem, new GHASpacerItem(2),
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
	public void addPreventivePlanificationSelectionListener(
			MaintenancePlanificationSelectionListener eiaDamageReportSelectionListener) {
		listeners.add(eiaDamageReportSelectionListener);
	}

	@Override
	public void clear() {
		super.clear();

		scheduledDateDateItem.clearValue();
		maintenacePlanSelectItem.clearValue();
		providerSelectItem.clearValue();
		roleSelectItem.clearValue();
		planificationStateSelectItem.clearValue();
		planStateSelectItem.clearValue();
	}

	@Override
	public void deactivate() {
		toggleForm(true);
	}

	private EiaMaintenancePlanification extract() {
		EiaMaintenancePlanification entity = new EiaMaintenancePlanification();
		EiaMaintenancePlanification planification = new EiaMaintenancePlanification();
		planification.setEia(selectedEia);

		if (providerSelectItem.getValue() != null) {
			Bsp maintenanceProvider = new Bsp();
			maintenanceProvider.setId(Long.valueOf(providerSelectItem
					.getValueAsString()));
			planification.setMaintenanceProvider(maintenanceProvider);
		}

		if (roleSelectItem.getValue() != null) {
			Job jobResponsable = new Job();
			jobResponsable
					.setId(Long.valueOf(roleSelectItem.getValueAsString()));
			planification.setJobResponsable(jobResponsable);
		}

		if (maintenacePlanSelectItem.getValue() != null) {
			EiaTypeMaintenancePlan plan = new EiaTypeMaintenancePlan();
			plan.setId(Long.valueOf(maintenacePlanSelectItem.getValueAsString()));
			entity.setPlan(plan);
		}

		planification.setScheduledDate(EIAUtil
				.getLogicalDate(scheduledDateDateItem.getValueAsDate()));

		entity.setPlanificationState(MaintenancePlanificationState
				.valueOf(planStateSelectItem.getValueAsString()));

		// VALIDANDO LOS DATOS
		Set<ConstraintViolation<EiaMaintenancePlanification>> violations = null;
		violations = validator.validate(entity);
		if (form.validate() && violations.isEmpty())
			return entity;
		else {
			List<String> violationsList = new ArrayList<String>();
			for (ConstraintViolation<EiaMaintenancePlanification> violation : violations)
				violationsList.add(violation.getMessage());
			GHAAlertManager.alert(violationsList);
		}
		return null;
	}

	@Override
	public void notifyPreventiveMaintenancePlanification(
			EiaMaintenancePlanification preventivePlanif) {
		for (MaintenancePlanificationSelectionListener listener : listeners)
			listener.select(preventivePlanif);
	}

	@Override
	public void onResize(ResizeEvent arg0) {
		form.resize();
	}

	@Override
	public void removePreventivePlanificationSelectionListener(
			MaintenancePlanificationSelectionListener eiaDamageReportSelectionListener) {
		listeners.remove(eiaDamageReportSelectionListener);
	}

	@Override
	public void save(
			final GHAAsyncCallback<EiaMaintenancePlanification> callback) {
		EiaMaintenancePlanification entity = extract();
		if (entity == null)
			return;

		EiaPreventiveMaintenancePlanificationModel.save(entity,
				new GHAAsyncCallback<EiaMaintenancePlanification>() {
					@Override
					public void onSuccess(EiaMaintenancePlanification result) {
						hasUnCommittedChanges = false;
						notifyPreventiveMaintenancePlanification(result);
						clear();
						if (callback != null)
							callback.onSuccess(result);
					}
				});

	}

	@Override
	public void select(Eia eia) {
		selectedEia = eia;
		maintenacePlanSelectItem.fillByEiaType(selectedEiaType);

		if (eia.getProvider() != null)
			providerSelectItem.setValue(eia.getProvider().getId());

		if (eia.getResponsibleRole() != null)
			roleSelectItem.setValue(eia.getResponsibleRole().getId());
	}

	@Override
	public void select(EiaType eiaType) {
		this.selectedEiaType = eiaType;
	}

	@Override
	public void set(EiaMaintenancePlanification entity) {
		scheduledDateDateItem.setValue(entity.getScheduledDate());
		providerSelectItem.setValue(entity.getMaintenanceProvider());
		roleSelectItem.setValue(entity.getJobResponsable());
		planificationStateSelectItem.setValue(entity.getPlanificationState());

		final MaintenancePlan plan = entity.getPlan().getMaintenancePlan();
		planStateSelectItem.setValue(plan.getState().name());
		maintenacePlanSelectItem.setValue(plan.getId());
	}

	private void toggleForm(boolean active) {
		scheduledDateDateItem.setDisabled(!active);
		maintenacePlanSelectItem.setDisabled(!active);
		providerSelectItem.setDisabled(!active);
		roleSelectItem.setDisabled(!active);
		planificationStateSelectItem.setDisabled(!active);
	}

	@Override
	public void update(GHAAsyncCallback<EiaMaintenancePlanification> callback) {
		// TODO Auto-generated method stub
	}

}
