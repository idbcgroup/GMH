/**
 * 
 */
package org.fourgeeks.gha.ejb.gar;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Facility;

/**
 * @author emiliot
 *
 */
@Remote
public interface FacilityServiceRemote {
	/**
	 * @param Id
	 * @throws GHAEJBException
	 */
	public void delete(long Id) throws GHAEJBException;

	/**
	 * @param entity
	 * @return the list of Facilities
	 * @throws GHAEJBException
	 */
	public List<Facility> find(Facility entity) throws GHAEJBException;

	/**
	 * @param Id
	 * @return the Facility
	 * @throws GHAEJBException
	 */
	public Facility find(long Id) throws GHAEJBException;

	/**
	 * @return the list of Facilities
	 * @throws GHAEJBException
	 */
	public List<Facility> getAll() throws GHAEJBException;

	/**
	 * @param entity
	 * @return the saved Facility
	 * @throws GHAEJBException
	 */
	public Facility save(Facility entity) throws GHAEJBException;

	/**
	 * @param entity
	 * @return the updated Facility
	 * @throws GHAEJBException
	 */
	public Facility update(Facility entity) throws GHAEJBException;
}
