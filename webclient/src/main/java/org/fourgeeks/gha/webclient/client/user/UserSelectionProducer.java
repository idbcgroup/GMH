package org.fourgeeks.gha.webclient.client.user;

import org.fourgeeks.gha.domain.ess.auth.SSOUser;

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

	/**
	 * @param ssoUser
	 */
	public void notifyUser(SSOUser ssoUser);

}
