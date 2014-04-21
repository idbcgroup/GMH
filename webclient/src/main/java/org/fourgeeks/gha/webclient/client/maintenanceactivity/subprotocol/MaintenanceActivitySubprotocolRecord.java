/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceactivity.subprotocol;

import org.fourgeeks.gha.domain.Activity;
import org.fourgeeks.gha.domain.ActivityType;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.gmh.SubProtocolAndChecklist;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;

/**
 * @author caparicio
 * 
 */
public class MaintenanceActivitySubprotocolRecord extends
		GHAGridRecord<SubProtocolAndChecklist> {
	private final SubProtocolAndChecklist entity;

	/**
	 * 
	 * @param entity
	 */
	public MaintenanceActivitySubprotocolRecord(SubProtocolAndChecklist entity) {
		this.entity = entity;
		setSubProtocolAndChecklistAttributes();
	}

	/**
	 * Method for populate the records
	 */
	private void setSubProtocolAndChecklistAttributes() {
		Activity activity = entity.getActivity();
		setAttribute("ordinal", entity.getOrdinal());
		ActivityType type = activity.getType();
		if (type != null)
			setAttribute("type",
					GHAStrings.get(type.getDescription().toLowerCase()));
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
	public SubProtocolAndChecklist toEntity() {
		return entity;
	}

}
