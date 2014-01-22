/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceplan.subprotocolactivities;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;

/**
 * @author caparicio
 * 
 */
public class MaintenanceSubprotocolActivitiesRecord extends GHAGridRecord<Eia> {
	private Eia eiaEntity;

	/**
	 * @param eiaEntity
	 */
	public MaintenanceSubprotocolActivitiesRecord(Eia eiaEntity) {
		this.eiaEntity = eiaEntity;
	}

	/**
	 * Method for populate the records
	 */
	public void setEiaNoServiceMaintenancePlanAttributes() {
		setAttribute("number", eiaEntity.getId());
		setAttribute("type", eiaEntity.getEiaType().getName());
		setAttribute("code", eiaEntity.getCode());
		setAttribute("name", eiaEntity.getSerialNumber());
		//TODO Calcular los valores de "quantity" y "cost"
		setAttribute("quantity", 0);
		setAttribute("cost", 0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord#toEntity()
	 */
	@Override
	public Eia toEntity() {
		return eiaEntity;
	}

}
