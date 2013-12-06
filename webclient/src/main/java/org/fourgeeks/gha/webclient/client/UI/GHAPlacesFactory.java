package org.fourgeeks.gha.webclient.client.UI;

import java.util.HashMap;
import java.util.Map;

import org.fourgeeks.gha.webclient.client.UI.exceptions.LoginNeededException;
import org.fourgeeks.gha.webclient.client.UI.exceptions.PermissionsNeededException;
import org.fourgeeks.gha.webclient.client.UI.places.GHAPlace;
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
	private static Map<String, GHAPlace> places = new HashMap<String, GHAPlace>();

	private GHAPlacesFactory() throws UnsupportedOperationException {
		throw new UnsupportedOperationException(
				"This class does not supposed to be instantiated");
	}

	/**
	 * @param token
	 */
	public static void showPlace(final String token) {
		int indexOf = token.indexOf("/");
		final String stripToken;
		if (indexOf == -1)
			stripToken = token;
		else
			stripToken = token.substring(0, indexOf);

		// Whether the place has already been open
		GHAPlace place = places.get(stripToken);
		if (place != null) {
			place.show();
			return;
		}

		if (stripToken.equals("login"))
			GWT.runAsync(new RunAsyncCallback() {

				@Override
				public void onSuccess() {
					LoginPlace place = new LoginPlace(token);
					place.show();
					places.put(stripToken, place);
				}

				@Override
				public void onFailure(Throwable reason) {

				}
			});

		else if (stripToken.equals("lostpass"))
			GWT.runAsync(new RunAsyncCallback() {

				@Override
				public void onSuccess() {
					ForgottenPasswordPlace place = new ForgottenPasswordPlace(
							token);
					place.show();
					places.put(stripToken, place);
				}

				@Override
				public void onFailure(Throwable reason) {

				}
			});

		else if (stripToken.equals("home"))
			GWT.runAsync(new RunAsyncCallback() {

				@Override
				public void onSuccess() {
					try {
						HomePlace place = new HomePlace(token);
						place.show();
						places.put(stripToken, place);
					} catch (LoginNeededException e) {
						History.newItem("login");
					}
				}

				@Override
				public void onFailure(Throwable reason) {

				}
			});
		else if (stripToken.equals("eiatype"))
			GWT.runAsync(new RunAsyncCallback() {

				@Override
				public void onSuccess() {
					try {
						EIATypePlace place = new EIATypePlace(token);
						place.show();
						places.put(stripToken, place);
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
		else if (stripToken.startsWith("eia"))
			GWT.runAsync(new RunAsyncCallback() {

				@Override
				public void onSuccess() {
					try {
						EIAPlace place = new EIAPlace(token);
						place.show();
						places.put(stripToken, place);
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
		else if (stripToken.startsWith("edt"))
			GWT.runAsync(new RunAsyncCallback() {

				@Override
				public void onSuccess() {
					try {
						EDTPlace place = new EDTPlace(token);
						place.show();
						places.put(stripToken, place);
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
		else if (stripToken.startsWith("mplan"))
			GWT.runAsync(new RunAsyncCallback() {

				@Override
				public void onSuccess() {
					try {
						MaintenancePlanPlace place = new MaintenancePlanPlace(
								token);
						place.show();
						places.put(stripToken, place);
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
		else if (stripToken.startsWith("user"))
			GWT.runAsync(new RunAsyncCallback() {

				@Override
				public void onSuccess() {
					try {
						UserPlace place = new UserPlace(token);
						place.show();
						places.put(stripToken, place);
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
