package org.fourgeeks.gha.ejb;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fourgeeks.gha.domain.TimerParams;
import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.ejb.gmh.EiaMaintenancePlanificationServiceLocal;
import org.fourgeeks.gha.ejb.pdt.PDTMessageProducerLocal;

/**
 * @author naramirez
 */
@Stateless
public class EiaPreventiveMaintenanceTimerServiceHandler implements
		TimerServiceHandler {

	private final static Logger logger = Logger
			.getLogger(EiaPreventiveMaintenanceTimerServiceHandler.class
					.getName());

	@EJB
	PDTMessageProducerLocal messageService;

	@EJB(lookup = "java:global/ear-1/ejb-1/EiaMaintenancePlanificationService!"
			+ "org.fourgeeks.gha.ejb.gmh.EiaMaintenancePlanificationServiceLocal")
	EiaMaintenancePlanificationServiceLocal planifService;

	@Override
	public void doWork(TimerParams timerParams) {
		try {
			Calendar calendar = Calendar.getInstance();
			List<EiaMaintenancePlanification> planifs = planifService.getAll();

			// para cada planificacion de mantenimiento
			for (EiaMaintenancePlanification planif : planifs) {
				// si el equipo no esta EN OPERACION no se programan nuevos
				// mantenimientos
				Eia eia = planif.getEia();
				if (eia.getState() != EiaStateEnum.IN_OPERATION)
					continue;

				// obtengo la fecha del ultimo mantenimiento programado
				Date lastMaintenanceDate = planifService
						.getScheduleDateOfLastMaintenance(planif);

				// obtengo la fecha de inicio de la planificacion
				Date beginningDate = planif.getBeginningDate();

				// si no se han programado mantenimientos se toma como la ultima
				// fecha de programacion la fecha de inicio de la planificacion
				long lastScheduleTime = (lastMaintenanceDate == null) ? beginningDate
						.getTime() : lastMaintenanceDate.getTime();

				// si ya paso la fecha del ultimo mantenimiento programado
				long actualTime = calendar.getTimeInMillis();
				if (actualTime >= lastScheduleTime) {
					calendar.setTimeInMillis(lastScheduleTime);
					MaintenancePlan maintenancePlan = planif.getPlan()
							.getMaintenancePlan();

					// frecuencia del plan de mantenimiento en segundos
					int planFrequency = GHAUtil.getDurationInSeconds(
							maintenancePlan.getFrequency(),
							maintenancePlan.getPot());

					// rango de tiempo para programar nuevos mantenimientos
					int timeRange = GHAUtil.getDurationInSeconds(
							timerParams.getDuration(),
							timerParams.getDurationPot());

					// cantidad de mantenimientos que se pueden programar en el
					// rango de tiempo dado
					int cantOfMaintenances = timeRange / planFrequency;

					// realizo la programacion de las nuevas planificaciones
					for (int i = 0; i < cantOfMaintenances; i++) {
						// nueva fecha de programación del mantenimiento
						calendar.add(Calendar.SECOND, planFrequency);

						// se preparan los datos a ser enviados al PDT
						HashMap<String, Object> params = new HashMap<String, Object>();
						params.put("planif", planif);
						params.put("scheduleDate", calendar.getTimeInMillis());

						// envio los datos al PDT
						String transactionCode = "preventive-maintenance";
						messageService.sendMessage(transactionCode, params);
					}
				}
			}
		} catch (GHAEJBException e) {
			String msg = "ERROR: problemas en EiaPreventiveMaintenanceTimerServiceHandler: ";
			logger.log(Level.INFO, msg, e);
		}
	}
}
