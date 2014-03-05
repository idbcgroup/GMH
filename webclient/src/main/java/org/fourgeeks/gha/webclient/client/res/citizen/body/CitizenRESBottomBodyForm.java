package org.fourgeeks.gha.webclient.client.res.citizen.body;

import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAHorizontalLayout;

/**
 * @author alacret
 * 
 */
public class CitizenRESBottomBodyForm extends GHAHorizontalLayout {

	/**
	 * 
	 */
	public CitizenRESBottomBodyForm() {
		addMember(new CitizenRESInternalTabSet());
		addMember(new CitizenRESBodyTools());
	}
}
