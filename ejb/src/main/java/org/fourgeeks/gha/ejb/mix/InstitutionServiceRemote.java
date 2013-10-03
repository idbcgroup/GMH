/**
 * 
 */
package org.fourgeeks.gha.ejb.mix;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.mix.Institution;

/**
 * @author emiliot
 *
 */
@Remote
public interface InstitutionServiceRemote {
	/**
	 * @param Id
	 * @throws GHAEJBException
	 */
	public void delete(long Id) throws GHAEJBException;

	/**
	 * @param institution
	 * @return the list of institutions
	 * @throws GHAEJBException
	 */
	public List<Institution> find(Institution institution) throws GHAEJBException;

	/**
	 * @param Id
	 * @return the institution
	 * @throws GHAEJBException
	 */
	public Institution find(long Id) throws GHAEJBException;
	
	/**
	 * @return the list of institutions
	 * @throws GHAEJBException
	 */
	public List<Institution> getAll() throws GHAEJBException;

	/**
	 * @param institution
	 * @return the saved institution
	 * @throws GHAEJBException
	 */
	public Institution save(Institution institution) throws GHAEJBException;

	/**
	 * @param institution
	 * @return the updated institution
	 * @throws GHAEJBException
	 */
	public Institution update(Institution institution) throws GHAEJBException;
}
