package org.fourgeeks.gha.webclient.client.UI.interfaces;

/**
 * @author alacret
 * 
 */
public interface ClosableProducer {
	/**
	 * @param closableListener
	 */
	public void addClosableListener(ClosableListener closableListener);

	/**
	 * @param closableListener
	 */
	public void removeClosableListener(ClosableListener closableListener);

}
