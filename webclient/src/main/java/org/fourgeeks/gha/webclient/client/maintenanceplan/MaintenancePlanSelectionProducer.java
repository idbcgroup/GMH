/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceplan;

import org.fourgeeks.gha.domain.gmh.MaintenancePlan;

/**
 * @author emiliot
 * 
 */
public interface MaintenancePlanSelectionProducer {
	/**
	 * @param maintenancePlanSelectionListener
	 */
	public void addMaintenancePlanSelectionListener(
			MaintenancePlanSelectionListener maintenancePlanSelectionListener);

	/**
	 * @param maintenancePlanSelectionListener
	 */
	public void removeMaintenancePlanSelectionListener(
			MaintenancePlanSelectionListener maintenancePlanSelectionListener);

	/**
	 * 
	 * @param maintenancePlan
	 */
	public void notifyMaintenancePlan(MaintenancePlan maintenancePlan);
}
