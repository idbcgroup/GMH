/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.ProtocolActivity;
import org.fourgeeks.gha.domain.gmh.RaS;

/**
 * @author emiliot
 *
 */
@Remote
public interface RaSServiceRemote {
	/**
	 * Delete a Resource/Service from database by Id
	 * @throws EJBException
	 */
	public void delete(long Id) throws EJBException;
	
	/**
	 * @param protocolActivity
	 * @return the list of Resource/Service that used by the ProtocolActivity given
	 * @throws EJBException
	 */
	public List<RaS> findByProtocolActivity(ProtocolActivity protocolActivity) throws EJBException;

	/**
	 * @param Id
	 * @return the Resource/Service with this Id
	 * @throws EJBException
	 */
	public RaS find(long Id) throws EJBException;

	/**
	 * @return the list with all Resource/Service Objects
	 * @throws EJBException
	 */
	public List<RaS> getAll() throws EJBException;

	/**
	 * @param offset
	 * @param size
	 * @return List of Resource/Service beginning in offset up to size
	 * @throws EJBException
	 */
	public List<RaS> getAll(int offset, int size)
			throws EJBException;

	/**
	 * @param RaS
	 *            the Resource/Service to be saved on database
	 * @throws EJBException
	 * @return Resource/Service saved
	 */
	public RaS save(RaS ras)
			throws EJBException;

	/**
	 * @param RaS
	 *            the Resource/Service to be updated
	 * @return Resource/Service updated
	 * @throws EJBException
	 */
	public RaS update(RaS ras)
			throws EJBException;
}
