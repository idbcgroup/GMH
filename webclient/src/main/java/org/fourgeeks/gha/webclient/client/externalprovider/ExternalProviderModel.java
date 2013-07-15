package org.fourgeeks.gha.webclient.client.externalprovider;

import java.util.List;

import org.fourgeeks.gha.domain.glm.ExternalProvider;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.client.GWT;

/**
 * @author alacret
 * 
 */
public class ExternalProviderModel {
	private static final GWTExternalProviderServiceAsync service = GWT
			.create(GWTExternalProviderService.class);

	private ExternalProviderModel() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	/**
	 * @param callback
	 */
	public static void getAll(GHAAsyncCallback<List<ExternalProvider>> callback) {
		service.getAll(callback);
	}

}
