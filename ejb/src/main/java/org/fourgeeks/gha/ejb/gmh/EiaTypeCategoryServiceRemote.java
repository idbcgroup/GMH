/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaTypeCategory;

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
	public void delete(EiaTypeCategory entity) throws GHAEJBException;

	/**
	 * @return all the categories for eiatype
	 * @throws GHAEJBException
	 */
	public List<EiaTypeCategory> getAll() throws GHAEJBException;

	/**
	 * @param eiaTypeCategory
	 * @return the eiatypecategory saved
	 * @throws GHAEJBException
	 * 
	 */
	public EiaTypeCategory save(EiaTypeCategory eiaTypeCategory)
			throws GHAEJBException;
}
