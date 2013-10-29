package org.fourgeeks.gha.webclient.client.eiatype.materialcategory;

import org.fourgeeks.gha.domain.glm.MaterialCategory;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaterialCategory;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;

/**
 * @author alacret
 * 
 */
public class EIATypeMaterialCategoryRecord extends
		GHAGridRecord<EiaTypeMaterialCategory> {

	private EiaTypeMaterialCategory eiaTypeMaterialCategory;

	/**
	 * @param eiaTypeMaterial
	 */
	public EIATypeMaterialCategoryRecord(
			EiaTypeMaterialCategory eiaTypeMaterialCategory) {
		this.eiaTypeMaterialCategory = eiaTypeMaterialCategory;
		MaterialCategory materialCategory = eiaTypeMaterialCategory
				.getMaterialCategory();
		setAttribute("code", materialCategory.getCode());
		setAttribute("name", materialCategory.getName());
		setAttribute("description", materialCategory.getDescription());
		setAttribute("model", materialCategory.getModel());
		setAttribute("extCode", materialCategory.getExternalCode());
		if (materialCategory.getType() != null)
			setAttribute("type", materialCategory.getType().toString());

	}

	@Override
	public EiaTypeMaterialCategory toEntity() {
		return eiaTypeMaterialCategory;
	}

}
