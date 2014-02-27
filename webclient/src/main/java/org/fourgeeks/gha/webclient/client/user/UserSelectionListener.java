package org.fourgeeks.gha.webclient.client.user;

import org.fourgeeks.gha.domain.ess.auth.SSOUser;

/**
 * @author alacret A user selection listener
 */
public interface UserSelectionListener {

	/**
	 * @param ssoUser
	 */
	public void select(SSOUser ssoUser);

}