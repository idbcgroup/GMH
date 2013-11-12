package org.fourgeeks.gha.webclient.client.eiatype.material;

import org.fourgeeks.gha.domain.glm.Material;
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
		Material material = eiaTypeMaterial.getMaterial();
		setAttribute("code", material.getCode());
		setAttribute("name", material.getName());
		setAttribute("description", material.getDescription());
		setAttribute("model", material.getModel());
		setAttribute("extCode", material.getExternalCode());
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
