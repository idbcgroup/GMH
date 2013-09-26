/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceactivity;


/**
 * @author emiliot
 *
 */
public interface MaintenanceActivitySelectionProducer {
	/**
	 * @param maintenanceActivity
	 */
	public void addMaintenanceActivitySelectionListener(
			MaintenanceActivitySelectionListener maintenanceActivitySelectionListener);
	/**
	 * @param maintenanceActivity
	 */
	public void removeMaintenanceActivitySelectionListener(
			MaintenanceActivitySelectionListener maintenanceActivitySelectionListener);
}
