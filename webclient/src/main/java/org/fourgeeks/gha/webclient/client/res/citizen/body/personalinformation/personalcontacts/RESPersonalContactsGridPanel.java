package org.fourgeeks.gha.webclient.client.res.citizen.body.personalinformation.personalcontacts;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAVerticalLayout;

import com.smartgwt.client.types.Overflow;

/**
 * @author jfuentes
 *
 */
@Deprecated
public class RESPersonalContactsGridPanel extends GHAVerticalLayout {

	private RESCitizenPersonalContactsGrid contactsGrid;
	{
		contactsGrid = new RESCitizenPersonalContactsGrid();
	}
	/**
	 * 
	 */
	public RESPersonalContactsGridPanel() {
		super();
		setWidth100();
		setHeight(GHAUiHelper.DEFAULT_PATIENT_MINISECTION_HEIGHT);
		setMaxHeight(GHAUiHelper.DEFAULT_PATIENT_MINISECTION_HEIGHT);
		setBorder("1px solid #666666");
		setOverflow(Overflow.AUTO);
		//		setBackgroundColor("green");

		final GHALabel title = new GHALabel(GHAStrings.get("citizen-personal-contacts-title")).colored();
		addMembers(title,contactsGrid);
	}

}
