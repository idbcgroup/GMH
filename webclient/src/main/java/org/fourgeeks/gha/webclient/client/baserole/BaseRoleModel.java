package org.fourgeeks.gha.webclient.client.baserole;

import java.util.List;

import org.fourgeeks.gha.domain.ess.BaseRole;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.client.GWT;

/**
 * @author alacret
 * 
 */
public class BaseRoleModel {
	private static final GWTBaseRoleServiceAsync service = GWT
			.create(GWTBaseRoleService.class);

	private BaseRoleModel() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	/**
	 * @param callback
	 */
	public static void getAll(GHAAsyncCallback<List<BaseRole>> callback) {
		service.getAll(callback);
	}
}