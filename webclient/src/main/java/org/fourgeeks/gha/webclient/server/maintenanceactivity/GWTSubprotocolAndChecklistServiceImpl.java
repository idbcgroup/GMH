/**
 * 
 */
package org.fourgeeks.gha.webclient.server.maintenanceactivity;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.Activity;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.SubProtocolAndChecklist;
import org.fourgeeks.gha.ejb.gmh.SubProtocolAndCheklistServiceRemote;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.subprotocol.GWTSubProtocolAndChecklistService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 * 
 */
@WebServlet(urlPatterns = { "/webclient/subProtocolAndCheklist" })
public class GWTSubprotocolAndChecklistServiceImpl extends RemoteServiceServlet
		implements GWTSubProtocolAndChecklistService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB(lookup = "java:global/ear-1/ejb-1/SubProtocolAndCheklistService!"
			+ "org.fourgeeks.gha.ejb.gmh.SubProtocolAndCheklistServiceRemote")
	SubProtocolAndCheklistServiceRemote service;

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
	public List<SubProtocolAndChecklist> findByParentActivity(
			Activity parentActivity) throws GHAEJBException {
		return service.findByParentActivity(parentActivity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.
	 * GWTMaintenanceSubProtocolService#find(long)
	 */
	@Override
	public SubProtocolAndChecklist find(long Id) throws GHAEJBException {
		return service.find(Id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.
	 * GWTMaintenanceSubProtocolService#getAll()
	 */
	@Override
	public List<SubProtocolAndChecklist> getAll() throws GHAEJBException {
		return service.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.
	 * GWTMaintenanceSubProtocolService#getAll(int, int)
	 */
	@Override
	public List<SubProtocolAndChecklist> getAll(int offset, int size)
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
	public SubProtocolAndChecklist save(
			SubProtocolAndChecklist maintenanceSubProtocol)
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
	public SubProtocolAndChecklist update(
			SubProtocolAndChecklist maintenanceSubProtocol)
			throws GHAEJBException {
		return service.update(maintenanceSubProtocol);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceactivity.subprotocol.
	 * GWTSubProtocolAndChecklistService#update(java.util.List)
	 */
	@Override
	public List<SubProtocolAndChecklist> update(
			List<SubProtocolAndChecklist> maintenanceSubProtocols)
			throws GHAEJBException {
		return service.update(maintenanceSubProtocols);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceactivity.subprotocol.
	 * GWTSubProtocolAndChecklistService#delete(java.util.List)
	 */
	@Override
	public void delete(List<SubProtocolAndChecklist> entities)
			throws GHAEJBException {
		service.delete(entities);
	}
}
