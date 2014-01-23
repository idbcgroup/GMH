package org.fourgeeks.gha.webclient.client.UI.places;

import org.fourgeeks.gha.webclient.client.UI.exceptions.LoginNeededException;
import org.fourgeeks.gha.webclient.client.UI.exceptions.PermissionsNeededException;
import org.fourgeeks.gha.webclient.client.activity.ActivityPlace;
import org.fourgeeks.gha.webclient.client.edt.EDTPlace;
import org.fourgeeks.gha.webclient.client.eia.EIAPlace;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypePlace;
import org.fourgeeks.gha.webclient.client.emh.EMHPlace;
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
	public static void showPlace(final String token) {
		if (token.equals("lostpass"))
			GWT.runAsync(new RunAsyncCallback() {

				@Override
				public void onSuccess() {
					GHAPlaceSet.showPlace(new ForgottenPasswordPlace(token));
				}

				@Override
				public void onFailure(Throwable reason) {

				}
			});

		// else if (token.equals("home"))
		// GWT.runAsync(new RunAsyncCallback() {
		//
		// @Override
		// public void onSuccess() {
		// try {
		// GHAPlaceSet.showPlace(new HomePlace(token));
		// } catch (LoginNeededException e) {
		// // TODO
		// }
		// }
		//
		// @Override
		// public void onFailure(Throwable reason) {
		//
		// }
		// });
		else if (token.equals("eiatype"))
			GWT.runAsync(new RunAsyncCallback() {

				@Override
				public void onSuccess() {
					try {
						GHAPlaceSet.showPlace(new EIATypePlace(token));
					} catch (LoginNeededException e) {
						// TODO
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
						GHAPlaceSet.showPlace(new EIAPlace(token));
					} catch (LoginNeededException e) {
						// TODO
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
						GHAPlaceSet.showPlace(new EDTPlace(token));
					} catch (LoginNeededException e) {
						// TODO
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
						GHAPlaceSet.showPlace(new MaintenancePlanPlace(token));
					} catch (LoginNeededException e) {
						// TODO
					} catch (PermissionsNeededException e) {
						History.newItem("home");
					}
				}

				@Override
				public void onFailure(Throwable reason) {

				}
			});
		else if (token.startsWith("activity"))
			GWT.runAsync(new RunAsyncCallback() {

				@Override
				public void onSuccess() {
					try {
						GHAPlaceSet.showPlace(new ActivityPlace(token));
					} catch (LoginNeededException e) {
						// TODO
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
						GHAPlaceSet.showPlace(new UserPlace(token));
					} catch (LoginNeededException e) {
						// TODO
					} catch (PermissionsNeededException e) {
						History.newItem("home");
					}
				}

				@Override
				public void onFailure(Throwable reason) {

				}
			});
		else if (token.startsWith("emh"))
			GWT.runAsync(new RunAsyncCallback() {

				@Override
				public void onSuccess() {
					try {
						GHAPlaceSet.showPlace(new EMHPlace(token));
					} catch (LoginNeededException e) {
						// TODO
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
