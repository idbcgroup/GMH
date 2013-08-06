package org.fourgeeks.gha.webclient.client.rolebase;

import java.util.List;

import org.fourgeeks.gha.domain.ess.RoleBase;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.client.GWT;

/**
 * @author alacret
 * 
 */
public class RoleBaseModel {
	private static final GWTRoleBaseServiceAsync service = GWT
			.create(GWTRoleBaseService.class);

	private RoleBaseModel() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	/**
	 * @param callback
	 */
	public static void getAll(GHAAsyncCallback<List<RoleBase>> callback) {
		service.getAll(callback);
	}
}