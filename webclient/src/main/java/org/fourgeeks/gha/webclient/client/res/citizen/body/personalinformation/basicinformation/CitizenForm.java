package org.fourgeeks.gha.webclient.client.res.citizen.body.personalinformation.basicinformation;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.fourgeeks.gha.domain.enu.DocumentTypeEnum;
import org.fourgeeks.gha.domain.enu.GenderTypeEnum;
import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.domain.mix.LegalEntity;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHAAlertManager;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHADateItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.dateitems.GHABirthDateItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHADoumentTypeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAGenderSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.textitems.GHAAgeTextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.textitems.GHAEmailTextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.textitems.GHANameTextItem;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImg;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAAlertLabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm.FormType;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAForm;
import org.fourgeeks.gha.webclient.client.citizen.CitizenModel;
import org.fourgeeks.gha.webclient.client.citizen.CitizenSelectionListener;
import org.fourgeeks.gha.webclient.client.citizen.CitizenSelectionProducer;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.DateUtil;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author jfuentes
 * 
 */
public class CitizenForm extends GHAForm<Citizen> implements
CitizenSelectionProducer {


	// private final BpuCitizenBasicInformationForm bpuForm = new
	// BpuBasicInformationForm();
	// private final CitizenParentBasicInformationForm parentForm = new
	// CitizenParentBasicInformationForm();
	private GHATextItem idItem, firstNameItem, secondNameItem, lastNameItem,
	secondLastNameItem, ageTextItem;
	private GHASelectItem typeidSelectItem, genderSelectItem;
	// private GHASelectItem bpiSelectItem;
	private GHADateItem birthDateItem;
	private GHAEmailTextItem primaryEmailItem;

	private GHADynamicForm form;
	private List<CitizenSelectionListener> listeners;

	{
		listeners = new ArrayList<CitizenSelectionListener>();
		typeidSelectItem = new GHADoumentTypeSelectItem();
		typeidSelectItem.setValueMap(DocumentTypeEnum.toValueMap());
		idItem = new GHATextItem(GHAStrings.get("user-identification"));
		genderSelectItem = new GHAGenderSelectItem();
		genderSelectItem.setValueMap(GenderTypeEnum.toValueMap());
		firstNameItem = new GHANameTextItem(GHAStrings.get("first-name"));
		secondNameItem = new GHANameTextItem(GHAStrings.get("second-name"));
		lastNameItem = new GHANameTextItem(GHAStrings.get("first-lastname"));
		lastNameItem.setTooltip(GHAStrings.get("user-tooltip-last-name"));
		secondLastNameItem = new GHANameTextItem(
				GHAStrings.get("second-lastname"));
		primaryEmailItem = new GHAEmailTextItem(
				GHAStrings.get("user-email-primary"));

		// bpiSelectItem = new GHABpiSelectItem();
		birthDateItem = new GHABirthDateItem();
		ageTextItem = new GHAAgeTextItem(GHAStrings.get("N/A"));

		typeidSelectItem.setValueMap(DocumentTypeEnum.toValueMap());
		genderSelectItem.setValueMap(GenderTypeEnum.toValueMap());

		form = new GHADynamicForm(5, FormType.SECTIONFORM_MINIFORM);
	}

	/**
	 * 
	 */
	public CitizenForm() {
		super();
		GHAUiHelper.addGHAResizeHandler(this);
		form.setItems(typeidSelectItem, idItem, genderSelectItem,
				birthDateItem, ageTextItem, firstNameItem, secondNameItem,
				lastNameItem, secondLastNameItem, /* bpiSelectItem, */
				primaryEmailItem);
		form.setAutoFocus(true);

		final HLayout formLayout = new HLayout();
		formLayout.setPadding(10);
		// formLayout.setHeight(GHAUiHelper.DEFAULT_INNER_TOP_SECTION_HEIGHT+
		// "px");
		formLayout.setDefaultLayoutAlign(VerticalAlignment.CENTER);
		formLayout.addMembers(form, getRightPhotoLayout(), new LayoutSpacer());
		addMember(formLayout/* ,bpuForm, parentForm */);

	}

	@Override
	public void activate() {
		toggleForm(true);
	}

	@Override
	public void addCitizenSelectionListener(CitizenSelectionListener listener) {
		listeners.add(listener);

	}

	@Override
	public void clear() {
		super.clear();
		firstNameItem.clearValue();
		secondNameItem.clearValue();
		lastNameItem.clearValue();
		secondLastNameItem.clearValue();
		typeidSelectItem.clearValue();
		idItem.clearValue();
		genderSelectItem.clearValue();
		birthDateItem.clearValue();
		primaryEmailItem.clearValue();
	}

	@Override
	public void deactivate() {
		toggleForm(false);

	}

	/**
	 * This method extract the values from the fields. the param is used to
	 * decide whether or not, the id values from the inner entities should be
	 * added in order to update or in the case of new citizen should not be
	 * added because is new.
	 * 
	 * @param update
	 * @return the Citizen to save/update
	 */
	private Citizen extract(boolean update) {
		final List<String> violationsList = new ArrayList<String>();
		final Citizen citizen = new Citizen();
		final LegalEntity legalEntity = new LegalEntity();

		if (update) {
			citizen.setId(this.originalEntity.getId());
			legalEntity.setId(this.originalEntity.getLegalEntity().getId());
			legalEntity.setIdentifier(this.originalEntity.getLegalEntity()
					.getIdentifier());
		}
		// citizen fields
		citizen.setFirstName(firstNameItem.getValueAsString());
		citizen.setSecondName(secondNameItem.getValueAsString());
		citizen.setFirstLastName(lastNameItem.getValueAsString());
		citizen.setSecondLastName(secondLastNameItem.getValueAsString());

		if (idItem.getValue() != null)
			citizen.setIdNumber(idItem.getValueAsString());

		if (primaryEmailItem.getValue() != null)
			citizen.setPrimaryEmail(primaryEmailItem.getValueAsString());

		if (birthDateItem.getValue() != null)
			citizen.setBirthDate(new Date(birthDateItem.getValueAsDate()
					.getTime()));

		if (typeidSelectItem.getValue() != null)
			citizen.setIdType(DocumentTypeEnum.valueOf(typeidSelectItem
					.getValueAsString()));

		if (genderSelectItem.getValue() != null)
			citizen.setGender(GenderTypeEnum.valueOf(genderSelectItem
					.getValueAsString()));
		// build the citizen object
		citizen.setLegalEntity(legalEntity);

		final Set<ConstraintViolation<LegalEntity>> violationsLegalEntity = validator
				.validate(legalEntity);
		final Set<ConstraintViolation<Citizen>> violationsCitizen = validator
				.validate(citizen);

		if (violationsLegalEntity.isEmpty()) {
			for (final Iterator<ConstraintViolation<LegalEntity>> it = violationsLegalEntity
					.iterator(); it.hasNext();)
				violationsList.add(it.next().getMessage());
		}

		if (!violationsCitizen.isEmpty()) {
			for (final Iterator<ConstraintViolation<Citizen>> it = violationsCitizen
					.iterator(); it.hasNext();)
				violationsList.add(it.next().getMessage());
		}

		if (form.validate() && violationsList.isEmpty())
			return citizen;

		GHAAlertManager.alert(violationsList.get(0));

		return null;
	}

	// Right Side Layout
	private HLayout getRightPhotoLayout() {
		final HLayout rightSideLayout = new HLayout();
		rightSideLayout.setWidth(170);
		rightSideLayout.setLayoutMargin(5);
		rightSideLayout.setMembersMargin(5);

		final VLayout alertLayout = new VLayout();
		setDefaultLayoutAlign(Alignment.CENTER);
		final GHAAlertLabel citizenAlertLabel = new GHAAlertLabel(
				GHAStrings.get("citizen"), "#D9D9D9");
		citizenAlertLabel.setBorder("1px solid #A9A9A9");
		alertLayout.addMember(citizenAlertLabel);

		rightSideLayout.addMembers(new GHAImg("../resources/img/photo.jpg", 75,
				75), alertLayout);

		return rightSideLayout;
	}

	@Override
	public void notifyCitizen(Citizen citizen) {
		for (final CitizenSelectionListener listener : listeners)
			listener.onCitizenSelect(citizen);

	}

	@Override
	public void onResize(ResizeEvent arg0) {
		form.resize();
	}

	@Override
	public void removeCitizenSelectionListener(CitizenSelectionListener listener) {
		listeners.remove(listener);

	}

	@Override
	public void save(final GHAAsyncCallback<Citizen> callback) {
		final Citizen citizen = extract(false);
		// if the validation fail, return
		if (citizen == null)
			return;

		CitizenModel.save(citizen, new GHAAsyncCallback<Citizen>() {

			@Override
			public void onSuccess(Citizen result) {
				hasUnCommittedChanges = false;
				notifyCitizen(result);
				clear();
				if (callback != null)
					callback.onSuccess(result);
			}
		});
	}

	@Override
	public void set(Citizen citizen) {
		this.originalEntity = citizen;
		typeidSelectItem.setValue(citizen.getIdType());
		idItem.setValue(citizen.getIdNumber());
		genderSelectItem.setValue(citizen.getGender());

		final Date birthDate = citizen.getBirthDate();
		if (birthDate != null) {
			birthDateItem.setValue(birthDate.toString());
			final int daysBetween = CalendarUtil.getDaysBetween(birthDate,
					DateUtil.create());
			ageTextItem.setValue("" + daysBetween / 365);
		}

		firstNameItem.setValue(citizen.getFirstName());
		secondNameItem.setValue(citizen.getSecondName());
		lastNameItem.setValue(citizen.getFirstLastName());
		secondLastNameItem.setValue(citizen.getSecondLastName());
		primaryEmailItem.setValue(citizen.getPrimaryEmail());

	}

	/**
	 * @param activate
	 */
	private void toggleForm(boolean activate) {
		firstNameItem.setDisabled(!activate);
		secondNameItem.setDisabled(!activate);
		lastNameItem.setDisabled(!activate);
		secondLastNameItem.setDisabled(!activate);
		typeidSelectItem.setDisabled(!activate);
		idItem.setDisabled(!activate);
		genderSelectItem.setDisabled(!activate);
		birthDateItem.setDisabled(!activate);
		primaryEmailItem.setDisabled(!activate);
	}

	@Override
	public void update(final GHAAsyncCallback<Citizen> callback) {
		final Citizen citizen = extract(true);
		// if the validation fail, return
		if (citizen == null)
			return;
		CitizenModel.update(citizen, new GHAAsyncCallback<Citizen>() {

			@Override
			public void onSuccess(Citizen result) {
				hasUnCommittedChanges = false;
				notifyCitizen(result);
				if (callback != null)
					callback.onSuccess(result);
			}
		});
	}

}
