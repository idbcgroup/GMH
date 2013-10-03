/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceprotocol;

import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;

/**
 * @author jfuentes
 *
 */
public class MaintenanceProtocolGridRecord extends GHAGridRecord<MaintenanceProtocol> {
	private MaintenanceProtocol maintenanceProtocol;


	/**
	 * @param maintenanceProtocol
	 */
	public MaintenanceProtocolGridRecord(MaintenanceProtocol maintenanceProtocol) {
		this.maintenanceProtocol = maintenanceProtocol;
		setAttribute("id", this.maintenanceProtocol.getId());
		setAttribute("name", this.maintenanceProtocol.getName());
		setAttribute("desc", this.maintenanceProtocol.getDescription());
	}


	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord#toEntity()
	 */
	@Override
	public MaintenanceProtocol toEntity() {
		return this.maintenanceProtocol;
	}

}
