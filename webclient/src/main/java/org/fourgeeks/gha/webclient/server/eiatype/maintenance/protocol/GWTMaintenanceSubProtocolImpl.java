/**
 * 
 */
package org.fourgeeks.gha.webclient.server.eiatype.maintenance.protocol;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.MaintenanceSubProtocol;
import org.fourgeeks.gha.ejb.gmh.MaintenanceSubProtocolServiceRemote;
import org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.GWTMaintenanceSubProtocolService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 * 
 */
@WebServlet(urlPatterns = { "/webclient/maintenanceSubProtocol" })
public class GWTMaintenanceSubProtocolImpl extends RemoteServiceServlet
		implements GWTMaintenanceSubProtocolService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB(lookup = "java:global/ear-1/ejb-1/MaintenanceSubProtocolService")
	MaintenanceSubProtocolServiceRemote service;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.
	 * GWTMaintenanceSubProtocolService#delete(long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		service.delete(Id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.
	 * GWTMaintenanceSubProtocolService
	 * #findByProtocolActivity(org.fourgeeks.gha.domain.gmh.ProtocolActivity)
	 */
	@Override
	public List<MaintenanceSubProtocol> findByMaintenanceActivity(
			MaintenanceActivity maintenanceActivity) throws GHAEJBException {
		return service.findByMaintenanceActivity(maintenanceActivity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.
	 * GWTMaintenanceSubProtocolService#find(long)
	 */
	@Override
	public MaintenanceSubProtocol find(long Id) throws GHAEJBException {
		return service.find(Id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.
	 * GWTMaintenanceSubProtocolService#getAll()
	 */
	@Override
	public List<MaintenanceSubProtocol> getAll() throws GHAEJBException {
		return service.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.
	 * GWTMaintenanceSubProtocolService#getAll(int, int)
	 */
	@Override
	public List<MaintenanceSubProtocol> getAll(int offset, int size)
			throws GHAEJBException {
		return service.getAll(offset, size);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.
	 * GWTMaintenanceSubProtocolService
	 * #save(org.fourgeeks.gha.domain.gmh.MaintenanceSubProtocol)
	 */
	@Override
	public MaintenanceSubProtocol save(
			MaintenanceSubProtocol maintenanceSubProtocol)
			throws GHAEJBException {
		return service.save(maintenanceSubProtocol);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.
	 * GWTMaintenanceSubProtocolService
	 * #update(org.fourgeeks.gha.domain.gmh.MaintenanceSubProtocol)
	 */
	@Override
	public MaintenanceSubProtocol update(
			MaintenanceSubProtocol maintenanceSubProtocol)
			throws GHAEJBException {
		return service.update(maintenanceSubProtocol);
	}

}
