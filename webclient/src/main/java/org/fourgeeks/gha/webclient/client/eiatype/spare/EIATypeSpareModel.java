package org.fourgeeks.gha.webclient.client.eiatype.spare;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeSpare;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.shared.GWT;

public class EIATypeSpareModel {

	private static final GWTEiaTypeSpareServiceAsync service = GWT
			.create(GWTEiaTypeSpareService.class);
	
	private EIATypeSpareModel() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	public static void save(EiaTypeSpare eiaTypeSpare,
			GHAAsyncCallback<EiaTypeSpare> callback) {
		service.save(eiaTypeSpare, callback);
	}

	public static void find(EiaType eiaType,
			GHAAsyncCallback<List<EiaTypeSpare>> callback) {
		service.find(eiaType, callback);
	}

	public static void delete(long id, GHAAsyncCallback<Void> callback) {
		service.delete(id, callback);
	}
	
}
