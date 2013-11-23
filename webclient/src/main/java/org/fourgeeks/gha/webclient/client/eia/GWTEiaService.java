/**
 * 
 */
package org.fourgeeks.gha.webclient.client.eia;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 * 
 */
@RemoteServiceRelativePath("eia")
public interface GWTEiaService extends RemoteService {
	/**
	 * @return the count of eias by type, the list is filled in the same order
	 *         as the EiaStateEnum
	 * @throws GHAEJBException
	 */
	public List<Long> countByState() throws GHAEJBException;

	/**
	 * @param Id
	 * @return a boolean with the result of the operation
	 * @throws GHAEJBException
	 */
	public boolean delete(long Id) throws GHAEJBException;

	/**
	 * 
	 * @param eias
	 * @throws GHAEJBException
	 */
	public void delete(List<Eia> eias) throws GHAEJBException;

	/**
	 * @param eia
	 * @return a List with Eia using an Eia as a filter
	 * @throws GHAEJBException
	 */
	public List<Eia> find(Eia eia) throws GHAEJBException;

	/**
	 * @param eia
	 * @param eiaType
	 * @return a List with Eia that can be components of this respective
	 *         eiatype,using an Eia as a filter
	 * @throws GHAEJBException
	 */
	public List<Eia> findComponents(Eia eia, EiaType eiaType)
			throws GHAEJBException;

	/**
	 * @param eiaType
	 * @return a List with Eia searching by EiaType
	 * @throws GHAEJBException
	 */
	public List<Eia> findByEiaType(EiaType eiaType) throws GHAEJBException;

	/**
	 * @param eiaType
	 * @return a list with eias that have the state DAMAGED or MAINTENANCE for
	 *         the given eiaType
	 * @throws GHAEJBException
	 */
	public List<Eia> findDamagedAndInMaintenance(EiaType eiaType)
			throws GHAEJBException;

	/**
	 * @param Id
	 * @return Find an Eia By Id
	 * @throws GHAEJBException
	 */
	public Eia find(long Id) throws GHAEJBException;

	/**
	 * @return a List with all the Eia
	 * @throws GHAEJBException
	 */
	public List<Eia> getAll() throws GHAEJBException;

	/**
	 * @param offset
	 * @param size
	 * @return a List with size Eia starting from offset
	 * @throws GHAEJBException
	 */
	public List<Eia> getAll(int offset, int size) throws GHAEJBException;

	/**
	 * @param eia
	 *            Persist an Eia to database
	 * @return the persisted entity
	 * @throws GHAEJBException
	 */
	public Eia save(Eia eia) throws GHAEJBException;

	/**
	 * @param eia
	 * @param Eia
	 *            eia
	 * @return a boolean with the result of the operation
	 * @throws GHAEJBException
	 * 
	 */
	public Eia update(Eia eia) throws GHAEJBException;
}
