/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceplan;

import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;

/**
 * @author emiliot
 * 
 */
public class MaintenancePlanRecord extends GHAGridRecord<MaintenancePlan> {
	private MaintenancePlan maintenancePlan;

	/**
	 * @param maintenancePlan
	 */
	public MaintenancePlanRecord(MaintenancePlan maintenancePlan) {
		this.maintenancePlan = maintenancePlan;
		setAttribute("id", this.maintenancePlan.getId());
		setAttribute("name", this.maintenancePlan.getName());
		setAttribute("desc", this.maintenancePlan.getDescription());
		setAttribute("freq", this.maintenancePlan.getFrequency());

		final TimePeriodEnum pot = this.maintenancePlan.getPot();
		if (pot != null) {
			final String key = pot.name().toLowerCase();
			setAttribute("pot", GHAStrings.get(key));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord#toEntity()
	 */
	@Override
	public MaintenancePlan toEntity() {
		return this.maintenancePlan;
	}

}
