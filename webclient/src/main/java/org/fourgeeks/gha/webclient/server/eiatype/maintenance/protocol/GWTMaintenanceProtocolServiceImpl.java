/**
 * 
 */
package org.fourgeeks.gha.webclient.server.eiatype.maintenance.protocol;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;
import org.fourgeeks.gha.ejb.gmh.MaintenanceProtocolServiceRemote;
import org.fourgeeks.gha.webclient.client.maintenanceplan.maintenanceprotocol.GWTMaintenanceProtocolService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 *
 */

@WebServlet(urlPatterns = {"/webclient/maintenanceProtocolService"})
public class GWTMaintenanceProtocolServiceImpl extends RemoteServiceServlet implements
	GWTMaintenanceProtocolService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB(name = "gmh.maintenanceProtocolService")
	MaintenanceProtocolServiceRemote mantProtocolService;

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.GWTMaintenanceProtocolService#delete(long)
	 */
	@Override
	public void delete(long Id) throws EJBException {
		mantProtocolService.delete(Id);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.GWTMaintenanceProtocolService#findByEiaTypeMaintenancePlan(org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan)
	 */
	@Override
	public List<MaintenanceProtocol> findByEiaTypeMaintenancePlan(
			MaintenancePlan maintenancePlan) throws EJBException {
		return mantProtocolService.findByEiaTypeMaintenancePlan(maintenancePlan);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.GWTMaintenanceProtocolService#findByEiaTypeMaintenancePlan(org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan, int, int)
	 */
	@Override
	public List<MaintenanceProtocol> findByEiaTypeMaintenancePlan(
			MaintenancePlan maintenancePlan, int offset, int size)
			throws EJBException {
		return mantProtocolService.findByEiaTypeMaintenancePlan(maintenancePlan);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.GWTMaintenanceProtocolService#find(long)
	 */
	@Override
	public MaintenanceProtocol find(long Id) throws EJBException {
		return mantProtocolService.find(Id);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.GWTMaintenanceProtocolService#getAll()
	 */
	@Override
	public List<MaintenanceProtocol> getAll() throws EJBException {
		return mantProtocolService.getAll();
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.GWTMaintenanceProtocolService#getAll(int, int)
	 */
	@Override
	public List<MaintenanceProtocol> getAll(int offset, int size)
			throws EJBException {
		return mantProtocolService.getAll(offset, size);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.GWTMaintenanceProtocolService#save(org.fourgeeks.gha.domain.gmh.MaintenanceProtocol)
	 */
	@Override
	public MaintenanceProtocol save(
			MaintenanceProtocol maintenanceProtocol) throws EJBException {
		return mantProtocolService.save(maintenanceProtocol);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.GWTMaintenanceProtocolService#update(org.fourgeeks.gha.domain.gmh.MaintenanceProtocol)
	 */
	@Override
	public MaintenanceProtocol update(
			MaintenanceProtocol maintenanceProtocol) throws EJBException {
		return mantProtocolService.update(maintenanceProtocol);
	}

}