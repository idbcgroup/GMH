/**
 * 
 */
package org.fourgeeks.gha.webclient.server.maintenance;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocolStadisticData;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocols;
import org.fourgeeks.gha.ejb.gmh.MaintenanceProtocolsServiceRemote;
import org.fourgeeks.gha.webclient.client.maintenanceprotocols.GWTMaintenanceProtocolsService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 * 
 */
@WebServlet(urlPatterns = { "/webclient/maintenanceProtocolsService" })
public class GWTMaintenanceProtocolsServiceImpl extends RemoteServiceServlet
		implements GWTMaintenanceProtocolsService {

	private static final long serialVersionUID = 1L;

	@EJB(lookup = "java:global/ear-1/ejb-1/MaintenanceProtocolsService")
	MaintenanceProtocolsServiceRemote service;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceprotocols.
	 * GWTMaintenanceProtocolsService
	 * #copyActivities(org.fourgeeks.gha.domain.gmh.MaintenancePlan,
	 * org.fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public void copyActivities(MaintenancePlan planFrom, MaintenancePlan planTo)
			throws GHAEJBException {
		service.copyActivities(planFrom, planTo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceprotocols.
	 * GWTMaintenanceProtocolsService#delete(java.util.List)
	 */
	@Override
	public void delete(List<MaintenanceProtocols> entities)
			throws GHAEJBException {
		service.delete(entities);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.
	 * GWTMaintenanceActivityService#delete(long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		service.delete(Id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceprotocols.
	 * GWTMaintenanceProtocolsService
	 * #deleteByMaintenancePlan(org.fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public int deleteByMaintenancePlan(MaintenancePlan plan)
			throws GHAEJBException {
		int entitiesDeleted = service.deleteByMaintenancePlan(plan);
		return entitiesDeleted;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceprotocols.
	 * GWTMaintenanceProtocolsService
	 * #findByMaintenancePlan(org.fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public List<MaintenanceProtocols> findByMaintenancePlan(MaintenancePlan plan)
			throws GHAEJBException {
		List<MaintenanceProtocols> protocol = service
				.findByMaintenancePlan(plan);
		return protocol;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceprotocols.
	 * GWTMaintenanceProtocolsService
	 * #getStadisticInfo(org.fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public MaintenanceProtocolStadisticData getStadisticInfo(
			MaintenancePlan mantenancePlan) throws GHAEJBException {
		MaintenanceProtocolStadisticData stadisticInfo = service
				.getStadisticInfo(mantenancePlan);
		return stadisticInfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceprotocols.
	 * GWTMaintenanceProtocolsService
	 * #save(org.fourgeeks.gha.domain.gmh.MaintenanceProtocols)
	 */
	@Override
	public MaintenanceProtocols save(MaintenanceProtocols entity)
			throws GHAEJBException {
		MaintenanceProtocols entitiy = service.save(entity);
		return entitiy;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceprotocols.
	 * GWTMaintenanceProtocolsService#update(java.util.List)
	 */
	@Override
	public void update(List<MaintenanceProtocols> entities)
			throws GHAEJBException {
		service.update(entities);
	}

}
