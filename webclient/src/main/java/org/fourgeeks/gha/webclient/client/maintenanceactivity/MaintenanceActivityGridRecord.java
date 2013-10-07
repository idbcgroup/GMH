package org.fourgeeks.gha.webclient.client.maintenanceactivity;

import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;

/**
 * @author emiliot
 *
 */
public class MaintenanceActivityGridRecord extends GHAGridRecord<MaintenanceActivity> {
	private MaintenanceActivity maintenanceActivity;


	public MaintenanceActivityGridRecord(MaintenanceActivity maintenanceActivity) {
		this.maintenanceActivity = maintenanceActivity;
		setAttribute("id", this.maintenanceActivity.getId());
		setAttribute("name", this.maintenanceActivity.getName());
		setAttribute("desc", this.maintenanceActivity.getDescription());
	}


	@Override
	public MaintenanceActivity toEntity() {
		return this.maintenanceActivity;
	}

}
