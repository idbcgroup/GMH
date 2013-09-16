/**
 * 
 */
package org.fourgeeks.gha.ejb.mix;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.mix.Institution;

/**
 * @author emiliot
 *
 */
@Remote
public interface InstitutionServiceRemote {
	/**
	 * @param Id
	 * @throws EJBException
	 */
	public void delete(long Id) throws EJBException;

	/**
	 * @param institution
	 * @return the list of institutions
	 * @throws EJBException
	 */
	public List<Institution> find(Institution institution) throws EJBException;

	/**
	 * @param Id
	 * @return the institution
	 * @throws EJBException
	 */
	public Institution find(long Id) throws EJBException;
	
	/**
	 * @return the list of institutions
	 * @throws EJBException
	 */
	public List<Institution> getAll() throws EJBException;

	/**
	 * @param institution
	 * @return the saved institution
	 * @throws EJBException
	 */
	public Institution save(Institution institution) throws EJBException;

	/**
	 * @param institution
	 * @return the updated institution
	 * @throws EJBException
	 */
	public Institution update(Institution institution) throws EJBException;
}
