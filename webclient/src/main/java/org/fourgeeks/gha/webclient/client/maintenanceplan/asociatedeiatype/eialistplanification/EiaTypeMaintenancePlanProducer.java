package org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.eialistplanification;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaPlanificationEntity;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;

public interface EiaTypeMaintenancePlanProducer {

	/**
	 * @param listener
	 */
	public void addEiaTypeMaintenancePlanSelectionListener(
			EiaTypeMaintenancePlanificationListener listener);

	/**
	 * @param listener
	 */
	public void removeEiaTypeMaintenancePlanSelectionListener(
			EiaTypeMaintenancePlanificationListener listener);

	/**
	 * @param EiaTypeMaintenancePlan
	 */
	public void notifyEiaTypeMaintenancePlan(EiaTypeMaintenancePlan plan);

	/**
	 * @param EiaPlanificationEntity
	 */
	public void notifyListEiaPlanificationEntity(
			List<EiaPlanificationEntity> eiaPlan);
}
