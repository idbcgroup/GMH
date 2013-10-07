package org.fourgeeks.gha.webclient.client.maintenanceprotocol.activities;

import org.fourgeeks.gha.domain.gmh.MaintenanceActivityMaintenanceProtocol;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;

/**
 * @author emiliot
 * 
 */
public class MaintenanceActivityMaintenanceProtocolGridRecord extends
		GHAGridRecord<MaintenanceActivityMaintenanceProtocol> {
	private MaintenanceActivityMaintenanceProtocol maintenanceActivityMaintenanceProtocol;

	/**
	 * @param maintenanceActivityMaintenanceProtocol
	 */
	public MaintenanceActivityMaintenanceProtocolGridRecord(
			MaintenanceActivityMaintenanceProtocol maintenanceActivityMaintenanceProtocol) {
		this.maintenanceActivityMaintenanceProtocol = maintenanceActivityMaintenanceProtocol;
		setAttribute("id", this.maintenanceActivityMaintenanceProtocol
				.getActivity().getId());
		setAttribute("name", this.maintenanceActivityMaintenanceProtocol
				.getActivity().getName());
		setAttribute("desc", this.maintenanceActivityMaintenanceProtocol
				.getActivity().getDescription());
	}

	@Override
	public MaintenanceActivityMaintenanceProtocol toEntity() {
		return this.maintenanceActivityMaintenanceProtocol;
	}
}
