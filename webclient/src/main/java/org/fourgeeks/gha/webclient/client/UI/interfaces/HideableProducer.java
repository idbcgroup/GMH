package org.fourgeeks.gha.webclient.client.UI.interfaces;

/**
 * @author alacret
 * 
 */
public interface HideableProducer {
	/**
	 * @param hideableListener
	 */
	public void addHideableListener(HideableListener hideableListener);

	/**
	 * @param hideableListener
	 */
	public void removeHideableListener(HideableListener hideableListener);
}
