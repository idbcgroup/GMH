package org.fourgeeks.gha.webclient.client.materialcategory;

import org.fourgeeks.gha.domain.glm.MaterialCategory;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;

/**
 * @author alacret Wrapper class for represent a MaterialCategory as a Record
 *         for grid components
 */
public class MaterialCategoryRecord extends GHAGridRecord<MaterialCategory> {

	private MaterialCategory materialCategory;

	/**
	 * @param materialCategory
	 */
	public MaterialCategoryRecord(MaterialCategory materialCategory) {
		this.materialCategory = materialCategory;
		setAttribute("code", materialCategory.getCode());
		setAttribute("name", materialCategory.getName());
		setAttribute("description", materialCategory.getDescription());
		setAttribute("model", materialCategory.getModel());
		setAttribute("extCode", materialCategory.getExternalCode());

		if (materialCategory.getType() != null)
			setAttribute("type", materialCategory.getType().toString());

	}

	@Override
	public MaterialCategory toEntity() {
		return materialCategory;
	}

}
