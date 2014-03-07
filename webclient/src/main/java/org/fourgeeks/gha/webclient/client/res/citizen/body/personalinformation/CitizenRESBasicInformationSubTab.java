package org.fourgeeks.gha.webclient.client.res.citizen.body.personalinformation;

import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASectionForm;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.citizen.CitizenSelectionListener;
import org.fourgeeks.gha.webclient.client.res.citizen.body.personalinformation.basicinformation.RESBasicInformationFormPanel;

import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class CitizenRESBasicInformationSubTab extends GHASubTab implements
		CitizenSelectionListener {
	// private final CitizenRESBasicInformationSectionFormPanel
	// citizenSectionFormPanel;

	private RESBasicInformationFormPanel formPanel;

	/**
	 * 
	 */
	public CitizenRESBasicInformationSubTab() {
		super(GHAStrings.get("basic-information"));
		// citizenSectionFormPanel = new
		// CitizenRESBasicInformationSectionFormPanel();
		// addClosableListener(citizenSectionFormPanel);
		// addHideableListener(citizenSectionFormPanel);

		final VLayout mainBody = new VLayout();
		mainBody.addMember(new GHALabel(GHAStrings
				.get("citizen-basic-information-title")));

		final GHASectionForm sectionForm = new GHASectionForm(
				GHAStrings.get("citizen"));
		mainBody.addMember(sectionForm);
		formPanel = new RESBasicInformationFormPanel();
		sectionForm.addSection(GHAStrings.get("basic-information"), formPanel);

		setPane(mainBody);

		sectionForm.openFirst();
	}

	@Override
	public void onCitizenSelect(Citizen citizen) {
		formPanel.onCitizenSelect(citizen);
		// citizenSectionFormPanel.onCitizenSelect(citizen);
	}

}
