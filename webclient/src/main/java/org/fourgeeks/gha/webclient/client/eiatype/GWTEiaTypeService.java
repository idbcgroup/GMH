/**
 * 
 */
package org.fourgeeks.gha.webclient.client.eiatype;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.EJBException;
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
	 * @return long the saved entity id
	 * @throws EJBException
	 */
	public long save(EiaType eiaType) throws EJBException;

	/**
	 * @param Id
	 * @return the EiaType with this Id
	 * @throws EJBException
	 */
	public EiaType find(long Id) throws EJBException;

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
	 * @param EiaType the EiaType to be updated
	 * @throws EJBException
	 */
	public boolean update(EiaType eiaType) throws EJBException;

	/**
	 * Delete an EiaType from database by Id
	 * @throws EJBException
	 */
	public void delete(long Id) throws EJBException;

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
}
