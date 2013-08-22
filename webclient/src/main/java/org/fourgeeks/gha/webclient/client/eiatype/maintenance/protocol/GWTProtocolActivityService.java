/**
 * 
 */
package org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenanceProtocol;
import org.fourgeeks.gha.domain.gmh.ProtocolActivity;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 *
 */

@RemoteServiceRelativePath("protocolActivityService")
public interface GWTProtocolActivityService {
	/**
	 * Delete a ProtocolActivity from database by Id
	 * 
	 * @throws EJBException
	 */
	public void delete(long Id) throws EJBException;

	/**
	 * @param eiaTypeMaintenanceProtocol
	 * @return a list with the activities associated with the protocol
	 * @throws EJBException
	 */
	public List<ProtocolActivity> findByEiaTypeMaintenanceProtocol(
			EiaTypeMaintenanceProtocol eiaTypeMaintenanceProtocol)
			throws EJBException;

	/**
	 * @param eiaTypeMaintenanceProtocol
	 * @param offset
	 * @param size
	 * @return a list with the activities associated with the protocol
	 * @throws EJBException
	 */
	public List<ProtocolActivity> findByEiaTypeMaintenanceProtocol(
			EiaTypeMaintenanceProtocol eiaTypeMaintenanceProtocol, int offset,
			int size) throws EJBException;

	/**
	 * @param Id
	 * @return the ProtocolActivity with this Id
	 * @throws EJBException
	 */
	public ProtocolActivity find(long Id) throws EJBException;

	/**
	 * @return the list with all ProtocolActivity Objects
	 * @throws EJBException
	 */
	public List<ProtocolActivity> getAll() throws EJBException;

	/**
	 * @param offset
	 * @param size
	 * @return List of ProtocolActivity beginning in offset up to size
	 * @throws EJBException
	 */
	public List<ProtocolActivity> getAll(int offset, int size)
			throws EJBException;

	/**
	 * @param ProtocolActivity
	 *            the ProtocolActivity to be saved on database
	 * @throws EJBException
	 * @return ProtocolActivity saved
	 */
	public ProtocolActivity save(ProtocolActivity protocolActivity)
			throws EJBException;

	/**
	 * @param ProtocolActivity
	 *            the ProtocolActivity to be updated
	 * @return ProtocolActivity updated
	 * @throws EJBException
	 */
	public ProtocolActivity update(ProtocolActivity protocolActivity)
			throws EJBException;
}
