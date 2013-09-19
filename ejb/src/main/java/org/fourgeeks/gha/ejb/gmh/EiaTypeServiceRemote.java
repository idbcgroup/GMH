/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
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
	 * @throws GHAEJBException
	 */
	public void delete(long Id) throws GHAEJBException;

	/**
	 * @param eiaType
	 * @return a list with possible matches to the eiaType passed by param
	 * @throws GHAEJBException
	 */
	public List<EiaType> find(EiaType eiaType) throws GHAEJBException;

	/**
	 * @param eiaType
	 * @param offset
	 * @param size
	 * @return a list with possible matches to the eiaType passed by param
	 * @throws GHAEJBException
	 */
	public List<EiaType> find(EiaType eiaType, int offset, int size)
			throws GHAEJBException;

	/**
	 * @param code
	 * @return the EiaType with this Id
	 * @throws GHAEJBException
	 */
	public EiaType find(String code) throws GHAEJBException;

	/**
	 * @return the list with all EiaType objects
	 * @throws GHAEJBException
	 */
	public List<EiaType> getAll() throws GHAEJBException;

	/**
	 * @param offset
	 * @param size
	 * @return List of EiaType beginning in offset up to size
	 * @throws GHAEJBException
	 */
	public List<EiaType> getAll(int offset, int size) throws GHAEJBException;

	/**
	 * @param eiaType
	 *            Persist an EiaType to database
	 * @throws GHAEJBException
	 * @return EiaType saved
	 */
	public EiaType save(EiaType eiaType) throws GHAEJBException;

	/**
	 * @param EiaType
	 *            the EiaType to be updated
	 * @return EiaType updated
	 * @throws GHAEJBException
	 */
	public EiaType update(EiaType eiaType) throws GHAEJBException;

}
