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
	 * @param Citizen
	 * @return the list of Citizens
	 * @throws EJBException
	 */
	public List<Citizen> find(Citizen Citizen) throws EJBException;

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
	 * @param Citizen
	 * @return the saved Citizen
	 * @throws EJBException
	 */
	public Citizen save(Citizen Citizen) throws EJBException;

	/**
	 * @param Citizen
	 * @return the updated Citizen
	 * @throws EJBException
	 */
	public Citizen update(Citizen Citizen) throws EJBException;
}
