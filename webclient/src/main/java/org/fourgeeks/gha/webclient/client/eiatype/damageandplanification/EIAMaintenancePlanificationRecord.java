package org.fourgeeks.gha.webclient.client.eiatype.damageandplanification;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;

public class EIAMaintenancePlanificationRecord extends
		GHAGridRecord<EiaMaintenancePlanification> {

	private final EiaMaintenancePlanification preventivePlanif;

	public EIAMaintenancePlanificationRecord(
			EiaMaintenancePlanification preventivePlanif) {
		this.preventivePlanif = preventivePlanif;
		EiaTypeMaintenancePlan plan = preventivePlanif.getPlan();
		Eia eia = preventivePlanif.getEia();

		setAttribute("id", preventivePlanif.getId());
		setAttribute("initDate", preventivePlanif.getBeginningDate());
		setAttribute("eia", eia == null ? "" : eia.getSerialNumber());
		setAttribute("plan", plan == null ? "" : plan.getMaintenancePlan()
				.getName());
	}

	@Override
	public EiaMaintenancePlanification toEntity() {
		return this.preventivePlanif;
	}

}
