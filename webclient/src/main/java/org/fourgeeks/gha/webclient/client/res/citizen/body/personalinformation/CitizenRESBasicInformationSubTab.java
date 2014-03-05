package org.fourgeeks.gha.webclient.client.res.citizen.body.personalinformation;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;

/**
 * @author alacret
 * 
 */
public class CitizenRESBasicInformationSubTab extends GHASubTab {
	private final CitizenRESBasicInformationSectionFormPanel citizenSectionFormPanel;
	/**
	 * 
	 */
	public CitizenRESBasicInformationSubTab() {
		super(GHAStrings.get("basic-information"));
		citizenSectionFormPanel = new CitizenRESBasicInformationSectionFormPanel();
		addClosableListener(citizenSectionFormPanel);
		addHideableListener(citizenSectionFormPanel);

		setPane(citizenSectionFormPanel);
	}

}
