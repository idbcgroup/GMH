/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceprotocol;


/**
 * @author emiliot
 *
 */
public interface MaintenanceProtocolSelectionProducer {
	/**
	 * @param maintenanceProtocolSelectionListener
	 */
	public void addMaintenanceProtocolSelectionListener(
			MaintenanceProtocolSelectionListener maintenanceProtocolSelectionListener);
	/**
	 * @param maintenanceProtocolSelectionListener
	 */
	public void removeMaintenanceProtocolSelectionListener(
			MaintenanceProtocolSelectionListener maintenanceProtocolSelectionListener);
}
