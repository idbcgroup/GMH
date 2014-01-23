package org.fourgeeks.gha.webclient.client.activity;

import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;

/**
 * 
 * @author caparicio
 * 
 */
public class ActivityRecord extends GHAGridRecord<MaintenanceActivity> {
	private MaintenanceActivity maintenanceActivity;

	/**
	 * @param maintenanceActivity
	 */
	public ActivityRecord(MaintenanceActivity maintenanceActivity) {
		this.maintenanceActivity = maintenanceActivity;
		setAttribute("id", this.maintenanceActivity.getId());
		setAttribute("name", this.maintenanceActivity.getName());
		setAttribute("desc", this.maintenanceActivity.getDescription());
		// setAttribute("freq", this.maintenanceActivity.getFrequency());
		//
		// final TimePeriodEnum pot = this.maintenanceActivity.getPot();
		// if (pot != null) {
		// final String key = pot.name().toLowerCase();
		// setAttribute("pot", GHAStrings.get(key));
		// }
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord#toEntity()
	 */
	@Override
	public MaintenanceActivity toEntity() {
		return this.maintenanceActivity;
	}

}
