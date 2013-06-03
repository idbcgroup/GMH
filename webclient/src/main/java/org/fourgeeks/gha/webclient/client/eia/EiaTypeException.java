/**
 * 
 */
package org.fourgeeks.gha.webclient.client.eia;

import java.io.Serializable;

/**
 * @author emiliot
 *
 */
public class EiaTypeException extends Exception implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EiaTypeException() {
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return "Error en el servicio EiaType";
	}
}
