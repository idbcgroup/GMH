package org.fourgeeks.gha.webclient.client.maintenanceactivity;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.maintenanceplan.maintenanceprotocol.GWTMaintenanceActivityServiceAsync;

import com.google.gwt.core.shared.GWT;

/**
 * @author emiliot
 * 
 */
public class MaintenanceActivityModel {
	private static final GWTMaintenanceActivityServiceAsync service = GWT
			.create(GWTMaintenanceActivityService.class);

	private MaintenanceActivityModel() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	/**
	 * @param callback
	 */
	public static void getAll(
			GHAAsyncCallback<List<MaintenanceActivity>> callback) {
		service.getAll(callback);
	}

	/**
	 * @param maintenanceActivity
	 * @param callback
	 */
	public static void save(MaintenanceActivity maintenanceActivity,
			GHAAsyncCallback<MaintenanceActivity> callback) {
		service.save(maintenanceActivity, callback);
	}

	/**
	 * @param maintenanceActivity
	 * @param callback
	 */
	public static void update(MaintenanceActivity maintenanceActivity,
			GHAAsyncCallback<MaintenanceActivity> callback) {
		service.update(maintenanceActivity, callback);
	}

	/**
	 * @param id
	 * @param callback
	 */
	public static void delete(long id, GHAAsyncCallback<Void> callback) {
		service.delete(id, callback);
	}

	/**
	 * @param activity
	 * @param callback
	 */
	public static void find(MaintenanceActivity activity,
			GHAAsyncCallback<List<MaintenanceActivity>> callback) {
		service.find(activity, callback);
	}
}
