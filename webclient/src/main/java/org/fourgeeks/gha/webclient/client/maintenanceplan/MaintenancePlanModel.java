/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceplan;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenancePlanStadisticData;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.eiatype.maintenance.plan.GWTMaintenancePlanService;
import org.fourgeeks.gha.webclient.client.eiatype.maintenance.plan.GWTMaintenancePlanServiceAsync;

import com.google.gwt.core.shared.GWT;

/**
 * @author emiliot
 * 
 */
public class MaintenancePlanModel {
	private static final GWTMaintenancePlanServiceAsync service = GWT
			.create(GWTMaintenancePlanService.class);

	private MaintenancePlanModel() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	/**
	 * @param callback
	 */
	public static void getAll(GHAAsyncCallback<List<MaintenancePlan>> callback) {
		service.getAll(callback);
	}

	/**
	 * @param maintenancePlan
	 * @param callback
	 */
	public static void save(MaintenancePlan maintenancePlan,
			GHAAsyncCallback<MaintenancePlan> callback) {
		service.save(maintenancePlan, callback);
	}

	/**
	 * @param id
	 * @param callback
	 */
	public static void delete(long id, GHAAsyncCallback<Void> callback) {
		service.delete(id, callback);
	}

	/**
	 * @param maintenancePlans
	 * @param callback
	 */
	public static void delete(List<MaintenancePlan> maintenancePlans,
			GHAAsyncCallback<Void> callback) {
		service.delete(maintenancePlans, callback);
	}

	/**
	 * @param maintenancePlan
	 * @param callback
	 */
	public static void find(MaintenancePlan maintenancePlan,
			GHAAsyncCallback<List<MaintenancePlan>> callback) {
		service.find(maintenancePlan, callback);
	}

	/**
	 * @param maintenancePlan
	 * @param callback
	 */
	public static void update(MaintenancePlan maintenancePlan,
			GHAAsyncCallback<MaintenancePlan> callback) {
		service.update(maintenancePlan, callback);
	}

	/**
	 * Return Stadistic information about the maintenance plan like: number of
	 * activities, estimated cost, times effectuated, number of eias with this
	 * plan, last time effectuated
	 * 
	 * @param mantenancePlan
	 * @param callback
	 *            the response from the server. this give a map with the
	 *            stadistic information. The keys of the map are:<br>
	 *            - number-activities <br>
	 *            - estimated-cost <br>
	 *            - estimated-time <br>
	 *            - times-effectuated <br>
	 *            - number-eias <br>
	 *            - last-time-effect <br>
	 */
	public static void getStadisticInfo(MaintenancePlan mantenancePlan,
			GHAAsyncCallback<MaintenancePlanStadisticData> callback) {
		service.getStadisticInfo(mantenancePlan, callback);
	}
}
