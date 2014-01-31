package org.fourgeeks.gha.webclient.client.eiatype.damageandplanification;

import org.fourgeeks.gha.domain.enu.MaintenancePlanificationStatus;
import org.fourgeeks.gha.domain.enu.MaintenancePlanificationType;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;

public class EIAMaintenancePlanificationRecord extends
		GHAGridRecord<EiaMaintenancePlanification> {

	private EiaMaintenancePlanification entity;

	public EIAMaintenancePlanificationRecord(EiaMaintenancePlanification entity) {
		this.entity = entity;
		Eia eia = entity.getEia();
		MaintenancePlanificationStatus status = entity.getStatus();
		MaintenancePlanificationType type = entity.getType();

		setAttribute("id", entity.getId());
		setAttribute("initDate", entity.getScheduledDate());
		setAttribute("eia", eia == null ? "" : eia.getSerialNumber());
		setAttribute("type", type == null ? "" : type);
		setAttribute("status", status == null ? "" : status);
	}

	@Override
	public EiaMaintenancePlanification toEntity() {
		return this.entity;
	}

}