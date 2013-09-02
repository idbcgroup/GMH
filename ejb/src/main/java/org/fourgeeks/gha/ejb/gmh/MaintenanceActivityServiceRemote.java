/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.EJBException;
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
	 * @throws EJBException
	 */
	public void delete(long Id) throws EJBException;

	/**
	 * @param maintenanceProtocol
	 * @return a list with the activities associated with the protocol
	 * @throws EJBException
	 */
	public List<MaintenanceActivity> findByMaintenanceProtocol(
			MaintenanceProtocol maintenanceProtocol)
			throws EJBException;

	/**
	 * @param maintenanceProtocol
	 * @param offset
	 * @param size
	 * @return a list with the activities associated with the protocol
	 * @throws EJBException
	 */
	public List<MaintenanceActivity> findByMaintenanceProtocol(
			MaintenanceProtocol maintenanceProtocol, int offset,
			int size) throws EJBException;
	
	/**
	 * @param serviceResource
	 * @return the list of maintenanceActivities that use the resource/service given
	 * @throws EJBException
	 */
	public List<MaintenanceActivity> findByServiceResource(ServiceResource serviceResource) throws EJBException;

	/**
	 * @param Id
	 * @return the maintenanceActivity with this Id
	 * @throws EJBException
	 */
	public MaintenanceActivity find(long Id) throws EJBException;

	/**
	 * @return the list with all maintenanceActivity Objects
	 * @throws EJBException
	 */
	public List<MaintenanceActivity> getAll() throws EJBException;

	/**
	 * @param offset
	 * @param size
	 * @return List of maintenanceActivity beginning in offset up to size
	 * @throws EJBException
	 */
	public List<MaintenanceActivity> getAll(int offset, int size)
			throws EJBException;

	/**
	 * @param MaintenanceActivity
	 *            the maintenanceActivity to be saved on database
	 * @throws EJBException
	 * @return maintenanceActivity saved
	 */
	public MaintenanceActivity save(MaintenanceActivity maintenanceActivity)
			throws EJBException;

	/**
	 * @param MaintenanceActivity
	 *            the maintenanceActivity to be updated
	 * @return maintenanceActivity updated
	 * @throws EJBException
	 */
	public MaintenanceActivity update(MaintenanceActivity maintenanceActivity)
			throws EJBException;
}
