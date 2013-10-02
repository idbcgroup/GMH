/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;

/**
 * @author emiliot
 *
 */
@Remote
public interface EiaTypeMaintenancePlanServiceRemote {
	/**
	 * Delete a EiaTypeMaintenancePlan from database by Id
	 * @throws GHAEJBException
	 */
	public void delete(long Id) throws GHAEJBException;
	/**
	 * @param eiaTypeMaintenancePlan
	 * @return the persistent entity
	 * @throws GHAEJBException
	 */
	public EiaTypeMaintenancePlan save(EiaTypeMaintenancePlan eiaTypeMaintenancePlan) throws GHAEJBException;
}
