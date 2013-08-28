/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceplan.maintenanceprotocol;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 *
 */

@RemoteServiceRelativePath("eiaTypeMaintenanceProtocolService")
public interface GWTEiaTypeMaintenanceProtocolService extends RemoteService{
	/**
	 * Delete a Maintenance Protocol from database by Id
	 * 
	 * @throws EJBException
	 */
	public void delete(long Id) throws EJBException;

	/**
	 * @param MaintenancePlan
	 * @return a list with the maintenance protocols related to the maintenance
	 *         plan
	 * @throws EJBException
	 */
	public List<MaintenanceProtocol> findByEiaTypeMaintenancePlan(
			MaintenancePlan maintenancePlan) throws EJBException;

	/**
	 * @param maintenancePlan
	 * @param offset
	 * @param size
	 * @returna list with size maintenance protocols related to the maintenance
	 *         plan starting at offset
	 * @throws EJBException
	 */
	public List<MaintenanceProtocol> findByEiaTypeMaintenancePlan(
			MaintenancePlan maintenancePlan, int offset, int size)
			throws EJBException;

	/**
	 * @param code
	 * @return the EiaTypeMaintenanceProtocol with this Id
	 * @throws EJBException
	 */
	public MaintenanceProtocol find(long Id) throws EJBException;

	/**
	 * @return the list with all EiaTypeMaintenanceProtocol Objects
	 * @throws EJBException
	 */
	public List<MaintenanceProtocol> getAll() throws EJBException;

	/**
	 * @param offset
	 * @param size
	 * @return List of EiaTypeMaintenanceProtocol beginning in offset up to size elements
	 * @throws EJBException
	 */
	public List<MaintenanceProtocol> getAll(int offset, int size)
			throws EJBException;

	/**
	 * @param MaintenanceProtocol
	 *            the plan to be saved on database
	 * @throws EJBException
	 * @return EiaTypeMaintenanceProtocol saved
	 */
	public MaintenanceProtocol save(MaintenanceProtocol maintenanceProtocol)
			throws EJBException;

	/**
	 * @param MaintenanceProtocol
	 *            the EiaTypeMaintenanceProtocol to be updated
	 * @return EiaTypeMaintenanceProtocol updated
	 * @throws EJBException
	 */
	public MaintenanceProtocol update(MaintenanceProtocol maintenanceProtocol)
			throws EJBException;
}
