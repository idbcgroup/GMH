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
	 * @param maintenanceActivity
	 */
	public void addMaintenanceActivitySelectionListener(
			MaintenanceActivity maintenanceActivity);
	/**
	 * @param maintenanceActivity
	 */
	public void removeMaintenanceActivitySelectionListener(
			MaintenanceActivity maintenanceActivity);
}
