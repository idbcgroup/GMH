/**
 * 
 */
package org.fourgeeks.gha.domain.exceptions;

import java.io.Serializable;

/**
 * @author emiliot
 * 
 */
public class EJBException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public EJBException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public EJBException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}