package org.fourgeeks.gha.ejb.pdt;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.enu.ServiceOrderState;
import org.fourgeeks.gha.domain.ess.MaintenanceServiceOrder;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaCorrectiveMaintenance;
import org.fourgeeks.gha.domain.gmh.EiaDamageReport;
import org.fourgeeks.gha.ejb.ess.MaintenanceServiceOrderService;
import org.fourgeeks.gha.ejb.ess.MaintenanceServiceOrderServiceLocal;
import org.fourgeeks.gha.ejb.gmh.EiaMaintenanceServiceRemote;
import org.fourgeeks.gha.ejb.gmh.EiaServiceRemote;

/**
 * Session Bean implementation class
 * CorrectiveMaintenanceServiceOrderPDTProcessor
 */
@Stateless
public class CorrectiveMaintenancePDTProcessor implements PDTProcessor {

	private final static Logger logger = Logger
			.getLogger(MaintenanceServiceOrderService.class.getName());

	@EJB(lookup = "java:global/ear-1/ejb-1/MaintenanceServiceOrderService!"
			+ "org.fourgeeks.gha.ejb.ess.MaintenanceServiceOrderServiceLocal")
	MaintenanceServiceOrderServiceLocal serviceOrderService;

	@EJB(lookup = "java:global/ear-1/ejb-1/EiaMaintenanceService!"
			+ "org.fourgeeks.gha.ejb.gmh.EiaMaintenanceServiceRemote")
	EiaMaintenanceServiceRemote maintenanceService;

	@EJB(lookup = "java:global/ear-1/ejb-1/EiaService!"
			+ "org.fourgeeks.gha.ejb.gmh.EiaServiceRemote")
	EiaServiceRemote eiaService;

	@Override
	public void processMessage(HashMap<String, Object> data) {
		long time = (new Date()).getTime();

		try {
			logger.log(Level.INFO, "AAA 1");
			Eia eia = (Eia) data.get("eia");
			logger.log(Level.INFO, "AAA 2");
			EiaDamageReport report = (EiaDamageReport) data
					.get("eiaDamageReport");
			logger.log(Level.INFO, "AAA 3");

			// se cambia el estado del equipo a dañado
			eia.setState(EiaStateEnum.DAMAGED);
			logger.log(Level.INFO, "AAA 4");
			eia = eiaService.update(eia);
			logger.log(Level.INFO, "AAA 5");

			// se crea el mantenimiento correctivo
			EiaCorrectiveMaintenance cm = new EiaCorrectiveMaintenance();
			logger.log(Level.INFO, "AAA 6");
			cm.setDamageReport(report);
			logger.log(Level.INFO, "AAA 7");
			cm.setDescription(report.getDamageMotive());
			logger.log(Level.INFO, "AAA 8");
			cm = maintenanceService.saveCorrectiveMaintenance(cm);
			logger.log(Level.INFO, "AAA 9");

			// se crea la orden de servicio de mantenimiento
			MaintenanceServiceOrder serviceOrder = new MaintenanceServiceOrder();
			logger.log(Level.INFO, "AAA 10");
			serviceOrder.setMaintenance(cm);
			logger.log(Level.INFO, "AAA 11");
			serviceOrder.setOpeningTimestamp(new Timestamp(time));
			logger.log(Level.INFO, "AAA 12");
			serviceOrder.setServiceOrderNumber("MSO0001");
			logger.log(Level.INFO, "AAA 13");
			serviceOrder.setState(ServiceOrderState.ACTIVE);
			logger.log(Level.INFO, "AAA 14");
			// maintenance.setMaintenanceProvider(eia.getMaintenanceProvider());
			logger.log(Level.INFO, "AAA 15");
			serviceOrder = serviceOrderService.save(serviceOrder);
			logger.log(Level.INFO, "AAA 16");

		} catch (Exception e) {
			String msg = "ERROR: procesando mensaje en CorrectiveMaintenancePDTProcessor: ";
			logger.log(Level.INFO, msg, e);
		}
	}
}
