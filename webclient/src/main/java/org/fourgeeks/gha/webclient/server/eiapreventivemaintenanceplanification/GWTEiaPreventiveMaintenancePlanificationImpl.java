/**
 * 
 */
package org.fourgeeks.gha.webclient.server.eiapreventivemaintenanceplanification;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaPreventiveMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.ejb.gmh.EiaPreventiveMaintenancePlanificationServiceRemote;
import org.fourgeeks.gha.webclient.client.eiapreventivemaintenanceplanification.GWTEiaPreventiveMaintenancePlanificationService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 * 
 */
@WebServlet(urlPatterns = { "/webclient/EiaPreventiveMaintenancePlanification" })
public class GWTEiaPreventiveMaintenancePlanificationImpl extends
		RemoteServiceServlet implements
		GWTEiaPreventiveMaintenancePlanificationService {

	private static final long serialVersionUID = 1L;

	@EJB(lookup = "java:global/ear-1/ejb-1/EiaPreventiveMaintenancePlanificationService")
	EiaPreventiveMaintenancePlanificationServiceRemote serviceRemote;

	@Override
	public List<EiaPreventiveMaintenancePlanification> find(EiaType eiaType)
			throws GHAEJBException {
		List<EiaPreventiveMaintenancePlanification> preventivePlanifs = serviceRemote
				.find(eiaType);

		return preventivePlanifs;
	}

	@Override
	public EiaPreventiveMaintenancePlanification save(
			EiaPreventiveMaintenancePlanification preventivePlanif)
			throws GHAEJBException {
		EiaPreventiveMaintenancePlanification savedDamageReport = serviceRemote
				.save(preventivePlanif);
		return savedDamageReport;
	}

}
