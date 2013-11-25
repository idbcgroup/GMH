/**
 * 
 */
package org.fourgeeks.gha.webclient.server.eiapreventivemaintenanceplanification;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaCorrectiveMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaPreventiveMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.ejb.gmh.EiaMaintenancePlanificationService;
import org.fourgeeks.gha.ejb.gmh.EiaMaintenancePlanificationServiceRemote;
import org.fourgeeks.gha.webclient.client.eiamaintenanceplanification.GWTEiaMaintenancePlanificationService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 * 
 */
@WebServlet(urlPatterns = { "/webclient/EiaMaintenancePlanification" })
public class GWTEiaMaintenancePlanificationServiceImpl extends
		RemoteServiceServlet implements GWTEiaMaintenancePlanificationService {

	private static final long serialVersionUID = 1L;

	@EJB(name = "gmh.EiaMaintenancePlanificationService")
	EiaMaintenancePlanificationServiceRemote serviceRemote;

	@Override
	public List<EiaMaintenancePlanification> find(EiaType eiaType)
			throws GHAEJBException {

		List<EiaMaintenancePlanification> resultList = serviceRemote
				.find(eiaType);
		return resultList;
	}

	@Override
	public EiaCorrectiveMaintenancePlanification getCorrectiveMaintenancePlanification(
			EiaMaintenancePlanificationService entity) throws GHAEJBException {

		EiaCorrectiveMaintenancePlanification correctiveMaintenance = serviceRemote
				.getCorrectiveMaintenancePlanification(entity);
		return correctiveMaintenance;
	}

	@Override
	public EiaPreventiveMaintenancePlanification getPreventiveMaintenancePlanification(
			EiaMaintenancePlanificationService entity) throws GHAEJBException {

		EiaPreventiveMaintenancePlanification preventiveMaintenance = serviceRemote
				.getPreventiveMaintenancePlanification(entity);
		return preventiveMaintenance;
	}

	@Override
	public EiaCorrectiveMaintenancePlanification saveCorrectiveMaintenance(
			EiaCorrectiveMaintenancePlanification entity)
			throws GHAEJBException {

		EiaCorrectiveMaintenancePlanification savedEntity = serviceRemote
				.saveCorrectiveMaintenance(entity);
		return savedEntity;
	}

	@Override
	public EiaPreventiveMaintenancePlanification savePreventiveMaintenance(
			EiaPreventiveMaintenancePlanification entity)
			throws GHAEJBException {

		EiaPreventiveMaintenancePlanification savedEntity = serviceRemote
				.savePreventiveMaintenance(entity);
		return savedEntity;
	}
}
