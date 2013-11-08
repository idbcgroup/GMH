package org.fourgeeks.gha.webclient.client.eiatype.material;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaterial;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.shared.GWT;

/**
 * @author emiliot
 * 
 */
public class EIATypeMaterialModel {
	private static final GWTEiaTypeMaterialServiceAsync service = GWT
			.create(GWTEiaTypeMaterialService.class);

	private EIATypeMaterialModel() {
		throw new UnsupportedOperationException(
				"This class should not be instantiated");
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

	/**
	 * @param entity
	 * @param callback
	 */
	public static void update(EiaTypeMaterial entity,
			GHAAsyncCallback<EiaTypeMaterial> callback) {
		service.update(entity, callback);
	}
}
