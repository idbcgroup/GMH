/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.ServiceResource;

/**
 * @author emiliot
 *
 */
@Remote
public interface ServiceResourceServiceRemote {
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
	public List<ServiceResource> findByProtocolActivity(MaintenanceActivity protocolActivity) throws GHAEJBException;

	/**
	 * @param Id
	 * @return the Resource/Service with this Id
	 * @throws GHAEJBException
	 */
	public ServiceResource find(long Id) throws GHAEJBException;

	/**
	 * @return the list with all Resource/Service Objects
	 * @throws GHAEJBException
	 */
	public List<ServiceResource> getAll() throws GHAEJBException;

	/**
	 * @param offset
	 * @param size
	 * @return List of Resource/Service beginning in offset up to size
	 * @throws GHAEJBException
	 */
	public List<ServiceResource> getAll(int offset, int size)
			throws GHAEJBException;

	/**
	 * @param ServiceResource
	 *            the Resource/Service to be saved on database
	 * @throws GHAEJBException
	 * @return Resource/Service saved
	 */
	public ServiceResource save(ServiceResource ras)
			throws GHAEJBException;

	/**
	 * @param ServiceResource
	 *            the Resource/Service to be updated
	 * @return Resource/Service updated
	 * @throws GHAEJBException
	 */
	public ServiceResource update(ServiceResource ras)
			throws GHAEJBException;
}
