/**
 * 
 */
package org.fourgeeks.gha.webclient.server.eiatype.maintenance.protocol;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;
import org.fourgeeks.gha.ejb.gmh.MaintenanceProtocolServiceRemote;
import org.fourgeeks.gha.webclient.client.maintenanceplan.maintenanceprotocol.GWTMaintenanceProtocolService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 * 
 */

@WebServlet(urlPatterns = { "/webclient/maintenanceProtocolService" })
public class GWTMaintenanceProtocolServiceImpl extends RemoteServiceServlet
		implements GWTMaintenanceProtocolService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB(lookup = "java:global/ear-1/ejb-1/MaintenanceProtocolService")
	MaintenanceProtocolServiceRemote mantProtocolService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.
	 * GWTMaintenanceProtocolService#delete(long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		mantProtocolService.delete(Id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.
	 * GWTMaintenanceProtocolService
	 * #findByMaintenancePlan(org.fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public List<MaintenanceProtocol> findByMaintenancePlan(
			MaintenancePlan maintenancePlan) throws GHAEJBException {
		return mantProtocolService.findByMaintenancePlan(maintenancePlan);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.
	 * GWTMaintenanceProtocolService
	 * #findByMaintenancePlan(org.fourgeeks.gha.domain.gmh.MaintenancePlan, int,
	 * int)
	 */
	@Override
	public List<MaintenanceProtocol> findByMaintenancePlan(
			MaintenancePlan maintenancePlan, int offset, int size)
			throws GHAEJBException {
		return mantProtocolService.findByMaintenancePlan(maintenancePlan);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.maintenanceplan.maintenanceprotocol
	 * .GWTMaintenanceProtocolService
	 * #find(org.fourgeeks.gha.domain.gmh.MaintenanceProtocol)
	 */
	@Override
	public List<MaintenanceProtocol> find(
			MaintenanceProtocol maintenanceProtocol) throws GHAEJBException {
		return mantProtocolService.find(maintenanceProtocol);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.
	 * GWTMaintenanceProtocolService#find(long)
	 */
	@Override
	public MaintenanceProtocol find(long Id) throws GHAEJBException {
		return mantProtocolService.find(Id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.
	 * GWTMaintenanceProtocolService#getAll()
	 */
	@Override
	public List<MaintenanceProtocol> getAll() throws GHAEJBException {
		return mantProtocolService.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.
	 * GWTMaintenanceProtocolService#getAll(int, int)
	 */
	@Override
	public List<MaintenanceProtocol> getAll(int offset, int size)
			throws GHAEJBException {
		return mantProtocolService.getAll(offset, size);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.
	 * GWTMaintenanceProtocolService
	 * #save(org.fourgeeks.gha.domain.gmh.MaintenanceProtocol)
	 */
	@Override
	public MaintenanceProtocol save(MaintenanceProtocol maintenanceProtocol)
			throws GHAEJBException {
		return mantProtocolService.save(maintenanceProtocol);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.
	 * GWTMaintenanceProtocolService
	 * #update(org.fourgeeks.gha.domain.gmh.MaintenanceProtocol)
	 */
	@Override
	public MaintenanceProtocol update(MaintenanceProtocol maintenanceProtocol)
			throws GHAEJBException {
		return mantProtocolService.update(maintenanceProtocol);
	}

}
