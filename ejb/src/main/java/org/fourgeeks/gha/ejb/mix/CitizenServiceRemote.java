/**
 * 
 */
package org.fourgeeks.gha.ejb.mix;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.mix.Citizen;

/**
 * @author emiliot
 *
 */

@Remote
public interface CitizenServiceRemote {
	/**
	 * @param Id
	 * @throws EJBException
	 */
	public void delete(long Id) throws EJBException;

	/**
	 * @param citizen
	 * @return the list of Citizens
	 * @throws EJBException
	 */
	public List<Citizen> find(Citizen citizen) throws EJBException;

	/**
	 * @param Id
	 * @return the Citizen
	 * @throws EJBException
	 */
	public Citizen find(long Id) throws EJBException;
	
	/**
	 * @return the list of Citizens
	 * @throws EJBException
	 */
	public List<Citizen> getAll() throws EJBException;

	/**
	 * @param citizen
	 * @return the saved Citizen
	 * @throws EJBException
	 */
	public Citizen save(Citizen citizen) throws EJBException;

	/**
	 * @param citizen
	 * @return the updated Citizen
	 * @throws EJBException
	 */
	public Citizen update(Citizen citizen) throws EJBException;
}
