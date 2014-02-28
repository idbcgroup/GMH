package org.fourgeeks.gha.webclient.client.viewpermission;

import java.util.List;

import org.fourgeeks.gha.domain.ess.ui.ViewFunction;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.client.GWT;

/**
 * @author alacret
 * 
 */
public class ViewPermissionModel {
	private static final GWTViewFunctionnServiceAsync service = GWT
			.create(GWTViewFunctionnService.class);

	/**
	 * @param bpu
	 * @param callback
	 */
	public static void getAll(GHAAsyncCallback<List<ViewFunction>> callback) {
		service.getAll(callback);
	}

}