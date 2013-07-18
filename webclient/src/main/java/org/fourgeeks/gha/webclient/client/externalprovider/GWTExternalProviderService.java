/**
 * 
 */
package org.fourgeeks.gha.webclient.client.externalprovider;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.EJBException;
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
	 * @throws EJBException
	 */
	public List<ExternalProvider> getAll() throws EJBException;

	/**
	 * @param offset
	 * @param size
	 * @return a List with size ExternalProvider starting from offset
	 * @throws EJBException
	 */
	public List<ExternalProvider> getAll(int offset, int size)
			throws EJBException;
}
