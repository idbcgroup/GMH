package org.fourgeeks.gha.webclient.server.eiatype.maintenance.activity;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivityMaintenanceProtocol;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;
import org.fourgeeks.gha.ejb.gmh.MaintenanceActivityMaintenanceProtocolServiceRemote;
import org.fourgeeks.gha.webclient.client.maintenanceprotocol.activities.GWTMaintenanceActivityMaintenanceProtocolService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 * 
 */
@WebServlet(urlPatterns = { "/webclient/maintenanceActivityMaintenanceProtocolService" })
public class GWTMaintenanceActivityMaintenanceProtocolImpl extends
		RemoteServiceServlet implements
		GWTMaintenanceActivityMaintenanceProtocolService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB(lookup = "java:global/ear-1/ejb-1/MaintenanceActivityMaintenanceProtocolService")
	MaintenanceActivityMaintenanceProtocolServiceRemote ejbService;

	@Override
	public void delete(long Id) throws GHAEJBException {
		ejbService.delete(Id);
	}

	@Override
	public MaintenanceActivityMaintenanceProtocol save(
			MaintenanceActivityMaintenanceProtocol entity)
			throws GHAEJBException {
		return ejbService.save(entity);
	}

	@Override
	public List<MaintenanceActivityMaintenanceProtocol> findByMaintenanceProtocol(
			MaintenanceProtocol maintenanceProtocol) throws GHAEJBException {
		return ejbService.findByMaintenanceProtocol(maintenanceProtocol);
	}

	@Override
	public List<MaintenanceActivityMaintenanceProtocol> findByMaintenanceActivity(
			MaintenanceActivity maintenanceActivity) throws GHAEJBException {
		return ejbService.findByMaintenanceActivity(maintenanceActivity);
	}

}
