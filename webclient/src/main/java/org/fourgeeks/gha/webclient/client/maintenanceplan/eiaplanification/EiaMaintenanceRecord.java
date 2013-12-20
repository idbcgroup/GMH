/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceplan.eiaplanification;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;

/**
 * @author emiliot
 * 
 */
public class EiaMaintenanceRecord extends GHAGridRecord<Eia> {
	private Eia eiaEntity;
	private EiaMaintenancePlanification planificationEntity;

	/**
	 * @param eiaEntity
	 * @param planificationEntity 
	 */
	public EiaMaintenanceRecord(Eia eiaEntity,
			EiaMaintenancePlanification planificationEntity) {
		this.eiaEntity = eiaEntity;
		this.planificationEntity = planificationEntity;
	}

	/**
	 * Method for populate the records
	 */
	public void setEiaMaintenancePlanAttributes() {
		setAttribute("number", eiaEntity.getId());
		setAttribute("type", eiaEntity.getEiaType().getName());
		setAttribute("code", eiaEntity.getCode());
		setAttribute("name", eiaEntity.getSerialNumber());
		setAttribute("quantity", planificationEntity.getEffectiveTime());
		setAttribute("cost", planificationEntity.getStatus());
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
