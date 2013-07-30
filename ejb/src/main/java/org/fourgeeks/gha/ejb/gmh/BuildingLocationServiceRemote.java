/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gar.BuildingLocation;

/**
 * @author alacret
 * 
 */

@Remote
public interface BuildingLocationServiceRemote {
	/**
	 * @param entity
	 * @param cb
	 * @param root
	 * @return a predicate with the filters used to search by eia
	 */
	public Predicate buildFilters(BuildingLocation entity, CriteriaBuilder cb, Root<BuildingLocation> root);
	
	/**
	 * 
	 * @param id
	 * @throws EJBException
	 */
	public void delete(String id) throws EJBException;

	/**
	 * 
	 * @param brand
	 * @return
	 * @throws EJBException
	 */
	public List<BuildingLocation> find(BuildingLocation brand)
			throws EJBException;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws EJBException
	 */
	public BuildingLocation find(String id) throws EJBException;

	/**
	 * 
	 * @return
	 * @throws EJBException
	 */
	public List<BuildingLocation> getAll() throws EJBException;

	/**
	 * 
	 * @param brand
	 * @return
	 * @throws EJBException
	 */
	public BuildingLocation save(BuildingLocation brand) throws EJBException;

	/**
	 * 
	 * @param brand
	 * @return
	 * @throws EJBException
	 */
	public BuildingLocation update(BuildingLocation brand) throws EJBException;
}
