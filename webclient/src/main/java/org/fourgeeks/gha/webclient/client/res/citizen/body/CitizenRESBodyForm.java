package org.fourgeeks.gha.webclient.client.res.citizen.body;

import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAHorizontalLayout;
import org.fourgeeks.gha.webclient.client.res.citizen.body.personalinformation.CitizenRESPersonalInformationTab;

/**
 * @author alacret
 * 
 */
public class CitizenRESBodyForm extends GHAHorizontalLayout {

	/**
	 * 
	 */
	public CitizenRESBodyForm() {
		setBackgroundColor("#CCFF99");
		final CitizenRESInternalTabSet component = new CitizenRESInternalTabSet();
		component.addTab(new CitizenRESPersonalInformationTab());
		addMember(component);
		addMember(new CitizenRESBodyTools());
	}
}
