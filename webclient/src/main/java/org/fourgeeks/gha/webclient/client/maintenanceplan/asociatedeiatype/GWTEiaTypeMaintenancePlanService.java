/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 *
 */

@RemoteServiceRelativePath("eiaTypeMaintenancePlanService")
public interface GWTEiaTypeMaintenancePlanService extends RemoteService{
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
	/**
	 * @param eiaType
	 * @return the eiatypemaintenance plan associated with the eiatype
	 * @throws GHAEJBException
	 */
	public List<EiaTypeMaintenancePlan> findByEiaType(EiaType eiaType) throws GHAEJBException;
	/**
	 * @param maintenancePlan
	 * @return the eiatypemaintenance plan associated with the plan
	 * @throws GHAEJBException
	 */
	public List<EiaTypeMaintenancePlan> findByMaintenancePlan(MaintenancePlan maintenancePlan) throws GHAEJBException;
}
