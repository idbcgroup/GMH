package org.fourgeeks.gha.ejb.pdt;

import java.util.Calendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fourgeeks.gha.domain.glm.Bsp;
import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaPreventiveMaintenance;

/**
 * Session Bean implementation class EiaPreventiveMaintenancePDTProcessor
 */
@Stateless
public class EiaPreventiveMaintenancePDTProcessor implements PDTProcessor {

	private final static Logger logger = Logger
			.getLogger(EiaPreventiveMaintenancePDTProcessor.class.getName());

	@EJB(lookup = "java:global/ear-1/ejb-1/MaintenanceServiceOrderService!"
			+ "org.fourgeeks.gha.ejb.ess.MaintenanceServiceOrderServiceLocal")
	@Override
	public void processMessage(final HashMap<String, Object> params) {
		long time = Calendar.getInstance().getTimeInMillis();

		try {
			long scheduledTime = (Long) params.get("scheduleDate");
			EiaMaintenancePlanification planif = (EiaMaintenancePlanification) params
					.get("planif");
			Bsp bsp = planif.getMaintenanceProvider();

			// se crea el mantenimiento preventivo
			EiaPreventiveMaintenance prevMaintenance = new EiaPreventiveMaintenance();

		} catch (Exception e) {
			logger.log(
					Level.WARNING,
					"ERROR: procesando mensaje en PreventiveMaintenancePDTProcessor: ",
					e);
		}
	}
}
