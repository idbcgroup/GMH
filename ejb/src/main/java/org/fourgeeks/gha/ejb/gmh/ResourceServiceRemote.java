/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.ProtocolActivity;
import org.fourgeeks.gha.domain.gmh.Resource;

/**
 * @author emiliot
 *
 */
@Remote
public interface ResourceServiceRemote {
	/**
	 * Delete a Resource from database by Id
	 * @throws EJBException
	 */
	public void delete(long Id) throws EJBException;
	
	/**
	 * @param protocolActivity
	 * @return the list of Resource that used by the ProtocolActivity given
	 * @throws EJBException
	 */
	public List<Resource> findByProtocolActivity(ProtocolActivity protocolActivity) throws EJBException;

	/**
	 * @param Id
	 * @return the Resource with this Id
	 * @throws EJBException
	 */
	public Resource find(long Id) throws EJBException;

	/**
	 * @return the list with all Resource Objects
	 * @throws EJBException
	 */
	public List<Resource> getAll() throws EJBException;

	/**
	 * @param offset
	 * @param size
	 * @return List of Resource beginning in offset up to size
	 * @throws EJBException
	 */
	public List<Resource> getAll(int offset, int size)
			throws EJBException;

	/**
	 * @param Resource
	 *            the Resource to be saved on database
	 * @throws EJBException
	 * @return Resource saved
	 */
	public Resource save(Resource resource)
			throws EJBException;

	/**
	 * @param Resource
	 *            the Resource to be updated
	 * @return Resource updated
	 * @throws EJBException
	 */
	public Resource update(Resource resource)
			throws EJBException;
}
