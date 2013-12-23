/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceactivity;

import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;

/**
 * @author emiliot
 * 
 */
public interface MaintenanceActivitySelectionProducer {
	/**
	 * @param selectionListener
	 */
	public void addMaintenanceActivitySelectionListener(
			MaintenanceActivitySelectionListener selectionListener);

	/**
	 * @param selectionListener
	 */
	public void removeMaintenanceActivitySelectionListener(
			MaintenanceActivitySelectionListener selectionListener);

	/**
	 * 
	 * @param maintenanceActivity
	 */
	public void notifyMaintenanceActivity(
			MaintenanceActivity maintenanceActivity);
}
