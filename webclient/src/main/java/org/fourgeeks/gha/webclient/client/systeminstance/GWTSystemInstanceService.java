/**
 * 
 */
package org.fourgeeks.gha.webclient.client.systeminstance;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.mix.SystemInstance;

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
	 * @throws EJBException
	 */
	public void delete(long Id) throws EJBException;

	/**
	 * @param systemInstance
	 * @return the list of systemInstances
	 * @throws EJBException
	 */
	public List<SystemInstance> find(SystemInstance systemInstance) throws EJBException;

	/**
	 * @param Id
	 * @return the systemInstance
	 * @throws EJBException
	 */
	public SystemInstance find(long Id) throws EJBException;
	
	/**
	 * @return the list of systemInstances
	 * @throws EJBException
	 */
	public List<SystemInstance> getAll() throws EJBException;

	/**
	 * @param systemInstance
	 * @return the saved systemInstance
	 * @throws EJBException
	 */
	public SystemInstance save(SystemInstance systemInstance) throws EJBException;

	/**
	 * @param systemInstance
	 * @return the updated systemInstance
	 * @throws EJBException
	 */
	public SystemInstance update(SystemInstance systemInstance) throws EJBException;
}
