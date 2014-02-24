package org.fourgeeks.gha.webclient.client.viewpermission;

import java.util.List;

import org.fourgeeks.gha.domain.ess.ui.ViewPermission;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.appformviewfunction.GWTAppFormViewFunctionServiceAsync;

import com.google.gwt.core.client.GWT;

/**
 * @author alacret
 * 
 */
public class ViewPermissionModel {
	private static final GWTAppFormViewFunctionServiceAsync service = GWT
			.create(GWTViewPermissionService.class);

	/**
	 * @param bpu
	 * @param callback
	 */
	public static void getAll(
			GHAAsyncCallback<List<ViewPermission>> callback) {
		service.getAll(callback);
	}

}