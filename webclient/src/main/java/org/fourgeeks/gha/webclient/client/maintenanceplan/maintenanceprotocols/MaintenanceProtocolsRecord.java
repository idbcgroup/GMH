/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceplan.maintenanceprotocols;

import org.fourgeeks.gha.domain.enu.ActivityCategoryEnum;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocols;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;

/**
 * @author emiliot
 * 
 */
public class MaintenanceProtocolsRecord extends
		GHAGridRecord<MaintenanceProtocols> {
	private final MaintenanceProtocols entity;

	/**
	 * @param entity
	 */
	public MaintenanceProtocolsRecord(MaintenanceProtocols entity) {
		if (entity == null)
			throw new IllegalArgumentException("The argument can't be null");

		this.entity = entity;
		MaintenanceActivity activity = entity.getMaintenanceActivity();

		setAttribute("ordinal", entity.getOrdinal());
		ActivityCategoryEnum type = activity.getCategory();
		setAttribute("type", GHAStrings.get(type.name().toLowerCase()));
		setAttribute("code", activity.getId());
		setAttribute("subprotocol", activity.getIsSubProtocol());
		setAttribute("name", activity.getName());
		setAttribute("desc", activity.getDescription());
		setAttribute("materials", activity.getIsMaterialsRequired());
		setAttribute("tools", activity.getIsToolsRequired());
		setAttribute("equips", activity.getIsEquipsRequired());
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
	public MaintenanceProtocols toEntity() {
		return this.entity;
	}

}