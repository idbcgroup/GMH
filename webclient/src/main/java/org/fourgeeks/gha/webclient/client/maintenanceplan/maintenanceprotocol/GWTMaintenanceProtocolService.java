/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceplan.maintenanceprotocol;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 * 
 */

@Deprecated
@RemoteServiceRelativePath("maintenanceProtocolService")
public interface GWTMaintenanceProtocolService extends RemoteService {
	/**
	 * Delete a Maintenance Protocol from database by Id
	 * 
	 * @throws GHAEJBException
	 */
	public void delete(long Id) throws GHAEJBException;

	/**
	 * @param MaintenancePlan
	 * @return a list with the maintenance protocols related to the maintenance
	 *         plan
	 * @throws GHAEJBException
	 */
	public List<MaintenanceProtocol> findByMaintenancePlan(
			MaintenancePlan maintenancePlan) throws GHAEJBException;

	/**
	 * @param maintenancePlan
	 * @param offset
	 * @param size
	 * @return a list with size maintenance protocols related to the maintenance
	 *         plan starting at offset
	 * @throws GHAEJBException
	 */
	public List<MaintenanceProtocol> findByMaintenancePlan(
			MaintenancePlan maintenancePlan, int offset, int size)
			throws GHAEJBException;

	/**
	 * @param maintenanceProtocol
	 * @return the list of Protocols that are alike the parameter
	 * @throws GHAEJBException
	 */
	public List<MaintenanceProtocol> find(
			MaintenanceProtocol maintenanceProtocol) throws GHAEJBException;

	/**
	 * @param code
	 * @return the EiaTypeMaintenanceProtocol with this Id
	 * @throws GHAEJBException
	 */
	public MaintenanceProtocol find(long Id) throws GHAEJBException;

	/**
	 * @return the list with all EiaTypeMaintenanceProtocol Objects
	 * @throws GHAEJBException
	 */
	public List<MaintenanceProtocol> getAll() throws GHAEJBException;

	/**
	 * @param offset
	 * @param size
	 * @return List of EiaTypeMaintenanceProtocol beginning in offset up to size
	 *         elements
	 * @throws GHAEJBException
	 */
	public List<MaintenanceProtocol> getAll(int offset, int size)
			throws GHAEJBException;

	/**
	 * @param MaintenanceProtocol
	 *            the plan to be saved on database
	 * @throws GHAEJBException
	 * @return EiaTypeMaintenanceProtocol saved
	 */
	public MaintenanceProtocol save(MaintenanceProtocol maintenanceProtocol)
			throws GHAEJBException;

	/**
	 * @param MaintenanceProtocol
	 *            the EiaTypeMaintenanceProtocol to be updated
	 * @return EiaTypeMaintenanceProtocol updated
	 * @throws GHAEJBException
	 */
	public MaintenanceProtocol update(MaintenanceProtocol maintenanceProtocol)
			throws GHAEJBException;
}
