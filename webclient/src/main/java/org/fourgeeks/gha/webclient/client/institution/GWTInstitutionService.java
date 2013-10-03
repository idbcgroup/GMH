/**
 * 
 */
package org.fourgeeks.gha.webclient.client.institution;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
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
