package org.fourgeeks.gha.webclient.client.eiapreventivemaintenanceplanification;

import org.fourgeeks.gha.domain.gmh.EiaPreventiveMaintenancePlanification;

public interface PreventivePlanificationSelectionProducer {
	/**
	 * @param eiaSelectionListener
	 */
	public void addPreventivePlanificationSelectionListener(
			PreventivePlanificationSelectionListener preventivePlanifSelectionListener);

	/**
	 * @param eiaSelectionListener
	 */
	public void removePreventivePlanificationSelectionListener(
			PreventivePlanificationSelectionListener preventivePlanifSelectionListener);

	/**
	 * @param eiaDamageReport
	 */
	public void notifyPreventiveMaintenancePlanification(
			EiaPreventiveMaintenancePlanification preventivePlanif);

}