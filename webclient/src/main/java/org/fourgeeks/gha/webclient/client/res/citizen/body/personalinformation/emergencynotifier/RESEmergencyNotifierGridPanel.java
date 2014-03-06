package org.fourgeeks.gha.webclient.client.res.citizen.body.personalinformation.emergencynotifier;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAFormLayout;

/**
 * @author jfuentes
 *
 */
public class RESEmergencyNotifierGridPanel extends GHAFormLayout {

	/**
	 * 
	 */
	public RESEmergencyNotifierGridPanel() {
		super();
		setWidth100();
		setHeight(GHAUiHelper.DEFAULT_PATIENT_MINIGRID_HEIGHT);
		setBackgroundColor("yellow");
	}

}
