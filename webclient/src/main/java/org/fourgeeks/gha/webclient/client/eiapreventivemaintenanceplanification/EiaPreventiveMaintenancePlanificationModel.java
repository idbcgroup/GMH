package org.fourgeeks.gha.webclient.client.eiapreventivemaintenanceplanification;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.eiamaintenanceplanification.GWTEiaMaintenancePlanificationServiceAsync;

import com.google.gwt.core.shared.GWT;

public class EiaPreventiveMaintenancePlanificationModel {
	private static final GWTEiaMaintenancePlanificationServiceAsync service = GWT
			.create(GWTMaintenancePlanificationService.class);

	public static void find(EiaType eiaType,
			GHAAsyncCallback<List<EiaMaintenancePlanification>> callback) {
		service.find(eiaType, callback);
	}

	public static void save(EiaMaintenancePlanification entity,
			GHAAsyncCallback<EiaMaintenancePlanification> callback) {
		service.save(entity, callback);
	}

}
