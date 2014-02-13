/**
 * 
 */
package org.fourgeeks.gha.ejb.mix;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.mix.Citizen;

/**
 * @author emiliot
 * 
 */

@Remote
public interface CitizenServiceRemote {
	/**
	 * @param citizens
	 * @throws GHAEJBException
	 */
	public void delete(List<Citizen> citizens) throws GHAEJBException;

	/**
	 * @param citizen
	 * @return the list of Citizens
	 * @throws GHAEJBException
	 */
	public List<Citizen> find(Citizen citizen) throws GHAEJBException;

	/**
	 * @param Id
	 * @return the Citizen
	 * @throws GHAEJBException
	 */
	public Citizen find(long Id) throws GHAEJBException;

	/**
	 * @return the list of Citizens
	 * @throws GHAEJBException
	 */
	public List<Citizen> getAll() throws GHAEJBException;

	/**
	 * @param citizen
	 * @return the saved Citizen
	 * @throws GHAEJBException
	 */
	public Citizen save(Citizen citizen) throws GHAEJBException;

	/**
	 * @param citizen
	 * @return the updated Citizen
	 * @throws GHAEJBException
	 */
	public Citizen update(Citizen citizen) throws GHAEJBException;
}
