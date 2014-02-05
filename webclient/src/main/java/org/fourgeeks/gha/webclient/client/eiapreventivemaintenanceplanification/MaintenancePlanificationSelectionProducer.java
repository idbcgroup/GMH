package org.fourgeeks.gha.webclient.client.eiapreventivemaintenanceplanification;

import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;

public interface MaintenancePlanificationSelectionProducer {
	/**
	 * @param eiaSelectionListener
	 */
	public void addPreventivePlanificationSelectionListener(
			MaintenancePlanificationSelectionListener preventivePlanifSelectionListener);

	/**
	 * @param eiaSelectionListener
	 */
	public void removePreventivePlanificationSelectionListener(
			MaintenancePlanificationSelectionListener preventivePlanifSelectionListener);

	/**
	 * @param eiaDamageReport
	 */
	public void notifyPreventiveMaintenancePlanification(
			EiaMaintenancePlanification preventivePlanif);

}