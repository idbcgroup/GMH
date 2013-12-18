package org.fourgeeks.gha.webclient.client.maintenanceprotocols;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocolStadisticData;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocols;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.shared.GWT;

/**
 * @author naramirez
 */
public class MaintenanceProtocolsModel {
	private static final GWTMaintenanceProtocolsServiceAsync service = GWT
			.create(GWTMaintenanceProtocolsService.class);

	/**
	 * Copy the activities from a mantenance plan to other maintenance plan
	 * 
	 * @param planFrom
	 *            the plan with the activities that are going to be copyed
	 * @param planTo
	 *            the plan who is going to recive the new activites
	 * @param callback
	 *            the response callback
	 */
	public static void copyActivities(MaintenancePlan planFrom,
			MaintenancePlan planTo, GHAAsyncCallback<Void> callback) {
		service.copyActivities(planFrom, planTo, callback);
	}

	/**
	 * Delete a activity from the {@link MaintenanceProtocols} table given the
	 * ID. (This mean unlink the activity from a maintenance plan)
	 * 
	 * @param id
	 *            the id of the entity to delete
	 * @param callback
	 *            the response callback
	 */
	public static void delete(long id, GHAAsyncCallback<Void> callback) {
		service.delete(id, callback);
	}

	/**
	 * Delete all the records in the {@link MaintenanceProtocols} table that are
	 * associated with the {@link MaintenancePlan} entity. (This mean unlink all
	 * the maintenance activities associated with the plan)
	 * 
	 * @param plan
	 * @param callback
	 *            the response callback with the number of entities deleted
	 */
	public static void deleteByMaintenancePlan(MaintenancePlan plan,
			GHAAsyncCallback<Integer> callback) {
		service.deleteByMaintenancePlan(plan, callback);
	}

	/**
	 * @param plan
	 * @param callback
	 *            the response callback with a list of
	 *            {@link MaintenanceProtocols} with the (MaintenanceActivity,
	 *            MaintenancePlan) pair
	 */
	public static void findByMaintenancePlan(MaintenancePlan plan,
			GHAAsyncCallback<List<MaintenanceProtocols>> callback) {
		service.findByMaintenancePlan(plan, callback);
	}

	/**
	 * Return Stadistic information about the protocol of the plan like:
	 * estimated duration (activities and subprotocols), estimated cost
	 * (activities and subprotocols), number of activities, number of
	 * subprotocols (and its activities)
	 * 
	 * @param mantenancePlan
	 *            the maintenance plan
	 * @param callback
	 *            the response callback with the stadistic information
	 */
	public static void getStadisticInfo(MaintenancePlan mantenancePlan,
			GHAAsyncCallback<MaintenanceProtocolStadisticData> callback) {
		service.getStadisticInfo(mantenancePlan, callback);
	}

	/**
	 * Associate an activity to a maintenance plan
	 * 
	 * @param entity
	 *            the entity whit the associated plan and activity
	 * 
	 * @param callback
	 *            the response callback with a {@link MaintenanceProtocols}
	 *            entity that have the associated activity and plan
	 */
	public static void save(MaintenanceProtocols entity,
			GHAAsyncCallback<MaintenanceProtocols> callback) {
		service.save(entity, callback);
	}
}
