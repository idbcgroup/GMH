package org.fourgeeks.gha.webclient.client.UI.places;

import org.fourgeeks.gha.webclient.client.UI.exceptions.LoginNeededException;
import org.fourgeeks.gha.webclient.client.UI.exceptions.PermissionsNeededException;
import org.fourgeeks.gha.webclient.client.edt.EDTPlace;
import org.fourgeeks.gha.webclient.client.eia.EIAPlace;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypePlace;
import org.fourgeeks.gha.webclient.client.emh.EMHPlace;
import org.fourgeeks.gha.webclient.client.login.ForgottenPassword.ForgottenPasswordPlace;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivityPlace;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanPlace;
import org.fourgeeks.gha.webclient.client.res.RESPlace;
import org.fourgeeks.gha.webclient.client.user.UserPlace;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;

/**
 * @author alacret
 * 
 */
public class GHAPlacesFactory {

	/**
	 * @param token
	 */
	public static void showPlace(final String token) {
		switch (token) {
		case "lostpass":
			GWT.runAsync(new RunAsyncCallback() {

				@Override
				public void onFailure(Throwable reason) {

				}

				@Override
				public void onSuccess() {
					GHAPlaceSet.showPlace(new ForgottenPasswordPlace(token));
				}
			});
			break;

		case "eiatype":
			GWT.runAsync(new RunAsyncCallback() {

				@Override
				public void onFailure(Throwable reason) {

				}

				@Override
				public void onSuccess() {
					try {
						GHAPlaceSet.showPlace(new EIATypePlace(token));
					} catch (final LoginNeededException e) {
						// TODO
					} catch (final PermissionsNeededException e) {
						History.newItem("home");
					}
				}
			});
			break;
		case "eia":
			if (token.startsWith("eia"))
				GWT.runAsync(new RunAsyncCallback() {

					@Override
					public void onFailure(Throwable reason) {

					}

					@Override
					public void onSuccess() {
						try {
							GHAPlaceSet.showPlace(new EIAPlace(token));
						} catch (final LoginNeededException e) {
							// TODO
						} catch (final PermissionsNeededException e) {
							History.newItem("home");
						}
					}
				});
			break;
		case "edt":
			GWT.runAsync(new RunAsyncCallback() {

				@Override
				public void onFailure(Throwable reason) {

				}

				@Override
				public void onSuccess() {
					try {
						GHAPlaceSet.showPlace(new EDTPlace(token));
					} catch (final LoginNeededException e) {
						// TODO
					} catch (final PermissionsNeededException e) {
						History.newItem("home");
					}
				}
			});
			break;
		case "mplan":
			GWT.runAsync(new RunAsyncCallback() {

				@Override
				public void onFailure(Throwable reason) {

				}

				@Override
				public void onSuccess() {
					try {
						GHAPlaceSet.showPlace(new MaintenancePlanPlace(token));
					} catch (final LoginNeededException e) {
						// TODO
					} catch (final PermissionsNeededException e) {
						History.newItem("home");
					}
				}
			});
			break;
		case "activity":
			GWT.runAsync(new RunAsyncCallback() {

				@Override
				public void onFailure(Throwable reason) {

				}

				@Override
				public void onSuccess() {
					try {
						GHAPlaceSet.showPlace(new MaintenanceActivityPlace(
								token));
					} catch (final LoginNeededException e) {
						// TODO
						Window.alert(e.toString());
					} catch (final PermissionsNeededException e) {
						History.newItem("home");
						Window.alert(e.toString());
					} catch (final Exception e) {
						Window.alert(e.toString());
					}
				}
			});
			break;
		case "user":
			GWT.runAsync(new RunAsyncCallback() {

				@Override
				public void onFailure(Throwable reason) {

				}

				@Override
				public void onSuccess() {
					try {
						GHAPlaceSet.showPlace(new UserPlace(token));
					} catch (final LoginNeededException e) {
						// TODO
					} catch (final PermissionsNeededException e) {
						History.newItem("home");
					}
				}
			});
			break;
		case "emh":
			GWT.runAsync(new RunAsyncCallback() {

				@Override
				public void onFailure(Throwable reason) {

				}

				@Override
				public void onSuccess() {
					try {
						GHAPlaceSet.showPlace(new EMHPlace(token));
					} catch (final LoginNeededException e) {
						// TODO
					} catch (final PermissionsNeededException e) {
						History.newItem("home");
					}
				}
			});
			break;
		case "res":
			GWT.runAsync(new RunAsyncCallback() {

				@Override
				public void onFailure(Throwable reason) {

				}

				@Override
				public void onSuccess() {
					try {
						GHAPlaceSet.showPlace(new RESPlace(token));
					} catch (final LoginNeededException e) {
						// TODO
					} catch (final PermissionsNeededException e) {
						History.newItem("home");
					}
				}
			});
			break;
		default:
			throw new IllegalArgumentException("Unsuported token");
		}
	}

	private GHAPlacesFactory() throws UnsupportedOperationException {
		throw new UnsupportedOperationException(
				"This class does not supposed to be instantiated");
	}
}
