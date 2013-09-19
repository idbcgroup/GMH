/**
 * 
 */
package org.fourgeeks.gha.ejb.mix;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.mix.SystemInstance;

/**
 * @author emiliot
 *
 */

@Remote
public interface SystemInstanceServiceRemote {
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
