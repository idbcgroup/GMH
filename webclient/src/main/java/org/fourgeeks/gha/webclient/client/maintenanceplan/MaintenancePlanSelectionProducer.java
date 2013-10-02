/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceplan;


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
}
