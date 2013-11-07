package org.fourgeeks.gha.webclient.client.UI.interfaces;

/**
 * @author alacret
 * 
 */
public interface ClosableProducer {
	public void addClosableHandler();

	public void removeClosableHandler();

	public void notifyClose();

}
