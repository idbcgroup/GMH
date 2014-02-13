package org.fourgeeks.gha.webclient.client.bpu;

import java.util.List;

import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.client.GWT;

/**
 * @author alacret, emiliot
 * 
 */
public class BpuModel {
	private static final GWTBpuServiceAsync service = GWT
			.create(GWTBpuService.class);

	private BpuModel() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	/**
	 * @param callback
	 */
	public static void getAll(GHAAsyncCallback<List<Bpu>> callback) {
		service.getAll(callback);
	}

}