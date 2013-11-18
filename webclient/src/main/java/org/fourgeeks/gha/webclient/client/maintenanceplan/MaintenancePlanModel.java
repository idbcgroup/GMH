/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceplan;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
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

	public static void getAll(GHAAsyncCallback<List<MaintenancePlan>> callback) {
		service.getAll(callback);
	}

	public static void save(MaintenancePlan maintenancePlan,
			GHAAsyncCallback<MaintenancePlan> callback) {
		service.save(maintenancePlan, callback);
	}

	public static void delete(long id, GHAAsyncCallback<Void> callback) {
		service.delete(id, callback);
	}

	public static void delete(List<MaintenancePlan> maintenancePlans,
			GHAAsyncCallback<Void> callback) {
		GHAAsyncCallback<Void> callback2 = new GHAAsyncCallback<Void>() {

			@Override
			public void onSuccess(Void arg0) {

			}
		};
		for (int i = 0, j = maintenancePlans.size(); i < j; i++)
			if (j == (i - 1))
				service.delete(maintenancePlans.get(i).getId(), callback);
			else
				service.delete(maintenancePlans.get(i).getId(), callback2);
	}

	public static void find(MaintenancePlan maintenancePlan,
			GHAAsyncCallback<List<MaintenancePlan>> callback) {
		service.find(maintenancePlan, callback);
	}

	/**
	 * @param maintenancePlan
	 * @param ghaAsyncCallback
	 */
	public static void update(MaintenancePlan maintenancePlan,
			GHAAsyncCallback<MaintenancePlan> callback) {
		service.update(maintenancePlan, callback);
	}
}
