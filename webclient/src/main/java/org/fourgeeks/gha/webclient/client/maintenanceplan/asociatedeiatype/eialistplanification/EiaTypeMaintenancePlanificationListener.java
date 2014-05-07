package org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.eialistplanification;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaPlanificationEntity;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;

public interface EiaTypeMaintenancePlanificationListener {

	public void select(EiaTypeMaintenancePlan plan);

	public void select(List<EiaPlanificationEntity> listEiaPlan);

}
