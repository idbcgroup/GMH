package org.fourgeeks.gha.webclient.client.manufacturer;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.Manufacturer;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.client.GWT;

public class ManufacturerModel {
	private static final GWTManufacturerServiceAsync service = GWT
			.create(GWTManufacturerService.class);

	private ManufacturerModel() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	public static void getAll(GHAAsyncCallback<List<Manufacturer>> callback) {
		service.getAll(callback);
	}
}