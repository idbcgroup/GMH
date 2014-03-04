/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceprotocols;

import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;

/**
 * @author emiliot
 * 
 */
public interface MaintenanceProtocolsSelectionListener {
	/**
	 * @param entity
	 */
	public void select(MaintenanceProtocol entity);
}
