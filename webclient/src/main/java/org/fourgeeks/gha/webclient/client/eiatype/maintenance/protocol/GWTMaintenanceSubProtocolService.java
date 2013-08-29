/**
 * 
 */
package org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.MaintenanceSubProtocol;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 *
 */

@RemoteServiceRelativePath("maintenanceSubProtocol")
public interface GWTMaintenanceSubProtocolService extends RemoteService {
	/**
	 * Delete a MaintenanceSubProtocol from database by Id
	 * @throws EJBException
	 */
	public void delete(long Id) throws EJBException;

	/**
	 * @param protocolActivity
	 * @return a list with the MaintenanceSubProtocols of the maintenanceActivity
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
