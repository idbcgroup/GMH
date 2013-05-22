package org.fourgeeks.gha.webclient.client.login;

import java.io.Serializable;

public class LoginException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginException() {

	}

	public String getMessage() {
		return "Su sesion ha expirado";
	}
}
