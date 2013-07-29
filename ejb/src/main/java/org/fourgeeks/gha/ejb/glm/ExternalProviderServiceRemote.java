/**
 * 
 */
package org.fourgeeks.gha.ejb.glm;

import java.util.List;

import javax.ejb.Remote;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.glm.ExternalProvider;

/**
 * @author alacret
 * 
 */
@Remote
public interface ExternalProviderServiceRemote {
	
	/**
	 * @param entity
	 * @param cb
	 * @param root
	 * @return
	 */
	public Predicate buildFilters(ExternalProvider entity, CriteriaBuilder cb,
			Root<ExternalProvider> root);
	
	/**
	 * @param Id
	 * @throws EJBException
	 */
	public void delete(long Id) throws EJBException;

	/**
	 * @param entity
	 * @return the list of ExternalProvider
	 * @throws EJBException
	 */
	public List<ExternalProvider> find(ExternalProvider entity)
			throws EJBException;

	/**
	 * @param Id
	 * @return the ExternalProvider
	 * @throws EJBException
	 */
	public ExternalProvider find(long Id) throws EJBException;

	/**
	 * @return the list of ExternalProviders
	 * @throws EJBException
	 */
	public List<ExternalProvider> getAll() throws EJBException;

	/**
	 * @param entity
	 * @return the saved Obu
	 * @throws EJBException
	 */
	public ExternalProvider save(ExternalProvider entity) throws EJBException;

	/**
	 * @param entity
	 * @return the updated ExternalProvider
	 * @throws EJBException
	 */
	public ExternalProvider update(ExternalProvider entity) throws EJBException;
}
