/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaTypeCategory;

/**
 * @author Emilio
 * 
 */
public interface EiaTypeCategoryServiceRemote {
	/**
	 * @return all the categories for eiatype
	 * @throws GHAEJBException
	 */
	public List<EiaTypeCategory> getAll() throws GHAEJBException;
}
