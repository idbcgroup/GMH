/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;

/**
 * @author emiliot
 *
 */

/**
 * @author emiliot
 * 
 */
@Remote
public interface EiaTypeServiceRemote {

	/**
	 * Delete an EiaType from database by Id
	 * 
	 * @return a boolean with the result of the operation
	 * @throws EJBException
	 * 
	 */
	public void delete(long Id) throws EJBException;

	/**
	 * @param eiaType
	 * @return a list with possible matches to the eiaType passed by param
	 * @throws EJBException
	 */
	public List<EiaType> find(EiaType eiaType) throws EJBException;

	/**
	 * @param eiaType
	 * @param offset
	 * @param size
	 * @return a list with possible matches to the eiaType passed by param
	 * @throws EJBException
	 */
	public List<EiaType> find(EiaType eiaType, int offset, int size)
			throws EJBException;

	/**
	 * @param Id
	 * @return the EiaType with this Id
	 * @throws EJBException
	 */
	public EiaType find(long Id) throws EJBException;

	/**
	 * @return the list with all EiaType objects
	 * @throws EJBException
	 */
	public List<EiaType> getAll() throws EJBException;

	/**
	 * @param offset
	 * @param size
	 * @return List of EiaType beginning in offset up to size
	 * @throws EJBException
	 */
	public List<EiaType> getAll(int offset, int size) throws EJBException;

	/**
	 * @param eiaType
	 *            Persist an EiaType to database
	 * @throws EJBException
	 * @return EiaType saved
	 */
	public EiaType save(EiaType eiaType) throws EJBException;

	/**
	 * @param EiaType
	 *            the EiaType to be updated
	 * @return EiaType updated
	 * @throws EJBException
	 */
	public EiaType update(EiaType eiaType) throws EJBException;

}
