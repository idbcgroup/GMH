package org.fourgeeks.gha.webclient.client.material;

import java.util.List;

import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.client.GWT;

/**
 * @author alacret Model with the RPC Services for Materials
 */
public class MaterialModel {

	private static final GWTMaterialServiceAsync service = GWT
			.create(GWTMaterialService.class);

	private MaterialModel() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	/**
	 * @param material
	 * @param callback
	 */
	public static void find(Material material,
			GHAAsyncCallback<List<Material>> callback) {
		service.find(material, callback);
	}

	/**
	 * @param callback
	 */
	public static void getAll(GHAAsyncCallback<List<Material>> callback) {
		service.getAll(callback);
	}

	/**
	 * @param offset
	 * @param size
	 * @param callback
	 */
	public static void getAll(int offset, int size,
			GHAAsyncCallback<List<Material>> callback) {
		service.getAll(offset, size, callback);
	}

	public static void save(Material material,
			GHAAsyncCallback<Material> callback) {
		service.save(material, callback);
	}

}