package org.fourgeeks.gha.ejb;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
public class CorrectiveMaintenanceServiceOrderPDTProcessor implements
		PDTProcessor {

	@PersistenceContext
	private EntityManager em;

	private final static Logger logger = Logger
			.getLogger(MaintenanceServiceOrderService.class.getName());

	@EJB
	MaintenanceServiceOrderServiceLocal serviceOrderService;

	@EJB
	EiaMaintenanceServiceRemote maintenanceService;

	@EJB
	EiaServiceRemote eiaService;

	@Override
	public void processMessage(HashMap<String, Object> data) {
		long time = (new Date()).getTime();

		try {
			Eia eia = (Eia) data.get("eia");
			EiaDamageReport damageReport = (EiaDamageReport) data
					.get("eiaDamageReport");

			// se cambia el estado del equipo a dañado
			eia.setState(EiaStateEnum.DAMAGED);

			// se crea el mantenimiento correctivo
			EiaCorrectiveMaintenance correctiveMaintenance = new EiaCorrectiveMaintenance();
			correctiveMaintenance.setDamageReport(damageReport);
			correctiveMaintenance
					.setDescription(damageReport.getDamageMotive());

			// se crea la orden de servicio de mantenimiento
			MaintenanceServiceOrder serviceOrder = new MaintenanceServiceOrder();
			serviceOrder.setMaintenance(correctiveMaintenance);
			// maintenance.setMaintenanceProvider(eia.getMaintenanceProvider());
			serviceOrder.setOpeningTimestamp(new Timestamp(time));
			serviceOrder.setServiceOrderNumber("MSO0001");
			serviceOrder.setState(ServiceOrderState.ACTIVE);

			em.merge(eia);
			em.persist(serviceOrder);
			em.persist(correctiveMaintenance);

			// se guarda el mantenimiento en BD
			// serviceOrderService.save(serviceOrder);
			// se guarda la orden de servicio en la BD
			// maintenanceService.saveCorrectiveMaintenance(correctiveMaintenance);
			// se actualiza el equipo en BD
			// eiaService.update(eia);

		} catch (Exception e) {
			String msg = "ERROR: procesando mensaje en CorrectiveMaintenanceServiceOrderPDTProcessor: ";
			logger.log(Level.INFO, msg, e);
		}
	}
}
