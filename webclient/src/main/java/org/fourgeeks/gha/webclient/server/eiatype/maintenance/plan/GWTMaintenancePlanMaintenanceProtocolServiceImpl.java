/**
 * 
 */
package org.fourgeeks.gha.webclient.server.eiatype.maintenance.plan;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenancePlanMaintenanceProtocol;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;
import org.fourgeeks.gha.ejb.gmh.MaintenancePlanMaintenanceProtocolServiceRemote;
import org.fourgeeks.gha.webclient.client.maintenanceplan.GWTMaintenancePlanMaintenanceProtocolService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 *
 */

@WebServlet(urlPatterns = {"/webclient/maintenancePlanMaintenanceProtocolService"})
public class GWTMaintenancePlanMaintenanceProtocolServiceImpl extends RemoteServiceServlet implements GWTMaintenancePlanMaintenanceProtocolService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB(name = "gmh.maintenancePlanMaintenanceProtocolService")
	MaintenancePlanMaintenanceProtocolServiceRemote ejbService;

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.maintenanceplan.GWTMaintenancePlanMaintenanceProtocolService#delete(long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		ejbService.delete(Id);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.maintenanceplan.GWTMaintenancePlanMaintenanceProtocolService#save(org.fourgeeks.gha.domain.gmh.MaintenancePlanMaintenanceProtocol)
	 */
	@Override
	public MaintenancePlanMaintenanceProtocol save(
			MaintenancePlanMaintenanceProtocol maintenancePlanMaintenanceProtocol)
			throws GHAEJBException {
		return ejbService.save(maintenancePlanMaintenanceProtocol);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.maintenanceplan.GWTMaintenancePlanMaintenanceProtocolService#findByMaintenanceProtocol(org.fourgeeks.gha.domain.gmh.MaintenanceProtocol)
	 */
	@Override
	public List<MaintenancePlanMaintenanceProtocol> findByMaintenanceProtocol(
			MaintenanceProtocol maintenanceProtocol) throws GHAEJBException {
		return ejbService.findByMaintenanceProtocol(maintenanceProtocol);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.maintenanceplan.GWTMaintenancePlanMaintenanceProtocolService#findByMaintenancePlan(org.fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public List<MaintenancePlanMaintenanceProtocol> findByMaintenancePlan(
			MaintenancePlan maintenancePlan) throws GHAEJBException {
		return ejbService.findByMaintenancePlan(maintenancePlan);
	}

}
