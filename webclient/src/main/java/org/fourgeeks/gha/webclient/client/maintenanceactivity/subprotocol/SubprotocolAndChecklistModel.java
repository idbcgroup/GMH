package org.fourgeeks.gha.webclient.client.maintenanceactivity.subprotocol;

import java.util.List;

import org.fourgeeks.gha.domain.Activity;
import org.fourgeeks.gha.domain.gmh.SubProtocolAndChecklist;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.shared.GWT;

/**
 * @author caparicio
 * 
 */
public class SubprotocolAndChecklistModel {
	private static final GWTSubProtocolAndChecklistServiceAsync service = GWT
			.create(GWTSubProtocolAndChecklistService.class);

	/**
	 * Delete a SubProtocolAndChecklist from database by Id
	 * 
	 * @param Id
	 * @param callback
	 */
	public static void delete(long Id, GHAAsyncCallback<Void> callback) {
		service.delete(Id, callback);
	}

	/**
	 * Delete a list of SubProtocolAndChecklist from database
	 * 
	 * @param entities
	 * @param callback
	 * 
	 */
	public static void delete(List<SubProtocolAndChecklist> entities,
			GHAAsyncCallback<Void> callback) {
		service.delete(entities, callback);
	}

	/**
	 * @param parentActivity
	 * 
	 * @param callback
	 *            a list with the MaintenanceSubProtocols of the parentActivity
	 */
	public static void findByParentActivity(Activity parentActivity,
			GHAAsyncCallback<List<SubProtocolAndChecklist>> callback) {
		service.findByParentActivity(parentActivity, callback);
	}

	/**
	 * @param Id
	 * @param callback
	 *            the MaintenanceSubProtocol with this Id
	 */
	public static void find(long Id,
			GHAAsyncCallback<SubProtocolAndChecklist> callback) {
		service.find(Id, callback);
	}

	/**
	 * @param callback
	 *            the list with all MaintenanceSubProtocols Objects
	 */
	public static void getAll(
			GHAAsyncCallback<List<SubProtocolAndChecklist>> callback) {
		service.getAll(callback);
	}

	/**
	 * @param offset
	 * @param size
	 * @param callback
	 *            List of MaintenanceSubProtocols beginning in offset up to size
	 */
	public static void getAll(int offset, int size,
			GHAAsyncCallback<List<SubProtocolAndChecklist>> callback) {
		service.getAll(offset, size, callback);
	}

	/**
	 * @param maintenanceSubProtocol
	 *            the MaintenanceSubProtocol to be saved on database
	 * @param callback
	 *            MaintenanceSubProtocol saved
	 */
	public static void save(SubProtocolAndChecklist maintenanceSubProtocol,
			GHAAsyncCallback<SubProtocolAndChecklist> callback) {
		service.save(maintenanceSubProtocol, callback);
	}

	/**
	 * @param maintenanceSubProtocol
	 *            the MaintenanceSubProtocol to be updated
	 * @param callback
	 *            MaintenanceSubProtocol updated
	 */
	public static void update(SubProtocolAndChecklist maintenanceSubProtocol,
			GHAAsyncCallback<SubProtocolAndChecklist> callback) {
		service.update(maintenanceSubProtocol, callback);
	}

	/**
	 * 
	 * @param maintenanceSubProtocols
	 * @param callback
	 */
	public static void update(
			List<SubProtocolAndChecklist> maintenanceSubProtocols,
			GHAAsyncCallback<List<SubProtocolAndChecklist>> callback) {
		service.update(maintenanceSubProtocols, callback);
	}
}
