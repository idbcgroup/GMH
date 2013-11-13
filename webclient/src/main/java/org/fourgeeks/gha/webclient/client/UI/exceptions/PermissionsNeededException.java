package org.fourgeeks.gha.webclient.client.UI.exceptions;

import java.io.Serializable;

/**
 * @author alacret
 * 
 */
public class PermissionsNeededException extends Exception implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;

	/**
	 * @param code
	 *            the code for which the permission is required
	 * 
	 */
	public PermissionsNeededException(String code) {
		this.code = code;
	}

	public String getMessage() {
		return "There is no permission for this object: " + code;
	}
}
