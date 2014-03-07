package org.fourgeeks.gha.webclient.client.eiatype.materialbrand;

import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.domain.glm.MaterialBrand;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaterialBrand;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;

/**
 * @author emiliot
 * 
 */
public class EIATypeMateriaBrandRecord extends
		GHAGridRecord<EiaTypeMaterialBrand> {
	private final EiaTypeMaterialBrand entity;

	public EIATypeMateriaBrandRecord(EiaTypeMaterialBrand eiaTypeMaterial) {
		this.entity = eiaTypeMaterial;
		MaterialBrand materialBrand = eiaTypeMaterial.getMaterialBrand();
		Material material = eiaTypeMaterial.getMaterialBrand().getMaterial();
		setAttribute("code", material.getCode());
		setAttribute("name", material.getName());
		setAttribute("description", material.getDescription());
		setAttribute("model", material.getModel());
		setAttribute("extCode", material.getExternalCode());
		setAttribute("category", material.getMaterialCategory().getName());
		setAttribute("brand", materialBrand.getBrand().getName());
		setAttribute("amount", eiaTypeMaterial.getAmount());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord#toEntity()
	 */
	@Override
	public EiaTypeMaterialBrand toEntity() {
		return entity;
	}

}
