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
	 * @throws GHAEJBException
	 */
	public void delete(String id) throws GHAEJBException;

	/**
	 * 
	 * @param brand
	 * @return
	 * @throws GHAEJBException
	 */
	public List<BuildingLocation> find(BuildingLocation buildingLocation)
			throws GHAEJBException;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws GHAEJBException
	 */
	public BuildingLocation find(String id) throws GHAEJBException;

	/**
	 * 
	 * @return
	 * @throws GHAEJBException
	 */
	public List<BuildingLocation> getAll() throws GHAEJBException;

	/**
	 * 
	 * @param brand
	 * @return
	 * @throws GHAEJBException
	 */
	public BuildingLocation save(BuildingLocation buildingLocation)
			throws GHAEJBException;

	/**
	 * 
	 * @param brand
	 * @return
	 * @throws GHAEJBException
	 */
	public BuildingLocation update(BuildingLocation buildingLocation)
			throws GHAEJBException;
}
