package org.fourgeeks.gha.webclient.client.res.citizen.top;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImg;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAAlertLabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAHorizontalLayout;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHATextLabelSmallSize;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHATitleLabelSmallSize;

import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class CitizenRESTopInformationview extends GHAHorizontalLayout {
	// labels
	private final GHATitleLabelSmallSize documentTypeTitleLabel = new GHATitleLabelSmallSize(
			"Cédula:");// TODO document type value
	private final GHATitleLabelSmallSize dobTitleLabel = new GHATitleLabelSmallSize(
			GHAStrings.get("birth") + ":");
	private final GHATitleLabelSmallSize ageTitleLabel = new GHATitleLabelSmallSize(
			GHAStrings.get("age") + ":");
	private final GHATitleLabelSmallSize bpiTitleLabel = new GHATitleLabelSmallSize(
			GHAStrings.get("bpi") + ":");
	private final GHATitleLabelSmallSize bTypeTitleLabel = new GHATitleLabelSmallSize(
			GHAStrings.get("btype") + ":");
	private final GHATitleLabelSmallSize weightTitleLabel = new GHATitleLabelSmallSize(
			GHAStrings.get("weight") + ":");
	private final GHATitleLabelSmallSize heightTitleLabel = new GHATitleLabelSmallSize(
			GHAStrings.get("height") + ":");
	private final GHATitleLabelSmallSize bsaTitleLabel = new GHATitleLabelSmallSize(
			GHAStrings.get("bsa") + ":");
	private final GHATitleLabelSmallSize bmiTitleLabel = new GHATitleLabelSmallSize(
			GHAStrings.get("bmi") + ":");
	private final GHATitleLabelSmallSize mainDiagnosisTitleLabel = new GHATitleLabelSmallSize(
			GHAStrings.get("main-diagnosis") + ":");
	// {
	// mainDiagnosisTitleLabel.setWidth(150);
	// }
	// text for the values
	private final GHATextLabelSmallSize documentNumberText = new GHATextLabelSmallSize(
			"V-18064066").bold();
	private final GHATextLabelSmallSize firstNameText = new GHATextLabelSmallSize(
			"Angel").bold();
	private final GHATextLabelSmallSize secondNameText = new GHATextLabelSmallSize(
			"Ernesto").bold();
	private final GHATextLabelSmallSize firstLastNameText = new GHATextLabelSmallSize(
			"Lacret").bold();
	private final GHATextLabelSmallSize secondLastNameText = new GHATextLabelSmallSize(
			"Silva").bold();
	private final GHATextLabelSmallSize dobText = new GHATextLabelSmallSize(
			"25/05/1987");
	private final GHATextLabelSmallSize ageText = new GHATextLabelSmallSize(
			"26 años 7 meses");// &nbsp;
	private final GHATextLabelSmallSize bpiText = new GHATextLabelSmallSize(
			"Hospital HOCS");
	private final GHATextLabelSmallSize bTypeText = new GHATextLabelSmallSize(
			"A+").bold();
	private final GHATextLabelSmallSize weightTypeText = new GHATextLabelSmallSize(
			"90kgs").bold();
	private final GHATextLabelSmallSize heightTypeText = new GHATextLabelSmallSize(
			"1,65mts").bold();
	private final GHATextLabelSmallSize bsaTypeText = new GHATextLabelSmallSize(
			"25,2 Kg/m2").bold();
	private final GHATextLabelSmallSize bmiTypeText = new GHATextLabelSmallSize(
			"22 m2").bold();
	private final GHATextLabelSmallSize mainDiagnosisText = new GHATextLabelSmallSize(
			"Trastorno de menisco debido a desgarro").bold();
	//	{
	//		mainDiagnosisText.setAutoFit(false);
	//		mainDiagnosisText.setWidth(300);
	//	}
	// boxes
	private final SmallBox documentTypeBox = new SmallBox(120),
			nameBox = new SmallBox(150), dobBox = new SmallBox(120), ageBox = new SmallBox(120),
			bpiBox = new SmallBox(150), bTypeBox = new SmallBox(120),
			weightBox = new SmallBox(100), heightBox = new SmallBox(100),
			bmiBox = new SmallBox(100), bsaBox = new SmallBox(120),
			mainDiagnosisBox = new SmallBox();
	{
		documentTypeBox.addMembers(documentTypeTitleLabel, documentNumberText);
		nameBox.addMembers(firstNameText, secondNameText, firstLastNameText,
				secondLastNameText);
		dobBox.addMembers(dobTitleLabel, dobText);
		ageBox.addMembers(ageTitleLabel, ageText);
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
	private final HLayout secondHorizontalLayout = new HLayout();
	private final HLayout thirdHorizontalLayout = new HLayout();

	// layout for the boxes
	private VLayout getLeftSideLayout() {
		final VLayout leftSideLayout = new VLayout();

		initializeHorizontalLayouts();
		leftSideLayout.setAlign(VerticalAlignment.CENTER);
		leftSideLayout.setLayoutMargin(5);
		leftSideLayout.setBorder("1px solid #000000");
		leftSideLayout.setBackgroundColor("white");
		leftSideLayout.addMember(firstHorizontalLayout);
		leftSideLayout.addMember(secondHorizontalLayout);
		leftSideLayout.addMember(thirdHorizontalLayout);

		return leftSideLayout;
	}

	private void initializeHorizontalLayouts() {
		firstHorizontalLayout.setMembersMargin(10);
		firstHorizontalLayout.setHeight(20);
		secondHorizontalLayout.setMembersMargin(10);
		secondHorizontalLayout.setHeight(20);
		thirdHorizontalLayout.setMembersMargin(10);
		thirdHorizontalLayout.setHeight(20);

		firstHorizontalLayout.addMembers(documentTypeBox, nameBox, dobBox, ageBox,
				bpiBox,new LayoutSpacer());
		secondHorizontalLayout.addMembers(bTypeBox, weightBox, heightBox,
				bmiBox, bsaBox,new LayoutSpacer());
		thirdHorizontalLayout.addMembers(mainDiagnosisBox,new LayoutSpacer());
	}

	// private final VLayout centerLayout = new VLayout();
	// private final GHAHeaderOption addOption = new GHAHeaderOption(
	// GHAStrings.get("encounter") + "...",
	// GHAUiHelper.DEFAULT_HEADER_OPTION_WIDTH, true,
	// "../resources/img/agregarButton.png",
	// "../resources/img/agregarButtonOver.png");
	// {
	//
	// centerLayout.setPadding(5);
	// centerLayout.setWidth(100);
	// centerLayout.addMember(addOption);
	// }

	// Right Side Layout
	private HLayout getRightSideLayout() {
		final HLayout rightSideLayout = new HLayout();
		final GHAImg photoImg = new GHAImg("../resources/img/photo.jpg", 60, 60);
		final VLayout alertLayout = new VLayout();

		rightSideLayout.setWidth(150);
		rightSideLayout.setLayoutMargin(5);
		rightSideLayout.setBorder("1px solid #000000");
		rightSideLayout.addMembers(photoImg, alertLayout);
		rightSideLayout.setBackgroundColor("white");
		alertLayout.addMembers(new GHAAlertLabel("Alergia", "yellow"),
				new GHAAlertLabel("Cardiopatia", "red"), new GHAAlertLabel(
						"Diabetes", "green"));
		return rightSideLayout;
	}

	/**
	 * 
	 */
	public CitizenRESTopInformationview() {
		setHeight(GHAUiHelper.DEFAULT_PATIENT_TOP_HEIGHT);
		//		setBackgroundColor("#FFFF4D");// TODO
		addMember(getLeftSideLayout());
		addMember(getRightSideLayout());
		addMember(new CitizenRESTopTools());
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

	}

}