package org.fourgeeks.gha.ejb;

import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.fourgeeks.gha.ejb.gmh.EiaMaintenancePlanificationServiceLocal;

/**
 * Singleton Session Bean implementation class GHATimerService
 * 
 * @author naramirez
 */
@Singleton
@Startup
public class GHATimerService {

	private final static Logger logger = Logger.getLogger(GHATimerService.class
			.getName());

	EiaMaintenancePlanificationServiceLocal service;

	/** */
	@Schedule(minute = "*/1", hour = "*")
	public void checkTimers() {
		logger.log(Level.INFO, "entrando en checkTimers");

		Calendar calendar = Calendar.getInstance();
		logger.log(Level.INFO, "calendar = " + calendar.getTime());

		calendar.add(Calendar.SECOND, 4);
		calendar.add(Calendar.MINUTE, 4);
		calendar.add(Calendar.HOUR_OF_DAY, 3);
		calendar.add(Calendar.DAY_OF_MONTH, 2);

		logger.log(Level.INFO, "calendar whit add = " + calendar.getTime());
	}
}
