package org.fourgeeks.gha.webclient.client.eiatype.damageandplanification;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaPreventiveMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;

public class EIAPreventiveMaintenancePlanificationRecord extends
		GHAGridRecord<EiaPreventiveMaintenancePlanification> {

	private EiaPreventiveMaintenancePlanification preventivePlanif;

	public EIAPreventiveMaintenancePlanificationRecord(
			EiaPreventiveMaintenancePlanification preventivePlanif) {
		this.preventivePlanif = preventivePlanif;
		EiaTypeMaintenancePlan plan = preventivePlanif.getPlan();
		EiaMaintenancePlanification planification = preventivePlanif
				.getPlanification();
		Eia eia = planification.getEia();

		setAttribute("id", planification.getId());
		setAttribute("initDate", planification.getScheduledDate());
		setAttribute("eia", eia == null ? "" : eia.getSerialNumber());
		setAttribute("plan", plan == null ? "" : plan.getMaintenancePlan()
				.getName());
	}

	@Override
	public EiaPreventiveMaintenancePlanification toEntity() {
		return this.preventivePlanif;
	}

}
