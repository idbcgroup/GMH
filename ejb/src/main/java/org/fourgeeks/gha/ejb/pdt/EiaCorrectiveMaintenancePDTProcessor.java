package org.fourgeeks.gha.ejb.pdt;

import java.util.Calendar;
import java.util.HashMap;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.glm.Bsp;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.GlaLog;
import org.fourgeeks.gha.ejb.gmh.EiaServiceRemote;

/**
 * Session Bean implementation class EiaCorrectiveMaintenancePDTProcessor
 */
@Stateless
public class EiaCorrectiveMaintenancePDTProcessor implements PDTProcessor {

	// private final static Logger logger = Logger
	// .getLogger(MaintenanceServiceOrderService.class.getName());
	//
	// @EJB(lookup = "java:global/ear-1/ejb-1/MaintenanceServiceOrderService!"
	// + "org.fourgeeks.gha.ejb.ess.MaintenanceServiceOrderServiceLocal")
	// MaintenanceServiceOrderServiceLocal serviceOrderService;

	@EJB(lookup = "java:global/ear-1/ejb-1/EiaService!"
			+ "org.fourgeeks.gha.ejb.gmh.EiaServiceRemote")
	EiaServiceRemote eiaService;

	@Override
	public void processMessage(final HashMap<String, Object> params) {
		long time = Calendar.getInstance().getTimeInMillis();

		try {
			Eia eia = (Eia) params.get("eia");
			GlaLog report = (GlaLog) params
					.get("eiaDamageReport");

			// se cambia el estado del equipo a dañado
			eia.setState(EiaStateEnum.DAMAGED);
			eia = eiaService.update(eia);
			Bsp bsp = eia.getMaintenanceProvider();

			// se crea el mantenimiento correctivo
			// EiaCorrectiveMaintenance cm = new EiaCorrectiveMaintenance();
			// cm.setDamageReport(report);
			// cm.setDescription(report.getDamageMotive());
			// cm.setProvider(bsp);
			// cm.setScheduledDate(new java.sql.Date(time));
			// cm = maintenanceService.saveCorrectiveMaintenance(cm);

			// // se crea la orden de servicio de mantenimiento
			// MaintenanceServiceOrder serviceOrder = new
			// MaintenanceServiceOrder();
			// serviceOrder.setMaintenance(cm);
			// serviceOrder.setOpeningTimestamp(new Timestamp(time));
			// serviceOrder.setServiceOrderNumber("MSO0001");
			// serviceOrder.setState(ServiceOrderState.ACTIVE);
			// serviceOrder.setMaintenanceProvider(bsp);
			// serviceOrder = serviceOrderService.save(serviceOrder);

		} catch (Exception e) {
			// logger.log(
			// Level.WARNING,
			// "ERROR: procesando mensaje en CorrectiveMaintenancePDTProcessor: ",
			// e);
		}
	}
}
