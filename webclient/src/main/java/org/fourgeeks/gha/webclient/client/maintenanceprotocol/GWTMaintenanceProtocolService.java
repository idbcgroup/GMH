package org.fourgeeks.gha.webclient.client.maintenanceprotocol;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocolStadisticData;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author naramirez
 */
@RemoteServiceRelativePath("maintenanceProtocolsService")
public interface GWTMaintenanceProtocolService extends RemoteService {

	/**
	 * Copy the activities from a mantenance plan to other maintenance plan
	 * 
	 * @param planFrom
	 *            the plan with the activities that are going to be copyed
	 * @param planTo
	 *            the plan who is going to recive the new activites
	 * @return
	 * @throws GHAEJBException
	 */
	public Boolean copyActivities(MaintenancePlan planFrom,
			MaintenancePlan planTo) throws GHAEJBException;

	/**
	 * Delete a group of {@link MaintenanceProtocol} entities (This mean unlink
	 * the activities from the maintenance plan)
	 * 
	 * @param entities
	 *            the entities to delete
	 * @throws GHAEJBException
	 */
	public void delete(List<MaintenanceProtocol> entities)
			throws GHAEJBException;

	/**
	 * Delete a activity from the {@link MaintenanceProtocol} table given the
	 * ID. (This mean unlink the activity from a maintenance plan)
	 * 
	 * @param id
	 * @throws GHAEJBException
	 */
	public void delete(long id) throws GHAEJBException;

	/**
	 * Delete all the records in the {@link MaintenanceProtocol} table that are
	 * associated with the {@link MaintenancePlan} entity. (This mean unlink all
	 * the maintenance activities associated with the plan)
	 * 
	 * @param plan
	 * @return the number of entities deleted
	 * @throws GHAEJBException
	 */
	public int deleteByMaintenancePlan(MaintenancePlan plan)
			throws GHAEJBException;

	/**
	 * @param plan
	 * @return a list of {@link MaintenanceProtocol} with the
	 *         (MaintenanceActivity, MaintenancePlan) pair
	 * @throws GHAEJBException
	 */
	public List<MaintenanceProtocol> findByMaintenancePlan(MaintenancePlan plan)
			throws GHAEJBException;

	/**
	 * @param maintenanceActivity
	 * @return a list of {@link MaintenanceProtocol} with the
	 *         (MaintenanceActivity, MaintenancePlan) pair
	 * @throws GHAEJBException
	 */
	public List<MaintenanceProtocol> findByMantenanceActivity(
			MaintenanceActivity act) throws GHAEJBException;

	/**
	 * Return Stadistic information about the protocol of the plan like:
	 * estimated duration (activities and subprotocols), estimated cost
	 * (activities and subprotocols), number of activities, number of
	 * subprotocols (and its activities)
	 * 
	 * @param mantenancePlan
	 *            the maintenance plan
	 * @return the stadistic information
	 * @throws GHAEJBException
	 */
	public MaintenanceProtocolStadisticData getStadisticInfo(
			MaintenancePlan mantenancePlan) throws GHAEJBException;

	/**
	 * Associate an activity to a maintenance plan
	 * 
	 * @param entity
	 *            the entity whit the associated plan and activity
	 * 
	 * @return A {@link MaintenanceProtocol} entity with the associated activity
	 *         and plan
	 * @throws GHAEJBException
	 */
	public MaintenanceProtocol save(MaintenanceProtocol entity)
			throws GHAEJBException;

	/**
	 * Update the given {@link MaintenanceProtocol} entities
	 * 
	 * @param entities
	 *            list with the {@link MaintenanceProtocol} entities to update
	 * @throws GHAEJBException
	 */
	public void update(List<MaintenanceProtocol> entities)
			throws GHAEJBException;
}
