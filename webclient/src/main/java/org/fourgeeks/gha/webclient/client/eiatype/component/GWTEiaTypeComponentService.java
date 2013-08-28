/**
 * 
 */
package org.fourgeeks.gha.webclient.client.eiatype.component;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeComponent;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 *
 */
@RemoteServiceRelativePath("eiaTypeComponent")
public interface GWTEiaTypeComponentService extends RemoteService{
	/**
	 * @param Id
	 * @throws EJBException
	 */
	public void delete(long Id) throws EJBException;

	/**
	 * @param Id
	 * @return
	 * @throws EJBException
	 */
	public EiaTypeComponent find(long Id) throws EJBException;

	/**
	 * @param eiaType
	 * @return
	 * @throws EJBException
	 */
	public List<EiaTypeComponent> findByParentEiaType(EiaType eiaType)
			throws EJBException;

	/**
	 * @return
	 * @throws EJBException
	 */
	public List<EiaTypeComponent> getAll() throws EJBException;

	/**
	 * @param offset
	 * @param size
	 * @return
	 * @throws EJBException
	 */
	public List<EiaTypeComponent> getAll(int offset, int size)
			throws EJBException;

	/**
	 * @param eiaTypeComponent
	 * @return
	 * @throws EJBException
	 */
	public EiaTypeComponent save(EiaTypeComponent eiaTypeComponent)
			throws EJBException;

	/**
	 * @param eiaTypeComponent
	 * @return
	 * @throws EJBException
	 */
	public EiaTypeComponent update(EiaTypeComponent eiaTypeComponent)
			throws EJBException;
}
