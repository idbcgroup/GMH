package org.fourgeeks.gha.webclient.client.UI.interfaces;

import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToCloseException;

/**
 * @author alacret
 * 
 */
public interface GHAClosable {

	/**
	 * @throws UnavailableToCloseException
	 */
	public void close() throws UnavailableToCloseException;
}