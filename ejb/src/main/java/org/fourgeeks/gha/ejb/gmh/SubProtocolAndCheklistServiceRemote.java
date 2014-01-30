/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.Activity;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.SubProtocolAndChecklist;

/**
 * @author emiliot
 * 
 */

@Remote
public interface SubProtocolAndCheklistServiceRemote {
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
	 * @return a list with the MaintenanceSubProtocols of the protocolActivity
	 * @throws GHAEJBException
	 */
	public List<SubProtocolAndChecklist> findByParentActivity(
			Activity maintenanceActivity) throws GHAEJBException;

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
	 *            the SubProtocol to be saved on database
	 * @throws GHAEJBException
	 * @return MaintenanceSubProtocol saved
	 */
	public SubProtocolAndChecklist save(SubProtocolAndChecklist subProtocol)
			throws GHAEJBException;

	/**
	 * @param subProtocol
	 *            the SubProtocol to be updated
	 * @return MaintenanceSubProtocol updated
	 * @throws GHAEJBException
	 */
	public SubProtocolAndChecklist update(SubProtocolAndChecklist subProtocol)
			throws GHAEJBException;
}
