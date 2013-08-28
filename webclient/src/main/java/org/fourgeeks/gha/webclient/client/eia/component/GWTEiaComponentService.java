/**
 * 
 */
package org.fourgeeks.gha.webclient.client.eia.component;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.EJBException;
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
	 * @throws EJBException
	 */
	public void delete(long Id) throws EJBException;
	/**
	 * @param eia
	 * @return
	 * @throws EJBException
	 */
	public List<EiaComponent> findByParentEia(Eia eia) throws EJBException;
	/**
	 * @param eia
	 * @param offset
	 * @param size
	 * @return
	 * @throws EJBException
	 */
	public List<EiaComponent> findByParentEia(Eia eia, int offset, int size)throws EJBException;
	/**
	 * @param Id
	 * @return
	 * @throws EJBException
	 */
	public EiaComponent find(long Id) throws EJBException;
	/**
	 * @return
	 * @throws EJBException
	 */
	public List<EiaComponent> getAll() throws EJBException;
	/**
	 * @param offset
	 * @param size
	 * @return
	 * @throws EJBException
	 */
	public List<EiaComponent> getAll(int offset, int size) throws EJBException;
	/**
	 * @param eiaComponent
	 * @return
	 * @throws EJBException
	 */
	public EiaComponent save(EiaComponent eiaComponent) throws EJBException;
	/**
	 * @param eiaComponent
	 * @return
	 * @throws EJBException
	 */
	public EiaComponent update(EiaComponent eiaComponent) throws EJBException;
}
