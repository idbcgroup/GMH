package org.fourgeeks.gha.webclient.client.materialcategory;

import java.util.List;

import org.fourgeeks.gha.domain.glm.ServicesResourceCategory;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.client.GWT;

/**
 * @author alacret Model with the RPC Services for MaterialCategories
 */
public class MaterialCategoryModel {

	private static final GWTMaterialCategoryServiceAsync service = GWT
			.create(GWTMaterialCategoryService.class);

	/**
	 * @param material
	 * @param callback
	 */
	public static void find(ServicesResourceCategory material,
			GHAAsyncCallback<List<ServicesResourceCategory>> callback) {
		service.find(material, callback);
	}

	/**
	 * @param callback
	 */
	public static void getAll(GHAAsyncCallback<List<ServicesResourceCategory>> callback) {
		service.getAll(callback);
	}

	/**
	 * @param offset
	 * @param size
	 * @param callback
	 */
	public static void getAll(int offset, int size,
			GHAAsyncCallback<List<ServicesResourceCategory>> callback) {
		service.getAll(offset, size, callback);
	}

	public static void save(ServicesResourceCategory material,
			GHAAsyncCallback<ServicesResourceCategory> callback) {
		service.save(material, callback);
	}

	private MaterialCategoryModel() {
		throw new UnsupportedOperationException(
				"This class should not be instantiated");
	}

}