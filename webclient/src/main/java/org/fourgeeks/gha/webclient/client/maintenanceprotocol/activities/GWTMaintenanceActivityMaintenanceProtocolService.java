package org.fourgeeks.gha.webclient.client.maintenanceprotocol.activities;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivityMaintenanceProtocol;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 * 
 */
@RemoteServiceRelativePath("maintenanceActivityMaintenanceProtocolService")
public interface GWTMaintenanceActivityMaintenanceProtocolService extends
		RemoteService {
	/**
	 * Delete a MaintenanceActivityMaintenanceProtocol from database by Id
	 * 
	 * @throws GHAEJBException
	 */
	public void delete(long Id) throws GHAEJBException;

	/**
	 * @param MaintenanceActivityMaintenanceProtocol
	 * @return the persistent entity
	 * @throws GHAEJBException
	 */
	public MaintenanceActivityMaintenanceProtocol save(
			MaintenanceActivityMaintenanceProtocol entity)
			throws GHAEJBException;

	/**
	 * @param MaintenanceProtocol
	 * @return the MaintenanceActivityMaintenanceProtocol associated with the
	 *         protocol
	 * @throws GHAEJBException
	 */
	public List<MaintenanceActivityMaintenanceProtocol> findByMaintenanceProtocol(
			MaintenanceProtocol maintenanceProtocol) throws GHAEJBException;

	/**
	 * @param maintenancePlan
	 * @return the MaintenanceActivityMaintenanceProtocol associated with the
	 *         activity
	 * @throws GHAEJBException
	 */
	public List<MaintenanceActivityMaintenanceProtocol> findByMaintenanceActivity(
			MaintenanceActivity maintenanceActivity) throws GHAEJBException;
}
