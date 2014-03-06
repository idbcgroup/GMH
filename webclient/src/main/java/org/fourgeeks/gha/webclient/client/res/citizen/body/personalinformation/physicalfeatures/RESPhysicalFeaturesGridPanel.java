package org.fourgeeks.gha.webclient.client.res.citizen.body.personalinformation.physicalfeatures;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAFormLayout;

/**
 * @author jfuentes
 *
 */
public class RESPhysicalFeaturesGridPanel extends GHAFormLayout {

	/**
	 * 
	 */
	public RESPhysicalFeaturesGridPanel() {
		super();
		setWidth100();
		setHeight(GHAUiHelper.DEFAULT_PATIENT_MINIGRID_HEIGHT);
		setBackgroundColor("red");
	}

}
