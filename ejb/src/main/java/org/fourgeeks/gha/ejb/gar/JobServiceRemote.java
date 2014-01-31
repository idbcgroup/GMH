/**
 * 
 */
package org.fourgeeks.gha.ejb.gar;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Job;

/**
 * @author alacret
 * 
 */
@Remote
public interface JobServiceRemote {

	/**
	 * @param Id
	 * @throws GHAEJBException
	 */
	public void delete(long Id) throws GHAEJBException;

	/**
	 * @param entity
	 * @return the list of Obu
	 * @throws GHAEJBException
	 */
	public List<Job> find(Job entity) throws GHAEJBException;

	/**
	 * @param Id
	 * @return the Obu
	 * @throws GHAEJBException
	 */
	public Job find(long Id) throws GHAEJBException;

	/**
	 * @return the list of obus
	 * @throws GHAEJBException
	 */
	public List<Job> getAll() throws GHAEJBException;

	/**
	 * @param entity
	 * @return the saved Obu
	 * @throws GHAEJBException
	 */
	public Job save(Job entity) throws GHAEJBException;

	/**
	 * @param entity
	 * @return the updated Obu
	 * @throws GHAEJBException
	 */
	public Job update(Job entity) throws GHAEJBException;
}
