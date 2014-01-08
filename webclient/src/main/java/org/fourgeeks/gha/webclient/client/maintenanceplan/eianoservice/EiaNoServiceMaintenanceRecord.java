/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceplan.eianoservice;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;

/**
 * @author emiliot
 * 
 */
public class EiaNoServiceMaintenanceRecord extends GHAGridRecord<Eia> {
	private Eia eiaEntity;

	/**
	 * @param eiaEntity
	 */
	public EiaNoServiceMaintenanceRecord(Eia eiaEntity) {
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
		setAttribute("state", eiaEntity.getState());
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
