package org.fourgeeks.gha.webclient.client.UI.interfaces;

/**
 * @author alacret
 * 
 */
public interface ClosableProducer {
	/**
	 * @param closableListener
	 */
	public void addClosableHandler(ClosableListener closableListener);

	/**
	 * @param closableListener
	 */
	public void removeClosableHandler(ClosableListener closableListener);

}
