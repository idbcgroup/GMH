package org.fourgeeks.gha.webclient.client.eiatype.material;

import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaterial;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;

/**
 * @author alacret
 * 
 */
public class EIATypeMaterialRecord extends GHAGridRecord<EiaTypeMaterial> {

	private EiaTypeMaterial eiaTypeMaterial;

	/**
	 * @param eiaTypeMaterial
	 */
	public EIATypeMaterialRecord(EiaTypeMaterial eiaTypeMaterial) {
		this.eiaTypeMaterial = eiaTypeMaterial;
		Material material = eiaTypeMaterial.getMaterial();
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
	public EiaTypeMaterial toEntity() {
		return eiaTypeMaterial;
	}

}
