package org.fourgeeks.gha.webclient.client.user;


/**
 * @author alacret A User selection producer
 */
public interface UserSelectionProducer {
	/**
	 * @param userSelectionListener
	 */
	public void addUserSelectionListener(
			UserSelectionListener userSelectionListener);

	/**
	 * @param userSelectionListener
	 */
	public void removeUserSelectionListener(
			UserSelectionListener userSelectionListener);
}