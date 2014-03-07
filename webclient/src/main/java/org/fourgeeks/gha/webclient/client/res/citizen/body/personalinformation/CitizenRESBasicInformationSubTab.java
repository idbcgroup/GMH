package org.fourgeeks.gha.webclient.client.res.citizen.body.personalinformation;

import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAFormLayout;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASectionForm;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.citizen.CitizenSelectionListener;
import org.fourgeeks.gha.webclient.client.res.citizen.body.personalinformation.basicinformation.RESBasicInformationFormPanel;

/**
 * @author alacret
 * 
 */
public class CitizenRESBasicInformationSubTab extends GHASubTab implements
CitizenSelectionListener {
	// private final CitizenRESBasicInformationSectionFormPanel
	// citizenSectionFormPanel;

	private final RESBasicInformationFormPanel formPanel;

	/**
	 * 
	 */
	public CitizenRESBasicInformationSubTab() {
		super(GHAStrings.get("basic-information"));
		formPanel = new RESBasicInformationFormPanel();

		// citizenSectionFormPanel = new
		// CitizenRESBasicInformationSectionFormPanel();
		// addClosableListener(citizenSectionFormPanel);
		// addHideableListener(citizenSectionFormPanel);

		setPane(getSubtabPane());
	}

	private GHAFormLayout getSubtabPane() {
		final GHAFormLayout mainBody = new GHAFormLayout();
		mainBody.addMember(new GHALabel(GHAStrings
				.get("citizen-basic-information-title")));

		final GHASectionForm sectionForm = new GHASectionForm(
				GHAStrings.get("citizen"));
		mainBody.addMember(sectionForm);

		sectionForm.addSection(GHAStrings.get("basic-information"), formPanel);
		sectionForm.openFirst();

		return mainBody;
	}

	@Override
	public void onCitizenSelect(Citizen citizen) {
		formPanel.onCitizenSelect(citizen);
	}

}
