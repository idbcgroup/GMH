package org.fourgeeks.gha.webclient.client.eia;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.client.GWT;

public class EIAModel {

	private static final GWTEiaTypeServiceAsync eiaService = GWT
			.create(GWTEiaTypeService.class);

	private EIAModel() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	public static void getAll(GHAAsyncCallback<List<EiaType>> callback) {
		eiaService.getAll(callback);
	}
}