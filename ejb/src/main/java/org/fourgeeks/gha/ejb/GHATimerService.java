package org.fourgeeks.gha.ejb;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.fourgeeks.gha.domain.TimerParams;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;

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

	@EJB
	TimerParamsServiceLocal service;

	/** */
	@Schedule(minute = "*/5", hour = "*")
	public void checkTimers() {
		logger.log(Level.INFO, "entrando en checkTimers");

		try {
			Context jndiContext = new InitialContext();
			List<TimerParams> timerParams = service.getAll();
			long currentTime = Calendar.getInstance().getTimeInMillis();

			for (TimerParams entity : timerParams) {
				Date lastTimeEffectuated = entity.getLastTimeEffectuated();

				// si el handler no se ha ejecutado nunca
				if (lastTimeEffectuated == null) {
					invokeTimerHandler(jndiContext, entity, currentTime);
				} else {
					long nextTime = getNextExecutionTime(entity);
					long diferenceTime = currentTime - nextTime;

					if (diferenceTime >= 0)
						invokeTimerHandler(jndiContext, entity, currentTime);
				}
			}

		} catch (Exception e) {
			logger.log(Level.INFO, "Error en GHATimerService.checkTimers: ", e);
		}
	}

	/**
	 * Return the next excecution time for the given timer parameters
	 * 
	 * @param entity
	 *            entity with the timer params
	 * @return the milliseconds of the next execution time
	 */
	private long getNextExecutionTime(TimerParams entity) {
		Calendar nextCalendar = Calendar.getInstance();
		nextCalendar.setTime(entity.getLastTimeEffectuated());

		nextCalendar.add(Calendar.SECOND, entity.getSeconds());
		nextCalendar.add(Calendar.MINUTE, entity.getMinutes());
		nextCalendar.add(Calendar.HOUR_OF_DAY, entity.getHours());
		nextCalendar.add(Calendar.DAY_OF_MONTH, entity.getDays());
		nextCalendar.add(Calendar.YEAR, entity.getYears());

		return nextCalendar.getTimeInMillis();
	}

	/**
	 * invoke the TimerHandler stored in the TimerParams entity and update the
	 * last execution time for this entity
	 * 
	 * @param jndiContext
	 *            context to made lookup of the JNDI name stored in the
	 *            TimerParams entity
	 * @param entity
	 *            the entity with the timer parameters
	 * @param time
	 *            the new last executed time in milliseconds
	 * @throws GHAEJBException
	 * @throws NamingException
	 */
	private void invokeTimerHandler(Context jndiContext, TimerParams entity,
			long time) throws GHAEJBException, NamingException {

		// se la asigno al entity con los parametros del timer
		entity.setLastTimeEffectuated(new Date(time));

		// agrego a la BD ultima vez que se ejecuto el handler
		entity = service.update(entity);

		// hago JNDI lookup del handler
		TimerServiceHandler handler = (TimerServiceHandler) jndiContext
				.lookup(entity.getJndiProcessorName());

		// ejecuto el handler
		handler.doWork();
	}
}
