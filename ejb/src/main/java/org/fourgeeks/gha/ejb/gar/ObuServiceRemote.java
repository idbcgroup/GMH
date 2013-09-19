/**
 * 
 */
package org.fourgeeks.gha.ejb.gar;

import java.util.List;

import javax.ejb.Remote;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Obu;

/**
 * @author alacret
 * 
 */
@Remote
public interface ObuServiceRemote {
	
	/**
	 * @param entity
	 * @param cb
	 * @param root
	 * @return
	 */
	public Predicate buildFilters(Obu entity, CriteriaBuilder cb,
			Root<Obu> root);
	
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
	public List<Obu> find(Obu entity) throws GHAEJBException;

	/**
	 * @param Id
	 * @return the Obu
	 * @throws GHAEJBException
	 */
	public Obu find(long Id) throws GHAEJBException;

	/**
	 * @return the list of obus
	 * @throws GHAEJBException
	 */
	public List<Obu> getAll() throws GHAEJBException;

	/**
	 * @param entity
	 * @return the saved Obu
	 * @throws GHAEJBException
	 */
	public Obu save(Obu entity) throws GHAEJBException;

	/**
	 * @param entity
	 * @return the updated Obu
	 * @throws GHAEJBException
	 */
	public Obu update(Obu entity) throws GHAEJBException;
}
