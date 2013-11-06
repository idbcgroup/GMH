package org.fourgeeks.gha.webclient.client.user;

import org.fourgeeks.gha.webclient.client.ssouser.GWTSSOUserService;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GWTSSOUserServiceAsync {

	/**
	 * GWT-RPC service asynchronous (client-side) interface
	 * 
	 * @see org.fourgeeks.gha.webclient.client.ssouser.GWTSSOUserService
	 */
	void delete(long Id, AsyncCallback<Void> callback);

	/**
	 * GWT-RPC service asynchronous (client-side) interface
	 * 
	 * @see org.fourgeeks.gha.webclient.client.ssouser.GWTSSOUserService
	 */
	void find(
			org.fourgeeks.gha.domain.ess.SSOUser ssoUser,
			AsyncCallback<java.util.List<org.fourgeeks.gha.domain.ess.SSOUser>> callback);

	/**
	 * GWT-RPC service asynchronous (client-side) interface
	 * 
	 * @see org.fourgeeks.gha.webclient.client.ssouser.GWTSSOUserService
	 */
	void find(long Id,
			AsyncCallback<org.fourgeeks.gha.domain.ess.SSOUser> callback);

	/**
	 * GWT-RPC service asynchronous (client-side) interface
	 * 
	 * @see org.fourgeeks.gha.webclient.client.ssouser.GWTSSOUserService
	 */
	void getAll(
			AsyncCallback<java.util.List<org.fourgeeks.gha.domain.ess.SSOUser>> callback);

	/**
	 * GWT-RPC service asynchronous (client-side) interface
	 * 
	 * @see org.fourgeeks.gha.webclient.client.ssouser.GWTSSOUserService
	 */
	void save(org.fourgeeks.gha.domain.ess.SSOUser ssoUser,
			AsyncCallback<org.fourgeeks.gha.domain.ess.SSOUser> callback);

	/**
	 * GWT-RPC service asynchronous (client-side) interface
	 * 
	 * @see org.fourgeeks.gha.webclient.client.ssouser.GWTSSOUserService
	 */
	void update(org.fourgeeks.gha.domain.ess.SSOUser ssoUser,
			AsyncCallback<org.fourgeeks.gha.domain.ess.SSOUser> callback);

	/**
	 * GWT-RPC service asynchronous (client-side) interface
	 * 
	 * @see org.fourgeeks.gha.webclient.client.ssouser.GWTSSOUserService
	 */
	void save(org.fourgeeks.gha.domain.ess.BpuFunction bpuFunction,
			AsyncCallback<org.fourgeeks.gha.domain.ess.BpuFunction> callback);

	/**
	 * GWT-RPC service asynchronous (client-side) interface
	 * 
	 * @see org.fourgeeks.gha.webclient.client.ssouser.GWTSSOUserService
	 */
	void delete(org.fourgeeks.gha.domain.ess.BpuFunction bpuFunction,
			AsyncCallback<Void> callback);

	/**
	 * GWT-RPC service asynchronous (client-side) interface
	 * 
	 * @see org.fourgeeks.gha.webclient.client.ssouser.GWTSSOUserService
	 */
	void getFunctionsByBpu(
			org.fourgeeks.gha.domain.gar.Bpu bpu,
			AsyncCallback<java.util.List<org.fourgeeks.gha.domain.ess.BpuFunction>> callback);

	/**
	 * Utility class to get the RPC Async interface from client-side code
	 */
	public static final class Util {
		private static GWTSSOUserServiceAsync instance;

		public static final GWTSSOUserServiceAsync getInstance() {
			if (instance == null) {
				instance = (GWTSSOUserServiceAsync) GWT
						.create(GWTSSOUserService.class);
			}
			return instance;
		}

		private Util() {
			// Utility class should not be instanciated
		}
	}
}
