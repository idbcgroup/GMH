package org.fourgeeks.gha.webclient.client.res.citizen.body;

import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAHorizontalLayout;

import com.smartgwt.client.widgets.layout.VLayout;

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
		final VLayout leftLayout = new VLayout();
		// leftLayout.addMember();
		addMember(leftLayout);
		addMember(new CitizenRESBodyTools());
	}
}
