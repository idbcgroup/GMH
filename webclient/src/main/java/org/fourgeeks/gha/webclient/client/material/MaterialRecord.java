package org.fourgeeks.gha.webclient.client.material;

import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.webclient.client.UI.GHAGridRecord;

/**
 * @author alacret Wrapper class for represent a Material as a Record for grid
 *         components
 */
public class MaterialRecord extends GHAGridRecord<Material> {

	private Material material;

	/**
	 * @param material
	 */
	public MaterialRecord(Material material) {
		this.material = material;
		setAttribute("code", material.getCode());
		setAttribute("name", material.getName());
		setAttribute("description", material.getDescription());
		setAttribute("model", material.getModel());
		setAttribute("extCode", material.getExtCode());

		if (material.getType() != null)
			setAttribute("type", material.getType().toString());

		if (material.getExternalProvider() != null)
			setAttribute("externalProvider", material.getExternalProvider()
					.getInstitution().getName());

	}

	@Override
	public Material toEntity() {
		return material;
	}

}
