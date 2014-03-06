package org.fourgeeks.gha.webclient.client.res.citizen.body;

import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAHorizontalLayout;
import org.fourgeeks.gha.webclient.client.citizen.CitizenSelectionListener;

/**
 * @author alacret
 * 
 */
public class CitizenRESBottomBodyForm extends GHAHorizontalLayout implements
		CitizenSelectionListener {

	/**
	 * 
	 */
	public CitizenRESBottomBodyForm() {
		super();
		addMember(new CitizenRESInternalTabSet());
	}

	@Override
	public void onCitizenSelect(Citizen citizen) {
		// TODO Auto-generated method stub

	}
}
