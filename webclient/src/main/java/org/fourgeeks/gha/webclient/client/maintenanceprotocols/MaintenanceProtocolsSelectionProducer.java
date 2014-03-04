/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceprotocols;

import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;

/**
 * @author emiliot
 * 
 */
public interface MaintenanceProtocolsSelectionProducer {
	/**
	 * @param selectionListener
	 */
	public void addMaintenanceProtocolsSelectionListener(
			MaintenanceProtocolsSelectionListener selectionListener);

	/**
	 * @param selectionListener
	 */
	public void removeMaintenanceProtocolsSelectionListener(
			MaintenanceProtocolsSelectionListener selectionListener);

	/**
	 * 
	 * @param entity
	 */
	public void notifyMaintenanceProtocols(MaintenanceProtocol entity);
}
