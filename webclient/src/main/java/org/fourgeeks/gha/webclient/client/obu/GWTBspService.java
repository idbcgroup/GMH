/**
 * 
 */
package org.fourgeeks.gha.webclient.client.obu;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.glm.Bsp;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author alacret
 * 
 */
@RemoteServiceRelativePath("bsp")
public interface GWTBspService extends RemoteService {
	/**
	 * @param Id
	 * @throws GHAEJBException
	 */
	public void delete(long Id) throws GHAEJBException;

	/**
	 * @param entity
	 * @return the list of Obu
	 * @throws GHAEJBException
	 */
	public List<Bsp> find(Bsp entity) throws GHAEJBException;

	/**
	 * @param Id
	 * @return the Obu
	 * @throws GHAEJBException
	 */
	public Bsp find(long Id) throws GHAEJBException;

	/**
	 * @return the list of obus
	 * @throws GHAEJBException
	 */
	public List<Bsp> getAll() throws GHAEJBException;

	/**
	 * @param entity
	 * @return the saved Obu
	 * @throws GHAEJBException
	 */
	public Bsp save(Bsp entity) throws GHAEJBException;

	/**
	 * @param entity
	 * @return the updated Obu
	 * @throws GHAEJBException
	 */
	public Bsp update(Bsp entity) throws GHAEJBException;
}
