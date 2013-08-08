package org.fourgeeks.gha.webclient.client.material;

import java.util.List;

import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

/**
 * @author alacret Model with the RPC Services for Materials
 */
public class MaterialModel {

	private MaterialModel() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	/**
	 * @param material
	 * @param callback
	 */
	public static void save(Material material,
			GHAAsyncCallback<Material> callback) {
		// TODO
	}

	/**
	 * @param material
	 * @param callback
	 */
	public static void find(Material material,
			GHAAsyncCallback<List<Material>> callback) {
		// TODO
	}

	/**
	 * @param id
	 * @param callback
	 */
	public static void delete(long id, GHAAsyncCallback<Void> callback) {
		// TODO
	}

}
