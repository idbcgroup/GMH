package org.fourgeeks.gha.webclient.client.UI;

import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAPlace;
import org.fourgeeks.gha.webclient.client.edt.EDTPlace;
import org.fourgeeks.gha.webclient.client.eia.EIAPlace;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypePlace;
import org.fourgeeks.gha.webclient.client.home.HomePlace;
import org.fourgeeks.gha.webclient.client.login.LoginPlace;
import org.fourgeeks.gha.webclient.client.login.ForgottenPassword.ForgottenPasswordPlace;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivityPlace;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanPlace;
import org.fourgeeks.gha.webclient.client.maintenanceprotocol.MaintenanceProtocolPlace;
import org.fourgeeks.gha.webclient.client.user.UserPlace;

public class GHAPlacesFactory {
	/*
	 * private UIPlacesFactory() throws UnavailableException{ throw new
	 * UnavailableException("This class does not supposed to be instantiated");
	 * }
	 */

	public static GHAPlace createPlace(String token) {
//		Window.alert("createplace"+ token);
		if (token.equals("login"))
			return new LoginPlace();
		if (token.equals("lostpass"))
			return new ForgottenPasswordPlace();
		else if (token.equals("home"))
			return new HomePlace();
		else if (token.equals("eiatype"))
			return new EIATypePlace();
		else if (token.startsWith("eia"))
			return new EIAPlace();
		else if (token.startsWith("edt"))
			return new EDTPlace();
		else if (token.startsWith("mplan"))
			return new MaintenancePlanPlace();
		else if (token.startsWith("mprot"))
			return new MaintenanceProtocolPlace();
		else if (token.startsWith("mact"))
			return new MaintenanceActivityPlace();
		else if (token.startsWith("user"))
			return new UserPlace();
		/*
		 * switch (token) { case "login": }
		 */
		throw new IllegalArgumentException("Unsuported token");
	}
}
