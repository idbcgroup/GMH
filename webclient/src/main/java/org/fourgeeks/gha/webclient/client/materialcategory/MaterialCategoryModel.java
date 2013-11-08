package org.fourgeeks.gha.webclient.client.materialcategory;

import java.util.List;

import org.fourgeeks.gha.domain.glm.MaterialCategory;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.client.GWT;

/**
 * @author alacret Model with the RPC Services for MaterialCategories
 */
public class MaterialCategoryModel {

	private static final GWTMaterialCategoryServiceAsync service = GWT
			.create(GWTMaterialCategoryService.class);

	private MaterialCategoryModel() {
		throw new UnsupportedOperationException(
				"This class should not be instantiated");
	}

	/**
	 * @param material
	 * @param callback
	 */
	public static void find(MaterialCategory material,
			GHAAsyncCallback<List<MaterialCategory>> callback) {
		service.find(material, callback);
	}

	/**
	 * @param callback
	 */
	public static void getAll(GHAAsyncCallback<List<MaterialCategory>> callback) {
		service.getAll(callback);
	}

	/**
	 * @param offset
	 * @param size
	 * @param callback
	 */
	public static void getAll(int offset, int size,
			GHAAsyncCallback<List<MaterialCategory>> callback) {
		service.getAll(offset, size, callback);
	}

	/**
	 * @param callback
	 */
	public static void getAllUtilities(
			GHAAsyncCallback<List<MaterialCategory>> callback) {
		service.getAllUtilities(callback);
	}

	public static void save(MaterialCategory material,
			GHAAsyncCallback<MaterialCategory> callback) {
		service.save(material, callback);
	}

}