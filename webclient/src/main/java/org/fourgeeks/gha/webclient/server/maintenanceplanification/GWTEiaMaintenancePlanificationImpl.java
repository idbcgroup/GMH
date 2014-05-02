/**
 * 
 */
package org.fourgeeks.gha.webclient.server.maintenanceplanification;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaPlanificationEntity;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.ejb.gmh.EiaMaintenancePlanificationServiceRemote;
import org.fourgeeks.gha.webclient.client.eiamaintenanceplanification.GWTMaintenancePlanificationService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 * 
 */
@WebServlet(urlPatterns = { "/webclient/EiaMaintenancePlanification" })
public class GWTEiaMaintenancePlanificationImpl extends RemoteServiceServlet
		implements GWTMaintenancePlanificationService {

	private static final long serialVersionUID = 1L;

	@EJB(lookup = "java:global/ear-1/ejb-1/EiaMaintenancePlanificationService!"
			+ "org.fourgeeks.gha.ejb.gmh.EiaMaintenancePlanificationServiceRemote")
	EiaMaintenancePlanificationServiceRemote serviceRemote;

	@Override
	public List<EiaMaintenancePlanification> find(EiaType eiaType)
			throws GHAEJBException {
		List<EiaMaintenancePlanification> preventivePlanifs = serviceRemote
				.find(eiaType);

		return preventivePlanifs;
	}

	@Override
	public EiaMaintenancePlanification save(
			EiaMaintenancePlanification preventivePlanif)
			throws GHAEJBException {
		EiaMaintenancePlanification savedDamageReport = serviceRemote
				.save(preventivePlanif);
		return savedDamageReport;
	}

	@Override
	public boolean existMantenancePlanification(Eia eia,
			EiaTypeMaintenancePlan plan) throws GHAEJBException {
		return serviceRemote.existMantenancePlanification(eia, plan);

	}

	@Override
	public List<EiaPlanificationEntity> findEiaMaintenancePlanificationStatus(
			Eia eia, EiaTypeMaintenancePlan plan) throws GHAEJBException {
		return serviceRemote.findEiaMaintenancePlanificationStatus(eia, plan);
	}

}
