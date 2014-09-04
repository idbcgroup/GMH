/**
 * 
 */
package org.fourgeeks.gha.ejb.glm;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.glm.ServicesResourceCategory;

/**
 * @author emiliot
 * 
 */

@Remote
public interface MaterialCategoryServiceRemote {
	/**
	 * @param code
	 * @throws GHAEJBException
	 */
	public void delete(String code) throws GHAEJBException;

	/**
	 * @param ServicesResourceCategory
	 * @return the list of MaterialCategories
	 * @throws GHAEJBException
	 */
	public List<ServicesResourceCategory> find(ServicesResourceCategory servicesResourceCategory)
			throws GHAEJBException;

	/**
	 * @param code
	 * @return the MaterialCategorys
	 * @throws GHAEJBException
	 */
	public ServicesResourceCategory find(String code) throws GHAEJBException;

	/**
	 * @return the list of MaterialCategories
	 * @throws GHAEJBException
	 */
	public List<ServicesResourceCategory> getAll() throws GHAEJBException;

	/**
	 * @param offset
	 * @param size
	 * @return the list of MaterialCategories
	 * @throws GHAEJBException
	 */
	public List<ServicesResourceCategory> getAll(int offset, int size)
			throws GHAEJBException;

	/**
	 * @param ServicesResourceCategory
	 * @return the saved ServicesResourceCategory
	 * @throws GHAEJBException
	 */
	public ServicesResourceCategory save(ServicesResourceCategory servicesResourceCategory)
			throws GHAEJBException;

	/**
	 * @param ServicesResourceCategory
	 * @return the updated ServicesResourceCategory
	 * @throws GHAEJBException
	 */
	public ServicesResourceCategory update(ServicesResourceCategory servicesResourceCategory)
			throws GHAEJBException;

}
