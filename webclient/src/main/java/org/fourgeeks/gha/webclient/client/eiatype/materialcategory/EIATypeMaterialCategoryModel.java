package org.fourgeeks.gha.webclient.client.eiatype.materialcategory;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaterialCategory;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.shared.GWT;

public class EIATypeMaterialCategoryModel {

	private static final GWTEiaTypeMaterialCategoryServiceAsync service = GWT
			.create(GWTEiaTypeMaterialCategoryService.class);

	private EIATypeMaterialCategoryModel() {
		throw new UnsupportedOperationException(
				"This class should not be instantiated");
	}

	public static void find(EiaType eiaType,
			GHAAsyncCallback<List<EiaTypeMaterialCategory>> callback) {
		service.findByEiaType(eiaType, callback);
	}

	public static void save(EiaTypeMaterialCategory eiaTypeMaterial,
			GHAAsyncCallback<EiaTypeMaterialCategory> callback) {
		service.save(eiaTypeMaterial, callback);
	}

	public static void delete(long id, GHAAsyncCallback<Void> callback) {
		service.delete(id, callback);
	}

	/**
	 * @param entity
	 * @param callback
	 */
	public static void update(EiaTypeMaterialCategory entity,
			GHAAsyncCallback<EiaTypeMaterialCategory> callback) {
		service.update(entity, callback);
	}

}
