package org.fourgeeks.gha.webclient.client.eiamaintenanceplanification;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;

/**
 * @author naramirez
 * 
 */
public class EiaMaintenancePlanificationDisplayForm extends
		EIAMaintenancePlanificationUpdateForm {

	/**
	 * Constructor
	 */
	public EiaMaintenancePlanificationDisplayForm() {
		super();
		setFormTitle(GHAStrings.get("eia-maintenance-planification-details"));

		sideBar.removeMember(saveButton);
		form.deactivate();
	}
}
