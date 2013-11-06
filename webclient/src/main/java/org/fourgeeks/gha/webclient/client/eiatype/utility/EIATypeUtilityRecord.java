package org.fourgeeks.gha.webclient.client.eiatype.utility;

import org.fourgeeks.gha.domain.glm.MaterialCategory;
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
		MaterialCategory materialCaterogy = this.eiaTypeUtility
				.getMaterialCategory();
		setAttribute("code", materialCaterogy.getCode());
		setAttribute("name", materialCaterogy.getName());
		setAttribute("description", materialCaterogy.getDescription());
		setAttribute("model", materialCaterogy.getModel());
		setAttribute("extCode", materialCaterogy.getExternalCode());
		setAttribute("amount", eiaTypeUtility.getAmount());

		if (materialCaterogy.getType() != null)
			setAttribute("type", materialCaterogy.getType().toString());
	}

	@Override
	public EiaTypeUtility toEntity() {
		return eiaTypeUtility;
	}
}
