package org.fourgeeks.gha.ejb;

import javax.ejb.Local;

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
	 * punto de entrada para que  
	 */
	public void doWork();
}
