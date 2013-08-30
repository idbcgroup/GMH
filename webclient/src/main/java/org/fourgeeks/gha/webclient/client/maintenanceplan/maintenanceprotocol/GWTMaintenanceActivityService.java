/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceplan.maintenanceprotocol;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;
import org.fourgeeks.gha.domain.gmh.ServiceResource;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 *
 */

@RemoteServiceRelativePath("maintenanceActivityService")
public interface GWTMaintenanceActivityService extends RemoteService{
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
	 * @return the list of MaintenanceActivities that use the resource/service given
	 * @throws EJBException
	 */
	public List<MaintenanceActivity> findByServiceResource(ServiceResource serviceResource) throws EJBException;

	/**
	 * @param Id
	 * @return the MaintenanceActivity with this Id
	 * @throws EJBException
	 */
	public MaintenanceActivity find(long Id) throws EJBException;

	/**
	 * @return the list with all MaintenanceActivity Objects
	 * @throws EJBException
	 */
	public List<MaintenanceActivity> getAll() throws EJBException;

	/**
	 * @param offset
	 * @param size
	 * @return List of MaintenanceActivity beginning in offset up to size
	 * @throws EJBException
	 */
	public List<MaintenanceActivity> getAll(int offset, int size)
			throws EJBException;

	/**
	 * @param MaintenanceActivity
	 *            the MaintenanceActivity to be saved on database
	 * @throws EJBException
	 * @return MaintenanceActivity saved
	 */
	public MaintenanceActivity save(MaintenanceActivity maintenanceActivity)
			throws EJBException;

	/**
	 * @param MaintenanceActivity
	 *            the MaintenanceActivity to be updated
	 * @return MaintenanceActivity updated
	 * @throws EJBException
	 */
	public MaintenanceActivity update(MaintenanceActivity maintenanceActivity)
			throws EJBException;
}
