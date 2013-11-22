/**
 * 
 */
package org.fourgeeks.gha.webclient.server.eiapreventivemaintenanceplanification;

import java.util.List;

import javax.ejb.EJB;

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
public class GWTEiaPreventiveMaintenancePlanificationImpl extends
		RemoteServiceServlet implements
		GWTEiaPreventiveMaintenancePlanificationService {

	private static final long serialVersionUID = 1L;

	@EJB(name = "gmh.EiaPreventiveMaintenancePlanificationService")
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
