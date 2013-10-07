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
	private EiaTypeMaintenancePlan eiaTypeMaintenancePlan;
	/**
	 * @param entity
	 */
	public EiaTypeMaintenancePlanRecord(EiaTypeMaintenancePlan entity) {
		this.eiaTypeMaintenancePlan = entity;
		
	}
	
	public void setEiaTypeAttributes(){
		setAttribute("name", this.eiaTypeMaintenancePlan.getEiaType().getName());
		setAttribute("code", this.eiaTypeMaintenancePlan.getEiaType().getCode());
		if (this.eiaTypeMaintenancePlan.getEiaType().getBrand() != null){
			setAttribute("brand", this.eiaTypeMaintenancePlan.getEiaType().getBrand().getName());
			if (this.eiaTypeMaintenancePlan.getEiaType().getBrand().getManufacturer() != null)
				setAttribute("manufacturer", this.eiaTypeMaintenancePlan.getEiaType().getBrand().getManufacturer().getName());
		}

		setAttribute("model", this.eiaTypeMaintenancePlan.getEiaType().getModel());
	}
	
	public void setMaintenancePlanAttributes(){
		setAttribute("name", this.eiaTypeMaintenancePlan.getMaintenancePlan().getName());
		setAttribute("desc", this.eiaTypeMaintenancePlan.getMaintenancePlan().getDescription());
		setAttribute("pot", this.eiaTypeMaintenancePlan.getMaintenancePlan().getPot());
		setAttribute("freq", this.eiaTypeMaintenancePlan.getMaintenancePlan().getFrequency());
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord#toEntity()
	 */
	@Override
	public EiaTypeMaintenancePlan toEntity() {
		return this.eiaTypeMaintenancePlan;
	}

}
