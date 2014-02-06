package org.fourgeeks.gha.webclient.client.eiatype.damageandplanification;

import org.fourgeeks.gha.domain.enu.EiaMaintenanceState;
import org.fourgeeks.gha.domain.enu.MaintenancePlanificationType;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;

public class EIAMaintenanceRecord extends
		GHAGridRecord<EiaMaintenancePlanification> {

	private EiaMaintenancePlanification entity;

	public EIAMaintenanceRecord(EiaMaintenancePlanification entity) {
		this.entity = entity;
		Eia eia = entity.getEia();
		EiaMaintenanceState status = entity.getStatus();
		MaintenancePlanificationType type = entity.getType();

		setAttribute("id", entity.getId());
		setAttribute("initDate", entity.getBeginningDate());
		setAttribute("eia", eia == null ? "" : eia.getSerialNumber());
		setAttribute("type", type == null ? "" : type);
		setAttribute("status", status == null ? "" : status);
	}

	@Override
	public EiaMaintenancePlanification toEntity() {
		return this.entity;
	}

}
