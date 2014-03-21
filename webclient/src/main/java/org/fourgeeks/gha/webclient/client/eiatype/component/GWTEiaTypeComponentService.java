/**
 * 
 */
package org.fourgeeks.gha.webclient.client.eiatype.component;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeComponent;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 * 
 */
@RemoteServiceRelativePath("eiaTypeComponent")
public interface GWTEiaTypeComponentService extends RemoteService {
	public void delete(List<EiaTypeComponent> entities) throws GHAEJBException;

	/**
	 * @param Id
	 * @throws GHAEJBException
	 */
	public void delete(long Id) throws GHAEJBException;

	/**
	 * @param Id
	 * @return
	 * @throws GHAEJBException
	 */
	public EiaTypeComponent find(long Id) throws GHAEJBException;

	/**
	 * @param eiaType
	 * @return
	 * @throws GHAEJBException
	 */
	public List<EiaTypeComponent> findByParentEiaType(EiaType eiaType)
			throws GHAEJBException;

	/**
	 * @return
	 * @throws GHAEJBException
	 */
	public List<EiaTypeComponent> getAll() throws GHAEJBException;

	/**
	 * @param offset
	 * @param size
	 * @return
	 * @throws GHAEJBException
	 */
	public List<EiaTypeComponent> getAll(int offset, int size)
			throws GHAEJBException;

	/**
	 * @param eiaTypeComponent
	 * @return
	 * @throws GHAEJBException
	 */
	public EiaTypeComponent save(EiaTypeComponent eiaTypeComponent)
			throws GHAEJBException;

	/**
	 * @param eiaTypeComponent
	 * @return
	 * @throws GHAEJBException
	 */
	public EiaTypeComponent update(EiaTypeComponent eiaTypeComponent)
			throws GHAEJBException;
}
