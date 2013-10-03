/**
 * 
 */
package org.fourgeeks.gha.webclient.server.eiatype.maintenance.protocol;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;
import org.fourgeeks.gha.domain.gmh.ServiceResource;
import org.fourgeeks.gha.ejb.gmh.MaintenanceActivityServiceRemote;
import org.fourgeeks.gha.webclient.client.maintenanceplan.maintenanceprotocol.GWTMaintenanceActivityService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 *
 */
@WebServlet(urlPatterns = {"/webclient/maintenanceActivityService"})
public class GWTMaintenanceActivityServiceImpl extends RemoteServiceServlet 
		implements GWTMaintenanceActivityService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB(name = "gmh.protocolActivityService")
	MaintenanceActivityServiceRemote ejbService;

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.GWTMaintenanceActivityService#delete(long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		ejbService.delete(Id);		
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.GWTMaintenanceActivityService#find(long)
	 */
	@Override
	public MaintenanceActivity find(long Id) throws GHAEJBException {
		return ejbService.find(Id);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.maintenanceplan.maintenanceprotocol.GWTMaintenanceActivityService#findByMaintenanceProtocol(org.fourgeeks.gha.domain.gmh.MaintenanceProtocol)
	 */
	@Override
	public List<MaintenanceActivity> findByMaintenanceProtocol(
			MaintenanceProtocol maintenanceProtocol) throws GHAEJBException {
		return ejbService.findByMaintenanceProtocol(maintenanceProtocol);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.maintenanceplan.maintenanceprotocol.GWTMaintenanceActivityService#findByMaintenanceProtocol(org.fourgeeks.gha.domain.gmh.MaintenanceProtocol, int, int)
	 */
	@Override
	public List<MaintenanceActivity> findByMaintenanceProtocol(
			MaintenanceProtocol maintenanceProtocol, int offset, int size)
			throws GHAEJBException {
		return ejbService.findByMaintenanceProtocol(maintenanceProtocol, offset, size);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.maintenanceplan.maintenanceprotocol.GWTMaintenanceActivityService#findByRaS(org.fourgeeks.gha.domain.gmh.RaS)
	 */
	@Override
	public List<MaintenanceActivity> findByServiceResource(ServiceResource serviceResource) throws GHAEJBException {
		return ejbService.findByServiceResource(serviceResource);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.GWTMaintenanceActivityService#getAll()
	 */
	@Override
	public List<MaintenanceActivity> getAll() throws GHAEJBException {
		return ejbService.getAll();
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.GWTMaintenanceActivityService#getAll(int, int)
	 */
	@Override
	public List<MaintenanceActivity> getAll(int offset, int size)
			throws GHAEJBException {
		return ejbService.getAll(offset, size);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.GWTMaintenanceActivityService#save(org.fourgeeks.gha.domain.gmh.MaintenanceActivity)
	 */
	@Override
	public MaintenanceActivity save(MaintenanceActivity maintenanceActivity)
			throws GHAEJBException {
		return ejbService.save(maintenanceActivity	);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.GWTMaintenanceActivityService#update(org.fourgeeks.gha.domain.gmh.MaintenanceActivity)
	 */
	@Override
	public MaintenanceActivity update(MaintenanceActivity maintenanceActivity)
			throws GHAEJBException {
		return ejbService.update(maintenanceActivity);
	}

}
