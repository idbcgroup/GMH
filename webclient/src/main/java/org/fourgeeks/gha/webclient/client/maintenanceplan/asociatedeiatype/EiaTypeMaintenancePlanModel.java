/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.shared.GWT;

/**
 * @author emiliot
 * 
 */
public class EiaTypeMaintenancePlanModel {
	private static final GWTEiaTypeMaintenancePlanServiceAsync service = GWT
			.create(GWTEiaTypeMaintenancePlanService.class);

	private EiaTypeMaintenancePlanModel() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	public static void save(EiaTypeMaintenancePlan eiaTypeMaintenancePlan,
			GHAAsyncCallback<EiaTypeMaintenancePlan> callback) {
		service.save(eiaTypeMaintenancePlan, callback);
	}

	public static void delete(Long id, GHAAsyncCallback<Void> callback) {
		service.delete(id, callback);
	}

	public static void delete(List<EiaTypeMaintenancePlan> entities,
			GHAAsyncCallback<Void> callback) {
		service.delete(entities, callback);
	}

	public static void findByEiaType(EiaType eiaType,
			GHAAsyncCallback<List<EiaTypeMaintenancePlan>> callback) {
		service.findByEiaType(eiaType, callback);
	}

	public static void findByMaintenancePlan(MaintenancePlan maintenancePlan,
			GHAAsyncCallback<List<EiaTypeMaintenancePlan>> callback) {
		service.findByMaintenancePlan(maintenancePlan, callback);
	}
}
