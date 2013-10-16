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
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public GHAEJBException(GHAMessage message) {
		super(message.getText());
		this.ghaMessage = message;
	}

	/**
	 * @param message
	 */
	public GHAEJBException(String message) {
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
	public void setGhaMessage(GHAMessage ghaMessage) {
		this.ghaMessage = ghaMessage;
	}
}