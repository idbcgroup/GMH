package org.fourgeeks.gha.webclient.client.res.citizen.body;

import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAInternalTabSet;
import org.fourgeeks.gha.webclient.client.citizen.CitizenSelectionListener;
import org.fourgeeks.gha.webclient.client.res.citizen.body.demographic.CitizenRESDemographicSubTab;
import org.fourgeeks.gha.webclient.client.res.citizen.body.insurancecompany.CitizenRESInsuranceCompanySubTab;
import org.fourgeeks.gha.webclient.client.res.citizen.body.occupational.CitizenRESOccupationalSubTab;
import org.fourgeeks.gha.webclient.client.res.citizen.body.personalinformation.CitizenRESBasicInformationSubTab;
import org.fourgeeks.gha.webclient.client.res.citizen.body.socialeconomic.CitizenRESSocialEconomicsSubTab;

import com.google.gwt.event.logical.shared.ResizeEvent;

/**
 * @author alacret
 * 
 */
public class CitizenRESInternalTabSet extends GHAInternalTabSet implements CitizenSelectionListener {

	private final CitizenRESBasicInformationSubTab citizenRESBasicInformationTab = new CitizenRESBasicInformationSubTab();
	private final CitizenRESDemographicSubTab citizenRESDemograficSubTab = new CitizenRESDemographicSubTab();
	private final CitizenRESSocialEconomicsSubTab citizenRESSocialEconomicsSubTab = new CitizenRESSocialEconomicsSubTab();
	private final CitizenRESInsuranceCompanySubTab citizenRESInsuranceCompanySubTab = new CitizenRESInsuranceCompanySubTab();
	private final CitizenRESOccupationalSubTab citizenRESOccupationalSubTab = new CitizenRESOccupationalSubTab();

	/**
	 * 
	 */
	public CitizenRESInternalTabSet() {
		super();
		//hideables
		hideables.add(citizenRESBasicInformationTab);
		hideables.add(citizenRESDemograficSubTab);
		hideables.add(citizenRESSocialEconomicsSubTab);
		hideables.add(citizenRESInsuranceCompanySubTab);
		hideables.add(citizenRESOccupationalSubTab);
		//closables
		closables.add(citizenRESBasicInformationTab);
		closables.add(citizenRESDemograficSubTab);
		closables.add(citizenRESSocialEconomicsSubTab);
		closables.add(citizenRESInsuranceCompanySubTab);
		closables.add(citizenRESOccupationalSubTab);

		addTab(citizenRESBasicInformationTab);
		addTab(citizenRESDemograficSubTab);
		addTab(citizenRESSocialEconomicsSubTab);
		addTab(citizenRESInsuranceCompanySubTab);
		addTab(citizenRESOccupationalSubTab);
	}

	@Override
	public void onResize(ResizeEvent event) {
		return;
	}

	@Override
	public void onCitizenSelect(Citizen citizen) {
		// TODO Auto-generated method stub

	}

}
