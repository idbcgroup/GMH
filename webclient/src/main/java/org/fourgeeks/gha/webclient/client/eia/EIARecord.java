package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;

/**
 * @author alacret, emiliot
 * 
 */
public class EIARecord extends GHAGridRecord<Eia> {

	private Eia eia;

	/**
	 * @param eia
	 */
	public EIARecord(Eia eia) {
		this.eia = eia;

		setAttribute("id", eia.getId());
		setAttribute("code", eia.getCode());
		setAttribute("serialNumber", eia.getSerialNumber());
		setAttribute("state", eia.getState());
		if (eia.getWorkingArea() != null) {
			setAttribute("location", eia.getWorkingArea().getName());
		} else if (eia.getFacility() != null) {
			setAttribute("location", eia.getFacility().getName());
		}

		EiaType eiaType = eia.getEiaType();
		if (eiaType != null) {
			setAttribute("name", eiaType.getName());
		}
	}

	@Override
	public Eia toEntity() {
		return this.eia;
	}

}