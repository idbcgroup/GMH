/**
 * 
 */
package org.fourgeeks.gha.webclient.client.eiatype;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaTypeCategory;

import com.google.gwt.user.client.rpc.RemoteService;

/**
 * @author emiliot
 * 
 */
public interface GWTEiaTypeCategoryService extends RemoteService {
	/**
	 * @return all the categories for eiatype
	 * @throws GHAEJBException
	 */
	public List<EiaTypeCategory> getAll() throws GHAEJBException;
}
