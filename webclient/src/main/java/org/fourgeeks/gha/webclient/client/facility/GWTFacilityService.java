/**
 * 
 */
package org.fourgeeks.gha.webclient.client.facility;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Facility;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 *
 */
@RemoteServiceRelativePath("facility")
public interface GWTFacilityService extends RemoteService{
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
