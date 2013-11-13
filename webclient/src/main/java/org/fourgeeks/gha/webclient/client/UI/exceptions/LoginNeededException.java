package org.fourgeeks.gha.webclient.client.UI.exceptions;

import java.io.Serializable;

/**
 * @author alacret
 * 
 */
public class LoginNeededException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public LoginNeededException() {

	}

	public String getMessage() {
		return "There is no session active";
	}
}
