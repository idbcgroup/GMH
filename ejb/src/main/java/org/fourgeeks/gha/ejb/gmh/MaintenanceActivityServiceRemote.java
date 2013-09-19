/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;
import org.fourgeeks.gha.domain.gmh.ServiceResource;

/**
 * @author emiliot
 * 
 */

@Remote
public interface MaintenanceActivityServiceRemote {
	/**
	 * Delete a MaintenanceActivity from database by Id
	 * 
	 * @throws GHAEJBException
	 */
	public void delete(long Id) throws GHAEJBException;

	/**
	 * @param maintenanceProtocol
	 * @return a list with the activities associated with the protocol
	 * @throws GHAEJBException
	 */
	public List<MaintenanceActivity> findByMaintenanceProtocol(
			MaintenanceProtocol maintenanceProtocol)
			throws GHAEJBException;

	/**
	 * @param maintenanceProtocol
	 * @param offset
	 * @param size
	 * @return a list with the activities associated with the protocol
	 * @throws GHAEJBException
	 */
	public List<MaintenanceActivity> findByMaintenanceProtocol(
			MaintenanceProtocol maintenanceProtocol, int offset,
			int size) throws GHAEJBException;
	
	/**
	 * @param serviceResource
	 * @return the list of maintenanceActivities that use the resource/service given
	 * @throws GHAEJBException
	 */
	public List<MaintenanceActivity> findByServiceResource(ServiceResource serviceResource) throws GHAEJBException;

	/**
	 * @param Id
	 * @return the maintenanceActivity with this Id
	 * @throws GHAEJBException
	 */
	public MaintenanceActivity find(long Id) throws GHAEJBException;

	/**
	 * @return the list with all maintenanceActivity Objects
	 * @throws GHAEJBException
	 */
	public List<MaintenanceActivity> getAll() throws GHAEJBException;

	/**
	 * @param offset
	 * @param size
	 * @return List of maintenanceActivity beginning in offset up to size
	 * @throws GHAEJBException
	 */
	public List<MaintenanceActivity> getAll(int offset, int size)
			throws GHAEJBException;

	/**
	 * @param MaintenanceActivity
	 *            the maintenanceActivity to be saved on database
	 * @throws GHAEJBException
	 * @return maintenanceActivity saved
	 */
	public MaintenanceActivity save(MaintenanceActivity maintenanceActivity)
			throws GHAEJBException;

	/**
	 * @param MaintenanceActivity
	 *            the maintenanceActivity to be updated
	 * @return maintenanceActivity updated
	 * @throws GHAEJBException
	 */
	public MaintenanceActivity update(MaintenanceActivity maintenanceActivity)
			throws GHAEJBException;
}
