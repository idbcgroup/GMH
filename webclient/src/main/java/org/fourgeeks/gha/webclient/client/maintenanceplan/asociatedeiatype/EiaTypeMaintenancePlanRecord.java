/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype;

import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;

/**
 * @author emiliot
 *
 */
public class EiaTypeMaintenancePlanRecord extends GHAGridRecord<EiaTypeMaintenancePlan> {
	EiaTypeMaintenancePlan eiaTypeMaintenancePlan;
	/**
	 * @param entity
	 */
	public EiaTypeMaintenancePlanRecord(EiaTypeMaintenancePlan entity) {
		this.eiaTypeMaintenancePlan = entity;
		setAttribute("name", entity.getEiaType().getName());
		setAttribute("code", entity.getEiaType().getCode());
		if (entity.getEiaType().getBrand() != null){
			setAttribute("brand", entity.getEiaType().getBrand().getName());
			if (entity.getEiaType().getBrand().getManufacturer() != null)
				setAttribute("manufacturer", entity.getEiaType().getBrand().getManufacturer().getName());
		}

		setAttribute("model", entity.getEiaType().getModel());
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord#toEntity()
	 */
	@Override
	public EiaTypeMaintenancePlan toEntity() {
		return this.eiaTypeMaintenancePlan;
	}

}
