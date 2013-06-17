package org.fourgeeks.gha.webclient.client.brand;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.client.GWT;

public class BrandModel {
	private static final GWTBrandServiceAsync service = GWT
			.create(GWTBrandService.class);

	private BrandModel() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	public static void getAll(GHAAsyncCallback<List<Brand>> callback) {
		service.getAll(callback);
	}

}
