/**
 * 
 */
package org.fourgeeks.gha.ejb.glm;

import java.util.List;

import javax.ejb.Remote;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
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
	 * @throws GHAEJBException
	 */
	public void delete(long Id) throws GHAEJBException;

	/**
	 * @param entity
	 * @return the list of ExternalProvider
	 * @throws GHAEJBException
	 */
	public List<ExternalProvider> find(ExternalProvider entity)
			throws GHAEJBException;

	/**
	 * @param Id
	 * @return the ExternalProvider
	 * @throws GHAEJBException
	 */
	public ExternalProvider find(long Id) throws GHAEJBException;

	/**
	 * @return the list of ExternalProviders
	 * @throws GHAEJBException
	 */
	public List<ExternalProvider> getAll() throws GHAEJBException;

	/**
	 * @param entity
	 * @return the saved Obu
	 * @throws GHAEJBException
	 */
	public ExternalProvider save(ExternalProvider entity) throws GHAEJBException;

	/**
	 * @param entity
	 * @return the updated ExternalProvider
	 * @throws GHAEJBException
	 */
	public ExternalProvider update(ExternalProvider entity) throws GHAEJBException;
}
