/**
 * 
 */
package org.fourgeeks.gha.webclient.server.eiatype.maintenance.plan;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.ejb.gmh.EiaTypeMaintenancePlanServiceRemote;
import org.fourgeeks.gha.webclient.client.eiatype.maintenance.plan.GWTEiaTypeMaintenancePlanService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 *
 */
@WebServlet(urlPatterns = {"/webclient/eiaTypeMaintenancePlanService"})
public class GWTEiaTypeMaintenancePlanImpl extends RemoteServiceServlet implements
		GWTEiaTypeMaintenancePlanService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB(name = "gmh.eiaTypeMaintenancePlanService")
	EiaTypeMaintenancePlanServiceRemote ejbService;

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.GWTEiaTypeMaintenancePlanService#delete(long)
	 */
	@Override
	public void delete(long Id) throws EJBException {
		ejbService.delete(Id);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.GWTEiaTypeMaintenancePlanService#findByEiaType(long)
	 */
	@Override
	public List<EiaTypeMaintenancePlan> findByEiaType(EiaType eiaType)
			throws EJBException {
		return ejbService.findByEiaType(eiaType);
	}
	
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.plan.GWTEiaTypeMaintenancePlanService#findByEiaType(org.fourgeeks.gha.domain.gmh.EiaType, int, int)
	 */
	@Override
	public List<EiaTypeMaintenancePlan> findByEiaType(EiaType eiaType,
			int offset, int size) throws EJBException {
		return ejbService.findByEiaType(eiaType, offset, size);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.GWTEiaTypeMaintenancePlanService#find(long)
	 */
	@Override
	public EiaTypeMaintenancePlan find(long Id) throws EJBException {
		return ejbService.find(Id);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.GWTEiaTypeMaintenancePlanService#getAll()
	 */
	@Override
	public List<EiaTypeMaintenancePlan> getAll() throws EJBException {
		return ejbService.getAll();
	}
	
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.plan.GWTEiaTypeMaintenancePlanService#getAll(int, int)
	 */
	@Override
	public List<EiaTypeMaintenancePlan> getAll(int offset, int size)
			throws EJBException {
		return ejbService.getAll(offset, size);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.GWTEiaTypeMaintenancePlanService#save(org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan)
	 */
	@Override
	public EiaTypeMaintenancePlan save(
			EiaTypeMaintenancePlan eiaTypeMaintenancePlan) throws EJBException {
		return ejbService.save(eiaTypeMaintenancePlan);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.GWTEiaTypeMaintenancePlanService#update(org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan)
	 */
	@Override
	public EiaTypeMaintenancePlan update(
			EiaTypeMaintenancePlan eiaTypeMaintenancePlan) throws EJBException {
		return ejbService.update(eiaTypeMaintenancePlan);
	}

}
