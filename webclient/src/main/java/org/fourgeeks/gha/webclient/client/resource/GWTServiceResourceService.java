/**
 * 
 */
package org.fourgeeks.gha.webclient.client.resource;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.ServiceAndResource;

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
	 * @throws GHAEJBException
	 */
	public void delete(long Id) throws GHAEJBException;
	
	/**
	 * @param protocolActivity
	 * @return the list of Resource/Service that used by the ProtocolActivity given
	 * @throws GHAEJBException
	 */
	public List<ServiceAndResource> findByProtocolActivity(MaintenanceActivity protocolActivity) throws GHAEJBException;

	/**
	 * @param Id
	 * @return the Resource/Service with this Id
	 * @throws GHAEJBException
	 */
	public ServiceAndResource find(long Id) throws GHAEJBException;

	/**
	 * @return the list with all Resource/Service Objects
	 * @throws GHAEJBException
	 */
	public List<ServiceAndResource> getAll() throws GHAEJBException;

	/**
	 * @param offset
	 * @param size
	 * @return List of Resource/Service beginning in offset up to size
	 * @throws GHAEJBException
	 */
	public List<ServiceAndResource> getAll(int offset, int size)
			throws GHAEJBException;

	/**
	 * @param ServiceAndResource
	 *            the Resource/Service to be saved on database
	 * @throws GHAEJBException
	 * @return Resource/Service saved
	 */
	public ServiceAndResource save(ServiceAndResource ras)
			throws GHAEJBException;

	/**
	 * @param ServiceAndResource
	 *            the Resource/Service to be updated
	 * @return Resource/Service updated
	 * @throws GHAEJBException
	 */
	public ServiceAndResource update(ServiceAndResource ras)
			throws GHAEJBException;
}
