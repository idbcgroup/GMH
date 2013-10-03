/**
 * 
 */
package org.fourgeeks.gha.webclient.client.externalprovider;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.glm.ExternalProvider;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author alacret
 * 
 */
@RemoteServiceRelativePath("externalProvider")
public interface GWTExternalProviderService extends RemoteService {
	/**
	 * @return a List with all the ExternalProvider
	 * @throws GHAEJBException
	 */
	public List<ExternalProvider> getAll() throws GHAEJBException;

	/**
	 * @param offset
	 * @param size
	 * @return a List with size ExternalProvider starting from offset
	 * @throws GHAEJBException
	 */
	public List<ExternalProvider> getAll(int offset, int size)
			throws GHAEJBException;
}
