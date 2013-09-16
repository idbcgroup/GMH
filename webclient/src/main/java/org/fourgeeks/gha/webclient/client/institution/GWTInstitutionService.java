/**
 * 
 */
package org.fourgeeks.gha.webclient.client.institution;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.mix.Institution;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 *
 */
@RemoteServiceRelativePath("institution")
public interface GWTInstitutionService extends RemoteService{
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
