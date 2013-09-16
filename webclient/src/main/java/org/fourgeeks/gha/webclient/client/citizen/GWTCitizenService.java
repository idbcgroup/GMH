/**
 * 
 */
package org.fourgeeks.gha.webclient.client.citizen;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.EJBException;
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
