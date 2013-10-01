/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceactivity;

import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.webclient.client.UI.grids.tree.GHATreeGridNode;

/**
 * @author jfuentes
 *
 */
public class MaintenanceActivityRecord extends GHATreeGridNode<MaintenanceActivity> {
	private MaintenanceActivity maintenanceActivity;
	

	/**
	 * @param maintenanceActivity
	 */
	public MaintenanceActivityRecord(MaintenanceActivity maintenanceActivity) {
		this.maintenanceActivity = maintenanceActivity;
		setAttribute("id", this.maintenanceActivity.getId());
		setAttribute("name", this.maintenanceActivity.getName());
		setAttribute("desc", this.maintenanceActivity.getDescription());
		
		//childrens = getallchildrens(maintenanceActivity)
		//	if(childrens != null)
		//	setchildren(childrens)
	}


	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord#toEntity()
	 */
	@Override
	public MaintenanceActivity toEntity() {
		return this.maintenanceActivity;
	}

}
