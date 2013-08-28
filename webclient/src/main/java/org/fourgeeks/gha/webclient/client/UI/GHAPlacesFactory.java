package org.fourgeeks.gha.webclient.client.UI;

import org.fourgeeks.gha.webclient.client.edt.EDTPlace;
import org.fourgeeks.gha.webclient.client.eia.EIAPlace;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypePlace;
import org.fourgeeks.gha.webclient.client.home.HomePlace;
import org.fourgeeks.gha.webclient.client.login.LoginPlace;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanPlace;

public class GHAPlacesFactory {
	/*
	 * private UIPlacesFactory() throws UnavailableException{ throw new
	 * UnavailableException("This class does not supposed to be instantiated");
	 * }
	 */

	public static GHAPlace createPlace(String token) {
		if (token.equals("login"))
			return new LoginPlace();
		else if (token.equals("home"))
			return new HomePlace();
		else if (token.equals("eiatype"))
			return new EIATypePlace();
		else if (token.startsWith("eia"))
			return new EIAPlace();
		else if (token.startsWith("edt"))
			return new EDTPlace();
		else if (token.startsWith("pdm"))
			return new MaintenancePlanPlace();
		/*
		 * switch (token) { case "login": }
		 */
		throw new IllegalArgumentException("Unsuported token");
	}
}
