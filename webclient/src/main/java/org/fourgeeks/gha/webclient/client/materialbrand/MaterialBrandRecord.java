/**
 * 
 */
package org.fourgeeks.gha.webclient.client.materialbrand;

import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.domain.glm.MaterialBrand;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;

/**
 * @author emiliot
 * 
 */
public class MaterialBrandRecord extends GHAGridRecord<MaterialBrand> {
	private final MaterialBrand entity;

	public MaterialBrandRecord(MaterialBrand entity) {
		this.entity = entity;
		final Material material = entity.getMaterial();

		setAttribute("code", material.getCode());
		setAttribute("name", material.getName());
		setAttribute("description", material.getDescription());
		setAttribute("model", material.getModel());
		setAttribute("extCode", material.getExternalCode());
		setAttribute("brand", entity.getBrand());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord#toEntity()
	 */
	@Override
	public MaterialBrand toEntity() {
		return this.entity;
	}
}
