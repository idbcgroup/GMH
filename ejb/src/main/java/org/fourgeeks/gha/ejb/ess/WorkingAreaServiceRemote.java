/**
 * 
 */
package org.fourgeeks.gha.ejb.ess;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.ess.WorkingArea;
import org.fourgeeks.gha.domain.exceptions.EJBException;

/**
 * @author emiliot
 *
 */

@Remote
public interface WorkingAreaServiceRemote {
	/**
	 * @param Id
	 * @throws EJBException
	 */
	public void delete(long Id) throws EJBException;

	/**
	 * @param entity
	 * @return the list of WorkingAreas
	 * @throws EJBException
	 */
	public List<WorkingArea> find(WorkingArea entity) throws EJBException;

	/**
	 * @param Id
	 * @return the WorkingArea
	 * @throws EJBException
	 */
	public WorkingArea find(long Id) throws EJBException;

	/**
	 * @return the list of WorkingAreas
	 * @throws EJBException
	 */
	public List<WorkingArea> getAll() throws EJBException;

	/**
	 * @param entity
	 * @return the saved WorkingArea
	 * @throws EJBException
	 */
	public WorkingArea save(WorkingArea entity) throws EJBException;

	/**
	 * @param entity
	 * @return the updated WorkingArea
	 * @throws EJBException
	 */
	public WorkingArea update(WorkingArea entity) throws EJBException;
}
