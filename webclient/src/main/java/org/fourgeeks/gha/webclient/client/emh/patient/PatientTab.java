package org.fourgeeks.gha.webclient.client.emh.patient;

import org.fourgeeks.gha.webclient.client.UI.tabs.GHATab;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATabHeader;

import com.google.gwt.user.client.History;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.HTMLFlow;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class PatientTab extends GHATab {

	private final String patientId;

	/**
	 * @param patientId
	 * 
	 */
	public PatientTab(final String patientId) {
		this.patientId = patientId;
		header = new GHATabHeader(this);
		header.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				History.newItem("emh/" + patientId);
			}
		});
		setVertical(false);

		VLayout bodyLayout = new VLayout();

		addMember(bodyLayout);
		addMember(new HMETools());

		bodyLayout.addMember(new PatientTopForm());

		HTMLFlow htmlFlow = new HTMLFlow();
		htmlFlow.setOverflow(Overflow.AUTO);
		htmlFlow.setPadding(10);

		String contents = "<b>Severity 1</b> - Critical problem<br>System is unavailable in production or "
				+ "is corrupting data, and the error severely impacts the user's operations."
				+ "<br><br><b>Severity 2</b> - Major problem<br>An important function of the system "
				+ "is not available in production, and the user's operations are restricted."
				+ "<br><br><b>Severity 3</b> - Minor problem<br>Inability to use a function of the "
				+ "system occurs, but it does not seriously affect the user's operations.";

		htmlFlow.setContents(contents);

		final SectionStack sectionStack = new SectionStack();
		sectionStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		sectionStack.setWidth(300);
		sectionStack.setHeight(350);

		SectionStackSection section1 = new SectionStackSection(
				"Situacion actual paciente: " + patientId);
		section1.setExpanded(true);
		section1.addItem(new Img("pieces/48/pawn_blue.png", 48, 48));
		sectionStack.addSection(section1);

		SectionStackSection section2 = new SectionStackSection(
				"Atencion medica");
		section2.setExpanded(true);
		section2.setCanCollapse(true);
		section2.addItem(htmlFlow);
		sectionStack.addSection(section2);

		SectionStackSection section3 = new SectionStackSection(
				"Ordenes medicas");
		section3.setExpanded(true);
		section3.setCanCollapse(false);
		section3.addItem(new Img("pieces/48/pawn_green.png", 48, 48));
		sectionStack.addSection(section3);

		SectionStackSection section4 = new SectionStackSection("ORH");
		section4.setExpanded(false);
		section4.addItem(new Img("pieces/48/piece_yellow.png", 48, 48));
		sectionStack.addSection(section4);

		HLayout layout = new HLayout();
		layout.setMembersMargin(20);
		layout.addMember(sectionStack);

		bodyLayout.addMember(layout);
	}

	@Override
	public String getId() {
		return patientId;
	}

	@Override
	public void search() {

	}

	@Override
	public String getTitleForHeader() {
		return "Patient : " + patientId;
	}

}
