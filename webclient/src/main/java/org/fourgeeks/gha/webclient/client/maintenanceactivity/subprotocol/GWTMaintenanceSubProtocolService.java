/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceactivity.subprotocol;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.SubProtocolAndChecklist;

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
	 * 
	 * @param Id
	 * 
	 * @throws GHAEJBException
	 */
	public void delete(long Id) throws GHAEJBException;

	/**
	 * @param maintenanceActivity
	 * @return a list with the MaintenanceSubProtocols of the
	 *         maintenanceActivity
	 * @throws GHAEJBException
	 */
	public List<SubProtocolAndChecklist> findByMaintenanceActivity(
			MaintenanceActivity maintenanceActivity) throws GHAEJBException;

	/**
	 * @param Id
	 * @return the MaintenanceSubProtocol with this Id
	 * @throws GHAEJBException
	 */
	public SubProtocolAndChecklist find(long Id) throws GHAEJBException;

	/**
	 * @return the list with all MaintenanceSubProtocols Objects
	 * @throws GHAEJBException
	 */
	public List<SubProtocolAndChecklist> getAll() throws GHAEJBException;

	/**
	 * @param offset
	 * @param size
	 * @return List of MaintenanceSubProtocols beginning in offset up to size
	 * @throws GHAEJBException
	 */
	public List<SubProtocolAndChecklist> getAll(int offset, int size)
			throws GHAEJBException;

	/**
	 * @param subProtocol
	 *            the MaintenanceSubProtocol to be saved on database
	 * @throws GHAEJBException
	 * @return MaintenanceSubProtocol saved
	 */
	public SubProtocolAndChecklist save(SubProtocolAndChecklist subProtocol)
			throws GHAEJBException;

	/**
	 * @param subProtocol
	 *            the MaintenanceSubProtocol to be updated
	 * @return MaintenanceSubProtocol updated
	 * @throws GHAEJBException
	 */
	public SubProtocolAndChecklist update(SubProtocolAndChecklist subProtocol)
			throws GHAEJBException;
}
