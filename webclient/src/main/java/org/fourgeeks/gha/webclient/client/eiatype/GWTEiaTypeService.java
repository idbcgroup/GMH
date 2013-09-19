/**
 * 
 */
package org.fourgeeks.gha.webclient.client.eiatype;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 * 
 */
@RemoteServiceRelativePath("eiaType")
public interface GWTEiaTypeService extends RemoteService {

	/**
	 * @param eiaType
	 * @return EiaType saved
	 * @throws GHAEJBException
	 */
	public EiaType save(EiaType eiaType) throws GHAEJBException;

	/**
	 * @param code
	 * @return the EiaType with this Id
	 * @throws GHAEJBException
	 */
	public EiaType find(String code) throws GHAEJBException;

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
	 * @param EiaType
	 *            the EiaType to be updated
	 * @return a EiaType updated
	 * @throws GHAEJBException
	 */
	public EiaType update(EiaType eiaType) throws GHAEJBException;

	/**
	 * Delete an EiaType from database by Id
	 * @throws GHAEJBException
	 */
	public void delete(long Id) throws GHAEJBException;

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
}
