package org.fourgeeks.gha.webclient.client.res.citizen.body.personalinformation;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
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
		setWidth100();
		setHeight("*");

		titleLabel = new GHALabel(GHAStrings.get("citizen-basic-information-title"));
		addMember(titleLabel);

		//		final HTMLFlow htmlFlow = new HTMLFlow();
		//		htmlFlow.setOverflow(Overflow.AUTO);
		//		htmlFlow.setPadding(10);
		//
		//		final String contents = "<b>Severity 1</b> - Critical problem<br>System is unavailable in production or "
		//				+ "is corrupting data, and the error severely impacts the user's operations."
		//				+ "<br><br><b>Severity 2</b> - Major problem<br>An important function of the system "
		//				+ "is not available in production, and the user's operations are restricted.";
		//
		//		htmlFlow.setContents(contents);
		//
		//		final SectionStack sectionStack = new SectionStack();
		//		sectionStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		//		sectionStack.setWidth100();
		//		sectionStack.setHeight(400);
		//
		//		final SectionStackSection section1 = new SectionStackSection(GHAStrings.get("citizen-basic-information-title"));
		//		section1.setExpanded(true);
		//		section1.addItem(htmlFlow);
		//		sectionStack.addSection(section1);
		//
		//		final SectionStackSection section2 = new SectionStackSection(GHAStrings.get("citizen-physical-features-title"));
		//		section2.setExpanded(true);
		//		section2.addItem(htmlFlow);
		//		sectionStack.addSection(section2);
		//
		//		final SectionStackSection section3 = new SectionStackSection(GHAStrings.get("citizen-personal-contacts-title"));
		//		section3.setExpanded(true);
		//		section3.addItem(htmlFlow);
		//		sectionStack.addSection(section3);
		//
		//		final SectionStackSection section4 = new SectionStackSection(GHAStrings.get("citizen-emergency-notify-title"));
		//		section4.setExpanded(false);
		//		section3.addItem(htmlFlow);
		//		sectionStack.addSection(section4);

		final VLayout informationLayout = new VLayout();
		informationLayout.setWidth100();

		informationLayout.addMembers(basicInformationFormPanel,physicalFeaturesGridPanel,personalContactsGridPanel,emergencyNotifierGridPanel);


		sectionForm.addSection(GHAStrings.get("basic-information"), informationLayout);
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