package org.fourgeeks.gha.webclient.client.res.citizen.body.personalinformation;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToCloseException;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAFormLayout;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASectionForm;
import org.fourgeeks.gha.webclient.client.res.citizen.body.personalinformation.basicinformation.RESBasicInformationFormPanel;
import org.fourgeeks.gha.webclient.client.res.citizen.body.personalinformation.emergencynotifier.RESEmergencyNotifierGridPanel;
import org.fourgeeks.gha.webclient.client.res.citizen.body.personalinformation.personalcontacts.RESPersonalContactsGridPanel;
import org.fourgeeks.gha.webclient.client.res.citizen.body.personalinformation.physicalfeatures.RESPhysicalFeaturesGridPanel;

import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author jfuentes
 *
 */
public class CitizenRESBasicInformationSectionFormPanel extends GHAFormLayout implements HideableListener,
ClosableListener{

	private final GHALabel titleLabel;

	private GHASectionForm sectionForm;
	private RESBasicInformationFormPanel basicInformationFormPanel;
	private RESPhysicalFeaturesGridPanel physicalFeaturesGridPanel;
	private RESPersonalContactsGridPanel personalContactsGridPanel;
	private RESEmergencyNotifierGridPanel emergencyNotifierGridPanel;
	{
		sectionForm = new GHASectionForm(GHAStrings.get("citizen"));
		basicInformationFormPanel = new RESBasicInformationFormPanel();
		physicalFeaturesGridPanel = new RESPhysicalFeaturesGridPanel();
		personalContactsGridPanel = new RESPersonalContactsGridPanel();
		emergencyNotifierGridPanel = new RESEmergencyNotifierGridPanel();
	}

	/**
	 * 
	 */
	public CitizenRESBasicInformationSectionFormPanel() {
		super();
		//		setWidth100();

		titleLabel = new GHALabel(GHAStrings.get("citizen-basic-information-title"));
		addMember(titleLabel);

		final VLayout basicInformationCanvas = new VLayout();
		basicInformationCanvas.setWidth100();
		basicInformationCanvas.setHeight(GHAUiHelper.getRESBodyHeight()-80);
		basicInformationCanvas.setMaxHeight(GHAUiHelper.getRESBodyHeight()-80);
		basicInformationCanvas.setMembersMargin(5);
		basicInformationCanvas.setOverflow(Overflow.AUTO);


		basicInformationCanvas.addMembers(basicInformationFormPanel,physicalFeaturesGridPanel,personalContactsGridPanel,emergencyNotifierGridPanel);


		sectionForm.addSection(GHAStrings.get("basic-information"), basicInformationCanvas);
		//		sectionForm.addSection(GHAStrings.get("relatives"), new Canvas());
		addMember(sectionForm);

		sectionForm.openFirst();
	}

	@Override
	public boolean canBeClosen(HideCloseAction closeAction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void close() throws UnavailableToCloseException {
		destroy();
	}

	@Override
	public boolean canBeHidden(HideCloseAction closeAction) {
		// TODO Auto-generated method stub
		return false;
	}
}