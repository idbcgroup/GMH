package org.fourgeeks.gha.webclient.client.material;

import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;

/**
 * @author emiliot Wrapper class for represent a Material as a Record for grid
 *         components
 * 
 */
public class MaterialRecord extends GHAGridRecord<Material> {
	private final Material material;

	/**
	 * @param material
	 */
	public MaterialRecord(Material material) {
		this.material = material;

		setAttribute("code", material.getCode());
		setAttribute("name", material.getName());
		setAttribute("description", material.getDescription());
		setAttribute("model", material.getModel());
		setAttribute("extCode", material.getExternalCode());
		// setAttribute("brand", material.getBrand());

		// if (materialCategory.getType() != null)
		// setAttribute("type", materialCategory.getType().toString());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord#toEntity()
	 */
	@Override
	public Material toEntity() {
		return material;
	}

}
