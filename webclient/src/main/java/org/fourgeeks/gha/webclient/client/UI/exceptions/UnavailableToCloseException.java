package org.fourgeeks.gha.webclient.client.UI.exceptions;

/**
 * @author alacret Exception to be throw when the implementer is unavailable to
 *         close
 */
public class UnavailableToCloseException extends RuntimeException {

	/**
	 * @param e
	 */
	public UnavailableToCloseException(Throwable e) {
		super(e);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
