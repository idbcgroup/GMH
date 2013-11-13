package org.fourgeeks.gha.webclient.client.eiatype;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;

/**
 * @author alacret
 * 
 */
public class EIATypeRecord extends GHAGridRecord<EiaType> {

	private EiaType eiaType;

	/**
	 * @param eiaType
	 */
	public EIATypeRecord(EiaType eiaType) {
		this.eiaType = eiaType;
		setAttribute("name", eiaType.getName());
		setAttribute("code", eiaType.getCode());
		setAttribute("type", eiaType.getType());
		setAttribute("subtype", eiaType.getSubtype());
		setAttribute("model", eiaType.getModel());

		if (eiaType.getBrand() != null)
			setAttribute("brand", eiaType.getBrand().getName());
	}

	@Override
	public EiaType toEntity() {
		return this.eiaType;
	}
}