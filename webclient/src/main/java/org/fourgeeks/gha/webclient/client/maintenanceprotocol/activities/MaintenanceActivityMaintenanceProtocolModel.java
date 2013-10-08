package org.fourgeeks.gha.webclient.client.maintenanceprotocol.activities;

import org.fourgeeks.gha.webclient.client.maintenanceprotocol.activities.GWTMaintenanceActivityMaintenanceProtocolServiceAsync;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivityMaintenanceProtocol;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.shared.GWT;

/**
 * @author emiliot
 * 
 */
public class MaintenanceActivityMaintenanceProtocolModel {
	private static final GWTMaintenanceActivityMaintenanceProtocolServiceAsync service = GWT
			.create(GWTMaintenanceActivityMaintenanceProtocolService.class);

	/**
	 * 
	 */
	private MaintenanceActivityMaintenanceProtocolModel() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	public static void delete(long id, GHAAsyncCallback<Void> callback) {
		service.delete(id, callback);
	}

	public static void save(
			MaintenanceActivityMaintenanceProtocol maintenanceActivityMaintenanceProtocol,
			GHAAsyncCallback<MaintenanceActivityMaintenanceProtocol> callback) {
		service.save(maintenanceActivityMaintenanceProtocol, callback);
	}

	public static void findByMaintenanceActivity(
			MaintenanceActivity maintenanceActivity,
			GHAAsyncCallback<List<MaintenanceActivityMaintenanceProtocol>> callback) {
		service.findByMaintenanceActivity(maintenanceActivity, callback);
	}

	public static void findByMaintenanceProtocol(
			MaintenanceProtocol maintenanceProtocol,
			GHAAsyncCallback<List<MaintenanceActivityMaintenanceProtocol>> callback) {
		service.findByMaintenanceProtocol(maintenanceProtocol, callback);
	}
}
