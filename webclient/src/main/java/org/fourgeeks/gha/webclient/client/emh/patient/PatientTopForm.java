package org.fourgeeks.gha.webclient.client.emh.patient;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImg;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHATextLabel;

import com.smartgwt.client.widgets.layout.HLayout;

/**
 * @author alacret
 * 
 */
public class PatientTopForm extends HLayout {
	// textlabels for the values
	private final GHATextLabel documentTypeText = new GHATextLabel(
			GHAStrings.get("document-type"));
	private final GHATextLabel documentNumberText = new GHATextLabel(
			GHAStrings.get("document-number"));
	private final GHATextLabel firstNameText = new GHATextLabel(
			GHAStrings.get("first-name"));
	private final GHATextLabel secondNameText = new GHATextLabel(
			GHAStrings.get("second-name"));
	private final GHATextLabel firstLastNameText = new GHATextLabel(
			GHAStrings.get("first-lastname"));
	private final GHATextLabel secondLastNameText = new GHATextLabel(
			GHAStrings.get("second-lastname"));
	private final GHATextLabel ageText = new GHATextLabel(
			GHAStrings.get("second-name"));
	private final GHATextLabel genderText = new GHATextLabel(
			GHAStrings.get("first-lastname"));
	private final GHATextLabel cityText = new GHATextLabel(
			GHAStrings.get("second-lastname"));
	private final GHATextLabel bpiText = new GHATextLabel(GHAStrings.get("bpi"));
	private final GHATextLabel diagnosisText = new GHATextLabel(
			GHAStrings.get("bpi"));
	private final GHATextLabel weightText = new GHATextLabel(
			GHAStrings.get("weight"));
	private final GHATextLabel heightText = new GHATextLabel(
			GHAStrings.get("height"));
	// columns layouts
	// private final HLayout firstVerticalLayout = new HLayout();
	// {
	// firstVerticalLayout.setMembersMargin(10);
	// }
	// private final HLayout secondVerticalLayout = new HLayout();
	//
	// {
	// secondVerticalLayout.setMembersMargin(10);
	// }
	// layout for the boxes
	private final HLayout leftSideLayout = new HLayout();
	{
		// leftSideLayout.setAlign(Alignment.LEFT);
		// leftSideLayout.addMember(firstVerticalLayout);
		// leftSideLayout.addMember(secondVerticalLayout);
	}
	// layout for the photo
	private final HLayout rightSideLayout = new HLayout();
	private final GHAImg photoImg = new GHAImg("../resources/img/photo.jpg",
			70, 70);
	{
		rightSideLayout.setPadding(5);
		rightSideLayout.setWidth(220);
		rightSideLayout.addMember(photoImg);
		rightSideLayout.setBackgroundColor("white");
	}
	{
		leftSideLayout.addMembers(documentTypeText, documentNumberText,
				firstNameText, secondNameText, firstLastNameText,
				secondLastNameText, ageText, genderText, cityText);
		//
	}

	/**
	 * 
	 */
	public PatientTopForm() {
		setWidth100();
		setHeight(GHAUiHelper.DEFAULT_PATIENT_TOP_HEIGHT);
		setBackgroundColor(GHAUiHelper.DEFAULT_BACKGROUND_COLOR);
		addMember(leftSideLayout);
		addMember(rightSideLayout);
	}

	// private static class SmallHLayout extends HLayout {
	// public SmallHLayout() {
	// setWidth(220);
	// }
	// }
}