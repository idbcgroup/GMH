package org.fourgeeks.gha.webclient.client.res.citizen.body.personalinformation.emergencynotifier;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAVerticalLayout;
import org.fourgeeks.gha.webclient.client.UI.superclasses.labels.GHATopTitleLabel;

import com.smartgwt.client.types.Overflow;

/**
 * @author jfuentes
 *
 */
@Deprecated
public class RESEmergencyNotifierGridPanel extends GHAVerticalLayout {

	private RESCitizenEmergencyNotifierGrid emergencyGrid;
	{
		emergencyGrid = new RESCitizenEmergencyNotifierGrid();
	}
	/**
	 * 
	 */
	public RESEmergencyNotifierGridPanel() {
		super();
		setWidth100();
		setHeight(GHAUiHelper.DEFAULT_PATIENT_MINISECTION_HEIGHT);
		setMaxHeight(GHAUiHelper.DEFAULT_PATIENT_MINISECTION_HEIGHT);
		setBorder("1px solid #666666");
		setOverflow(Overflow.AUTO);
		//		setBackgroundColor("yellow");

		final GHATopTitleLabel title = new GHATopTitleLabel(GHAStrings.get("citizen-emergency-notify-title")).backgroundColored();
		addMembers(title,emergencyGrid);
	}

}
