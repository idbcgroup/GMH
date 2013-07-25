/**
 * 
 */
package org.fourgeeks.gha.ejb.gar;

import java.util.List;

import javax.ejb.Remote;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.fourgeeks.gha.domain.exceptions.EJBException;
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
	 * @throws EJBException
	 */
	public void delete(long Id) throws EJBException;

	/**
	 * @param entity
	 * @return the list of Obu
	 * @throws EJBException
	 */
	public List<Obu> find(Obu entity) throws EJBException;

	/**
	 * @param Id
	 * @return the Obu
	 * @throws EJBException
	 */
	public Obu find(long Id) throws EJBException;

	/**
	 * @return the list of obus
	 * @throws EJBException
	 */
	public List<Obu> getAll() throws EJBException;

	/**
	 * @param entity
	 * @return the saved Obu
	 * @throws EJBException
	 */
	public Obu save(Obu entity) throws EJBException;

	/**
	 * @param entity
	 * @return the updated Obu
	 * @throws EJBException
	 */
	public Obu update(Obu entity) throws EJBException;
}
