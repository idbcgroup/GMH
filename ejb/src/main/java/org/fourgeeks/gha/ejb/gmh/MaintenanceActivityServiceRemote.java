/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;

/**
 * @author emiliot
 * 
 */

@Remote
public interface MaintenanceActivityServiceRemote {
	/**
	 * Delete a MaintenanceActivity from database by Id
	 * 
	 * @param Id
	 * 
	 * @throws GHAEJBException
	 */
	public void delete(long Id) throws GHAEJBException;

	/**
	 * @param Id
	 * @return the maintenanceActivity with this Id
	 * @throws GHAEJBException
	 */
	public MaintenanceActivity find(long Id) throws GHAEJBException;

	/**
	 * @param maintenanceActivity
	 * @return a list with the maintenance activities that are like the
	 *         parameter
	 * @throws GHAEJBException
	 */
	public List<MaintenanceActivity> find(
			MaintenanceActivity maintenanceActivity) throws GHAEJBException;

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
	 * @param maintenanceActivity
	 *            the maintenanceActivity to be saved on database
	 * @throws GHAEJBException
	 * @return maintenanceActivity saved
	 */
	public MaintenanceActivity save(MaintenanceActivity maintenanceActivity)
			throws GHAEJBException;

	/**
	 * @param maintenanceActivity
	 *            the maintenanceActivity to be updated
	 * @return maintenanceActivity updated
	 * @throws GHAEJBException
	 */
	public MaintenanceActivity update(MaintenanceActivity maintenanceActivity)
			throws GHAEJBException;
}
