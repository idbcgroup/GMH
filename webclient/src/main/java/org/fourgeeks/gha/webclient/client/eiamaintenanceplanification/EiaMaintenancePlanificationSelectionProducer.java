package org.fourgeeks.gha.webclient.client.eiamaintenanceplanification;

import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;

public interface EiaMaintenancePlanificationSelectionProducer {
	/**
	 * @param listener
	 */
	public void addEiaMaintenancePlanificationSelectionListener(
			EiaMaintenancePlanificationSelectionListener listener);

	/**
	 * @param listener
	 */
	public void removeEiaMaintenancePlanificationSelectionListener(
			EiaMaintenancePlanificationSelectionListener listener);

	/**
	 * @param entity
	 */
	public void notifyEiaMaintenancePlanification(
			EiaMaintenancePlanification entity);

}