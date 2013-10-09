package org.fourgeeks.gha.webclient.client.UI;

import org.fourgeeks.gha.domain.gar.Bpu;

/**
 * @author alacret
 * 
 */
public abstract class GHASessionData {

	private static Bpu loggedUser;

	/**
	 * @return the logged User
	 */
	public static Bpu getLoggedUser() {
		return loggedUser;
	}

	/**
	 * @param loggedUser
	 */
	public static void setLoggedUser(Bpu loggedUser) {
		GHASessionData.loggedUser = loggedUser;
	}
}
