package org.fourgeeks.gha.webclient.client.res.citizen.body;

import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAInternalTabSet;
import org.fourgeeks.gha.webclient.client.citizen.CitizenSelectionListener;
import org.fourgeeks.gha.webclient.client.res.citizen.body.personalinformation.CitizenRESBasicInformationSubTab;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.widgets.tab.Tab;

/**
 * @author alacret
 * 
 */
public class CitizenRESInternalTabSet extends GHAInternalTabSet implements
CitizenSelectionListener {

	private final CitizenRESBasicInformationSubTab citizenRESBasicInformationTab = new CitizenRESBasicInformationSubTab();

	// private final CitizenRESDemographicSubTab citizenRESDemograficSubTab =
	// new CitizenRESDemographicSubTab();
	// private final CitizenRESSocialEconomicsSubTab
	// citizenRESSocialEconomicsSubTab = new CitizenRESSocialEconomicsSubTab();
	// private final CitizenRESInsuranceCompanySubTab
	// citizenRESInsuranceCompanySubTab = new
	// CitizenRESInsuranceCompanySubTab();
	// private final CitizenRESOccupationalSubTab citizenRESOccupationalSubTab =
	// new CitizenRESOccupationalSubTab();

	/**
	 * 
	 */
	public CitizenRESInternalTabSet() {
		super();
		setHeight(GHAUiHelper.getRESBodyHeight());
		// hideables
		hideables.add(citizenRESBasicInformationTab);
		// hideables.add(citizenRESDemograficSubTab);
		// hideables.add(citizenRESSocialEconomicsSubTab);
		// hideables.add(citizenRESInsuranceCompanySubTab);
		// hideables.add(citizenRESOccupationalSubTab);
		// closables
		closables.add(citizenRESBasicInformationTab);
		// closables.add(citizenRESDemograficSubTab);
		// closables.add(citizenRESSocialEconomicsSubTab);
		// closables.add(citizenRESInsuranceCompanySubTab);
		// closables.add(citizenRESOccupationalSubTab);

		addTab(citizenRESBasicInformationTab);
		// addTab(citizenRESDemograficSubTab);
		// addTab(citizenRESSocialEconomicsSubTab);
		// addTab(citizenRESInsuranceCompanySubTab);
		// addTab(citizenRESOccupationalSubTab);
	}

	@Override
	public void onCitizenSelect(Citizen citizen) {
		citizenRESBasicInformationTab.onCitizenSelect(citizen);

	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getRESBodyHeight());
	}

	@Override
	public void show() {
		final Tab selectedTab = getSelectedTab();
		if (selectedTab == citizenRESBasicInformationTab)
			citizenRESBasicInformationTab.show();
		super.show();
	}

}
