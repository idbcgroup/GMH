/**
 * 
 */
package org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.ProtocolActivity;
import org.fourgeeks.gha.domain.gmh.ProtocolActivityComponent;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 *
 */

@RemoteServiceRelativePath("protocolActivityComponent")
public interface GWTProtocolActivityComponentService extends RemoteService {
	/**
	 * Delete a ProtocolActivityComponent from database by Id
	 * @throws EJBException
	 */
	public void delete(long Id) throws EJBException;

	/**
	 * @param protocolActivity
	 * @return a list with the ProtocolActivityComponents of the protocolActivity
	 * @throws EJBException
	 */
	public List<ProtocolActivityComponent> findByProtocolActivity(
			ProtocolActivity protocolActivity)
			throws EJBException;

	/**
	 * @param Id
	 * @return the ProtocolActivityComponent with this Id
	 * @throws EJBException
	 */
	public ProtocolActivityComponent find(long Id) throws EJBException;

	/**
	 * @return the list with all ProtocolActivityComponents Objects
	 * @throws EJBException
	 */
	public List<ProtocolActivityComponent> getAll() throws EJBException;

	/**
	 * @param offset
	 * @param size
	 * @return List of ProtocolActivityComponents beginning in offset up to size
	 * @throws EJBException
	 */
	public List<ProtocolActivityComponent> getAll(int offset, int size)
			throws EJBException;

	/**
	 * @param ProtocolActivityComponent
	 *            the ProtocolActivityComponent to be saved on database
	 * @throws EJBException
	 * @return ProtocolActivityComponent saved
	 */
	public ProtocolActivityComponent save(ProtocolActivityComponent protocolActivityComponent)
			throws EJBException;

	/**
	 * @param ProtocolActivityComponent
	 *            the ProtocolActivityComponent to be updated
	 * @return ProtocolActivityComponent updated
	 * @throws EJBException
	 */
	public ProtocolActivityComponent update(ProtocolActivityComponent protocolActivityComponent)
			throws EJBException;
}
