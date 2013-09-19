/**
 * 
 */
package org.fourgeeks.gha.webclient.client.bpa;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.mix.Bpa;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 *
 */
@RemoteServiceRelativePath("bpa")
public interface GWTBpaService extends RemoteService{
	/**
	 * @param Id
	 * @throws GHAEJBException
	 */
	public void delete(long Id) throws GHAEJBException;

	/**
	 * @param bpa
	 * @return the list of bpas
	 * @throws GHAEJBException
	 */
	public List<Bpa> find(Bpa bpa) throws GHAEJBException;

	/**
	 * @param Id
	 * @return the bpa
	 * @throws GHAEJBException
	 */
	public Bpa find(long Id) throws GHAEJBException;
	
	/**
	 * @return the list of bpas
	 * @throws GHAEJBException
	 */
	public List<Bpa> getAll() throws GHAEJBException;

	/**
	 * @param bpa
	 * @return the saved bpa
	 * @throws GHAEJBException
	 */
	public Bpa save(Bpa bpa) throws GHAEJBException;

	/**
	 * @param bpa
	 * @return the updated bpa
	 * @throws GHAEJBException
	 */
	public Bpa update(Bpa bpa) throws GHAEJBException;

}
