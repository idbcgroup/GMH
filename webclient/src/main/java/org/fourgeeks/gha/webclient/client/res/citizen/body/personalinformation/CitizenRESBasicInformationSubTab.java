package org.fourgeeks.gha.webclient.client.res.citizen.body.personalinformation;

import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAFormLayout;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASectionForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.labels.GHATopTitleLabel;
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
	private final GHASectionForm sectionForm;
	private final GHAFormLayout mainBody = new GHAFormLayout(){
		@Override
		public void show() {
			sectionForm.show();
			super.show();
		}
	};

	/**
	 * 
	 */
	public CitizenRESBasicInformationSubTab() {
		super(GHAStrings.get("basic-information"));
		formPanel = new RESBasicInformationFormPanel();
		sectionForm = new GHASectionForm(GHAStrings.get("citizen"));
		addClosableListener(sectionForm);
		addHideableListener(sectionForm);

		setPane(initSubtabPane());
	}

	private GHAFormLayout initSubtabPane() {

		mainBody.addMember(new GHATopTitleLabel(GHAStrings
				.get("citizen-basic-information-title")));

		mainBody.addMember(sectionForm);
		sectionForm.addSection(GHAStrings.get("basic-information"), formPanel);

		sectionForm.openFirst();

		return mainBody;
	}

	@Override
	public void onCitizenSelect(Citizen citizen) {
		formPanel.onCitizenSelect(citizen);
	}

	/**
	 * 
	 */
	public void show() {
		mainBody.show();
	}



}
