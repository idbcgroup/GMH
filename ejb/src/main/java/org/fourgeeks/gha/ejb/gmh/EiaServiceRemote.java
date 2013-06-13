/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;

/**
 * @author emiliot
 *
 */

@Remote
public interface EiaServiceRemote {
	
	/**
	 * @param eia
	 * Persist an eia to database
	 */
	public void save(Eia eia);
	/**
	 * @param Id
	 * @return Find an eia By Id
	 */
	public Eia find(long Id);
	/**
	 * @param eia
	 * @return a List with eias using an eia as a filter
	 */
	public List<Eia> find(Eia eia);
	/**
	 * @param eiaType
	 * @return a List with eias searching by EiaType
	 */
	public List<Eia> find(EiaType eiaType);
	/**
	 * @return
	 * a List with all the eias
	 */
	public List<Eia> getAll();
	/**
	 * @param offset
	 * @param size
	 * @return a List with size eias starting from offset
	 */
	public List<Eia> getAll(int offset, int size);
	/**
	 * @param Id
	 * Delete an entity from database using its id
	 */
	public void delete(long Id);

}
