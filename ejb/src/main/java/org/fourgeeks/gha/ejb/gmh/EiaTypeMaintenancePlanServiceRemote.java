/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;

/**
 * @author emiliot
 * 
 */
@Remote
public interface EiaTypeMaintenancePlanServiceRemote {
	/**
	 * Delete a EiaTypeMaintenancePlan from database by Id
	 * 
	 * @throws GHAEJBException
	 */
	public void delete(long Id) throws GHAEJBException;

	public void delete(List<EiaTypeMaintenancePlan> eiatypemaintenanceplan)
			throws GHAEJBException;

	/**
	 * @param eiaTypeMaintenancePlan
	 * @return the persistent entity
	 * @throws GHAEJBException
	 */
	public EiaTypeMaintenancePlan save(
			EiaTypeMaintenancePlan eiaTypeMaintenancePlan)
			throws GHAEJBException;

	/**
	 * @param eiaType
	 * @return the eiatypemaintenance plan associated with the eiatype
	 * @throws GHAEJBException
	 */
	public List<EiaTypeMaintenancePlan> findByEiaType(EiaType eiaType)
			throws GHAEJBException;

	/**
	 * @param maintenancePlan
	 * @return the eiatypemaintenance plan associated with the plan
	 * @throws GHAEJBException
	 */
	public List<EiaTypeMaintenancePlan> findByMaintenancePlan(
			MaintenancePlan maintenancePlan) throws GHAEJBException;
}
