package org.fourgeeks.gha.webclient.client.UI;

import org.fourgeeks.gha.webclient.client.UI.exceptions.LoginNeededException;
import org.fourgeeks.gha.webclient.client.UI.exceptions.PermissionsNeededException;
import org.fourgeeks.gha.webclient.client.edt.EDTPlace;
import org.fourgeeks.gha.webclient.client.eia.EIAPlace;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypePlace;
import org.fourgeeks.gha.webclient.client.home.HomePlace;
//import org.fourgeeks.gha.webclient.client.login.LoginPlace;
import org.fourgeeks.gha.webclient.client.login.ForgottenPassword.ForgottenPasswordPlace;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivityPlace;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanPlace;
import org.fourgeeks.gha.webclient.client.maintenanceprotocol.MaintenanceProtocolPlace;
import org.fourgeeks.gha.webclient.client.user.UserPlace;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.Window;

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
		int indexOf = token.indexOf("/");
		String stripToken = null;
		if (indexOf == -1)
			stripToken = token;
		else
			stripToken = token.substring(0, indexOf);
//		if (stripToken.equals("login"))
//			GWT.runAsync(new RunAsyncCallback() {
//
//				@Override
//				public void onSuccess() {
//					new LoginPlace(token).show();
//				}
//
//				@Override
//				public void onFailure(Throwable reason) {
//
//				}
//			});

//		else 
			if (stripToken.equals("lostpass"))
			GWT.runAsync(new RunAsyncCallback() {

				@Override
				public void onSuccess() {
					new ForgottenPasswordPlace(token).show();
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
						new HomePlace(token).show();
					} catch (LoginNeededException e) {
						Window.alert("LoginNeededException");
//						new LoginPlace(token).show();
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
						new EIATypePlace(token).show();
					} catch (LoginNeededException e) {
						Window.alert("LoginNeededException");
//						new LoginPlace(token).show();
					} catch (PermissionsNeededException e) {
						try {
							new HomePlace(token).show();
						} catch (LoginNeededException e1) {
							Window.alert("LoginNeededException");
//							new LoginPlace(token).show();
						}
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
						new EIAPlace(token).show();
					} catch (LoginNeededException e) {
						Window.alert("LoginNeededException");
//						new LoginPlace(token).show();
					} catch (PermissionsNeededException e) {
						Window.alert("LoginNeededException");
						try {
							new HomePlace(token).show();
						} catch (LoginNeededException e1) {
							Window.alert("LoginNeededException");
//							new LoginPlace(token).show();
						}
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
						new EDTPlace(token).show();
					} catch (LoginNeededException e) {
						Window.alert("LoginNeededException");
//						new LoginPlace(token).show();
					} catch (PermissionsNeededException e) {
						try {
							new HomePlace(token).show();
						} catch (LoginNeededException e1) {
							Window.alert("LoginNeededException");
//							new LoginPlace(token).show();
						}
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
						new MaintenancePlanPlace(token).show();
					} catch (LoginNeededException e) {
						Window.alert("LoginNeededException");
//						new LoginPlace(token).show();
					} catch (PermissionsNeededException e) {
						try {
							new HomePlace(token).show();
						} catch (LoginNeededException e1) {
							Window.alert("LoginNeededException");
//							new LoginPlace(token).show();
						}
					}
				}

				@Override
				public void onFailure(Throwable reason) {

				}
			});
		else if (stripToken.startsWith("mprot"))
			GWT.runAsync(new RunAsyncCallback() {

				@Override
				public void onSuccess() {
					try {
						new MaintenanceProtocolPlace(token).show();
					} catch (LoginNeededException e) {
						Window.alert("LoginNeededException");
//						new LoginPlace(token).show();
					} catch (PermissionsNeededException e) {
						try {
							new HomePlace(token).show();
						} catch (LoginNeededException e1) {
							Window.alert("LoginNeededException");
//							new LoginPlace(token).show();
						}
					}
				}

				@Override
				public void onFailure(Throwable reason) {

				}
			});
		else if (stripToken.startsWith("mact"))
			GWT.runAsync(new RunAsyncCallback() {

				@Override
				public void onSuccess() {
					try {
						new MaintenanceActivityPlace(token).show();
					} catch (LoginNeededException e) {
						Window.alert("LoginNeededException");
//						new LoginPlace(token).show();
					} catch (PermissionsNeededException e) {
						try {
							new HomePlace(token).show();
						} catch (LoginNeededException e1) {
							Window.alert("LoginNeededException");
//							new LoginPlace(token).show();
						}
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
						new UserPlace(token).show();
					} catch (LoginNeededException e) {
						Window.alert("LoginNeededException");
//						new LoginPlace(token).show();
					} catch (PermissionsNeededException e) {
						try {
							new HomePlace(token).show();
						} catch (LoginNeededException e1) {
							Window.alert("LoginNeededException");
//							new LoginPlace(token).show();
						}
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
