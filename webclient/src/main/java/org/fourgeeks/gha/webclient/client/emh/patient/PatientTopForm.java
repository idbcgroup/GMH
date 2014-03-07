package org.fourgeeks.gha.webclient.client.emh.patient;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImg;
import org.fourgeeks.gha.webclient.client.UI.panels.GHAHeaderOption;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAAlertLabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHATextLabelMediumSize;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHATitleLabelMediumSize;

import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class PatientTopForm extends HLayout {
	// labels
	private final GHATitleLabelMediumSize documentTypeTitleLabel = new GHATitleLabelMediumSize(
			"Cédula:");// TODO document type value
	private final GHATitleLabelMediumSize dobTitleLabel = new GHATitleLabelMediumSize(
			GHAStrings.get("birth") + ": ");
	private final GHATitleLabelMediumSize ageTitleLabel = new GHATitleLabelMediumSize(
			GHAStrings.get("age") + ": ");
	private final GHATitleLabelMediumSize bpiTitleLabel = new GHATitleLabelMediumSize(
			GHAStrings.get("bpi") + ": ");
	private final GHATitleLabelMediumSize bTypeTitleLabel = new GHATitleLabelMediumSize(
			GHAStrings.get("btype") + ": ");
	private final GHATitleLabelMediumSize weightTitleLabel = new GHATitleLabelMediumSize(
			GHAStrings.get("weight") + ": ");
	private final GHATitleLabelMediumSize heightTitleLabel = new GHATitleLabelMediumSize(
			GHAStrings.get("height") + ": ");
	private final GHATitleLabelMediumSize bsaTitleLabel = new GHATitleLabelMediumSize(
			GHAStrings.get("bsa") + ": ");
	private final GHATitleLabelMediumSize bmiTitleLabel = new GHATitleLabelMediumSize(
			GHAStrings.get("bmi") + ": ");
	private final GHATitleLabelMediumSize mainDiagnosisTitleLabel = new GHATitleLabelMediumSize(
			GHAStrings.get("main-diagnosis") + ": ");
	// {
	// mainDiagnosisTitleLabel.setWidth(150);
	// }
	// text for the values
	private final GHATextLabelMediumSize documentNumberText = new GHATextLabelMediumSize(
			"V-18064066");
	private final GHATextLabelMediumSize firstNameText = new GHATextLabelMediumSize(
			"Angel").bold();
	private final GHATextLabelMediumSize secondNameText = new GHATextLabelMediumSize(
			"Ernesto").bold();
	private final GHATextLabelMediumSize firstLastNameText = new GHATextLabelMediumSize(
			"Lacret").bold();
	private final GHATextLabelMediumSize secondLastNameText = new GHATextLabelMediumSize(
			"Silva").bold();
	private final GHATextLabelMediumSize dobText = new GHATextLabelMediumSize(
			"25/05/1987");
	private final GHATextLabelMediumSize ageText = new GHATextLabelMediumSize(
			"26 años 7 meses");
	private final GHATextLabelMediumSize bpiText = new GHATextLabelMediumSize(
			"Hospital HOCS");
	private final GHATextLabelMediumSize bTypeText = new GHATextLabelMediumSize(
			"A+");
	private final GHATextLabelMediumSize weightTypeText = new GHATextLabelMediumSize(
			"90kgs");
	private final GHATextLabelMediumSize heightTypeText = new GHATextLabelMediumSize(
			"1,65mts");
	private final GHATextLabelMediumSize bsaTypeText = new GHATextLabelMediumSize(
			"25,2 Kg/m2");
	private final GHATextLabelMediumSize bmiTypeText = new GHATextLabelMediumSize(
			"22 m2");
	private final GHATextLabelMediumSize mainDiagnosisText = new GHATextLabelMediumSize(
			"Trastorno de menisco debido a desgarro");
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

}