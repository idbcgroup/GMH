package org.fourgeeks.gha.ejb.pdt;

import java.util.HashMap;

import javax.ejb.Local;

/**
 * Interface comun para las clases que se encargan de procesar los mensajes
 * consumidos por el PDT
 * 
 * @author nelson
 * 
 */
@Local
public interface PDTProcessor {
	/**
	 * 
	 * @param data
	 *            los datos el mensaje a procesar
	 */
	public void processMessage(HashMap<String, Object> data);
}
