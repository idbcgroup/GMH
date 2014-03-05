package org.fourgeeks.gha.webclient.client.res.citizen.body.personalinformation;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToCloseException;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAFormLayout;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASectionForm;

import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.HTMLFlow;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;

/**
 * @author jfuentes
 *
 */
public class CitizenRESBasicInformationSectionFormPanel extends GHAFormLayout implements HideableListener,
ClosableListener{
	/**
	 * 
	 */
	public GHALabel titleLabel;
	/**
	 * 
	 */
	public GHASectionForm sectionForm;
	{
		sectionForm = new GHASectionForm(GHAStrings.get("citizen"));
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

		final HTMLFlow htmlFlow = new HTMLFlow();
		htmlFlow.setOverflow(Overflow.AUTO);
		htmlFlow.setPadding(10);

		final String contents = "<b>Severity 1</b> - Critical problem<br>System is unavailable in production or "
				+ "is corrupting data, and the error severely impacts the user's operations."
				+ "<br><br><b>Severity 2</b> - Major problem<br>An important function of the system "
				+ "is not available in production, and the user's operations are restricted.";

		htmlFlow.setContents(contents);

		final SectionStack sectionStack = new SectionStack();
		sectionStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		sectionStack.setWidth100();
		sectionStack.setHeight(400);

		final SectionStackSection section1 = new SectionStackSection(GHAStrings.get("citizen-basic-information-title"));
		section1.setExpanded(true);
		section1.addItem(htmlFlow);
		sectionStack.addSection(section1);

		final SectionStackSection section2 = new SectionStackSection(GHAStrings.get("citizen-physical-features-title"));
		section2.setExpanded(true);
		section2.addItem(htmlFlow);
		sectionStack.addSection(section2);

		final SectionStackSection section3 = new SectionStackSection(GHAStrings.get("citizen-personal-contacts-title"));
		section3.setExpanded(true);
		section3.addItem(htmlFlow);
		sectionStack.addSection(section3);

		final SectionStackSection section4 = new SectionStackSection(GHAStrings.get("citizen-emergency-notify-title"));
		section4.setExpanded(false);
		section3.addItem(htmlFlow);
		sectionStack.addSection(section4);

		sectionForm.addSection(GHAStrings.get("basic-information"), sectionStack);
		sectionForm.addSection(GHAStrings.get("relatives"), new Canvas());

		addMember(sectionForm);
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