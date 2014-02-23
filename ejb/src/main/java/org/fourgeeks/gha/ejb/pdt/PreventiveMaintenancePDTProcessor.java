package org.fourgeeks.gha.ejb.pdt;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fourgeeks.gha.domain.enu.ServiceOrderState;
import org.fourgeeks.gha.domain.ess.MaintenanceServiceOrder;
import org.fourgeeks.gha.domain.glm.Bsp;
import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaPreventiveMaintenance;
import org.fourgeeks.gha.ejb.ess.MaintenanceServiceOrderServiceLocal;
import org.fourgeeks.gha.ejb.gmh.EiaMaintenanceServiceRemote;

/**
 * Session Bean implementation class
 * CorrectiveMaintenanceServiceOrderPDTProcessor
 */
@Stateless
public class PreventiveMaintenancePDTProcessor implements PDTProcessor {

	private final static Logger logger = Logger
			.getLogger(PreventiveMaintenancePDTProcessor.class.getName());

	@EJB(lookup = "java:global/ear-1/ejb-1/MaintenanceServiceOrderService!"
			+ "org.fourgeeks.gha.ejb.ess.MaintenanceServiceOrderServiceLocal")
	MaintenanceServiceOrderServiceLocal serviceOrderService;

	@EJB(lookup = "java:global/ear-1/ejb-1/EiaMaintenanceService!"
			+ "org.fourgeeks.gha.ejb.gmh.EiaMaintenanceServiceRemote")
	EiaMaintenanceServiceRemote maintenanceService;

	@Override
	public void processMessage(HashMap<String, Object> data) {
		long time = (new Date()).getTime();

		try {
			logger.log(Level.INFO, "BBB 1");
			EiaMaintenancePlanification planification = (EiaMaintenancePlanification) data
					.get("planification");

			// se crea el mantenimiento preventivo
			logger.log(Level.INFO, "BBB 2");
			EiaPreventiveMaintenance epm = new EiaPreventiveMaintenance();
			logger.log(Level.INFO, "BBB 3");
			epm.setPlanification(planification);
			logger.log(Level.INFO, "BBB 4");
			Bsp bsp = planification.getMaintenanceProvider();
			logger.log(Level.INFO, "BBB 5");
			epm.setProvider(bsp);
			logger.log(Level.INFO, "BBB 6");
			epm = maintenanceService.savePreventiveMaintenance(epm);
			logger.log(Level.INFO, "BBB 7");

			// se crea la orden de servicio de mantenimiento
			MaintenanceServiceOrder serviceOrder = new MaintenanceServiceOrder();
			logger.log(Level.INFO, "BBB 8");
			serviceOrder.setMaintenance(epm);
			logger.log(Level.INFO, "BBB 9");
			serviceOrder.setOpeningTimestamp(new Timestamp(time));
			logger.log(Level.INFO, "BBB 10");
			serviceOrder.setServiceOrderNumber("MSO0001");
			logger.log(Level.INFO, "BBB 11");
			serviceOrder.setState(ServiceOrderState.ACTIVE);
			logger.log(Level.INFO, "BBB 12");
			serviceOrder.setMaintenanceProvider(bsp);
			logger.log(Level.INFO, "BBB 13");
			serviceOrder = serviceOrderService.save(serviceOrder);
			logger.log(Level.INFO, "BBB 14");

		} catch (Exception e) {
			String msg = "ERROR: procesando mensaje en PreventiveMaintenancePDTProcessor: ";
			logger.log(Level.INFO, msg, e);
		}
	}
}
