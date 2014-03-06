package org.fourgeeks.gha.webclient.client.res.citizen.body.personalinformation.emergencynotifier;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAVerticalLayout;

import com.smartgwt.client.types.Overflow;

/**
 * @author jfuentes
 *
 */
public class RESEmergencyNotifierGridPanel extends GHAVerticalLayout {

	/**
	 * 
	 */
	public RESEmergencyNotifierGridPanel() {
		super();
		setWidth100();
		setHeight(GHAUiHelper.DEFAULT_PATIENT_MINIGRID_HEIGHT);
		setMaxHeight(GHAUiHelper.DEFAULT_PATIENT_MINIGRID_HEIGHT);
		setOverflow(Overflow.AUTO);
		setBackgroundColor("yellow");

		final GHALabel title = new GHALabel(GHAStrings.get("citizen-emergency-notify-title")).colored();
		addMembers(title/*,grid*/);
	}

}
