package org.fourgeeks.gha.webclient.client.res.citizen.top;

import java.sql.Date;

import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImg;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAAlertLabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAHorizontalLayout;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHATextLabelSmallSize;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHATitleLabelSmallSize;
import org.fourgeeks.gha.webclient.client.citizen.CitizenSelectionListener;

import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.DateUtil;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class CitizenRESTopInformationview extends GHAHorizontalLayout implements
		CitizenSelectionListener {
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
	private final GHATextLabelSmallSize documentNumberText = new GHATextLabelSmallSize()
			.bold();
	private final GHATextLabelSmallSize firstNameText = new GHATextLabelSmallSize()
			.bold();
	private final GHATextLabelSmallSize secondNameText = new GHATextLabelSmallSize()
			.bold();
	private final GHATextLabelSmallSize firstLastNameText = new GHATextLabelSmallSize()
			.bold();
	private final GHATextLabelSmallSize secondLastNameText = new GHATextLabelSmallSize()
			.bold();
	private final GHATextLabelSmallSize dobText = new GHATextLabelSmallSize();
	private final GHATextLabelSmallSize ageText = new GHATextLabelSmallSize();
	private final GHATextLabelSmallSize bpiText = new GHATextLabelSmallSize();
	private final GHATextLabelSmallSize bTypeText = new GHATextLabelSmallSize()
			.bold();
	private final GHATextLabelSmallSize weightTypeText = new GHATextLabelSmallSize()
			.bold();
	private final GHATextLabelSmallSize heightTypeText = new GHATextLabelSmallSize()
			.bold();
	private final GHATextLabelSmallSize bsaTypeText = new GHATextLabelSmallSize()
			.bold();
	private final GHATextLabelSmallSize bmiTypeText = new GHATextLabelSmallSize()
			.bold();
	private final GHATextLabelSmallSize mainDiagnosisText = new GHATextLabelSmallSize()
			.bold();
	// {
	// mainDiagnosisText.setAutoFit(false);
	// mainDiagnosisText.setWidth(300);
	// }
	// boxes
	private final SmallBox documentTypeBox = new SmallBox(120),
			nameBox = new SmallBox(150), dobBox = new SmallBox(120),
			ageBox = new SmallBox(120), bpiBox = new SmallBox(150),
			bTypeBox = new SmallBox(120), weightBox = new SmallBox(100),
			heightBox = new SmallBox(100), bmiBox = new SmallBox(100),
			bsaBox = new SmallBox(120), mainDiagnosisBox = new SmallBox(300);

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

	/**
	 * 
	 */
	public CitizenRESTopInformationview() {
		setHeight(GHAUiHelper.DEFAULT_PATIENT_TOP_HEIGHT);
		addMember(getLeftSideLayout());
		addMember(getRightSideLayout());
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

	// layout for the boxes
	private VLayout getLeftSideLayout() {
		final VLayout leftSideLayout = new VLayout();

		initializeHorizontalLayouts();
		leftSideLayout.setAlign(VerticalAlignment.CENTER);
		leftSideLayout.setLayoutMargin(5);
		leftSideLayout.addMember(firstHorizontalLayout);
		leftSideLayout.addMember(secondHorizontalLayout);
		leftSideLayout.addMember(thirdHorizontalLayout);

		return leftSideLayout;
	}

	// Right Side Layout
	private HLayout getRightSideLayout() {
		final HLayout rightSideLayout = new HLayout();
		rightSideLayout.setWidth(300);
		rightSideLayout.setLayoutMargin(5);
		rightSideLayout.setMembersMargin(5);
		rightSideLayout.setBackgroundColor("light gray");// TODO

		final VLayout alertLayout = new VLayout();
		alertLayout.setAlign(Alignment.CENTER);
		alertLayout.addMember(new GHATitleLabelSmallSize(GHAStrings
				.get("notifications-and-alarms")));

		final HLayout horizontalLayout1 = new HLayout();
		horizontalLayout1.addMembers(new GHAAlertLabel("Alergia", "yellow"),
				new GHAAlertLabel("Cardiopatia", "red"));

		final HLayout horizontalLayout2 = new HLayout();
		horizontalLayout2.addMembers(new GHAAlertLabel("Diabetes", "green"),
				new GHAAlertLabel("Cardiopatia", "red"));

		alertLayout.addMembers(horizontalLayout1, horizontalLayout2);

		rightSideLayout.addMembers(new GHAImg("../resources/img/photo.jpg", 60,
				60), alertLayout);

		return rightSideLayout;
	}

	private void initializeHorizontalLayouts() {
		firstHorizontalLayout.setMembersMargin(10);
		firstHorizontalLayout.setHeight(20);
		secondHorizontalLayout.setMembersMargin(10);
		secondHorizontalLayout.setHeight(20);
		thirdHorizontalLayout.setMembersMargin(10);
		thirdHorizontalLayout.setHeight(20);

		firstHorizontalLayout.addMembers(documentTypeBox, nameBox, dobBox,
				ageBox, new LayoutSpacer());
		secondHorizontalLayout.addMembers(bTypeBox, weightBox, heightBox,
				bmiBox, bsaBox, new LayoutSpacer());
		thirdHorizontalLayout.addMembers(mainDiagnosisBox, bpiBox,
				new LayoutSpacer());
	}

	@Override
	public void onCitizenSelect(Citizen citizen) {
		firstNameText.setContents(citizen.getFirstName());
		secondNameText.setContents(citizen.getSecondName());
		firstLastNameText.setContents(citizen.getFirstLastName());
		secondLastNameText.setContents(citizen.getSecondLastName());
		documentNumberText.setContents(citizen.getIdNumber());

		final Date birthDate = citizen.getBirthDate();
		if (birthDate != null) {
			dobText.setContents(birthDate.toString());
			final int daysBetween = CalendarUtil.getDaysBetween(birthDate,
					DateUtil.create());
			ageText.setContents("" + daysBetween);
		}

		bpiText.setContents("TODO");
		bTypeText.setContents("TODO");
		weightTypeText.setContents("TODO");
		heightTypeText.setContents("TODO");
		bmiTypeText.setContents("TODO");
		bsaTypeText.setContents("TODO");
		mainDiagnosisText.setContents("TODO");

	}

}