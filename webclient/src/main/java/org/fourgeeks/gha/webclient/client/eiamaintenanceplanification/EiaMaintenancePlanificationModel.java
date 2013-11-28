package org.fourgeeks.gha.webclient.client.eiamaintenanceplanification;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaCorrectiveMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaPreventiveMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaType;
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

	public static void getCorrectiveMaintenance(
			EiaMaintenancePlanification entity,
			GHAAsyncCallback<EiaCorrectiveMaintenancePlanification> callback) {

		service.getCorrectiveMaintenancePlanification(entity, callback);
	}

	public static void getPreventiveMaintenance(
			EiaMaintenancePlanification entity,
			GHAAsyncCallback<EiaPreventiveMaintenancePlanification> callback) {

		service.getPreventiveMaintenancePlanification(entity, callback);
	}

	public static void saveCorrectiveMaintenance(
			EiaCorrectiveMaintenancePlanification entity,
			AsyncCallback<EiaCorrectiveMaintenancePlanification> callback) {

		service.saveCorrectiveMaintenance(entity, callback);
	}

	public static void savePreventiveMaintenance(
			EiaPreventiveMaintenancePlanification entity,
			AsyncCallback<EiaPreventiveMaintenancePlanification> callback) {

		service.savePreventiveMaintenance(entity, callback);
	}

	public static void updateCorrectiveMaintenance(
			EiaCorrectiveMaintenancePlanification entity,
			AsyncCallback<EiaCorrectiveMaintenancePlanification> callback) {

		service.updateCorrectiveMaintenance(entity, callback);
	}

	public static void updatePreventiveMaintenance(
			EiaPreventiveMaintenancePlanification entity,
			AsyncCallback<EiaPreventiveMaintenancePlanification> callback) {

		service.updatePreventiveMaintenance(entity, callback);
	}
}
