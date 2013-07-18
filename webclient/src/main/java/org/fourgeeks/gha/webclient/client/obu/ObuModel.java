package org.fourgeeks.gha.webclient.client.obu;

import java.util.List;

import org.fourgeeks.gha.domain.gar.Obu;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.client.GWT;

/**
 * @author alacret
 * 
 */
public class ObuModel {
	private static final GWTObuServiceAsync service = GWT
			.create(GWTObuService.class);

	private ObuModel() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	/**
	 * @param callback
	 */
	public static void getAll(GHAAsyncCallback<List<Obu>> callback) {
		service.getAll(callback);
	}
}