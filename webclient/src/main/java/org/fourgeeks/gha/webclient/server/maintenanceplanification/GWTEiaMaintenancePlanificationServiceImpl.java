/**
 * 
 */
package org.fourgeeks.gha.webclient.server.maintenanceplanification;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaCorrectiveMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaPreventiveMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.ejb.gmh.EiaMaintenanceServiceRemote;
import org.fourgeeks.gha.webclient.client.eiamaintenance.GWTEiaMaintenanceService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot, naramirez
 * 
 */
@WebServlet(urlPatterns = { "/webclient/EiaMaintenancePlanification" })
public class GWTEiaMaintenancePlanificationServiceImpl extends
		RemoteServiceServlet implements GWTEiaMaintenanceService {

	private static final long serialVersionUID = 1L;

	@EJB(lookup = "java:global/ear-1/ejb-1/EiaMaintenancePlanificationService")
	EiaMaintenanceServiceRemote serviceRemote;

	@Override
	public List<EiaMaintenancePlanification> find(EiaType eiaType)
			throws GHAEJBException {

		List<EiaMaintenancePlanification> resultList = serviceRemote
				.find(eiaType);
		return resultList;
	}

	@Override
	public EiaCorrectiveMaintenancePlanification getCorrectiveMaintenancePlanification(
			EiaMaintenancePlanification entity) throws GHAEJBException {

		EiaCorrectiveMaintenancePlanification correctiveMaintenance = serviceRemote
				.getCorrectiveMaintenance(entity);
		return correctiveMaintenance;
	}

	@Override
	public EiaPreventiveMaintenancePlanification getPreventiveMaintenancePlanification(
			EiaMaintenancePlanification entity) throws GHAEJBException {

		EiaPreventiveMaintenancePlanification preventiveMaintenance = serviceRemote
				.getPreventiveMaintenance(entity);
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

	@Override
	public EiaCorrectiveMaintenancePlanification updateCorrectiveMaintenance(
			EiaCorrectiveMaintenancePlanification entity)
			throws GHAEJBException {

		EiaCorrectiveMaintenancePlanification updatedEntity = serviceRemote
				.updateCorrectiveMaintenance(entity);
		return updatedEntity;
	}

	@Override
	public EiaPreventiveMaintenancePlanification updatePreventiveMaintenance(
			EiaPreventiveMaintenancePlanification entity)
			throws GHAEJBException {

		EiaPreventiveMaintenancePlanification updatedEntity = serviceRemote
				.updatePreventiveMaintenance(entity);
		return updatedEntity;
	}
}
