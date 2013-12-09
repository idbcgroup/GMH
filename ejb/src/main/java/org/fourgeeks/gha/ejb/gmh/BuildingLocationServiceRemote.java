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
	public Predicate buildFilters(BuildingLocation entity, CriteriaBuilder cb,
			Root<BuildingLocation> root);

	/**
	 * 
	 * @param id
	 * @throws GHAEJBException
	 */
	public void delete(String id) throws GHAEJBException;

	/**
	 * 
	 * @param buildingLocation
	 * @return a list of building locations based on the passed one
	 * @throws GHAEJBException
	 */
	public List<BuildingLocation> find(BuildingLocation buildingLocation)
			throws GHAEJBException;

	/**
	 * 
	 * @param id
	 * @return a building location with the specified ID
	 * @throws GHAEJBException
	 *             if the entity is not found
	 */
	public BuildingLocation find(String id) throws GHAEJBException;

	/**
	 * 
	 * @return a complete list of all building locations in the database
	 * @throws GHAEJBException
	 */
	public List<BuildingLocation> getAll() throws GHAEJBException;

	/**
	 * 
	 * @param buildingLocation
	 * @return the saved version of the building location
	 * @throws GHAEJBException
	 */
	public BuildingLocation save(BuildingLocation buildingLocation)
			throws GHAEJBException;

	/**
	 * @param buildingLocation
	 * @return the update version of the building location
	 * @throws GHAEJBException
	 */
	public BuildingLocation update(BuildingLocation buildingLocation)
			throws GHAEJBException;
}
