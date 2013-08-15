/**
 * 
 */
package org.fourgeeks.gha.webclient.server.eiatype.maintenance.plan;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.ejb.gmh.EiaTypeMaintenancePlanServiceRemote;
import org.fourgeeks.gha.webclient.client.eiatype.maintenance.plan.GWTEiaTypeMaintenancePlanService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 *
 */
@WebServlet(urlPatterns = {"/webclient/eiaTypeMaintenancePlan"})
public class GWTEiaTypeMaintenancePlanImpl extends RemoteServiceServlet implements
		GWTEiaTypeMaintenancePlanService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB(name = "gmh.eiaTypeMaintenancePlanService")
	EiaTypeMaintenancePlanServiceRemote eiaTypeMantPlanService;

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.GWTEiaTypeMaintenancePlanService#delete(long)
	 */
	@Override
	public void delete(long Id) throws EJBException {
		eiaTypeMantPlanService.delete(Id);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.GWTEiaTypeMaintenancePlanService#findByEiaType(long)
	 */
	@Override
	public List<EiaTypeMaintenancePlan> findByEiaType(String eiaTypeCode)
			throws EJBException {
		return eiaTypeMantPlanService.findByEiaType(eiaTypeCode);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.GWTEiaTypeMaintenancePlanService#find(long)
	 */
	@Override
	public EiaTypeMaintenancePlan find(long Id) throws EJBException {
		return eiaTypeMantPlanService.find(Id);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.GWTEiaTypeMaintenancePlanService#getAll()
	 */
	@Override
	public List<EiaTypeMaintenancePlan> getAll() throws EJBException {
		return eiaTypeMantPlanService.getAll();
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.GWTEiaTypeMaintenancePlanService#save(org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan)
	 */
	@Override
	public EiaTypeMaintenancePlan save(
			EiaTypeMaintenancePlan eiaTypeMaintenancePlan) throws EJBException {
		return eiaTypeMantPlanService.save(eiaTypeMaintenancePlan);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.GWTEiaTypeMaintenancePlanService#update(org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan)
	 */
	@Override
	public EiaTypeMaintenancePlan update(
			EiaTypeMaintenancePlan eiaTypeMaintenancePlan) throws EJBException {
		return eiaTypeMantPlanService.update(eiaTypeMaintenancePlan);
	}

}
