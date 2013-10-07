/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceplan.maintenanceprotocol;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
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
	 * @return the list of MaintenanceActivities that use the resource/service given
	 * @throws GHAEJBException
	 */
	public List<MaintenanceActivity> findByServiceResource(ServiceResource serviceResource) throws GHAEJBException;

	/**
	 * @param Id
	 * @return the MaintenanceActivity with this Id
	 * @throws GHAEJBException
	 */
	public MaintenanceActivity find(long Id) throws GHAEJBException;
	/**
	 * @param maintenanceActivity
	 * @return a list with the maintenance activities that are like the parameter
	 * @throws GHAEJBException
	 */
	public List<MaintenanceActivity> find(MaintenanceActivity maintenanceActivity) throws GHAEJBException;

	/**
	 * @return the list with all MaintenanceActivity Objects
	 * @throws GHAEJBException
	 */
	public List<MaintenanceActivity> getAll() throws GHAEJBException;

	/**
	 * @param offset
	 * @param size
	 * @return List of MaintenanceActivity beginning in offset up to size
	 * @throws GHAEJBException
	 */
	public List<MaintenanceActivity> getAll(int offset, int size)
			throws GHAEJBException;

	/**
	 * @param MaintenanceActivity
	 *            the MaintenanceActivity to be saved on database
	 * @throws GHAEJBException
	 * @return MaintenanceActivity saved
	 */
	public MaintenanceActivity save(MaintenanceActivity maintenanceActivity)
			throws GHAEJBException;

	/**
	 * @param MaintenanceActivity
	 *            the MaintenanceActivity to be updated
	 * @return MaintenanceActivity updated
	 * @throws GHAEJBException
	 */
	public MaintenanceActivity update(MaintenanceActivity maintenanceActivity)
			throws GHAEJBException;
}
