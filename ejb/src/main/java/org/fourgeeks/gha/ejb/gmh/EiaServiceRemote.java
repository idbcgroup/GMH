/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.Eia;

/**
 * @author emiliot
 *
 */

@Remote
public interface EiaServiceRemote {
	
	/**
	 * @param equipment
	 * Persist an Equipment to database
	 */
	public void save(Eia equipment);
	/**
	 * @param Id
	 * @return Find an Equipment By Id
	 */
	public Eia find(long Id);
	/**
	 * @param equipment
	 * @return a List with Equipments using an Equipment as a filter
	 */
	public List<Eia> find(Eia equipment);
	/**
	 * @param eiaType
	 * @return a List with Equipments searching by EiaType
	 */
	public List<Eia> find(EiaType eiaType);
	/**
	 * @return
	 * a List with all the Equipments
	 */
	public List<Eia> getAll();
	/**
	 * @param offset
	 * @param size
	 * @return a List with size Equipments starting from offset
	 */
	public List<Eia> getAll(int offset, int size);
	/**
	 * @param Id
	 * Delete an entity from database using its id
	 */
	public void delete(long Id);

}
