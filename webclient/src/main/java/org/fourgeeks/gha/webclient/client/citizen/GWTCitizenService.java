/**
 * 
 */
package org.fourgeeks.gha.webclient.client.citizen;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.mix.Citizen;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 *
 */
@RemoteServiceRelativePath("citizen")
public interface GWTCitizenService extends RemoteService{
	/**
	 * @param Id
	 * @throws GHAEJBException
	 */
	public void delete(long Id) throws GHAEJBException;

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
