package org.fourgeeks.gha.webclient.client.eiatype;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAGridRecord;

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
		if (eiaType.getBrand() != null)
			setAttribute("brand", eiaType.getBrand().getName());
		if (eiaType.getManufacturer() != null)
			setAttribute("manufacturer", eiaType.getManufacturer().getName());
		setAttribute("model", eiaType.getModel());
	}

	@Override
	public EiaType toEntity() {
		return this.eiaType;
	}
}