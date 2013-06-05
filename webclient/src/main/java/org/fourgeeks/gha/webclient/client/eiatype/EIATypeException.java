/**
 * 
 */
package org.fourgeeks.gha.webclient.client.eiatype;

import java.io.Serializable;

/**
 * @author emiliot
 *
 */
public class EIATypeException extends Exception implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EIATypeException() {
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return "Error en el servicio EiaType";
	}
}
