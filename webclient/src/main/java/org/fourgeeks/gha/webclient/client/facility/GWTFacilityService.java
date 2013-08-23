/**
 * 
 */
package org.fourgeeks.gha.webclient.client.facility;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gar.Facility;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 *
 */
@RemoteServiceRelativePath("facility")
public interface GWTFacilityService {
	/**
	 * @param Id
	 * @throws EJBException
	 */
	public void delete(long Id) throws EJBException;

	/**
	 * @param entity
	 * @return the list of Facilities
	 * @throws EJBException
	 */
	public List<Facility> find(Facility entity) throws EJBException;

	/**
	 * @param Id
	 * @return the Facility
	 * @throws EJBException
	 */
	public Facility find(long Id) throws EJBException;

	/**
	 * @return the list of Facilities
	 * @throws EJBException
	 */
	public List<Facility> getAll() throws EJBException;

	/**
	 * @param entity
	 * @return the saved Facility
	 * @throws EJBException
	 */
	public Facility save(Facility entity) throws EJBException;

	/**
	 * @param entity
	 * @return the updated Facility
	 * @throws EJBException
	 */
	public Facility update(Facility entity) throws EJBException;
}
