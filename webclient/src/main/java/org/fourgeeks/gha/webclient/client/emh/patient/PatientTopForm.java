package org.fourgeeks.gha.webclient.client.emh.patient;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.imageitems.GHAImg;
import org.fourgeeks.gha.webclient.client.UI.panels.GHAHeaderOption;
import org.fourgeeks.gha.webclient.client.UI.superclasses.labels.GHAAlertLabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.labels.GHATextLabel;

import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class PatientTopForm extends HLayout {
	private static class SmallBox extends HLayout {

		public SmallBox() {
			setWidth100();
			setMembersMargin(5);
			addStyleName("margin-items");
		}

		public SmallBox(int width) {
			this();
			setWidth(width);
		}

		public SmallBox(String width) {
			this();
			setWidth(width);
		}

	}
	// labels
	private final GHATextLabel documentTypeTitleLabel = new GHATextLabel(
			"Cédula:").smallTitle();// TODO document type value
	private final GHATextLabel dobTitleLabel = new GHATextLabel(
			GHAStrings.get("birth") + ": ").smallTitle();
	private final GHATextLabel ageTitleLabel = new GHATextLabel(
			GHAStrings.get("age") + ": ").smallTitle();
	private final GHATextLabel bpiTitleLabel = new GHATextLabel(
			GHAStrings.get("bpi") + ": ").smallTitle();
	private final GHATextLabel bTypeTitleLabel = new GHATextLabel(
			GHAStrings.get("btype") + ": ").smallTitle();
	private final GHATextLabel weightTitleLabel = new GHATextLabel(
			GHAStrings.get("weight") + ": ").smallTitle();
	private final GHATextLabel heightTitleLabel = new GHATextLabel(
			GHAStrings.get("height") + ": ").smallTitle();
	private final GHATextLabel bsaTitleLabel = new GHATextLabel(
			GHAStrings.get("bsa") + ": ").smallTitle();
	private final GHATextLabel bmiTitleLabel = new GHATextLabel(
			GHAStrings.get("bmi") + ": ").smallTitle();
	private final GHATextLabel mainDiagnosisTitleLabel = new GHATextLabel(
			GHAStrings.get("main-diagnosis") + ": ").smallTitle();
	//	{
	//		mainDiagnosisTitleLabel.setWidth(GHAUiHelper.DEFAULT_ITEM_WIDTH);
	//	}
	// text for the values
	private final GHATextLabel documentNumberText = new GHATextLabel(
			"V-18064066").small();
	private final GHATextLabel firstNameText = new GHATextLabel(
			"Angel").bold().small();
	private final GHATextLabel secondNameText = new GHATextLabel(
			"Ernesto").bold().small();
	private final GHATextLabel firstLastNameText = new GHATextLabel(
			"Lacret").bold().small();
	private final GHATextLabel secondLastNameText = new GHATextLabel(
			"Silva").bold().small();
	private final GHATextLabel dobText = new GHATextLabel(
			"25/05/1987").small();
	private final GHATextLabel ageText = new GHATextLabel(
			"26 años 7 meses").small();
	private final GHATextLabel bpiText = new GHATextLabel(
			"Hospital HOCS").small();
	private final GHATextLabel bTypeText = new GHATextLabel(
			"A+").small();
	private final GHATextLabel weightTypeText = new GHATextLabel(
			"90kgs").small();
	private final GHATextLabel heightTypeText = new GHATextLabel(
			"1,65mts").small();
	private final GHATextLabel bsaTypeText = new GHATextLabel(
			"25,2 Kg/m2").small();
	private final GHATextLabel bmiTypeText = new GHATextLabel(
			"22 m2").small();
	private final GHATextLabel mainDiagnosisText = new GHATextLabel(
			"Trastorno de menisco debido a desgarro").small();
	//	{
	//		mainDiagnosisText.setAutoFit(false);
	//		mainDiagnosisText.setWidth(300);
	//	}
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
	public PatientTopForm() {
		setWidth100();
		setHeight(GHAUiHelper.DEFAULT_PATIENT_TOP_HEIGHT);
		setBackgroundColor(GHAUiHelper.DEFAULT_BACKGROUND_COLOR);
		addMember(leftSideLayout);
		addMember(centerLayout);
		addMember(rightSideLayout);
	}

}