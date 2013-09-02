/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.MaintenanceSubProtocol;

/**
 * @author emiliot
 *
 */

@Remote
public interface MaintenanceSubProtocolServiceRemote {
	/**
	 * Delete a MaintenanceSubProtocol from database by Id
	 * @throws EJBException
	 */
	public void delete(long Id) throws EJBException;

	/**
	 * @param maintenanceActivity
	 * @return a list with the MaintenanceSubProtocols of the protocolActivity
	 * @throws EJBException
	 */
	public List<MaintenanceSubProtocol> findByMaintenanceActivity(
			MaintenanceActivity maintenanceActivity)
			throws EJBException;

	/**
	 * @param Id
	 * @return the MaintenanceSubProtocol with this Id
	 * @throws EJBException
	 */
	public MaintenanceSubProtocol find(long Id) throws EJBException;

	/**
	 * @return the list with all MaintenanceSubProtocols Objects
	 * @throws EJBException
	 */
	public List<MaintenanceSubProtocol> getAll() throws EJBException;

	/**
	 * @param offset
	 * @param size
	 * @return List of MaintenanceSubProtocols beginning in offset up to size
	 * @throws EJBException
	 */
	public List<MaintenanceSubProtocol> getAll(int offset, int size)
			throws EJBException;

	/**
	 * @param MaintenanceSubProtocol
	 *            the MaintenanceSubProtocol to be saved on database
	 * @throws EJBException
	 * @return MaintenanceSubProtocol saved
	 */
	public MaintenanceSubProtocol save(MaintenanceSubProtocol maintenanceSubProtocol)
			throws EJBException;

	/**
	 * @param MaintenanceSubProtocol
	 *            the MaintenanceSubProtocol to be updated
	 * @return MaintenanceSubProtocol updated
	 * @throws EJBException
	 */
	public MaintenanceSubProtocol update(MaintenanceSubProtocol maintenanceSubProtocol)
			throws EJBException;
}
