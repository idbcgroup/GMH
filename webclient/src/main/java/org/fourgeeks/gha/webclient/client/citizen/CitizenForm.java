package org.fourgeeks.gha.webclient.client.citizen;

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
import org.fourgeeks.gha.webclient.client.UI.alerts.GHAAlertManager;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHADateItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.dateitems.GHABirthDateItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.textitems.GHAEmailTextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.textitems.GHANameTextItem;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm.FormType;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAForm;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;

/**
 * @author alacret
 * 
 */
public class CitizenForm extends GHAForm<Citizen> implements
		CitizenSelectionProducer {

	private GHATextItem idItem, firstNameItem, secondNameItem, lastNameItem,
			secondLastNameItem, nationalityItem, legalEntityIdentifierItem;
	private GHASelectItem typeidSelectItem, genderSelectItem;
	private GHADateItem birthDateItem;
	private GHAEmailTextItem primaryEmailItem, alternativeEmailItem;

	private List<CitizenSelectionListener> listeners;
	private GHADynamicForm form;

	{
		firstNameItem = new GHANameTextItem(GHAStrings.get("first-name"),
				false, changedHandler);

		firstNameItem.setLength(20);

		firstNameItem.validateWords();
		firstNameItem.setTooltip(GHAStrings.get("user-tooltip-first-name"));

		secondNameItem = new GHANameTextItem(GHAStrings.get("second-name"),
				false, changedHandler);
		secondNameItem.setLength(20);
		secondNameItem.setTooltip(GHAStrings.get("user-tooltip-second-name"));

		secondNameItem.validateWords();

		lastNameItem = new GHANameTextItem(GHAStrings.get("first-lastname"),
				false, changedHandler);
		lastNameItem.setTooltip(GHAStrings.get("user-tooltip-last-name"));
		lastNameItem.setLength(20);
		// lastNameItem.setMask("[a-zA-ZáéíóúAÉÍÓÚÑñ][a-zA-ZáéíóúAÉÍÓÚÑñ][a-zA-ZáéíóúAÉÍÓÚÑñ][a-zA-ZáéíóúAÉÍÓÚÑñ][a-zA-ZáéíóúAÉÍÓÚÑñ][a-zA-ZáéíóúAÉÍÓÚÑñ][a-zA-ZáéíóúAÉÍÓÚÑñ][a-zA-ZáéíóúAÉÍÓÚÑñ][a-zA-ZáéíóúAÉÍÓÚÑñ][a-zA-ZáéíóúAÉÍÓÚÑñ][a-zA-ZáéíóúAÉÍÓÚÑñ][a-zA-ZáéíóúAÉÍÓÚÑñ][a-zA-ZáéíóúAÉÍÓÚÑñ][a-zA-ZáéíóúAÉÍÓÚÑñ][a-zA-ZáéíóúAÉÍÓÚÑñ][a-zA-ZáéíóúAÉÍÓÚÑñ][a-zA-ZáéíóúAÉÍÓÚÑñ][a-zA-ZáéíóúAÉÍÓÚÑñ][a-zA-ZáéíóúAÉÍÓÚÑñ][a-zA-ZáéíóúAÉÍÓÚÑñ]");
		lastNameItem.validateWords();

		secondLastNameItem = new GHANameTextItem(
				GHAStrings.get("second-lastname"), false, changedHandler);
		secondLastNameItem.setLength(20);
		secondLastNameItem.setTooltip(GHAStrings
				.get("user-tooltip-secondlast-name"));
		secondLastNameItem.validateWords();

		primaryEmailItem = new GHAEmailTextItem("Email Primario",
				changedHandler);
		primaryEmailItem.setTooltip(GHAStrings.get("user-tooltip-email"));

		alternativeEmailItem = new GHAEmailTextItem("Email Secundario",
				changedHandler);

		alternativeEmailItem.setTooltip(GHAStrings
				.get("user-tooltip-email-secundario"));

		typeidSelectItem = new GHASelectItem("Tipo ID", true, changedHandler);
		typeidSelectItem.setTooltip(GHAStrings.get("user-tooltip-tipo-id"));

		idItem = new GHATextItem("No. Identificiación", true, changedHandler);
		idItem.setLength(20);
		idItem.validateCustomExpre(
				GHAStrings.get("user-error-formatter-identification"),
				"^[a-zA-Z0-9|-]+$");
		idItem.setShowErrorIcon(false);
		idItem.setValidateOnExit(false);
		idItem.setTooltip(GHAStrings.get("user-tooltip-identification"));

		genderSelectItem = new GHASelectItem("Género", true, changedHandler);
		genderSelectItem.setTooltip(GHAStrings.get("user-tooltip-genero"));

		nationalityItem = new GHATextItem("Nacionalidad", false, changedHandler);
		nationalityItem.setLength(20);
		nationalityItem.setTooltip(GHAStrings.get("user-tooltip-nacionalidad"));
		nationalityItem.validateWords();

		birthDateItem = new GHABirthDateItem(GHAStrings.get("birthdate"),
				changedHandler);
		birthDateItem.setUseMask(true);
		birthDateItem.setTooltip(GHAStrings.get("user-tooltip-birthdate"));

		legalEntityIdentifierItem = new GHATextItem("R.I.F.", false,
				changedHandler);
		legalEntityIdentifierItem.setLength(17);
		legalEntityIdentifierItem.validateCustomExpre(
				GHAStrings.get("user-error-formatter-rif"),
				"^[VvMmPprREeJjIiGg0-9|-]+$");

		legalEntityIdentifierItem
				.setTooltip(GHAStrings.get("user-tooltip-rif"));

		listeners = new ArrayList<CitizenSelectionListener>();

		form = new GHADynamicForm(4, FormType.NORMAL_FORM);

	}

	/**
	 * 
	 */
	public CitizenForm() {
		final HLayout mainPanel = new HLayout();

		form.setItems(typeidSelectItem, idItem, genderSelectItem,
				new GHASpacerItem(), firstNameItem, secondNameItem,
				lastNameItem, new GHASpacerItem(), secondLastNameItem,
				nationalityItem, birthDateItem, new GHASpacerItem(),
				primaryEmailItem, alternativeEmailItem,
				legalEntityIdentifierItem);

		mainPanel.addMembers(form, new LayoutSpacer());
		addMember(mainPanel);
		fill();

	}

	@Override
	public void activate() {
		toggleForm(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.user.UserSelectionProducer#
	 * addUserSelectionListener
	 * (org.fourgeeks.gha.webclient.client.user.UserSelectionListener)
	 */
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
		nationalityItem.clearValue();
		birthDateItem.clearValue();
		legalEntityIdentifierItem.clearValue();
		primaryEmailItem.clearValue();
		alternativeEmailItem.clearValue();
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
		}
		// citizen fields
		citizen.setFirstName(firstNameItem.getValueAsString());
		citizen.setSecondName(secondNameItem.getValueAsString());
		citizen.setFirstLastName(lastNameItem.getValueAsString());
		citizen.setSecondLastName(secondLastNameItem.getValueAsString());
		citizen.setNationality(nationalityItem.getValueAsString());

		if (idItem.getValue() != null)
			citizen.setIdNumber(idItem.getValueAsString());

		if (legalEntityIdentifierItem.getValue() != null)
			legalEntity.setIdentifier(legalEntityIdentifierItem
					.getValueAsString());

		if (primaryEmailItem.getValue() != null)
			citizen.setPrimaryEmail(primaryEmailItem.getValueAsString());

		if (alternativeEmailItem.getValue() != null)
			citizen.setAlternativeEmail(alternativeEmailItem.getValueAsString());

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

		GHAAlertManager.alert(violationsList);

		return null;
	}

	private void fill() {
		typeidSelectItem.setValueMap(DocumentTypeEnum.toValueMap());
		genderSelectItem.setValueMap(GenderTypeEnum.toValueMap());
	}

	@Override
	public void notifyCitizen(Citizen citizen) {
		for (final CitizenSelectionListener listener : listeners)
			listener.onCitizenSelect(citizen);
	}

	@Override
	public void onResize(ResizeEvent event) {
		form.resize();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.user.UserSelectionProducer#
	 * removeUserSelectionListener
	 * (org.fourgeeks.gha.webclient.client.user.UserSelectionListener)
	 */
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

		if (citizen.getFirstName() != null)
			firstNameItem.setValue(citizen.getFirstName());
		if (citizen.getSecondName() != null)
			secondNameItem.setValue(citizen.getSecondName());
		if (citizen.getFirstLastName() != null)
			lastNameItem.setValue(citizen.getFirstLastName());
		if (citizen.getSecondLastName() != null)
			secondLastNameItem.setValue(citizen.getSecondLastName());
		if (citizen.getBirthDate() != null)
			birthDateItem.setValue(citizen.getBirthDate());
		if (citizen.getIdType() != null)
			typeidSelectItem.setValue(citizen.getIdType().name());
		if (citizen.getIdNumber() != null)
			idItem.setValue(citizen.getIdNumber());
		if (citizen.getNationality() != null)
			nationalityItem.setValue(citizen.getNationality());
		if (citizen.getGender() != null)
			genderSelectItem.setValue(citizen.getGender().name());
		if (citizen.getPrimaryEmail() != null)
			primaryEmailItem.setValue(citizen.getPrimaryEmail());
		if (citizen.getAlternativeEmail() != null)
			alternativeEmailItem.setValue(citizen.getAlternativeEmail());
		if (citizen.getLegalEntity() != null
				&& citizen.getLegalEntity().getIdentifier() != null)
			legalEntityIdentifierItem.setValue(citizen.getLegalEntity()
					.getIdentifier());

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
		nationalityItem.setDisabled(!activate);
		birthDateItem.setDisabled(!activate);
		legalEntityIdentifierItem.setDisabled(!activate);
		primaryEmailItem.setDisabled(!activate);
		alternativeEmailItem.setDisabled(!activate);
	}

	@Override
	public void undo() {
		if (originalEntity == null)
			clear();
		else
			this.set(originalEntity);
		hasUnCommittedChanges = false;

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
