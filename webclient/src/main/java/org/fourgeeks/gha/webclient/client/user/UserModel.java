package org.fourgeeks.gha.webclient.client.user;

import java.util.List;

import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.bpu.GWTBpuService;
import org.fourgeeks.gha.webclient.client.bpu.GWTBpuServiceAsync;

import com.google.gwt.core.client.GWT;

/**
 * @author alacret
 * 
 */
public class UserModel {
	private static final GWTBpuServiceAsync service = GWT
			.create(GWTBpuService.class);

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
	 * @param bpu
	 *            the bpu base to find
	 * @param callback
	 */
	public static void find(Bpu bpu, GHAAsyncCallback<List<Bpu>> callback) {
		service.find(bpu, callback);
	}
}