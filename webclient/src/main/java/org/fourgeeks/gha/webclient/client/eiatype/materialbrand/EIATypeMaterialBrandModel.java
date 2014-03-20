package org.fourgeeks.gha.webclient.client.eiatype.materialbrand;

import java.util.List;

import org.fourgeeks.gha.domain.glm.MaterialTypeEnum;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaterialBrand;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.shared.GWT;

/**
 * @author emiliot
 * 
 */
public class EIATypeMaterialBrandModel {
	private static final GWTEiaTypeMaterialServiceAsync service = GWT
			.create(GWTEiaTypeMaterialService.class);

	public static void delete(long id, GHAAsyncCallback<Void> callback) {
		service.delete(id, callback);
	}

	public static void find(EiaType eiaType, MaterialTypeEnum type,
			GHAAsyncCallback<List<EiaTypeMaterialBrand>> callback) {
		service.findByEiaType(eiaType, type, callback);
	}

	public static void save(EiaTypeMaterialBrand eiaTypeMaterial,
			GHAAsyncCallback<EiaTypeMaterialBrand> callback) {
		service.save(eiaTypeMaterial, callback);
	}

	/**
	 * @param entity
	 * @param callback
	 */
	public static void update(EiaTypeMaterialBrand entity,
			GHAAsyncCallback<EiaTypeMaterialBrand> callback) {
		service.update(entity, callback);
	}

	private EIATypeMaterialBrandModel() {
		throw new UnsupportedOperationException(
				"This class should not be instantiated");
	}
}
