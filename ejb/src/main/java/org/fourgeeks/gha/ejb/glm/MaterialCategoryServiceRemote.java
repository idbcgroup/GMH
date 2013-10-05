/**
 * 
 */
package org.fourgeeks.gha.ejb.glm;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.glm.MaterialCategory;

/**
 * @author emiliot
 *
 */

@Remote
public interface MaterialCategoryServiceRemote {
	/**
	 * @param Id
	 * @throws GHAEJBException
	 */
	public void delete(long Id) throws GHAEJBException;

	/**
	 * @param MaterialCategory
	 * @return the list of MaterialCategories
	 * @throws GHAEJBException
	 */
	public List<MaterialCategory> find(MaterialCategory materialCategory) throws GHAEJBException;

	/**
	 * @param Id
	 * @return the MaterialCategorys
	 * @throws GHAEJBException
	 */
	public MaterialCategory find(long Id) throws GHAEJBException;
	

	/**
	 * @return the list of MaterialCategories
	 * @throws GHAEJBException
	 */
	public List<MaterialCategory> getAll() throws GHAEJBException;

	/**
	 * @param offset
	 * @param size
	 * @return the list of MaterialCategories
	 * @throws GHAEJBException
	 */
	public List<MaterialCategory> getAll(int offset, int size) throws GHAEJBException;

	/**
	 * @param MaterialCategory
	 * @return the saved MaterialCategory
	 * @throws GHAEJBException
	 */
	public MaterialCategory save(MaterialCategory materialCategory) throws GHAEJBException;

	/**
	 * @param MaterialCategory
	 * @return the updated MaterialCategory
	 * @throws GHAEJBException
	 */
	public MaterialCategory update(MaterialCategory materialCategory) throws GHAEJBException;

}
