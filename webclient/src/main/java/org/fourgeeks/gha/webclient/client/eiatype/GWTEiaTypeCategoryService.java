/**
 * 
 */
package org.fourgeeks.gha.webclient.client.eiatype;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.ServiceResourceCategory;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 * 
 */
@RemoteServiceRelativePath("eiaTypeCategory")
public interface GWTEiaTypeCategoryService extends RemoteService {
	/**
	 * @return all the categories for eiatype
	 * @throws GHAEJBException
	 */
	public List<ServiceResourceCategory> getAll() throws GHAEJBException;
}
