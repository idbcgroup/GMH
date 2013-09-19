package org.fourgeeks.gha.webclient.client.user;

import java.util.List;

import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.bpu.GWTBpuService;
import org.fourgeeks.gha.webclient.client.bpu.GWTBpuServiceAsync;
import org.fourgeeks.gha.webclient.client.ssouser.GWTSSOUserService;
import org.fourgeeks.gha.webclient.client.ssouser.GWTSSOUserServiceAsync;

import com.google.gwt.core.client.GWT;

/**
 * @author alacret
 * 
 */
public class UserModel {
	private static final GWTBpuServiceAsync service = GWT.create(GWTBpuService.class);
	private static final GWTSSOUserServiceAsync ssoUservice = GWT.create(GWTSSOUserService.class);

	private UserModel() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	/**
	 * @param callback
	 */
	public static void getAll(GHAAsyncCallback<List<Bpu>> callback) {
		service.getAll(callback);
	}

	/**
	 * @param ssoUser
	 *            the ssoUser base to find
	 * @param callback
	 */
	public static void find(SSOUser ssoUser,
			GHAAsyncCallback<List<SSOUser>> callback) {
		ssoUservice.find(ssoUser, callback);
	}

	/**
	 * @param ssoUser
	 * @param ghaAsyncCallback
	 */
	public static void save(SSOUser ssoUser,
			GHAAsyncCallback<SSOUser> callback) {
		ssoUservice.save(ssoUser, callback);		
	}

}