/**
 * 
 */
package org.fourgeeks.gha.webclient.server.eiatype.maintenance.plan;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.ejb.gmh.MaintenancePlanServiceRemote;
import org.fourgeeks.gha.webclient.client.eiatype.maintenance.plan.GWTMaintenancePlanService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 *
 */
@WebServlet(urlPatterns = {"/webclient/maintenancePlanService"})
public class GWTMaintenancePlanServiceImpl extends RemoteServiceServlet implements
		GWTMaintenancePlanService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB(name = "gmh.maintenancePlanService")
	MaintenancePlanServiceRemote ejbService;
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.plan.GWTMaintenancePlanService#delete(long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		ejbService.delete(Id);
	}
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.plan.GWTMaintenancePlanService#findByEiaType(org.fourgeeks.gha.domain.gmh.EiaType)
	 */
	@Override
	public List<MaintenancePlan> findByEiaType(EiaType eiaType)
			throws GHAEJBException {
		return ejbService.findByEiaType(eiaType);
	}
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.plan.GWTMaintenancePlanService#findByEiaType(org.fourgeeks.gha.domain.gmh.EiaType, int, int)
	 */
	@Override
	public List<MaintenancePlan> findByEiaType(EiaType eiaType, int offset,
			int size) throws GHAEJBException {
		return ejbService.findByEiaType(eiaType, offset, size);
	}
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.plan.GWTMaintenancePlanService#find(long)
	 */
	@Override
	public MaintenancePlan find(long Id) throws GHAEJBException {
		return ejbService.find(Id);
	}
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.plan.GWTMaintenancePlanService#getAll()
	 */
	@Override
	public List<MaintenancePlan> getAll() throws GHAEJBException {
		return ejbService.getAll();
	}
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.plan.GWTMaintenancePlanService#getAll(int, int)
	 */
	@Override
	public List<MaintenancePlan> getAll(int offset, int size)
			throws GHAEJBException {
		return ejbService.getAll(offset, size);
	}
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.plan.GWTMaintenancePlanService#save(org.fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public MaintenancePlan save(MaintenancePlan maintenancePlan)
			throws GHAEJBException {
		return ejbService.save(maintenancePlan);
	}
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.plan.GWTMaintenancePlanService#update(org.fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public MaintenancePlan update(MaintenancePlan maintenancePlan)
			throws GHAEJBException {
		return ejbService.update(maintenancePlan);
	}
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.plan.GWTMaintenancePlanService#find(org.fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public List<MaintenancePlan> find(MaintenancePlan maintenancePlan)
			throws GHAEJBException {
		return ejbService.find(maintenancePlan);
	}
}
