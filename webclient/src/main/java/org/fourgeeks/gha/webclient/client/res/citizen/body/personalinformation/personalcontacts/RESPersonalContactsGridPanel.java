package org.fourgeeks.gha.webclient.client.res.citizen.body.personalinformation.personalcontacts;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAFormLayout;

/**
 * @author jfuentes
 *
 */
public class RESPersonalContactsGridPanel extends GHAFormLayout {

	/**
	 * 
	 */
	public RESPersonalContactsGridPanel() {
		setWidth100();
		setHeight(GHAUiHelper.DEFAULT_PATIENT_MINIGRID_HEIGHT);
		setBackgroundColor("green");
	}

}
