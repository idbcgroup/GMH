package org.fourgeeks.gha.webclient.client.res.citizen.body.personalinformation.basicinformation;

import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToCloseException;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
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
CitizenSelectionListener, ClosableListener, HideableListener {

	private final CitizenForm citizenForm;
	private final RESPhysicalFeaturesGridPanel physicalFeaturesGridPanel;
	private final RESPersonalContactsGridPanel personalContactsGridPanel;
	private final RESEmergencyNotifierGridPanel emergencyNotifierGridPanel;

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
		setMembersMargin(5);
		setOverflow(Overflow.AUTO);
		// setBackgroundColor("cyan");
		citizenForm = new CitizenForm();
		citizenForm.setHeight(150);
		physicalFeaturesGridPanel = new RESPhysicalFeaturesGridPanel();
		personalContactsGridPanel = new RESPersonalContactsGridPanel();
		emergencyNotifierGridPanel = new RESEmergencyNotifierGridPanel();
		final GHALabel title = new GHALabel(
				GHAStrings.get("citizen-basic-information-title")).colored();
		addMembers(title, citizenForm,physicalFeaturesGridPanel,
				personalContactsGridPanel,
				emergencyNotifierGridPanel
				);
	}

	@Override
	public void onCitizenSelect(Citizen citizen) {
		citizenForm.set(citizen);
	}

	@Override
	public boolean canBeHidden(HideCloseAction closeAction) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canBeClosen(HideCloseAction closeAction) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void close() throws UnavailableToCloseException {
		// TODO Auto-generated method stub
		citizenForm.destroy();
		physicalFeaturesGridPanel.destroy();
		personalContactsGridPanel.destroy();
		emergencyNotifierGridPanel.destroy();
	}

	@Override
	public void hide() {
		citizenForm.hide();
		physicalFeaturesGridPanel.hide();
		personalContactsGridPanel.hide();
		emergencyNotifierGridPanel.hide();
		super.hide();
	}

}
