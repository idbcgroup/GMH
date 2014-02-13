package org.fourgeeks.gha.webclient.client.eiamaintenanceplanification;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.eiapreventivemaintenanceplanification.GWTMaintenancePlanificationServiceAsync;

import com.google.gwt.core.shared.GWT;

/**
 * @author naramirez
 * 
 */
public class EiaMaintenancePlanificationModel {
	private static final GWTMaintenancePlanificationServiceAsync service = GWT
			.create(GWTMaintenancePlanificationService.class);

	/**
	 * @param eiaType
	 * @param callback
	 */
	public static void find(EiaType eiaType,
			GHAAsyncCallback<List<EiaMaintenancePlanification>> callback) {
		service.find(eiaType, callback);
	}

	/**
	 * @param entity
	 * @param callback
	 */
	public static void save(EiaMaintenancePlanification entity,
			GHAAsyncCallback<EiaMaintenancePlanification> callback) {
		service.save(entity, callback);
	}

}
