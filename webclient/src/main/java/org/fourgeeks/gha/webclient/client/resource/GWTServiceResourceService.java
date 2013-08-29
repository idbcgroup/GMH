/**
 * 
 */
package org.fourgeeks.gha.webclient.client.resource;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.ProtocolActivity;
import org.fourgeeks.gha.domain.gmh.ServiceResource;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 *
 */

@RemoteServiceRelativePath("serviceResource")
public interface GWTServiceResourceService extends RemoteService{
	/**
	 * Delete a Resource/Service from database by Id
	 * @throws EJBException
	 */
	public void delete(long Id) throws EJBException;
	
	/**
	 * @param protocolActivity
	 * @return the list of Resource/Service that used by the ProtocolActivity given
	 * @throws EJBException
	 */
	public List<ServiceResource> findByProtocolActivity(ProtocolActivity protocolActivity) throws EJBException;

	/**
	 * @param Id
	 * @return the Resource/Service with this Id
	 * @throws EJBException
	 */
	public ServiceResource find(long Id) throws EJBException;

	/**
	 * @return the list with all Resource/Service Objects
	 * @throws EJBException
	 */
	public List<ServiceResource> getAll() throws EJBException;

	/**
	 * @param offset
	 * @param size
	 * @return List of Resource/Service beginning in offset up to size
	 * @throws EJBException
	 */
	public List<ServiceResource> getAll(int offset, int size)
			throws EJBException;

	/**
	 * @param ServiceResource
	 *            the Resource/Service to be saved on database
	 * @throws EJBException
	 * @return Resource/Service saved
	 */
	public ServiceResource save(ServiceResource ras)
			throws EJBException;

	/**
	 * @param ServiceResource
	 *            the Resource/Service to be updated
	 * @return Resource/Service updated
	 * @throws EJBException
	 */
	public ServiceResource update(ServiceResource ras)
			throws EJBException;
}
