/**
 * 
 */
package org.fourgeeks.gha.webclient.client.obu;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Job;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author alacret
 * 
 */
@RemoteServiceRelativePath("job")
public interface GWTJobService extends RemoteService {
	/**
	 * @param Id
	 * @throws GHAEJBException
	 */
	public void delete(long Id) throws GHAEJBException;

	/**
	 * @param entity
	 * @return the list of Job
	 * @throws GHAEJBException
	 */
	public List<Job> find(Job entity) throws GHAEJBException;

	/**
	 * @param Id
	 * @return the Job
	 * @throws GHAEJBException
	 */
	public Job find(long Id) throws GHAEJBException;

	/**
	 * @return the list of jobs
	 * @throws GHAEJBException
	 */
	public List<Job> getAll() throws GHAEJBException;

	/**
	 * @param entity
	 * @return the saved Job
	 * @throws GHAEJBException
	 */
	public Job save(Job entity) throws GHAEJBException;

	/**
	 * @param entity
	 * @return the updated Job
	 * @throws GHAEJBException
	 */
	public Job update(Job entity) throws GHAEJBException;
}
