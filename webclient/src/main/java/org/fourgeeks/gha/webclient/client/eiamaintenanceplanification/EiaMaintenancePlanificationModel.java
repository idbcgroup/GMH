package org.fourgeeks.gha.webclient.client.eiamaintenanceplanification;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaPlanificationEntity;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

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

	/**
	 * @param eia
	 * @param plan
	 * @param callback
	 */
	public static void existMantenancePlanification(Eia eia,
			EiaTypeMaintenancePlan plan, GHAAsyncCallback<Boolean> callback) {
		service.existMantenancePlanification(eia, plan, callback);
	}

	public static void findEiaMaintenancePlanificationStatus(Eia eia,
			EiaTypeMaintenancePlan plan,
			GHAAsyncCallback<List<EiaPlanificationEntity>> callback) {
		service.findEiaMaintenancePlanificationStatus(eia, plan, callback);
	}

}
