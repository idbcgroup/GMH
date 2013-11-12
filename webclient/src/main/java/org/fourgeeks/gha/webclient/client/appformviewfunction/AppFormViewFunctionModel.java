package org.fourgeeks.gha.webclient.client.appformviewfunction;

import java.util.List;

import org.fourgeeks.gha.domain.ess.ui.AppFormViewFunction;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.client.GWT;

/**
 * @author alacret
 * 
 */
public class AppFormViewFunctionModel {
	private static final GWTAppFormViewFunctionServiceAsync service = GWT
			.create(GWTAppFormViewFunctionService.class);

	/**
	 * @param bpu
	 * @param callback
	 */
	public static void getAll(
			GHAAsyncCallback<List<AppFormViewFunction>> callback) {
		service.getAll(callback);
	}

}