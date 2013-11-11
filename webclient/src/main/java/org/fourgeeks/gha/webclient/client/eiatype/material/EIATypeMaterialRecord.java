package org.fourgeeks.gha.webclient.client.eiatype.material;

import org.fourgeeks.gha.domain.glm.MaterialCategory;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaterial;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;

/**
 * @author emiliot
 * 
 */
public class EIATypeMaterialRecord extends GHAGridRecord<EiaTypeMaterial> {
	private EiaTypeMaterial eiaTypeMaterial;

	public EIATypeMaterialRecord(EiaTypeMaterial eiaTypeMaterial) {
		this.eiaTypeMaterial = eiaTypeMaterial;
		MaterialCategory materialCategory = eiaTypeMaterial.getMaterial()
				.getMaterialCategory();
		if (materialCategory == null)
			return;
		setAttribute("code", materialCategory.getCode());
		setAttribute("name", materialCategory.getName());
		setAttribute("description", materialCategory.getDescription());
		setAttribute("model", materialCategory.getModel());
		setAttribute("extCode", materialCategory.getExternalCode());
		// if (materialCategory.getType() != null)
		// setAttribute("type", materialCategory.getType().toString());
		setAttribute("amount", eiaTypeMaterial.getAmount());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord#toEntity()
	 */
	@Override
	public EiaTypeMaterial toEntity() {
		return eiaTypeMaterial;
	}

}
