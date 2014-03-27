package org.fourgeeks.gha.webclient.client.permissionbpu;

import java.util.List;

import org.fourgeeks.gha.domain.ess.auth.Function;
import org.fourgeeks.gha.domain.ess.auth.FunctionBpu;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.client.GWT;

/**
 * @author alacret
 * 
 */
public class FunctionBpuModel {
	private static final GWTFunctionBpuServiceAsync service = GWT
			.create(GWTFunctionBpuService.class);

	/**
	 * @param bpuFunction
	 * @param callback
	 */
	public static void delete(final FunctionBpu bpuFunction,
			final GHAAsyncCallback<Void> callback) {
		service.delete(bpuFunction, callback);
	}

	/**
	 * @param bpu
	 * @param callback
	 */
	public static void getPermissionsByBpu(final Bpu bpu,
			final GHAAsyncCallback<List<Function>> callback) {
		service.getPermissionsByBpu(bpu, callback);
	}

	/**
	 * @param bpuFunction
	 * @param callback
	 */
	public static void save(final FunctionBpu bpuFunction,
			final GHAAsyncCallback<FunctionBpu> callback) {
		service.save(bpuFunction, callback);
	}

}