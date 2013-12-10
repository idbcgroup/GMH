package org.fourgeeks.gha.webclient.client.UI;

import org.fourgeeks.gha.webclient.client.UI.exceptions.LoginNeededException;
import org.fourgeeks.gha.webclient.client.UI.exceptions.PermissionsNeededException;
import org.fourgeeks.gha.webclient.client.UI.places.GHAPlaceSet;
import org.fourgeeks.gha.webclient.client.edt.EDTPlace;
import org.fourgeeks.gha.webclient.client.eia.EIAPlace;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypePlace;
import org.fourgeeks.gha.webclient.client.home.HomePlace;
import org.fourgeeks.gha.webclient.client.login.LoginPlace;
import org.fourgeeks.gha.webclient.client.login.ForgottenPassword.ForgottenPasswordPlace;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanPlace;
import org.fourgeeks.gha.webclient.client.user.UserPlace;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.History;

/**
 * @author alacret
 * 
 */
public class GHAPlacesFactory {

	private GHAPlacesFactory() throws UnsupportedOperationException {
		throw new UnsupportedOperationException(
				"This class does not supposed to be instantiated");
	}

	/**
	 * @param token
	 */
	public static void showPlace(final String Htoken) {

		int indexOf = Htoken.indexOf("/");
		final String token;
		if (indexOf == -1)
			token = Htoken;
		else
			token = Htoken.substring(0, indexOf);

		if (token.equals("login"))
			GWT.runAsync(new RunAsyncCallback() {

				@Override
				public void onSuccess() {
					new LoginPlace(token).showPlace();
				}

				@Override
				public void onFailure(Throwable reason) {

				}
			});

		else if (token.equals("lostpass"))
			GWT.runAsync(new RunAsyncCallback() {

				@Override
				public void onSuccess() {
					new ForgottenPasswordPlace(token).showPlace();
				}

				@Override
				public void onFailure(Throwable reason) {

				}
			});

		else if (token.equals("home"))
			GWT.runAsync(new RunAsyncCallback() {

				@Override
				public void onSuccess() {
					try {
						HomePlace homePlace = new HomePlace(token);
						GHAPlaceSet.showPlace(homePlace);
					} catch (LoginNeededException e) {
						History.newItem("login");
					}
				}

				@Override
				public void onFailure(Throwable reason) {

				}
			});
		else if (token.equals("eiatype"))
			GWT.runAsync(new RunAsyncCallback() {

				@Override
				public void onSuccess() {
					try {
						new EIATypePlace(token).showPlace();
					} catch (LoginNeededException e) {
						History.newItem("login");
					} catch (PermissionsNeededException e) {
						History.newItem("home");
					}
				}

				@Override
				public void onFailure(Throwable reason) {

				}
			});
		else if (token.startsWith("eia"))
			GWT.runAsync(new RunAsyncCallback() {

				@Override
				public void onSuccess() {
					try {
						new EIAPlace(token).showPlace();
					} catch (LoginNeededException e) {
						History.newItem("login");
					} catch (PermissionsNeededException e) {
						History.newItem("home");
					}
				}

				@Override
				public void onFailure(Throwable reason) {

				}
			});
		else if (token.startsWith("edt"))
			GWT.runAsync(new RunAsyncCallback() {

				@Override
				public void onSuccess() {
					try {
						new EDTPlace(token).showPlace();
					} catch (LoginNeededException e) {
						History.newItem("login");
					} catch (PermissionsNeededException e) {
						History.newItem("home");
					}
				}

				@Override
				public void onFailure(Throwable reason) {

				}
			});
		else if (token.startsWith("mplan"))
			GWT.runAsync(new RunAsyncCallback() {

				@Override
				public void onSuccess() {
					try {
						new MaintenancePlanPlace(token).showPlace();
					} catch (LoginNeededException e) {
						History.newItem("login");
					} catch (PermissionsNeededException e) {
						History.newItem("home");
					}
				}

				@Override
				public void onFailure(Throwable reason) {

				}
			});
		else if (token.startsWith("user"))
			GWT.runAsync(new RunAsyncCallback() {

				@Override
				public void onSuccess() {
					try {
						new UserPlace(token).showPlace();
					} catch (LoginNeededException e) {
						History.newItem("login");
					} catch (PermissionsNeededException e) {
						History.newItem("home");
					}
				}

				@Override
				public void onFailure(Throwable reason) {

				}
			});
		else
			throw new IllegalArgumentException("Unsuported token");
	}
}
