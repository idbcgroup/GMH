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

@Remote
public interface EiaTypeService {
	
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
	 * @return the EiaType by the param
	 */
	public EiaType find(EiaType eiaType);
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
