/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;
import org.fourgeeks.gha.domain.gmh.ProtocolActivity;
import org.fourgeeks.gha.domain.gmh.RaS;

/**
 * @author emiliot
 * 
 */

@Remote
public interface ProtocolActivityServiceRemote {
	/**
	 * Delete a ProtocolActivity from database by Id
	 * 
	 * @throws EJBException
	 */
	public void delete(long Id) throws EJBException;

	/**
	 * @param maintenanceProtocol
	 * @return a list with the activities associated with the protocol
	 * @throws EJBException
	 */
	public List<ProtocolActivity> findByMaintenanceProtocol(
			MaintenanceProtocol maintenanceProtocol)
			throws EJBException;

	/**
	 * @param maintenanceProtocol
	 * @param offset
	 * @param size
	 * @return a list with the activities associated with the protocol
	 * @throws EJBException
	 */
	public List<ProtocolActivity> findByMaintenanceProtocol(
			MaintenanceProtocol maintenanceProtocol, int offset,
			int size) throws EJBException;
	
	/**
	 * @param ras
	 * @return the list of ProtocolActivities that use the resource/service given
	 * @throws EJBException
	 */
	public List<ProtocolActivity> findByRaS(RaS ras) throws EJBException;

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
