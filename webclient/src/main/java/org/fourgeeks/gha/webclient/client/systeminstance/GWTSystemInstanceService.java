/**
 * 
 */
package org.fourgeeks.gha.webclient.client.systeminstance;

import java.util.List;

import org.fourgeeks.gha.domain.ess.auth.SystemInstance;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 *
 */
@RemoteServiceRelativePath("systemInstance")
public interface GWTSystemInstanceService extends RemoteService{
	/**
	 * @param Id
	 * @throws GHAEJBException
	 */
	public void delete(long Id) throws GHAEJBException;

	/**
	 * @param systemInstance
	 * @return the list of systemInstances
	 * @throws GHAEJBException
	 */
	public List<SystemInstance> find(SystemInstance systemInstance) throws GHAEJBException;

	/**
	 * @param Id
	 * @return the systemInstance
	 * @throws GHAEJBException
	 */
	public SystemInstance find(long Id) throws GHAEJBException;
	
	/**
	 * @return the list of systemInstances
	 * @throws GHAEJBException
	 */
	public List<SystemInstance> getAll() throws GHAEJBException;

	/**
	 * @param systemInstance
	 * @return the saved systemInstance
	 * @throws GHAEJBException
	 */
	public SystemInstance save(SystemInstance systemInstance) throws GHAEJBException;

	/**
	 * @param systemInstance
	 * @return the updated systemInstance
	 * @throws GHAEJBException
	 */
	public SystemInstance update(SystemInstance systemInstance) throws GHAEJBException;
}
