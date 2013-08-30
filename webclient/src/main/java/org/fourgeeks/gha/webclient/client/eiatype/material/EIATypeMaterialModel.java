package org.fourgeeks.gha.webclient.client.eiatype.material;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaterial;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.shared.GWT;

public class EIATypeMaterialModel {

	private static final GWTEiaTypeMaterialServiceAsync service = GWT
			.create(GWTEiaTypeMaterialService.class);
	
	private EIATypeMaterialModel() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}
	
	public static void find(EiaType eiaType,
			GHAAsyncCallback<List<EiaTypeMaterial>> callback) {
		service.findByEiaType(eiaType, callback);
	}

	public static void save(EiaTypeMaterial eiaTypeMaterial,
			GHAAsyncCallback<EiaTypeMaterial> callback) {
		service.save(eiaTypeMaterial, callback);
	}

	public static void delete(long id, GHAAsyncCallback<Void> callback) {
		service.delete(id, callback);
	}
	
}
