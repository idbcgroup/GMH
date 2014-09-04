/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.ServiceResourceCategory;

/**
 * @author Emilio
 * 
 */
@Remote
public interface EiaTypeCategoryServiceRemote {
	/**
	 * Delete and EiatypeCategory
	 * 
	 * @param entity
	 * @throws GHAEJBException
	 */
	public void delete(ServiceResourceCategory entity) throws GHAEJBException;

	/**
	 * @return all the categories for eiatype
	 * @throws GHAEJBException
	 */
	public List<ServiceResourceCategory> getAll() throws GHAEJBException;

	/**
	 * @param serviceResourceCategory
	 * @return the eiatypecategory saved
	 * @throws GHAEJBException
	 * 
	 */
	public ServiceResourceCategory save(ServiceResourceCategory serviceResourceCategory)
			throws GHAEJBException;
}
