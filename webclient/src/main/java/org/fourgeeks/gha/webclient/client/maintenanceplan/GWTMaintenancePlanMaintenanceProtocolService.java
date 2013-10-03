/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceplan;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenancePlanMaintenanceProtocol;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 *
 */
@RemoteServiceRelativePath("maintenancePlanMaintenanceProtocolService")
public interface GWTMaintenancePlanMaintenanceProtocolService extends RemoteService {
	/**
	 * Delete a MaintenancePlanMaintenanceProtocol from database by Id
	 * @throws GHAEJBException
	 */
	public void delete(long Id) throws GHAEJBException;
	/**
	 * @param maintenancePlanMaintenanceProtocol
	 * @return the persistent entity
	 * @throws GHAEJBException
	 */
	public MaintenancePlanMaintenanceProtocol save(MaintenancePlanMaintenanceProtocol maintenancePlanMaintenanceProtocol) throws GHAEJBException;
	
	/**
	 * @param MaintenanceProtocol
	 * @return the MaintenancePlanMaintenanceProtocols associated with the protocol
	 * @throws GHAEJBException
	 */
	public List<MaintenancePlanMaintenanceProtocol> findByMaintenanceProtocol(MaintenanceProtocol maintenanceProtocol) throws GHAEJBException;
	/**
	 * @param maintenancePlan
	 * @return the MaintenancePlanMaintenanceProtocol associated with the plan
	 * @throws GHAEJBException
	 */
	public List<MaintenancePlanMaintenanceProtocol> findByMaintenancePlan(MaintenancePlan maintenancePlan) throws GHAEJBException;

}
