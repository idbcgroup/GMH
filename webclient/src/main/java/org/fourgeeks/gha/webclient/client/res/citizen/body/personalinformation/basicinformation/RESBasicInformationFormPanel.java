package org.fourgeeks.gha.webclient.client.res.citizen.body.personalinformation.basicinformation;

import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAVerticalLayout;
import org.fourgeeks.gha.webclient.client.citizen.CitizenSelectionListener;
import org.fourgeeks.gha.webclient.client.res.citizen.body.personalinformation.emergencynotifier.RESEmergencyNotifierGridPanel;
import org.fourgeeks.gha.webclient.client.res.citizen.body.personalinformation.personalcontacts.RESPersonalContactsGridPanel;
import org.fourgeeks.gha.webclient.client.res.citizen.body.personalinformation.physicalfeatures.RESPhysicalFeaturesGridPanel;

import com.smartgwt.client.types.Overflow;

/**
 * @author jfuentes
 * 
 */
public class RESBasicInformationFormPanel extends GHAVerticalLayout implements
		CitizenSelectionListener {

	private final CitizenForm citizenForm = new CitizenForm();

	// private final RESBpuBasicInformationForm bpuForm = new
	// RESBpuBasicInformationForm();
	// private final RESCitizenParentBasicInformationForm parentForm = new
	// RESCitizenParentBasicInformationForm();
	/**
	 * 
	 */
	public RESBasicInformationFormPanel() {
		super();
		setWidth100();
		setHeight100();
		// setHeight(GHAUiHelper.DEFAULT_PATIENT_BASIC_INFO_HEIGHT);
		// setMaxHeight(GHAUiHelper.DEFAULT_PATIENT_BASIC_INFO_HEIGHT);
		// setBorder("1px solid #666666");
		setOverflow(Overflow.AUTO);
		// setBackgroundColor("cyan");
		citizenForm.setHeight(150);
		final GHALabel title = new GHALabel(
				GHAStrings.get("citizen-basic-information-title")).colored();
		addMembers(title, citizenForm/* ,bpuForm, parentForm */,
				new RESPhysicalFeaturesGridPanel(),
				new RESPhysicalFeaturesGridPanel(),
				new RESPersonalContactsGridPanel(),
				new RESEmergencyNotifierGridPanel());
	}

	@Override
	public void onCitizenSelect(Citizen citizen) {
		citizenForm.set(citizen);
	}

}
