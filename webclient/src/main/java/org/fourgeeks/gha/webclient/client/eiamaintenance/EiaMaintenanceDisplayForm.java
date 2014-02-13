package org.fourgeeks.gha.webclient.client.eiamaintenance;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;

/**
 * @author naramirez
 * 
 */
public class EiaMaintenanceDisplayForm extends
		EIAMaintenanceUpdateForm {

	/**
	 * Constructor
	 */
	public EiaMaintenanceDisplayForm() {
		super();
		setFormTitle(GHAStrings.get("eia-maintenance-planification-details"));

		sideBar.removeMember(saveButton);
		form.deactivate();
	}
}
