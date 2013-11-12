package org.fourgeeks.gha.webclient.client.eiatype.utility;

import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.domain.gmh.EiaTypeUtility;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;

/**
 * @author sizturriaga, emiliot
 * 
 */
public class EIATypeUtilityRecord extends GHAGridRecord<EiaTypeUtility> {

	private EiaTypeUtility eiaTypeUtility;

	public EIATypeUtilityRecord(EiaTypeUtility eiaTypeUtility) {
		this.eiaTypeUtility = eiaTypeUtility;
		Material material = this.eiaTypeUtility.getMaterial();
		setAttribute("code", material.getCode());
		setAttribute("name", material.getName());
		setAttribute("description", material.getDescription());
		setAttribute("model", material.getModel());
		setAttribute("extCode", material.getExternalCode());
		setAttribute("amount", eiaTypeUtility.getAmount());

		// if (materialCaterogy.getType() != null)
		// setAttribute("type", materialCaterogy.getType().toString());
	}

	@Override
	public EiaTypeUtility toEntity() {
		return eiaTypeUtility;
	}
}
