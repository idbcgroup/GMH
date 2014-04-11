package org.fourgeeks.gha.webclient.client.maintenanceprotocol;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocolStadisticData;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.shared.GWT;

/**
 * @author naramirez
 */
public class MaintenanceProtocolModel {
	private static final GWTMaintenanceProtocolServiceAsync service = GWT
			.create(GWTMaintenanceProtocolService.class);

	/**
	 * Copy the activities from a mantenance plan to other maintenance plan
	 * 
	 * @param planFrom
	 *            the plan with the activities that are going to be copyed
	 * @param planTo
	 *            the plan who is going to recive the new activites
	 * @param ghaAsyncCallback
	 *            the response callback
	 */
	public static void copyActivities(MaintenancePlan planFrom,
			MaintenancePlan planTo, GHAAsyncCallback<Boolean> ghaAsyncCallback) {
		service.copyActivities(planFrom, planTo, ghaAsyncCallback);
	}

	/**
	 * Delete a group of {@link MaintenanceProtocol} entities (This mean unlink
	 * the activities from the maintenance plan)
	 * 
	 * @param entities
	 *            the entities to delete
	 * @param callback
	 *            the response callback
	 */
	public static void delete(List<MaintenanceProtocol> entities,
			GHAAsyncCallback<Void> callback) {
		service.delete(entities, callback);
	}

	/**
	 * Delete a activity from the {@link MaintenanceProtocol} table given the
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
	 * Delete all the records in the {@link MaintenanceProtocol} table that are
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
	 *            {@link MaintenanceProtocol} with the (MaintenanceActivity,
	 *            MaintenancePlan) pair
	 */
	public static void findByMaintenancePlan(MaintenancePlan plan,
			GHAAsyncCallback<List<MaintenanceProtocol>> callback) {
		service.findByMaintenancePlan(plan, callback);
	}

	/**
	 * @param mantenanceactivity
	 * @param callback
	 *            the response callback with a list of
	 *            {@link MaintenanceProtocol} with the (MaintenanceActivity,
	 *            MaintenancePlan) pair
	 */
	public static void findByMantenanceActivity(MaintenanceActivity act,
			GHAAsyncCallback<List<MaintenanceProtocol>> callback) {
		service.findByMantenanceActivity(act, callback);
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
	 *            the response callback with a {@link MaintenanceProtocol}
	 *            entity that have the associated activity and plan
	 */
	public static void save(MaintenanceProtocol entity,
			GHAAsyncCallback<MaintenanceProtocol> callback) {
		service.save(entity, callback);
	}

	/**
	 * Update the given {@link MaintenanceProtocol} entities
	 * 
	 * @param entities
	 *            list with the {@link MaintenanceProtocol} entities to update
	 * @param callback
	 *            the response callback
	 */
	public static void update(List<MaintenanceProtocol> entities,
			GHAAsyncCallback<Void> callback) {
		service.update(entities, callback);
	}
}
