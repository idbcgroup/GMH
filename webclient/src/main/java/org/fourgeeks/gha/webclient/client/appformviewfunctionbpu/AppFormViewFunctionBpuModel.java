package org.fourgeeks.gha.webclient.client.appformviewfunctionbpu;

import java.util.List;

import org.fourgeeks.gha.domain.ess.ui.AppFormViewFunctionBpu;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.client.GWT;

/**
 * @author alacret
 * 
 */
public class AppFormViewFunctionBpuModel {
	private static final GWTAppFormViewFunctionBpuServiceAsync service = GWT
			.create(GWTAppFormViewFunctionBpuService.class);

	/**
	 * @param bpuFunction
	 * @param callback
	 */
	public static void save(AppFormViewFunctionBpu bpuFunction,
			GHAAsyncCallback<AppFormViewFunctionBpu> callback) {
		service.save(bpuFunction, callback);
	}

	/**
	 * @param bpuFunction
	 * @param callback
	 */
	public static void delete(AppFormViewFunctionBpu bpuFunction,
			GHAAsyncCallback<Void> callback) {
		service.delete(bpuFunction, callback);
	}

	/**
	 * @param bpu
	 * @param callback
	 */
	public static void getFunctionsByBpu(Bpu bpu,
			GHAAsyncCallback<List<AppFormViewFunctionBpu>> callback) {
		service.getFunctionsByBpu(bpu, callback);
	}

}