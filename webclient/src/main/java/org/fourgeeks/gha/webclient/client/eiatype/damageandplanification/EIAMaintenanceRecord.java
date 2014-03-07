package org.fourgeeks.gha.webclient.client.eiatype.damageandplanification;

import org.fourgeeks.gha.domain.enu.EiaMaintenanceState;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaCorrectiveMaintenance;
import org.fourgeeks.gha.domain.gmh.EiaDamageReport;
import org.fourgeeks.gha.domain.gmh.EiaMaintenance;
import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaPreventiveMaintenance;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;

/**
 * @author naramirez
 * 
 */
public class EIAMaintenanceRecord extends GHAGridRecord<EiaMaintenance> {

	private EiaMaintenance entity;

	/**
	 * @param entity
	 */
	public EIAMaintenanceRecord(EiaMaintenance entity) {
		this.entity = entity;

		Eia eia = null;
		if (entity instanceof EiaCorrectiveMaintenance) {
			EiaCorrectiveMaintenance maintenance = (EiaCorrectiveMaintenance) entity;
			EiaDamageReport damageReport = maintenance.getDamageReport();
			eia = damageReport.getEia();
			setAttribute("type", GHAStrings.get("corrective"));
		} else {
			EiaPreventiveMaintenance maintenance = (EiaPreventiveMaintenance) entity;
			EiaMaintenancePlanification planif = maintenance.getPlanification();
			eia = planif.getEia();
			setAttribute("type", GHAStrings.get("preventive"));
		}

		setAttribute("id", entity.getId());
		setAttribute("initDate", entity.getScheduledDate());
		setAttribute("eia", eia == null ? "" : eia.getSerialNumber());

		EiaMaintenanceState status = entity.getState();
		setAttribute("status", status == null ? "" : status);
	}

	@Override
	public EiaMaintenance toEntity() {
		return this.entity;
	}

}
