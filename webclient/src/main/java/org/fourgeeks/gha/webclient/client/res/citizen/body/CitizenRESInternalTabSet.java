package org.fourgeeks.gha.webclient.client.res.citizen.body;

import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAInternalTabSet;
import org.fourgeeks.gha.webclient.client.res.citizen.body.demographic.CitizenRESDemographicTab;
import org.fourgeeks.gha.webclient.client.res.citizen.body.insurancecompany.CitizenRESInsuranceCompanyTab;
import org.fourgeeks.gha.webclient.client.res.citizen.body.occupational.CitizenRESOccupationalTab;
import org.fourgeeks.gha.webclient.client.res.citizen.body.personalinformation.CitizenRESBasicInformationTab;
import org.fourgeeks.gha.webclient.client.res.citizen.body.socialeconomic.CitizenRESSocialEconomicsTab;

import com.google.gwt.event.logical.shared.ResizeEvent;

/**
 * @author alacret
 * 
 */
public class CitizenRESInternalTabSet extends GHAInternalTabSet {

	/**
	 * 
	 */
	public CitizenRESInternalTabSet() {
		super();
		setHeight100();
		addTab(new CitizenRESBasicInformationTab());
		addTab(new CitizenRESDemographicTab());
		addTab(new CitizenRESSocialEconomicsTab());
		addTab(new CitizenRESInsuranceCompanyTab());
		addTab(new CitizenRESOccupationalTab());
	}

	@Override
	public void onResize(ResizeEvent event) {
		return;
	}

}
