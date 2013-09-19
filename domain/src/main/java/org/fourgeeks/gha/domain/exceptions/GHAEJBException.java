/**
 * 
 */
package org.fourgeeks.gha.domain.exceptions;

import java.io.Serializable;

/**
 * @author alacret
 * 
 */
public class GHAEJBException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public GHAEJBException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public GHAEJBException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}