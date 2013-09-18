package org.fourgeeks.gha.webclient.client.UI;

import org.fourgeeks.gha.domain.gar.Bpu;

public abstract class GHASessionData {

	private static Bpu loggedUser;

	public static Bpu getLoggedUser() {
		return loggedUser;
	}

	public static void setLoggedUser(Bpu loggedUser) {
		GHASessionData.loggedUser = loggedUser;
	}
}
