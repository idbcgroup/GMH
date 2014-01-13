package org.fourgeeks.gha.webclient.client.maintenanceactivity;

import org.fourgeeks.gha.domain.enu.MaintenanceActivityTypeEnum;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;

/**
 * @author emiliot
 * 
 */
public class MaintenanceActivityGridRecord extends
		GHAGridRecord<MaintenanceActivity> {
	private MaintenanceActivity maintenanceActivity;

	/**
	 * @param activity
	 */
	public MaintenanceActivityGridRecord(MaintenanceActivity activity) {
		this.maintenanceActivity = activity;
		setAttribute("id", activity.getId());
		MaintenanceActivityTypeEnum type = activity.getType();
		setAttribute("type", GHAStrings.get(type.name().toLowerCase()));
		setAttribute("code", activity.getId());
		setAttribute("name", activity.getName());
		setAttribute("time", activity.getEstimatedDuration());
		TimePeriodEnum pot = activity.getEstimatedDurationPoT();
		setAttribute("pot", GHAStrings.get(pot.name().toLowerCase()));
		setAttribute("cost", activity.getEstimatedCost());
		setAttribute("currency", activity.getEstimatedCostCurrency());
	}

	@Override
	public MaintenanceActivity toEntity() {
		return this.maintenanceActivity;
	}

}
