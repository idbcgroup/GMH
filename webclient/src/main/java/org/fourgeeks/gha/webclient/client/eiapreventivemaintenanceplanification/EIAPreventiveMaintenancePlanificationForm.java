package org.fourgeeks.gha.webclient.client.eiapreventivemaintenanceplanification;

import java.util.LinkedHashMap;
import java.util.List;

import org.fourgeeks.gha.domain.enu.MaintenancePlanificationState;
import org.fourgeeks.gha.domain.enu.MaintenancePlanificationStatus;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaPreventiveMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHADateItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHAExternalProviderSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHARoleSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAForm;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.EiaTypeMaintenancePlanModel;

import com.google.gwt.event.logical.shared.ResizeEvent;

public class EIAPreventiveMaintenancePlanificationForm extends
		GHAForm<EiaPreventiveMaintenancePlanification> implements
		EIASelectionListener, EIATypeSelectionListener,
		PreventivePlanificationSelectionProducer {

	GHADynamicForm form;
	GHAExternalProviderSelectItem providerSelectItem;
	GHARoleSelectItem roleSelectItem;
	GHADateItem scheduledDateDateItem, deliverDateDateItem,
			acceptationDateDateItem;
	GHASelectItem statusSelectItem, stateSelectItem, maintenacePlanSelectItem;

	{
	}

	public EIAPreventiveMaintenancePlanificationForm() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addPreventivePlanificationSelectionListener(
			PreventivePlanificationSelectionListener eiaDamageReportSelectionListener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub

	}

	private void fill() {
		stateSelectItem.setValueMap(MaintenancePlanificationState.toValueMap());
		statusSelectItem.setValueMap(MaintenancePlanificationStatus
				.toValueMap());

	}

	@Override
	public void notifyPreventiveMaintenancePlanification(
			EiaPreventiveMaintenancePlanification preventivePlanif) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onResize(ResizeEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removePreventivePlanificationSelectionListener(
			PreventivePlanificationSelectionListener eiaDamageReportSelectionListener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void save(
			GHAAsyncCallback<EiaPreventiveMaintenancePlanification> callback) {
		// TODO Auto-generated method stub

	}

	@Override
	public void select(Eia eia) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

	@Override
	public void update(
			GHAAsyncCallback<EiaPreventiveMaintenancePlanification> callback) {
		// TODO Auto-generated method stub

	}

}
