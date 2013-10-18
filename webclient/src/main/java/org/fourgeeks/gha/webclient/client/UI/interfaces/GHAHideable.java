package org.fourgeeks.gha.webclient.client.UI.interfaces;

import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToHideException;

/**
 * @author alacret
 * 
 */
public interface GHAHideable {

	/**
	 * @throws UnavailableToHideException
	 */
	public void hide() throws UnavailableToHideException;
}