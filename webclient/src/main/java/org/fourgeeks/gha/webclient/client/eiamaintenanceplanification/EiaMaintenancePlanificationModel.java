package org.fourgeeks.gha.webclient.client.eiamaintenanceplanification;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaCorrectiveMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaPreventiveMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.ejb.gmh.EiaMaintenancePlanificationService;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class EiaMaintenancePlanificationModel {
	private static final GWTEiaMaintenancePlanificationServiceAsync service = GWT
			.create(GWTEiaMaintenancePlanificationService.class);

	public static void find(EiaType eiaType,
			GHAAsyncCallback<List<EiaMaintenancePlanification>> callback) {

		service.find(eiaType, callback);
	}

	public void getCorrectiveMaintenancePlanification(
			EiaMaintenancePlanificationService entity,
			GHAAsyncCallback<EiaCorrectiveMaintenancePlanification> callback) {

		service.getCorrectiveMaintenancePlanification(entity, callback);
	}

	public void getPreventiveMaintenancePlanification(
			EiaMaintenancePlanificationService entity,
			GHAAsyncCallback<EiaPreventiveMaintenancePlanification> callback) {

		service.getPreventiveMaintenancePlanification(entity, callback);
	}

	public void saveCorrectiveMaintenance(
			EiaCorrectiveMaintenancePlanification entity,
			AsyncCallback<EiaCorrectiveMaintenancePlanification> callback) {

		service.saveCorrectiveMaintenance(entity, callback);
	}

	public void savePreventiveMaintenance(
			EiaPreventiveMaintenancePlanification entity,
			AsyncCallback<EiaPreventiveMaintenancePlanification> callback) {

		service.savePreventiveMaintenance(entity, callback);
	}

}
