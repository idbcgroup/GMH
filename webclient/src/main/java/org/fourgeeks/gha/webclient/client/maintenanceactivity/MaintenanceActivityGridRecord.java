package org.fourgeeks.gha.webclient.client.maintenanceactivity;

import org.fourgeeks.gha.domain.enu.ActivityCategoryEnum;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.gmh.Activity;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;

/**
 * @author emiliot
 * 
 */
public class MaintenanceActivityGridRecord extends
		GHAGridRecord<MaintenanceActivity> {
	private final MaintenanceActivity maintenanceActivity;

	/**
	 * @param activity
	 */
	public MaintenanceActivityGridRecord(MaintenanceActivity entity) {
		this.maintenanceActivity = entity;
		Activity activity = entity.getActivity();

		setAttribute("id", activity.getId());
		ActivityCategoryEnum category = activity.getCategory();
		setAttribute("type", GHAStrings.get(category.name().toLowerCase()));
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
