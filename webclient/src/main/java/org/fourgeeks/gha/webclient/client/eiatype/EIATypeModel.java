package org.fourgeeks.gha.webclient.client.eiatype;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.client.GWT;

public class EIATypeModel {

	private static final GWTEiaTypeServiceAsync service = GWT
			.create(GWTEiaTypeService.class);

	private EIATypeModel() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	public static void getAll(GHAAsyncCallback<List<EiaType>> callback) {
		service.getAll(callback);
	}

	public static void find(EiaType eiaType,
			GHAAsyncCallback<List<EiaType>> ghaAsyncCallback) {
		service.find(eiaType, ghaAsyncCallback);

	}

	public static void save(EiaType eiaType, GHAAsyncCallback<Long> callback) {
		service.save(eiaType, callback);

	}

	public static void update(EiaType eiaType,
			GHAAsyncCallback<Boolean> callback) {
		service.update(eiaType, callback);
	}
}