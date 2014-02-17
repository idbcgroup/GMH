package org.fourgeeks.gha.webclient.client.res.citizen.body;

import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAHorizontalLayout;

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
		addMember(new CitizenRESInternalTabSet());
		addMember(new CitizenRESBodyTools());
	}
}
