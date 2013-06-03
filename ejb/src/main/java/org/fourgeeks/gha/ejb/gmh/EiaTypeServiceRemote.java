/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

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
	 * @param eiaType
	 * Persist an EiaType to database
	 */
	public void save (EiaType eiaType);
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
	public List <EiaType> find(EiaType eiaType, int offset, int size);
	/**
	 * @param EiaType the EiaType to be updated
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
	public List <EiaType> getAll();
	/**
	 * @param offset
	 * @param size
	 * @return List of EiaType beginning in offset up to size
	 */
	public List <EiaType> getAll(int offset, int size);

}
