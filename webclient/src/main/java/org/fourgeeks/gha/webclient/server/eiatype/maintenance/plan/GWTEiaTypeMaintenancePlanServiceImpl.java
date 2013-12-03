/**
 * 
 */
package org.fourgeeks.gha.webclient.server.eiatype.maintenance.plan;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.ejb.gmh.EiaTypeMaintenancePlanServiceRemote;
import org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.GWTEiaTypeMaintenancePlanService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 * 
 */
@WebServlet(urlPatterns = { "/webclient/eiaTypeMaintenancePlanService" })
public class GWTEiaTypeMaintenancePlanServiceImpl extends RemoteServiceServlet
		implements GWTEiaTypeMaintenancePlanService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB(lookup = "java:global/ear-1/ejb-1/EiaTypeMaintenancePlanService")
	EiaTypeMaintenancePlanServiceRemote ejbService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.
	 * GWTEiaTypeMaintenancePlanService#delete(long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		ejbService.delete(Id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.
	 * GWTEiaTypeMaintenancePlanService
	 * #save(org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan)
	 */
	@Override
	public EiaTypeMaintenancePlan save(
			EiaTypeMaintenancePlan eiaTypeMaintenancePlan)
			throws GHAEJBException {
		return ejbService.save(eiaTypeMaintenancePlan);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.
	 * GWTEiaTypeMaintenancePlanService
	 * #findByEiaType(org.fourgeeks.gha.domain.gmh.EiaType)
	 */
	@Override
	public List<EiaTypeMaintenancePlan> findByEiaType(EiaType eiaType)
			throws GHAEJBException {
		return ejbService.findByEiaType(eiaType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.
	 * GWTEiaTypeMaintenancePlanService
	 * #findByMaintenancePlan(org.fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public List<EiaTypeMaintenancePlan> findByMaintenancePlan(
			MaintenancePlan maintenancePlan) throws GHAEJBException {
		return ejbService.findByMaintenancePlan(maintenancePlan);
	}

}
