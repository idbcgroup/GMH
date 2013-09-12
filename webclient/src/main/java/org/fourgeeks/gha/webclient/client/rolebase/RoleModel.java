package org.fourgeeks.gha.webclient.client.rolebase;

import java.util.List;

import org.fourgeeks.gha.domain.ess.Role;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.client.GWT;

/**
 * @author alacret
 * 
 */
public class RoleModel {
	private static final GWTRoleServiceAsync service = GWT
			.create(GWTRoleService.class);

	private RoleModel() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	/**
	 * @param callback
	 */
	public static void getAll(GHAAsyncCallback<List<Role>> callback) {
		service.getAll(callback);
	}
}