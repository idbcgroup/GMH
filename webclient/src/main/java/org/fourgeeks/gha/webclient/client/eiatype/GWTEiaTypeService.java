/**
 * 
 */
package org.fourgeeks.gha.webclient.client.eiatype;

import java.util.List;

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
	 *            Persist an EiaType to database
	 */
	public boolean save(EiaType eiaType);

	/**
	 * @param Id
	 * @return the EiaType with this Id
	 */
	public EiaType find(long Id);

	/**
	 * @param eiaType
	 * @return a list with possible matches to the eiaType passed by param
	 */
	public List<EiaType> find(EiaType eiaType);

	/**
	 * @param eiaType
	 * @param offset
	 * @param size
	 * @return a list with possible matches to the eiaType passed by param
	 */
	public List<EiaType> find(EiaType eiaType, int offset, int size);

	/**
	 * @param EiaType
	 *            the EiaType to be updated
	 */
	public void update(EiaType eiaType);

	/**
	 * Delete an EiaType from database by Id
	 * 
	 */
	public void delete(long Id);

	/**
	 * @return the list with all EiaType objects
	 */
	public List<EiaType> getAll();

	/**
	 * @param offset
	 * @param size
	 * @return List of EiaType beginning in offset up to size
	 */
	public List<EiaType> getAll(int offset, int size);
}
