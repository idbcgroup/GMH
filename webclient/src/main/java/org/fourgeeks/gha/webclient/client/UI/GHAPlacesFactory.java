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

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;

public class GHAPlacesFactory {
	/*
	 * private UIPlacesFactory() throws UnavailableException{ throw new
	 * UnavailableException("This class does not supposed to be instantiated");
	 * }
	 */

	/**
	 * @param token
	 */
	public static void createPlace(final String token) {
		GWT.runAsync(new RunAsyncCallback() {

			@Override
			public void onSuccess() {
				GHAPlace place = null;
				if (token.equals("login"))
					place = new LoginPlace();
				else if (token.equals("lostpass"))
					place = new ForgottenPasswordPlace();
				else if (token.equals("home"))
					place = new HomePlace();
				else if (token.equals("eiatype"))
					place = new EIATypePlace();
				else if (token.startsWith("eia"))
					place = new EIAPlace();
				else if (token.startsWith("edt"))
					place = new EDTPlace();
				else if (token.startsWith("mplan"))
					place = new MaintenancePlanPlace();
				else if (token.startsWith("mprot"))
					place = new MaintenanceProtocolPlace();
				else if (token.startsWith("mact"))
					place = new MaintenanceActivityPlace();
				else if (token.startsWith("user"))
					place = new UserPlace();
				else
					throw new IllegalArgumentException("Unsuported token");
				place.setToken(token);
				place.show();

			}

			@Override
			public void onFailure(Throwable reason) {
				// TODO Auto-generated method stub

			}
		});

	}
}
