/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;

/**
 * @author emiliot
 * 
 */

@Remote
public interface EiaServiceRemote {

	/**
	 * @param entity
	 * @param cb
	 * @param root
	 * @return a predicate with the filters used to search by eia
	 */
	public Predicate buildFilters(Eia entity, CriteriaBuilder cb, Root<Eia> root);

	/**
	 * @param Id
	 * @return a boolean with the result of the operation
	 * @throws GHAEJBException
	 *             Delete an entity from database using its id
	 */
	public boolean delete(long Id) throws GHAEJBException;

	/**
	 * @param eia
	 * @return a List with eias using an eia as a filter
	 * @throws EJBExceptions
	 */
	public List<Eia> find(Eia eia) throws GHAEJBException;

	/**
	 * @param eiaType
	 * @return a List with eias searching by EiaType
	 * @throws GHAEJBException
	 *             Find all the eias BASED on the eiaType ID
	 */
	public List<Eia> findByEiaType(EiaType eiaType) throws GHAEJBException;

	/**
	 * @param Id
	 * @return Find an eia By Id
	 * @throws GHAEJBException
	 */
	public Eia find(long Id) throws GHAEJBException;

	/**
	 * @return a List with all the eias
	 * @throws GHAEJBException
	 */
	public List<Eia> getAll() throws GHAEJBException;

	/**
	 * @param offset
	 * @param size
	 * @return a List with size eias starting from offset
	 * @throws GHAEJBException
	 */
	public List<Eia> getAll(int offset, int size) throws GHAEJBException;

	/**
	 * @param eia
	 * @Return the persisted entity
	 * @throws GHAEJBException
	 *             Persist an eia to database
	 */
	public Eia save(Eia eia) throws GHAEJBException;

	/**
	 * @param eia
	 * @return the updated entity
	 * @throws GHAEJBException
	 *             Update the Eia
	 */
	public Eia update(Eia eia) throws GHAEJBException;

}
