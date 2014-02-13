package org.fourgeeks.gha.webclient.client.eiapreventivemaintenanceplanification;

import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;

/**
 * @author naramirez
 * 
 */
public interface MaintenancePlanificationSelectionProducer {
	/**
	 * @param listener
	 */
	public void addMaintenancePlanificationSelectionListener(
			MaintenancePlanificationSelectionListener listener);

	/**
	 * @param listener
	 */
	public void removeMaintenancePlanificationSelectionListener(
			MaintenancePlanificationSelectionListener listener);

	/**
	 * @param panification
	 */
	public void notifyMaintenancePlanification(
			EiaMaintenancePlanification panification);

}