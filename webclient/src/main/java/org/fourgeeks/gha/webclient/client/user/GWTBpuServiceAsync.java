package org.fourgeeks.gha.webclient.client.user;

import org.fourgeeks.gha.webclient.client.bpu.GWTBpuService;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GWTBpuServiceAsync {

	/**
	 * GWT-RPC service asynchronous (client-side) interface
	 * 
	 * @see org.fourgeeks.gha.webclient.client.bpu.GWTBpuService
	 */
	void delete(long Id, AsyncCallback<Void> callback);

	/**
	 * GWT-RPC service asynchronous (client-side) interface
	 * 
	 * @see org.fourgeeks.gha.webclient.client.bpu.GWTBpuService
	 */
	void find(
			org.fourgeeks.gha.domain.gar.Bpu bpu,
			AsyncCallback<java.util.List<org.fourgeeks.gha.domain.gar.Bpu>> callback);

	/**
	 * GWT-RPC service asynchronous (client-side) interface
	 * 
	 * @see org.fourgeeks.gha.webclient.client.bpu.GWTBpuService
	 */
	void find(long Id, AsyncCallback<org.fourgeeks.gha.domain.gar.Bpu> callback);

	/**
	 * GWT-RPC service asynchronous (client-side) interface
	 * 
	 * @see org.fourgeeks.gha.webclient.client.bpu.GWTBpuService
	 */
	void getAll(
			AsyncCallback<java.util.List<org.fourgeeks.gha.domain.gar.Bpu>> callback);

	/**
	 * GWT-RPC service asynchronous (client-side) interface
	 * 
	 * @see org.fourgeeks.gha.webclient.client.bpu.GWTBpuService
	 */
	void save(org.fourgeeks.gha.domain.gar.Bpu bpu,
			AsyncCallback<org.fourgeeks.gha.domain.gar.Bpu> callback);

	/**
	 * GWT-RPC service asynchronous (client-side) interface
	 * 
	 * @see org.fourgeeks.gha.webclient.client.bpu.GWTBpuService
	 */
	void update(org.fourgeeks.gha.domain.gar.Bpu bpu,
			AsyncCallback<org.fourgeeks.gha.domain.gar.Bpu> callback);

	/**
	 * Utility class to get the RPC Async interface from client-side code
	 */
	public static final class Util {
		private static GWTBpuServiceAsync instance;

		public static final GWTBpuServiceAsync getInstance() {
			if (instance == null) {
				instance = (GWTBpuServiceAsync) GWT.create(GWTBpuService.class);
			}
			return instance;
		}

		private Util() {
			// Utility class should not be instanciated
		}
	}
}
