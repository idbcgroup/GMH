/**
 * 
 */
package org.fourgeeks.gha.webclient.client.eia.component;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaComponent;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 *
 */

@RemoteServiceRelativePath("eiaComponent")
public interface GWTEiaComponentService extends RemoteService{
	/**
	 * @param Id
	 * @throws GHAEJBException
	 */
	public void delete(long Id) throws GHAEJBException;
	/**
	 * @param eia
	 * @return
	 * @throws GHAEJBException
	 */
	public List<EiaComponent> findByParentEia(Eia eia) throws GHAEJBException;
	/**
	 * @param eia
	 * @param offset
	 * @param size
	 * @return
	 * @throws GHAEJBException
	 */
	public List<EiaComponent> findByParentEia(Eia eia, int offset, int size)throws GHAEJBException;
	/**
	 * @param Id
	 * @return
	 * @throws GHAEJBException
	 */
	public EiaComponent find(long Id) throws GHAEJBException;
	/**
	 * @return
	 * @throws GHAEJBException
	 */
	public List<EiaComponent> getAll() throws GHAEJBException;
	/**
	 * @param offset
	 * @param size
	 * @return
	 * @throws GHAEJBException
	 */
	public List<EiaComponent> getAll(int offset, int size) throws GHAEJBException;
	/**
	 * @param eiaComponent
	 * @return
	 * @throws GHAEJBException
	 */
	public EiaComponent save(EiaComponent eiaComponent) throws GHAEJBException;
	/**
	 * @param eiaComponent
	 * @return
	 * @throws GHAEJBException
	 */
	public EiaComponent update(EiaComponent eiaComponent) throws GHAEJBException;
}
