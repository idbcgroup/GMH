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
import org.fourgeeks.gha.webclient.client.res.citizen.body.personalinformation.emergencynotifier.RESCitizenEmergencyNotifierGrid;
import org.fourgeeks.gha.webclient.client.res.citizen.body.personalinformation.personalcontacts.RESCitizenPersonalContactsGrid;
import org.fourgeeks.gha.webclient.client.res.citizen.body.personalinformation.physicalfeatures.RESCitizenPhysicalFeaturesGrid;

import com.smartgwt.client.types.Overflow;

/**
 * @author jfuentes
 * 
 */
public class RESBasicInformationFormPanel extends GHAVerticalLayout implements
CitizenSelectionListener, ClosableListener, HideableListener {

	private final CitizenForm citizenForm;
	private final RESCitizenPhysicalFeaturesGrid featuresGrid;
	private final RESCitizenPersonalContactsGrid contactsGrid;
	private final RESCitizenEmergencyNotifierGrid emergencyGrid;

	/**
	 * 
	 */
	public RESBasicInformationFormPanel() {
		super();
		setWidth100();
		setHeight100();
		//		setMembersMargin(5);
		setOverflow(Overflow.AUTO);

		//Citizen basic info part
		final GHALabel citizenInfoTitle = new GHALabel(GHAStrings.get("citizen-basic-information-title")).colored();
		citizenForm = new CitizenForm();
		//Citizen basic info part
		final GHALabel featuresTitle = new GHALabel(GHAStrings.get("citizen-physical-features-title")).colored();
		featuresGrid = new RESCitizenPhysicalFeaturesGrid();
		//Personal Contacts part
		final GHALabel contactsTitle = new GHALabel(GHAStrings.get("citizen-personal-contacts-title")).colored();
		contactsGrid = new RESCitizenPersonalContactsGrid();
		//"Contacts for an emergency" part
		final GHALabel emergencyTitle = new GHALabel(GHAStrings.get("citizen-emergency-notify-title")).colored();
		emergencyGrid = new RESCitizenEmergencyNotifierGrid();

		addMembers(citizenInfoTitle, citizenForm,
				featuresTitle,featuresGrid,
				contactsTitle,contactsGrid,
				emergencyTitle,emergencyGrid
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
		featuresGrid.destroy();
		contactsGrid.destroy();
		emergencyGrid.destroy();
	}

	@Override
	public void hide() {
		citizenForm.hide();
		featuresGrid.hide();
		contactsGrid.hide();
		emergencyGrid.hide();
		super.hide();
	}

}
