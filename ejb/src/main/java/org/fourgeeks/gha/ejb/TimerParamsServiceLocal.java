package org.fourgeeks.gha.ejb;

import java.util.List;

import javax.ejb.Local;

import org.fourgeeks.gha.domain.TimerParams;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;

/**
 * @author naramirez
 */
@Local
public interface TimerParamsServiceLocal {
	/**
	 * @param entity
	 * @return update the entity
	 * @throws GHAEJBException
	 */
	public TimerParams update(TimerParams entity) throws GHAEJBException;

	/**
	 * @return the list of timer params
	 * @throws GHAEJBException
	 */
	public List<TimerParams> getAll() throws GHAEJBException;

	/**
	 * Save a entity
	 * 
	 * @param entity
	 * @return the saved entity
	 * @throws GHAEJBException
	 */
	public TimerParams save(TimerParams entity) throws GHAEJBException;

	/**
	 * Delete a entity
	 * 
	 * @param code
	 * @throws GHAEJBException
	 */
	void delete(String code) throws GHAEJBException;
}
