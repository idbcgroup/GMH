/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceprotocol;

import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;

/**
 * @author emiliot
 * 
 */
public interface MaintenanceProtocolSelectionProducer {
	/**
	 * @param selectionListener
	 */
	public void addMaintenanceProtocolsSelectionListener(
			MaintenanceProtocolSelectionListener selectionListener);

	/**
	 * @param selectionListener
	 */
	public void removeMaintenanceProtocolsSelectionListener(
			MaintenanceProtocolSelectionListener selectionListener);

	/**
	 * 
	 * @param entity
	 */
	public void notifyMaintenanceProtocols(MaintenanceProtocol entity);
}
