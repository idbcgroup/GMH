package org.fourgeeks.gha.webclient.client.res.citizen.body.personalinformation.personalcontacts;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAVerticalLayout;
import org.fourgeeks.gha.webclient.client.res.citizen.body.personalinformation.physicalfeatures.RESCitizenPhysicalFeaturesGrid;

import com.smartgwt.client.types.Overflow;

/**
 * @author jfuentes
 *
 */
public class RESPersonalContactsGridPanel extends GHAVerticalLayout {

	private RESCitizenPhysicalFeaturesGrid grid;
	{
		grid = new RESCitizenPhysicalFeaturesGrid();
	}
	/**
	 * 
	 */
	public RESPersonalContactsGridPanel() {
		super();
		setWidth100();
		setHeight(GHAUiHelper.DEFAULT_PATIENT_MINIGRID_HEIGHT);
		setMaxHeight(GHAUiHelper.DEFAULT_PATIENT_MINIGRID_HEIGHT);
		setOverflow(Overflow.AUTO);
		setBackgroundColor("green");

		final GHALabel title = new GHALabel(GHAStrings.get("citizen-personal-contacts-title")).colored();
		addMembers(title,grid);
	}

}
