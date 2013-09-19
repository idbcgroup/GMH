package org.fourgeeks.gha.webclient.client.function;

import java.util.List;

import org.fourgeeks.gha.domain.ess.Function;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.client.GWT;

/**
 * @author alacret
 * 
 */
public class FunctionModel {
	private static final GWTFunctionServiceAsync service = GWT
			.create(GWTFunctionService.class);

	/**
	 * @param bpu
	 * @param callback
	 */
	public static void getAll(GHAAsyncCallback<List<Function>> callback) {
		service.getAll(callback);
	}

}