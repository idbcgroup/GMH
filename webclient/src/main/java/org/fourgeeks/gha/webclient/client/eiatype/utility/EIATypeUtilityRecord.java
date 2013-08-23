package org.fourgeeks.gha.webclient.client.eiatype.utility;

import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.domain.gmh.EiaTypeUtility;
import org.fourgeeks.gha.webclient.client.UI.GHAGridRecord;

/**
 * @author sizturriaga
 * 
 */
public class EIATypeUtilityRecord  extends GHAGridRecord<EiaTypeUtility>{

	private EiaTypeUtility eiaTypeUtility;
	
	public EIATypeUtilityRecord(EiaTypeUtility eiaTypeUtility){
		//setEiaTypeComponent(eiaTypeUtility);
		this.eiaTypeUtility = eiaTypeUtility;
		Material material = this.eiaTypeUtility.getMaterial();
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
	public EiaTypeUtility toEntity() {
		// TODO Auto-generated method stub
		return eiaTypeUtility;
	}
}
