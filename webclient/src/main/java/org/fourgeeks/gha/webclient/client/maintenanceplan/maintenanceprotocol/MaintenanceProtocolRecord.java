/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceplan.maintenanceprotocol;

import org.fourgeeks.gha.domain.Activity;
import org.fourgeeks.gha.domain.ActivityType;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;

/**
 * @author emiliot
 * 
 */
public class MaintenanceProtocolRecord extends
		GHAGridRecord<MaintenanceProtocol> {
	private final MaintenanceProtocol entity;

	/**
	 * @param entity
	 */
	public MaintenanceProtocolRecord(MaintenanceProtocol entity) {
		if (entity == null)
			throw new IllegalArgumentException("The argument can't be null");

		this.entity = entity;
		MaintenanceActivity mActivity = entity.getMaintenanceActivity();
		final Activity activity = mActivity.getActivity();

		setAttribute("ordinal", entity.getOrdinal());
		ActivityType type = activity.getType();
		if (type != null)
			setAttribute("type",
					GHAStrings.get(type.getDescription().toLowerCase()));
		setAttribute("code", activity.getId());
		setAttribute("subprotocol", activity.getIsSubProtocol());
		setAttribute("name", activity.getName());
		setAttribute("desc", activity.getDescription());
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
	public MaintenanceProtocol toEntity() {
		return this.entity;
	}

}
