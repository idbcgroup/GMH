/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceplan.maintenanceprotocol;

import org.fourgeeks.gha.domain.gmh.MaintenancePlanMaintenanceProtocol;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;

/**
 * @author emiliot
 *
 */
public class MaintenancePlanMaintenanceProtocolRecord extends GHAGridRecord<MaintenancePlanMaintenanceProtocol> {
	private MaintenancePlanMaintenanceProtocol maintenancePlanMaintenanceProtocol;
	
	/**
	 * 
	 */
	public MaintenancePlanMaintenanceProtocolRecord(MaintenancePlanMaintenanceProtocol maintenancePlanMaintenanceProtocol) {
		this.maintenancePlanMaintenanceProtocol = maintenancePlanMaintenanceProtocol;
	}
	
	public void setMaintenancePlanAttributes(){
		setAttribute("id", this.maintenancePlanMaintenanceProtocol.getMaintenancePlan().getId());
		setAttribute("name", this.maintenancePlanMaintenanceProtocol.getMaintenancePlan().getName());
		setAttribute("desc", this.maintenancePlanMaintenanceProtocol.getMaintenancePlan().getDescription());
		setAttribute("pot", this.maintenancePlanMaintenanceProtocol.getMaintenancePlan().getPot());
		setAttribute("freq", this.maintenancePlanMaintenanceProtocol.getMaintenancePlan().getFrequency());
	}
	
	public void setMaintenanceProtocolAttributes(){
		setAttribute("id", this.maintenancePlanMaintenanceProtocol.getMaintenanceProtocol().getId());
		setAttribute("name", this.maintenancePlanMaintenanceProtocol.getMaintenanceProtocol().getName());
		setAttribute("description", this.maintenancePlanMaintenanceProtocol.getMaintenanceProtocol().getDescription());
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord#toEntity()
	 */
	@Override
	public MaintenancePlanMaintenanceProtocol toEntity() {
		return this.maintenancePlanMaintenanceProtocol;
	}

}
