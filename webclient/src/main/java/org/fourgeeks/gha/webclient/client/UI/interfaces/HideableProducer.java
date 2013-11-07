package org.fourgeeks.gha.webclient.client.UI.interfaces;

/**
 * @author alacret
 * 
 */
public interface HideableProducer {
	/**
	 * @param hideableListener
	 */
	public void addHideableHandler(HideableListener hideableListener);

	/**
	 * @param hideableListener
	 */
	public void removeHideableHandler(HideableListener hideableListener);
}
