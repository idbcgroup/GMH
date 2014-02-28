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
	 * @param params
	 *            los parametros requeridos pro el procesador del menaje para
	 *            ejecutar la logica del negocio
	 */
	public void processMessage(HashMap<String, Object> params);
}
