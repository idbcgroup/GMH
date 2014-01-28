/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceplan.subprotocolactivities;

import org.fourgeeks.gha.domain.enu.ActivityCategoryEnum;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.gmh.Activity;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.MaintenanceSubProtocol;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;

/**
 * @author caparicio
 * 
 */
public class MaintenanceSubprotocolRecord extends
		GHAGridRecord<MaintenanceSubProtocol> {
	private final MaintenanceSubProtocol entity;

	/**
	 * @param eiaEntity
	 */
	public MaintenanceSubprotocolRecord(MaintenanceSubProtocol entity) {
		this.entity = entity;
	}

	/**
	 * Method for populate the records
	 */
	public void setEiaNoServiceMaintenancePlanAttributes() {
		MaintenanceActivity maintenanceActivity = entity
				.getMaintenanceActivity();
		final Activity activity = maintenanceActivity.getActivity();

		setAttribute("ordinal", entity.getOrdinal());
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord#toEntity()
	 */
	@Override
	public MaintenanceSubProtocol toEntity() {
		return entity;
	}

}
