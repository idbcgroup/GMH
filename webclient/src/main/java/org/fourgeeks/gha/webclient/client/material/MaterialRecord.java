package org.fourgeeks.gha.webclient.client.material;

import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.domain.glm.MaterialCategory;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;

/**
 * @author emiliotWrapper class for represent a Material as a Record for grid
 *         components
 * 
 */
public class MaterialRecord extends GHAGridRecord<Material> {
	private Material material;

	/**
	 * @param material
	 */
	public MaterialRecord(Material material) {
		this.material = material;
		final MaterialCategory materialCategory = material
				.getMaterialCategory();

		setAttribute("code", materialCategory.getCode());
		setAttribute("name", materialCategory.getName());
		setAttribute("description", materialCategory.getDescription());
		setAttribute("model", materialCategory.getModel());
		setAttribute("extCode", materialCategory.getExternalCode());

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
