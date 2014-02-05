package org.fourgeeks.gha.webclient.client.eiamaintenance;

import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;

public interface EiaMaintenanceSelectionProducer {
	/**
	 * @param listener
	 */
	public void addEiaMaintenancePlanificationSelectionListener(
			EiaMaintenanceSelectionListener listener);

	/**
	 * @param listener
	 */
	public void removeEiaMaintenancePlanificationSelectionListener(
			EiaMaintenanceSelectionListener listener);

	/**
	 * @param entity
	 */
	public void notifyEiaMaintenancePlanification(
			EiaMaintenancePlanification entity);

}