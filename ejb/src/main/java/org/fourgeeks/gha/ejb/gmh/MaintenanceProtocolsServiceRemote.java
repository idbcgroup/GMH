package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocolStadisticData;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocols;

/**
 * @author naramirez
 */
@Remote
public interface MaintenanceProtocolsServiceRemote {

	/**
	 * Copy the activities from a mantenance plan to other maintenance plan
	 * 
	 * @param planFrom
	 *            the plan with the activities that are going to be copyed
	 * @param planTo
	 *            the plan who is going to recive the new activites
	 * @throws GHAEJBException
	 */
	public void copyActivities(MaintenancePlan planFrom, MaintenancePlan planTo)
			throws GHAEJBException;

	/**
	 * Delete a activity from the {@link MaintenanceProtocols} table given the
	 * ID. (This mean unlink the activity from a maintenance plan)
	 * 
	 * @param id
	 * @throws GHAEJBException
	 */
	public void delete(long id) throws GHAEJBException;

	/**
	 * Delete all the records in the {@link MaintenanceProtocols} table that are
	 * associated with the {@link MaintenancePlan} entity. (This mean unlink all
	 * the maintenance activities associated with the plan)
	 * 
	 * @param plan
	 * @return
	 * @throws GHAEJBException
	 */
	public int deleteByMaintenancePlan(MaintenancePlan plan)
			throws GHAEJBException;

	/**
	 * @param plan
	 * @return a list of {@link MaintenanceProtocols} with the
	 *         (MaintenanceActivity, MaintenancePlan) pair
	 * @throws GHAEJBException
	 */
	public List<MaintenanceProtocols> findByMaintenancePlan(MaintenancePlan plan)
			throws GHAEJBException;

	/**
	 * Return Stadistic information about the protocol of the plan like:
	 * estimated duration (activities with subprotocols), estimated cost
	 * (activities with subprotocols), number of activities, number of
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
	 * @param activity
	 *            the activity that is going to be associated to the maintenance
	 *            plan
	 * @param plan
	 *            the plan that the activity is going to be associated
	 * @param ordinal
	 *            the order of the activity in the maintenance plan
	 * @return A {@link MaintenanceProtocols} entity with the associated
	 *         activity and plan
	 * @throws GHAEJBException
	 */
	public MaintenanceProtocols save(MaintenanceActivity activity,
			MaintenancePlan plan, int ordinal) throws GHAEJBException;
}
