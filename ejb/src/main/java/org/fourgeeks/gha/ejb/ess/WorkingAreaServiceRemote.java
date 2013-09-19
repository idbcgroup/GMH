/**
 * 
 */
package org.fourgeeks.gha.ejb.ess;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.ess.WorkingArea;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;

/**
 * @author emiliot
 *
 */

@Remote
public interface WorkingAreaServiceRemote {
	/**
	 * @param Id
	 * @throws GHAEJBException
	 */
	public void delete(long Id) throws GHAEJBException;

	/**
	 * @param entity
	 * @return the list of WorkingAreas
	 * @throws GHAEJBException
	 */
	public List<WorkingArea> find(WorkingArea entity) throws GHAEJBException;

	/**
	 * @param Id
	 * @return the WorkingArea
	 * @throws GHAEJBException
	 */
	public WorkingArea find(long Id) throws GHAEJBException;

	/**
	 * @return the list of WorkingAreas
	 * @throws GHAEJBException
	 */
	public List<WorkingArea> getAll() throws GHAEJBException;

	/**
	 * @param entity
	 * @return the saved WorkingArea
	 * @throws GHAEJBException
	 */
	public WorkingArea save(WorkingArea entity) throws GHAEJBException;

	/**
	 * @param entity
	 * @return the updated WorkingArea
	 * @throws GHAEJBException
	 */
	public WorkingArea update(WorkingArea entity) throws GHAEJBException;
}
