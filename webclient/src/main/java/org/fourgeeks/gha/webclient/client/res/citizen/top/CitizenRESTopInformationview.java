package org.fourgeeks.gha.webclient.client.res.citizen.top;

import java.sql.Date;

import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToCloseException;
import org.fourgeeks.gha.webclient.client.UI.imageitems.GHAImg;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAHorizontalLayout;
import org.fourgeeks.gha.webclient.client.UI.superclasses.labels.GHAAlertLabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.labels.GHATextLabel;
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
public class CitizenRESTopInformationview extends GHAHorizontalLayout implements ClosableListener, HideableListener,
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
	private final GHATextLabel documentTypeTitleLabel = new GHATextLabel(
			"Cédula:").smallTitle();// TODO document type value
	private final GHATextLabel dobTitleLabel = new GHATextLabel(
			GHAStrings.get("birth") + ":").smallTitle();
	private final GHATextLabel ageTitleLabel = new GHATextLabel(
			GHAStrings.get("age") + ":").smallTitle();
	private final GHATextLabel bpiTitleLabel = new GHATextLabel(
			GHAStrings.get("bpi") + ":").smallTitle();
	private final GHATextLabel bTypeTitleLabel = new GHATextLabel(
			GHAStrings.get("btype") + ":").smallTitle();
	private final GHATextLabel weightTitleLabel = new GHATextLabel(
			GHAStrings.get("weight") + ":").smallTitle();
	private final GHATextLabel heightTitleLabel = new GHATextLabel(
			GHAStrings.get("height") + ":").smallTitle();
	private final GHATextLabel bsaTitleLabel = new GHATextLabel(
			GHAStrings.get("bsa") + ":").smallTitle();
	private final GHATextLabel bmiTitleLabel = new GHATextLabel(
			GHAStrings.get("bmi") + ":").smallTitle();
	private final GHATextLabel mainDiagnosisTitleLabel = new GHATextLabel(
			GHAStrings.get("main-diagnosis") + ":").smallTitle();
	// {
	// mainDiagnosisTitleLabel.setWidth(150);
	// }
	// text for the values
	private final GHATextLabel documentNumberText = new GHATextLabel().small()
			.bold();
	private final GHATextLabel firstNameText = new GHATextLabel().small()
			.bold();
	private final GHATextLabel secondNameText = new GHATextLabel().small()
			.bold();
	private final GHATextLabel firstLastNameText = new GHATextLabel().small()
			.bold();
	private final GHATextLabel secondLastNameText = new GHATextLabel().small()
			.bold();
	private final GHATextLabel dobText = new GHATextLabel().small().bold();
	private final GHATextLabel ageText = new GHATextLabel().small().bold();
	private final GHATextLabel bpiText = new GHATextLabel().small().bold();
	private final GHATextLabel bTypeText = new GHATextLabel().small()
			.bold();
	private final GHATextLabel weightTypeText = new GHATextLabel().small()
			.bold();
	private final GHATextLabel heightTypeText = new GHATextLabel().small()
			.bold();
	private final GHATextLabel bsaTypeText = new GHATextLabel().small()
			.bold();
	private final GHATextLabel bmiTypeText = new GHATextLabel().small()
			.bold();
	private final GHATextLabel mainDiagnosisText = new GHATextLabel().small()
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

	@Override
	public boolean canBeClosen(HideCloseAction closeAction) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canBeHidden(HideCloseAction closeAction) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void close() throws UnavailableToCloseException {
		destroy();
	}

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
		alertLayout.addMember(new GHATextLabel(GHAStrings
				.get("notifications-and-alarms")).smallTitle());

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
			ageText.setContents("" + daysBetween / 365);
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