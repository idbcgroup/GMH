/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceplan.subprotocolactivities;

import org.fourgeeks.gha.domain.enu.MaintenanceActivityTypeEnum;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
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
	private MaintenanceSubProtocol entity;

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
		MaintenanceActivity activity = entity.getMaintenanceActivity();
		setAttribute("ordinal", entity.getOrdinal());
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
