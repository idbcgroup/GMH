package org.fourgeeks.gha.webclient.client.maintenanceplan.subprotocolactivities;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.MaintenanceSubProtocol;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.GWTMaintenanceSubProtocolServiceAsync;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.subprotocol.GWTMaintenanceSubProtocolService;

import com.google.gwt.core.shared.GWT;

/**
 * @author caparicio
 * 
 */
public class MaintenanceSubprotocolModel {
	private static final GWTMaintenanceSubProtocolServiceAsync service = GWT
			.create(GWTMaintenanceSubProtocolService.class);

	/**
	 * Delete a MaintenanceSubProtocol from database by Id
	 * 
	 * @throws GHAEJBException
	 */
	public static void delete(long Id, GHAAsyncCallback<Void> callback) {
		service.delete(Id, callback);
	}

	/**
	 * @param protocolActivity
	 * @return a list with the MaintenanceSubProtocols of the
	 *         maintenanceActivity
	 * @throws GHAEJBException
	 */
	public static void findByMaintenanceActivity(
			MaintenanceActivity maintenanceActivity,
			GHAAsyncCallback<List<MaintenanceSubProtocol>> callback) {
		service.findByMaintenanceActivity(maintenanceActivity, callback);
	}

	/**
	 * @param Id
	 * @return the MaintenanceSubProtocol with this Id
	 * @throws GHAEJBException
	 */
	public static void find(long Id, GHAAsyncCallback<MaintenanceSubProtocol> callback) {
		service.find(Id, callback);
	}

	/**
	 * @return the list with all MaintenanceSubProtocols Objects
	 * @throws GHAEJBException
	 */
	public static void getAll(GHAAsyncCallback<List<MaintenanceSubProtocol>> callback) {
		service.getAll(callback);
	}

	/**
	 * @param offset
	 * @param size
	 * @return List of MaintenanceSubProtocols beginning in offset up to size
	 * @throws GHAEJBException
	 */
	public static void getAll(int offset, int size,
			GHAAsyncCallback<List<MaintenanceSubProtocol>> callback) {
		service.getAll(offset, size, callback);
	}

	/**
	 * @param MaintenanceSubProtocol
	 *            the MaintenanceSubProtocol to be saved on database
	 * @throws GHAEJBException
	 * @return MaintenanceSubProtocol saved
	 */
	public static void save(MaintenanceSubProtocol maintenanceSubProtocol,
			GHAAsyncCallback<MaintenanceSubProtocol> callback) {
		service.save(maintenanceSubProtocol, callback);
	}

	/**
	 * @param MaintenanceSubProtocol
	 *            the MaintenanceSubProtocol to be updated
	 * @return MaintenanceSubProtocol updated
	 * @throws GHAEJBException
	 */
	public static void update(MaintenanceSubProtocol maintenanceSubProtocol,
			GHAAsyncCallback<MaintenanceSubProtocol> callback) {
		service.update(maintenanceSubProtocol, callback);
	}
}
