/**
 * 
 */
package org.fourgeeks.gha.webclient.client.materialbrand;

import java.util.List;

import org.fourgeeks.gha.domain.glm.MaterialBrand;
import org.fourgeeks.gha.domain.glm.MaterialTypeEnum;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.client.GWT;

/**
 * @author emiliot
 * 
 */
public class MaterialBrandModel {
	private static final GWTMaterialBrandServiceAsync service = GWT
			.create(GWTMaterialBrandService.class);

	/**
	 * @param material
	 * @param callback
	 */
	public static void find(MaterialBrand materialBrand,
			GHAAsyncCallback<List<MaterialBrand>> callback) {
		service.find(materialBrand, callback);
	}

	/**
	 * @param callback
	 */
	public static void getAll(GHAAsyncCallback<List<MaterialBrand>> callback) {
		service.getAll(callback);
	}

	/**
	 * @param offset
	 * @param size
	 * @param callback
	 */
	public static void getAll(int offset, int size,
			GHAAsyncCallback<List<MaterialBrand>> callback) {
		service.getAll(offset, size, callback);
	}

	/**
	 * @param callback
	 */
	public static void getAllUtilities(
			GHAAsyncCallback<List<MaterialBrand>> callback) {
		service.findByMaterialType(MaterialTypeEnum.UTILITARIO, callback);
	}

	public static void save(MaterialBrand materialBrand,
			GHAAsyncCallback<MaterialBrand> callback) {
		service.save(materialBrand, callback);
	}

	private MaterialBrandModel() {
		throw new UnsupportedOperationException(
				"This class should not be instantiated");
	}
}
