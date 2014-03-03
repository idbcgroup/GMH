package org.fourgeeks.gha.ejb;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fourgeeks.gha.ejb.gmh.EiaMaintenancePlanificationServiceRemote;
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
			+ "org.fourgeeks.gha.ejb.gmh.EiaMaintenancePlanificationServiceRemote")
	EiaMaintenancePlanificationServiceRemote planifService;

	@Override
	public void doWork() {
		logger.log(Level.INFO, "entro en el handler");

	}

}
