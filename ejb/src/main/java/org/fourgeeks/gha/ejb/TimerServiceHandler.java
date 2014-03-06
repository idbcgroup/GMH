package org.fourgeeks.gha.ejb;

import javax.ejb.Local;

import org.fourgeeks.gha.domain.TimerParams;

/**
 * Interface comun para las clases que se encargan de procesar los mensajes
 * consumidos por el PDT
 * 
 * @author nelson
 * 
 */
@Local
public interface TimerServiceHandler {

	/**
	 * @param timerParams
	 */
	public void doWork(TimerParams timerParams);
}
