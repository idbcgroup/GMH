package org.fourgeeks.gha.ejb;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * Singleton Session Bean implementation class GHATimerService
 */
@Singleton
@Startup
public class GHATimerService {

	private final static Logger logger = Logger.getLogger(GHATimerService.class
			.getName());

	/** */
	@Schedule(minute = "*/1", hour = "*")
	public void checkTimers() {
		logger.log(Level.INFO, "PROBANDO PROBANDO 1,2,3 PROBANDO!");
	}
}
