package org.fourgeeks.gha.webclient.client.UI.interfaces;

import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToHideException;

/**
 * @author alacret
 * 
 */
public interface GHAHideable {

	/**
	 * @return if the component can be set to hidden
	 */
	public boolean canBeHidden();

	/**
	 * @throws UnavailableToHideException
	 */
	public void hide() throws UnavailableToHideException;
}