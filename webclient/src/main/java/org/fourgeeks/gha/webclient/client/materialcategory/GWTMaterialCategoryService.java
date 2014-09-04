/**
 * 
 */
package org.fourgeeks.gha.webclient.client.materialcategory;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.glm.ServicesResourceCategory;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 * 
 */

@RemoteServiceRelativePath("materialCategory")
public interface GWTMaterialCategoryService extends RemoteService {
	/**
	 * @param Id
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
	 * @param Id
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
