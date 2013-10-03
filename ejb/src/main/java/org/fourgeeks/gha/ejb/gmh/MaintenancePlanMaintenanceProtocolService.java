/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenancePlanMaintenanceProtocol;

/**
 * @author emiliot
 *
 */
public class MaintenancePlanMaintenanceProtocolService implements
		MaintenancePlanMaintenanceProtocolServiceRemote {

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.MaintenancePlanMaintenanceProtocolServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.MaintenancePlanMaintenanceProtocolServiceRemote#save(org.fourgeeks.gha.domain.gmh.MaintenancePlanMaintenanceProtocol)
	 */
	@Override
	public MaintenancePlanMaintenanceProtocol save(
			MaintenancePlanMaintenanceProtocol maintenancePlanMaintenanceProtocol)
			throws GHAEJBException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.MaintenancePlanMaintenanceProtocolServiceRemote#findByMaintenanceProtocol(org.fourgeeks.gha.domain.gmh.MaintenancePlanMaintenanceProtocol)
	 */
	@Override
	public List<MaintenancePlanMaintenanceProtocol> findByMaintenanceProtocol(
			MaintenancePlanMaintenanceProtocol maintenancePlanMaintenanceProtocol)
			throws GHAEJBException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.MaintenancePlanMaintenanceProtocolServiceRemote#findByMaintenancePlan(org.fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public List<MaintenancePlanMaintenanceProtocol> findByMaintenancePlan(
			MaintenancePlan maintenancePlan) throws GHAEJBException {
		// TODO Auto-generated method stub
		return null;
	}

}
