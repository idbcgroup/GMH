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
 * Session Bean implementation class EiaPreventiveMaintenancePDTProcessor
 */
@Stateless
public class EiaPreventiveMaintenancePDTProcessor implements PDTProcessor {

	private final static Logger logger = Logger
			.getLogger(EiaPreventiveMaintenancePDTProcessor.class.getName());

	@EJB(lookup = "java:global/ear-1/ejb-1/MaintenanceServiceOrderService!"
			+ "org.fourgeeks.gha.ejb.ess.MaintenanceServiceOrderServiceLocal")
	MaintenanceServiceOrderServiceLocal serviceOrderService;

	@EJB(lookup = "java:global/ear-1/ejb-1/EiaMaintenanceService!"
			+ "org.fourgeeks.gha.ejb.gmh.EiaMaintenanceServiceRemote")
	EiaMaintenanceServiceRemote maintenanceService;

	@Override
	public void processMessage(HashMap<String, Object> params) {
		long time = (new Date()).getTime();
		EiaMaintenancePlanification planif = null;
		EiaPreventiveMaintenance prevMaintenance = null;

		try {
			planif = (EiaMaintenancePlanification) params.get("planif");
			Bsp bsp = planif.getMaintenanceProvider();

			// se crea el mantenimiento preventivo
			prevMaintenance = new EiaPreventiveMaintenance();
			prevMaintenance.setPlanification(planif);
			prevMaintenance.setProvider(bsp);
			prevMaintenance = maintenanceService
					.savePreventiveMaintenance(prevMaintenance);

			// se crea la orden de servicio de mantenimiento
			MaintenanceServiceOrder serviceOrder = new MaintenanceServiceOrder();
			serviceOrder.setMaintenance(prevMaintenance);
			serviceOrder.setOpeningTimestamp(new Timestamp(time));
			serviceOrder.setServiceOrderNumber("MSO0001");
			serviceOrder.setState(ServiceOrderState.ACTIVE);
			serviceOrder.setMaintenanceProvider(bsp);
			serviceOrder = serviceOrderService.save(serviceOrder);

		} catch (Exception e) {
			String msg = "ERROR: procesando mensaje en PreventiveMaintenancePDTProcessor: ";
			logger.log(Level.INFO, msg, e);
		}
	}
}
