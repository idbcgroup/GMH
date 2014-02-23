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
 * Session Bean implementation class EiaCorrectiveMaintenancePDTProcessor
 */
@Stateless
public class EiaCorrectiveMaintenancePDTProcessor implements PDTProcessor {

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
			Eia eia = (Eia) data.get("eia");
			EiaDamageReport report = (EiaDamageReport) data
					.get("eiaDamageReport");

			// se cambia el estado del equipo a dañado
			eia.setState(EiaStateEnum.DAMAGED);
			eia = eiaService.update(eia);

			// se crea el mantenimiento correctivo
			EiaCorrectiveMaintenance cm = new EiaCorrectiveMaintenance();
			cm.setDamageReport(report);
			cm.setDescription(report.getDamageMotive());
			cm = maintenanceService.saveCorrectiveMaintenance(cm);

			// se crea la orden de servicio de mantenimiento
			MaintenanceServiceOrder serviceOrder = new MaintenanceServiceOrder();
			serviceOrder.setMaintenance(cm);
			serviceOrder.setOpeningTimestamp(new Timestamp(time));
			serviceOrder.setServiceOrderNumber("MSO0001");
			serviceOrder.setState(ServiceOrderState.ACTIVE);
			serviceOrder.setMaintenanceProvider(eia.getMaintenanceProvider());
			serviceOrder = serviceOrderService.save(serviceOrder);

		} catch (Exception e) {
			String msg = "ERROR: procesando mensaje en CorrectiveMaintenancePDTProcessor: ";
			logger.log(Level.INFO, msg, e);
		}
	}
}
