package org.fourgeeks.gha.webclient.client.UI;

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

/**
 * @author alacret
 * 
 */
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

		if (token.equals("login"))
			GWT.runAsync(new RunAsyncCallback() {

				@Override
				public void onSuccess() {
					new LoginPlace(token).show();
				}

				@Override
				public void onFailure(Throwable reason) {

				}
			});

		else if (token.equals("lostpass"))
			GWT.runAsync(new RunAsyncCallback() {

				@Override
				public void onSuccess() {
					new ForgottenPasswordPlace(token).show();
					;
				}

				@Override
				public void onFailure(Throwable reason) {

				}
			});

		else if (token.equals("home"))
			GWT.runAsync(new RunAsyncCallback() {

				@Override
				public void onSuccess() {
					new HomePlace(token).show();
				}

				@Override
				public void onFailure(Throwable reason) {

				}
			});
		else if (token.equals("eiatype"))
			GWT.runAsync(new RunAsyncCallback() {

				@Override
				public void onSuccess() {
					new EIATypePlace(token).show();
				}

				@Override
				public void onFailure(Throwable reason) {

				}
			});
		else if (token.startsWith("eia"))
			GWT.runAsync(new RunAsyncCallback() {

				@Override
				public void onSuccess() {
					new EIAPlace(token).show();
				}

				@Override
				public void onFailure(Throwable reason) {

				}
			});
		else if (token.startsWith("edt"))
			GWT.runAsync(new RunAsyncCallback() {

				@Override
				public void onSuccess() {
					new EDTPlace(token).show();
				}

				@Override
				public void onFailure(Throwable reason) {

				}
			});
		else if (token.startsWith("mplan"))
			GWT.runAsync(new RunAsyncCallback() {

				@Override
				public void onSuccess() {
					new MaintenancePlanPlace(token).show();
				}

				@Override
				public void onFailure(Throwable reason) {

				}
			});
		else if (token.startsWith("mprot"))
			GWT.runAsync(new RunAsyncCallback() {

				@Override
				public void onSuccess() {
					new MaintenanceProtocolPlace(token).show();
				}

				@Override
				public void onFailure(Throwable reason) {

				}
			});
		else if (token.startsWith("mact"))
			GWT.runAsync(new RunAsyncCallback() {

				@Override
				public void onSuccess() {
					new MaintenanceActivityPlace(token).show();
				}

				@Override
				public void onFailure(Throwable reason) {

				}
			});
		else if (token.startsWith("user"))
			GWT.runAsync(new RunAsyncCallback() {

				@Override
				public void onSuccess() {
					new UserPlace(token).show();
				}

				@Override
				public void onFailure(Throwable reason) {

				}
			});
		else
			throw new IllegalArgumentException("Unsuported token");

	}
}
