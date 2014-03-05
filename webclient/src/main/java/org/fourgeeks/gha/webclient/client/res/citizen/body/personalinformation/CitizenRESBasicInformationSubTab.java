package org.fourgeeks.gha.webclient.client.res.citizen.body.personalinformation;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASectionForm;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;

import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.HTMLFlow;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;

/**
 * @author alacret
 * 
 */
public class CitizenRESBasicInformationSubTab extends GHASubTab {

	private GHASectionForm sectionForm;
	{
		sectionForm = new GHASectionForm("Opciones");
	}
	/**
	 * @param title
	 */
	public CitizenRESBasicInformationSubTab() {
		super(GHAStrings.get("basic-information"));

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

		final SectionStackSection section1 = new SectionStackSection(
				"Información Básica del Ciudadano");
		section1.setExpanded(true);
		section1.addItem(htmlFlow);
		sectionStack.addSection(section1);

		final SectionStackSection section2 = new SectionStackSection(
				"Caracteristicas Físicas");
		section2.setExpanded(true);
		section2.addItem(htmlFlow);
		sectionStack.addSection(section2);

		final SectionStackSection section3 = new SectionStackSection(
				"Contactos Personales");
		section3.setExpanded(true);
		section3.addItem(htmlFlow);
		sectionStack.addSection(section3);

		final SectionStackSection section4 = new SectionStackSection(
				"Notificar en caso de Emergencia");
		section4.setExpanded(false);
		section3.addItem(htmlFlow);
		sectionStack.addSection(section4);

		final HLayout layout = new HLayout();
		layout.setWidth100();
		layout.setHeight("*");
		//		layout.setMembersMargin(20);
		layout.addMember(sectionStack);

		setPane(layout);
	}

}
