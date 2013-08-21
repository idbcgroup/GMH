/**
 * 
 */
package org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 *
 */
@RemoteServiceRelativePath("eiaTypeMaintenanceProtocol")
public interface GWTEiaTypeMaintenanceProtocolService {
	/**
	 * @param Id
	 * @throws EJBException
	 */
	public void delete(long Id) throws EJBException;

	/**
	 * @param eiaTypeMaintenancePlan
	 * @return a list with the EiaTypeMaintenanceProtocols associated with this
	 *         Plan
	 * @throws EJBException
	 */
	public List<MaintenanceProtocol> findByEiaTypeMaintenancePlan(
			EiaTypeMaintenancePlan eiaTypeMaintenancePlan) throws EJBException;

	/**
	 * @param Id
	 * @return the EiaTypeMaintenanceProtocol
	 * @throws EJBException
	 */
	public MaintenanceProtocol find(long Id) throws EJBException;

	/**
	 * @return the list of EiaTypeMaintenanceProtocols
	 * @throws EJBException
	 */
	public List<MaintenanceProtocol> getAll() throws EJBException;

	/**
	 * @param EiaTypeMaintenanceProtocolServiceTest
	 * @return the saved EiaTypeMaintenanceProtocol
	 * @throws EJBException
	 */
	public MaintenanceProtocol save(
			MaintenanceProtocol eiaTypeMaintenanceProtocol)
			throws EJBException;

	/**
	 * @param EiaTypeMaintenanceProtocolServiceTest
	 * @return the updated EiaTypeMaintenanceProtocol
	 * @throws EJBException
	 */
	public MaintenanceProtocol update(
			MaintenanceProtocol eiaTypeMaintenanceProtocol)
			throws EJBException;
}
