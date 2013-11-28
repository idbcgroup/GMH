package org.fourgeeks.gha.webclient.client.eiapreventivemaintenanceplanification;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.fourgeeks.gha.domain.enu.MaintenancePlanificationState;
import org.fourgeeks.gha.domain.enu.MaintenancePlanificationStatus;
import org.fourgeeks.gha.domain.ess.Role;
import org.fourgeeks.gha.domain.glm.ExternalProvider;
import org.fourgeeks.gha.domain.gmh.Eia;
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
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAExternalProviderSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHARoleSelectItem;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eia.EIAUtil;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.EiaTypeMaintenancePlanModel;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;

public class EIAPreventiveMaintenancePlanificationForm extends
		GHAForm<EiaPreventiveMaintenancePlanification> implements
		EIASelectionListener, EIATypeSelectionListener,
		PreventivePlanificationSelectionProducer {

	private List<PreventivePlanificationSelectionListener> listeners;
	private Eia selectedEia;

	private GHADynamicForm form;
	private GHAExternalProviderSelectItem providerSelectItem;
	private GHARoleSelectItem roleSelectItem;
	private GHADateItem scheduledDateDateItem, deliverDateDateItem,
			acceptationDateDateItem;
	private GHASelectItem statusSelectItem, stateSelectItem,
			maintenacePlanSelectItem;

	{
		listeners = new ArrayList<PreventivePlanificationSelectionListener>();

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

	public EIAPreventiveMaintenancePlanificationForm() {
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
	public void addPreventivePlanificationSelectionListener(
			PreventivePlanificationSelectionListener eiaDamageReportSelectionListener) {
		listeners.add(eiaDamageReportSelectionListener);
	}

	@Override
	public void deactivate() {
		toggleForm(true);
	}

	private EiaPreventiveMaintenancePlanification extract() {
		EiaPreventiveMaintenancePlanification entity = new EiaPreventiveMaintenancePlanification();
		EiaMaintenancePlanification planification = new EiaMaintenancePlanification();
		planification.setEia(selectedEia);
		entity.setPlanification(planification);

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

		if (maintenacePlanSelectItem.getValue() != null) {
			EiaTypeMaintenancePlan plan = new EiaTypeMaintenancePlan();
			plan.setId(Long.valueOf(maintenacePlanSelectItem.getValueAsString()));
			entity.setPlan(plan);
		}

		planification.setScheduledDate(EIAUtil
				.getLogicalDate(scheduledDateDateItem.getValueAsDate()));

		planification.setDeliverDate(EIAUtil.getLogicalDate(deliverDateDateItem
				.getValueAsDate()));

		planification.setAcceptationDate(EIAUtil
				.getLogicalDate(acceptationDateDateItem.getValueAsDate()));

		planification.setState(MaintenancePlanificationState
				.valueOf(stateSelectItem.getValueAsString()));

		planification.setStatus(MaintenancePlanificationStatus
				.valueOf(statusSelectItem.getValueAsString()));

		// VALIDANDO LOS DATOS
		Set<ConstraintViolation<EiaPreventiveMaintenancePlanification>> violations = null;
		violations = validator.validate(entity);
		if (form.validate() && violations.isEmpty())
			return entity;
		else {
			List<String> violationsList = new ArrayList<String>();
			for (ConstraintViolation<EiaPreventiveMaintenancePlanification> violation : violations)
				violationsList.add(violation.getMessage());
			GHANotification.alert(violationsList);
		}
		return null;
	}

	private void fill() {
		stateSelectItem.setValueMap(MaintenancePlanificationState.toValueMap());
		statusSelectItem.setValueMap(MaintenancePlanificationStatus
				.toValueMap());
	}

	@Override
	public void notifyPreventiveMaintenancePlanification(
			EiaPreventiveMaintenancePlanification preventivePlanif) {
		for (PreventivePlanificationSelectionListener listener : listeners)
			listener.select(preventivePlanif);
	}

	@Override
	public void onResize(ResizeEvent arg0) {
		form.resize(GHAUiHelper.getNormalFormWidth(30), 4);
	}

	@Override
	public void removePreventivePlanificationSelectionListener(
			PreventivePlanificationSelectionListener eiaDamageReportSelectionListener) {
		listeners.remove(eiaDamageReportSelectionListener);
	}

	@Override
	public void save(
			final GHAAsyncCallback<EiaPreventiveMaintenancePlanification> callback) {
		EiaPreventiveMaintenancePlanification entity = extract();
		if (entity == null)
			return;

		EiaPreventiveMaintenancePlanificationModel.save(entity,
				new GHAAsyncCallback<EiaPreventiveMaintenancePlanification>() {
					@Override
					public void onSuccess(
							EiaPreventiveMaintenancePlanification result) {
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
		if (eia.getProvider() != null)
			providerSelectItem.setValue(eia.getProvider().getId());

		if (eia.getResponsibleRole() != null)
			roleSelectItem.setValue(eia.getResponsibleRole().getId());
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
	public void set(EiaPreventiveMaintenancePlanification entity) {
		EiaMaintenancePlanification planification = entity.getPlanification();
		scheduledDateDateItem.setValue(planification.getScheduledDate());
		deliverDateDateItem.setValue(planification.getDeliverDate());
		acceptationDateDateItem.setValue(planification.getAcceptationDate());
		providerSelectItem.setValue(planification.getProvider());
		roleSelectItem.setValue(planification.getRole());
		statusSelectItem.setValue(planification.getStatus().name());
		stateSelectItem.setValue(planification.getState().name());
		EiaTypeMaintenancePlan plan = entity.getPlan();
		maintenacePlanSelectItem.setValue(plan.getId());
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
			GHAAsyncCallback<EiaPreventiveMaintenancePlanification> callback) {
		// TODO Auto-generated method stub
	}

}
