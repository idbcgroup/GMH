/**
 * 
 */
package org.fourgeeks.gha.domain.exceptions;

import java.io.Serializable;

import org.fourgeeks.gha.domain.msg.GHAMessage;

/**
 * @author alacret
 * 
 */
public class GHAEJBException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GHAMessage ghaMessage;

	/**
	 * 
	 */
	public GHAEJBException() {
		super();
	}

	/**
	 * @param message
	 */
	public GHAEJBException(final GHAMessage message) {
		super(message.getMessageText());
		this.ghaMessage = message;
	}

	/**
	 * @param message
	 */
	public GHAEJBException(final String message) {
		super(message);
	}

	/**
	 * @return the ghaMessage
	 */
	public GHAMessage getGhaMessage() {
		return ghaMessage;
	}

	/**
	 * @param ghaMessage
	 *            the ghaMessage to set
	 */
	public void setGhaMessage(final GHAMessage ghaMessage) {
		this.ghaMessage = ghaMessage;
	}
}