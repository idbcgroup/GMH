package org.fourgeeks.gha.webclient.client.res.citizen.top;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImg;
import org.fourgeeks.gha.webclient.client.UI.panels.GHAHeaderOption;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAAlertLabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHATextLabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHATitleLabel;

import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class CitizenRESTopForm extends HLayout {
	// labels
	private final GHATitleLabel documentTypeTitleLabel = new GHATitleLabel(
			"Cédula:" + GHAUiHelper.BLANK_SPACE);// TODO document type value
	private final GHATitleLabel dobTitleLabel = new GHATitleLabel(
			GHAStrings.get("birth") + ":" + GHAUiHelper.BLANK_SPACE);
	private final GHATitleLabel ageTitleLabel = new GHATitleLabel(
			GHAStrings.get("age") + ":" + GHAUiHelper.BLANK_SPACE);
	private final GHATitleLabel bpiTitleLabel = new GHATitleLabel(
			GHAStrings.get("bpi") + ":" + GHAUiHelper.BLANK_SPACE);
	private final GHATitleLabel bTypeTitleLabel = new GHATitleLabel(
			GHAStrings.get("btype") + ":" + GHAUiHelper.BLANK_SPACE);
	private final GHATitleLabel weightTitleLabel = new GHATitleLabel(
			GHAStrings.get("weight") + ":" + GHAUiHelper.BLANK_SPACE);
	private final GHATitleLabel heightTitleLabel = new GHATitleLabel(
			GHAStrings.get("height") + ":" + GHAUiHelper.BLANK_SPACE);
	private final GHATitleLabel bsaTitleLabel = new GHATitleLabel(
			GHAStrings.get("bsa") + ":" + GHAUiHelper.BLANK_SPACE);
	private final GHATitleLabel bmiTitleLabel = new GHATitleLabel(
			GHAStrings.get("bmi") + ":" + GHAUiHelper.BLANK_SPACE);
	private final GHATitleLabel mainDiagnosisTitleLabel = new GHATitleLabel(
			GHAStrings.get("main-diagnosis") + ":" + GHAUiHelper.BLANK_SPACE);
	{
		mainDiagnosisTitleLabel.setWidth(150);
	}
	// text for the values
	private final GHATextLabel documentNumberText = new GHATextLabel(
			"V-18064066");
	private final GHATextLabel firstNameText = new GHATextLabel("Angel"
			+ GHAUiHelper.BLANK_SPACE).bold();
	private final GHATextLabel secondNameText = new GHATextLabel("Ernesto"
			+ GHAUiHelper.BLANK_SPACE).bold();
	private final GHATextLabel firstLastNameText = new GHATextLabel("Lacret"
			+ GHAUiHelper.BLANK_SPACE).bold();
	private final GHATextLabel secondLastNameText = new GHATextLabel("Silva")
			.bold();
	private final GHATextLabel dobText = new GHATextLabel("25/05/1987"
			+ GHAUiHelper.BLANK_SPACE);
	private final GHATextLabel ageText = new GHATextLabel(
			"26&nbsp;años&nbsp;7&nbsp;meses" + GHAUiHelper.BLANK_SPACE);
	private final GHATextLabel bpiText = new GHATextLabel("Hospital&nbsp;HOCS"
			+ GHAUiHelper.BLANK_SPACE);
	private final GHATextLabel bTypeText = new GHATextLabel("A+"
			+ GHAUiHelper.BLANK_SPACE);
	private final GHATextLabel weightTypeText = new GHATextLabel("90kgs"
			+ GHAUiHelper.BLANK_SPACE);
	private final GHATextLabel heightTypeText = new GHATextLabel("1,65mts"
			+ GHAUiHelper.BLANK_SPACE);
	private final GHATextLabel bsaTypeText = new GHATextLabel("25,2&nbsp;Kg/m2"
			+ GHAUiHelper.BLANK_SPACE);
	private final GHATextLabel bmiTypeText = new GHATextLabel("22&nbsp;m2"
			+ GHAUiHelper.BLANK_SPACE);
	private final GHATextLabel mainDiagnosisText = new GHATextLabel(
			"Trastorno de menisco debido a desgarro" + GHAUiHelper.BLANK_SPACE);
	{
		mainDiagnosisText.setAutoFit(false);
		mainDiagnosisText.setWidth(300);
	}
	// boxes
	private final SmallBox documentTypeBox = new SmallBox(30),
			nameBox = new SmallBox(30), dobBox = new SmallBox(30),
			bpiBox = new SmallBox(30), bTypeBox = new SmallBox(30),
			weightBox = new SmallBox(30), heightBox = new SmallBox(30),
			bmiBox = new SmallBox(30), bsaBox = new SmallBox(30),
			mainDiagnosisBox = new SmallBox(30);
	{
		documentTypeBox.addMembers(documentTypeTitleLabel, documentNumberText);
		nameBox.addMembers(firstNameText, secondNameText, firstLastNameText,
				secondLastNameText);
		dobBox.addMembers(dobTitleLabel, dobText, ageTitleLabel, ageText);
		bpiBox.addMembers(bpiTitleLabel, bpiText);
		bTypeBox.addMembers(bTypeTitleLabel, bTypeText);
		weightBox.addMembers(weightTitleLabel, weightTypeText);
		heightBox.addMembers(heightTitleLabel, heightTypeText);
		bmiBox.addMembers(bmiTitleLabel, bmiTypeText);
		bsaBox.addMembers(bsaTitleLabel, bsaTypeText);
		mainDiagnosisBox.addMembers(mainDiagnosisTitleLabel, mainDiagnosisText);
	}
	// columns layouts
	private final HLayout firstHorizontalLayout = new HLayout();
	{
		firstHorizontalLayout.setHeight(20);
	}
	private final HLayout secondHorizontalLayout = new HLayout();
	//
	{
		secondHorizontalLayout.setHeight(20);
	}
	private final HLayout thirdHorizontalLayout = new HLayout();
	//
	{
		thirdHorizontalLayout.setHeight(20);
	}
	// layout for the boxes
	private final VLayout leftSideLayout = new VLayout();
	{

		leftSideLayout.setAlign(VerticalAlignment.TOP);
		leftSideLayout.addMember(firstHorizontalLayout);
		leftSideLayout.addMember(secondHorizontalLayout);
		leftSideLayout.addMember(thirdHorizontalLayout);
	}
	private final VLayout centerLayout = new VLayout();
	private final GHAHeaderOption addOption = new GHAHeaderOption(
			GHAStrings.get("encounter") + "...",
			GHAUiHelper.DEFAULT_HEADER_OPTION_WIDTH, true,
			"../resources/img/agregarButton.png",
			"../resources/img/agregarButtonOver.png");
	{

		centerLayout.setPadding(5);
		centerLayout.setWidth(200);
		centerLayout.addMember(addOption);
	}

	// layout for the photo
	private final HLayout rightSideLayout = new HLayout();
	private final GHAImg photoImg = new GHAImg("../resources/img/photo.jpg",
			70, 70);
	private final VLayout alertLayout = new VLayout();
	{
		rightSideLayout.setPadding(5);
		rightSideLayout.setWidth(220);
		rightSideLayout.addMembers(photoImg, alertLayout);
		rightSideLayout.setBackgroundColor("white");
		alertLayout.addMembers(new GHAAlertLabel("Alergia", "yellow"),
				new GHAAlertLabel("Cardiopata", "red"), new GHAAlertLabel(
						"Diabetes", "green"));
	}
	{
		firstHorizontalLayout.addMembers(documentTypeBox, nameBox, dobBox,
				bpiBox);
		secondHorizontalLayout.addMembers(bTypeBox, weightBox, heightBox,
				bmiBox, bsaBox);
		thirdHorizontalLayout.addMembers(mainDiagnosisBox);
	}

	/**
	 * 
	 */
	public CitizenRESTopForm() {
		setWidth100();
		setHeight(GHAUiHelper.DEFAULT_PATIENT_TOP_HEIGHT);
		setBackgroundColor(GHAUiHelper.DEFAULT_BACKGROUND_COLOR);
		setBackgroundColor("#FFFF4D");// TODO
		addMember(leftSideLayout);
		addMember(centerLayout);
		addMember(rightSideLayout);
		addMember(new CitizenRESTopTools());

	}

	private static class SmallBox extends HLayout {
		public SmallBox(int width) {
			setWidth(width);
			// setMargin(2);
			addStyleName("margin");
		}

		public SmallBox(String width) {
			setWidth(width);
			// setMargin(2);
			addStyleName("margin");
		}

	}

}