package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.GlaLog;
import org.fourgeeks.gha.domain.gmh.EiaType;

@Remote
public interface GlaLogServiceRemote {
	/**
	 * @param Id
	 * @return a boolean with the result of the operation
	 * @throws GHAEJBException
	 *             Delete an entity from database using its id
	 */
	public boolean delete(long Id) throws GHAEJBException;

	/**
	 * @param eiaType
	 * @return a List with {@link GlaLog} searching by EiaType
	 * @throws GHAEJBException
	 *             Find all the eias BASED on the eiaType ID
	 */
	public List<GlaLog> findByEiaType(EiaType eiaType)
			throws GHAEJBException;

	/**
	 * @return a List with all the GlaLog
	 * @throws GHAEJBException
	 */
	public List<GlaLog> getAll() throws GHAEJBException;

	/**
	 * @param glaLog
	 * @return the persisted entity
	 * @throws GHAEJBException
	 *             Persist an GlaLog to database
	 */
	public GlaLog save(GlaLog glaLog)
			throws GHAEJBException;

	/**
	 * @param glaLog
	 * @return the updated entity
	 * @throws GHAEJBException
	 *             Update the GlaLog
	 */
	public GlaLog update(GlaLog glaLog)
			throws GHAEJBException;
}
