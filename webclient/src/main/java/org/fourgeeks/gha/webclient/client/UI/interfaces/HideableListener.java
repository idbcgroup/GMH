package org.fourgeeks.gha.webclient.client.UI.interfaces;

import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToHideException;

/**
 * @author alacret
 * 
 */
public interface HideableListener {

	/**
	 * @param closeAction
	 * @return if the component can be set to hidden
	 */
	public boolean canBeHidden(CloseHideAction closeAction);

	/**
	 * @throws UnavailableToHideException
	 */
	public void hide() throws UnavailableToHideException;
}