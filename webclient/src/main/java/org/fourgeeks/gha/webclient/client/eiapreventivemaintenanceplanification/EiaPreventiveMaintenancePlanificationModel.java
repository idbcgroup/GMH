package org.fourgeeks.gha.webclient.client.eiapreventivemaintenanceplanification;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaPreventiveMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.shared.GWT;

public class EiaPreventiveMaintenancePlanificationModel {
	private static final GWTEiaPreventiveMaintenancePlanificationServiceAsync service = GWT
			.create(GWTEiaPreventiveMaintenancePlanificationService.class);

	public static void find(
			EiaType eiaType,
			GHAAsyncCallback<List<EiaPreventiveMaintenancePlanification>> callback) {
		service.find(eiaType, callback);
	}

	public static void save(
			EiaPreventiveMaintenancePlanification eiaDamageReport,
			GHAAsyncCallback<EiaPreventiveMaintenancePlanification> callback) {
		service.save(eiaDamageReport, callback);
	}

}
