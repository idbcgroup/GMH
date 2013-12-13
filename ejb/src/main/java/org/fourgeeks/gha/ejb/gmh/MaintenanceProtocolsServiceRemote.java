package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocols;

/**
 * @author naramirez
 */
@Remote
public interface MaintenanceProtocolsServiceRemote {

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
	 * @throws GHAEJBException
	 */
	public void deleteByMaintenancePlan(MaintenancePlan plan)
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
