package org.fourgeeks.gha.webclient.client.res.citizen.body;

import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class CitizenRESBodyForm extends HLayout {

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
