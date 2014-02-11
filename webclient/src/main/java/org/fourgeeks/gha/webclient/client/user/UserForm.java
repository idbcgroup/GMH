package org.fourgeeks.gha.webclient.client.user;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.fourgeeks.gha.domain.enu.DocumentTypeEnum;
import org.fourgeeks.gha.domain.enu.GenderTypeEnum;
import org.fourgeeks.gha.domain.enu.UserLogonStatusEnum;
import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.mix.Bpi;
import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.domain.mix.LegalEntity;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHAAlertManager;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHADateItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.dateitems.GHABirthDateItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.textitems.GHAEmailTextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.textitems.GHANameTextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.textitems.GHAUserNameTextItem;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm.FormType;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAForm;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;

/**
 * @author alacret, emiliot
 * 
 */
public class UserForm extends GHAForm<SSOUser> implements UserSelectionProducer {

	private GHATextItem usernameItem, passwordItem, confirmPasswordItem,
			idItem, firstNameItem, secondNameItem, lastNameItem,
			secondLastNameItem, nationalityItem, legalEntityIdentifierItem;
	private GHASelectItem typeidSelectItem, genderSelectItem, bpiSelectItem;
	private GHADateItem birthDateItem;
	private GHAEmailTextItem primaryEmailItem, alternativeEmailItem;

	private List<UserSelectionListener> listeners;
	private List<Bpi> bpis;
	private GHADynamicForm form;

	{
		usernameItem = new GHAUserNameTextItem(true, changedHandler);
		usernameItem.setTooltip(GHAStrings.get("user-name-tooltip"));

		passwordItem = new GHATextItem(GHAStrings.get("password"), true,
				changedHandler);
		passwordItem.setTooltip(GHAStrings.get("user-password-tooltip"));
		passwordItem.setLength(20);
		confirmPasswordItem = new GHATextItem("Confirme contraseña", true,
				changedHandler);
		confirmPasswordItem.setLength(20);
		confirmPasswordItem.setTooltip(GHAStrings
				.get("user-confirm-password-tooltip"));
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

		birthDateItem = new GHABirthDateItem(GHAStrings.get("birthdate"),changedHandler);
		birthDateItem.setUseMask(true);
		birthDateItem.setTooltip(GHAStrings.get("user-tooltip-birthdate"));
		
		bpiSelectItem = new GHASelectItem("Institución");
		bpiSelectItem.setRequired(true);
		bpiSelectItem.addChangedHandler(changedHandler);
		bpiSelectItem.setTooltip(GHAStrings.get("user-tooltip-institution"));

		legalEntityIdentifierItem = new GHATextItem("R.I.F.", false,
				changedHandler);
		legalEntityIdentifierItem.setLength(17);
		legalEntityIdentifierItem.validateCustomExpre(
				GHAStrings.get("user-error-formatter-rif"),
				"^[VvMmPprREeJjIiGg0-9|-]+$");

		legalEntityIdentifierItem
				.setTooltip(GHAStrings.get("user-tooltip-rif"));

		listeners = new ArrayList<UserSelectionListener>();

		form = new GHADynamicForm(4, FormType.NORMAL_FORM);
		
	}

	/**
	 * 
	 */
	public UserForm() {
		final HLayout mainPanel = new HLayout();

		form.setItems(usernameItem, passwordItem, confirmPasswordItem,
				new GHASpacerItem(), typeidSelectItem, idItem,
				genderSelectItem, new GHASpacerItem(), firstNameItem,
				secondNameItem, lastNameItem, new GHASpacerItem(),
				secondLastNameItem, nationalityItem, birthDateItem,
				new GHASpacerItem(), primaryEmailItem, alternativeEmailItem,
				bpiSelectItem, new GHASpacerItem(), legalEntityIdentifierItem);
		
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
	public void addUserSelectionListener(
			UserSelectionListener userSelectionListener) {
		listeners.add(userSelectionListener);

	}

	@Override
	public void clear() {
		super.clear();
		usernameItem.clearValue();
		passwordItem.clearValue();
		confirmPasswordItem.clearValue();
		firstNameItem.clearValue();
		secondNameItem.clearValue();
		lastNameItem.clearValue();
		secondLastNameItem.clearValue();
		typeidSelectItem.clearValue();
		idItem.clearValue();
		genderSelectItem.clearValue();
		nationalityItem.clearValue();
		birthDateItem.clearValue();
		bpiSelectItem.clearValue();
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
	 * added in order to update or in the case of new ssoUser should not be
	 * added because is new.
	 * 
	 * @param update
	 * @return the SSOUser to save/update
	 */
	private SSOUser extract(boolean update) {
		final List<String> violationsList = new ArrayList<String>();
		final SSOUser ssoUser = new SSOUser();
		final Bpu bpu = new Bpu();
		final Citizen citizen = new Citizen();
		final LegalEntity legalEntity = new LegalEntity();
		Bpi bpi = new Bpi();

		if (update) {
			ssoUser.setId(this.originalEntity.getId());
			bpu.setId(this.originalEntity.getBpu().getId());
			citizen.setId(this.originalEntity.getBpu().getCitizen().getId());
			legalEntity.setId(this.originalEntity.getBpu().getCitizen()
					.getLegalEntity().getId());
			bpi.setId(this.originalEntity.getBpu().getBpi().getId());
		}
		// ssoUser fields
		ssoUser.setUserName(usernameItem.getValueAsString());
		ssoUser.setPassword(passwordItem.getValueAsString());
		ssoUser.setUserLogonStatus(UserLogonStatusEnum.STAYIN);
		// citizen fields
		citizen.setFirstName(firstNameItem.getValueAsString());
		citizen.setSecondName(secondNameItem.getValueAsString());
		citizen.setFirstLastName(lastNameItem.getValueAsString());
		citizen.setSecondLastName(secondLastNameItem.getValueAsString());
		citizen.setNationality(nationalityItem.getValueAsString());

		if (idItem.getValue() != null) {
			citizen.setIdNumber(idItem.getValueAsString());
		}

		if (legalEntityIdentifierItem.getValue() != null) {
			legalEntity.setIdentifier(legalEntityIdentifierItem
					.getValueAsString());
		}

		if (primaryEmailItem.getValue() != null) {
			citizen.setPrimaryEmail(primaryEmailItem.getValueAsString());
		}

		if (alternativeEmailItem.getValue() != null) {
			citizen.setAlternativeEmail(alternativeEmailItem.getValueAsString());
		}

		if (birthDateItem.getValue() != null) {
			citizen.setBirthDate(new Date(birthDateItem.getValueAsDate()
					.getTime()));
		}
		if (typeidSelectItem.getValue() != null) {
			citizen.setIdType(DocumentTypeEnum.valueOf(typeidSelectItem
					.getValueAsString()));
		}

		if (genderSelectItem.getValue() != null) {
			citizen.setGender(GenderTypeEnum.valueOf(genderSelectItem
					.getValueAsString()));
		}
		// bpu fields
		if (bpiSelectItem.getValue() != null) {
			final long bpiId = Long.valueOf(bpiSelectItem.getValueAsString());
			for (final Bpi next : bpis) {
				if (next.getId() == bpiId) {
					bpi = next;
					break;
				}
			}
		}

		// build the ssoUser object
		citizen.setLegalEntity(legalEntity);
		bpu.setCitizen(citizen);
		bpu.setBpi(bpi);
		ssoUser.setBpu(bpu);

		final Set<ConstraintViolation<LegalEntity>> violationsLegalEntity = validator
				.validate(legalEntity);
		final Set<ConstraintViolation<Citizen>> violationsCitizen = validator
				.validate(citizen);
		final Set<ConstraintViolation<Bpu>> violationsBpu = validator
				.validate(bpu);
		final Set<ConstraintViolation<SSOUser>> violationsSSOUser = validator
				.validate(ssoUser);
		final Set<ConstraintViolation<Bpi>> violationsBpi = validator
				.validate(bpi);

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

		if (!violationsBpu.isEmpty()) {
			for (final Iterator<ConstraintViolation<Bpu>> it = violationsBpu
					.iterator(); it.hasNext();)
				violationsList.add(it.next().getMessage());
		}

		if (!violationsSSOUser.isEmpty()) {
			for (final Iterator<ConstraintViolation<SSOUser>> it = violationsSSOUser
					.iterator(); it.hasNext();)
				violationsList.add(it.next().getMessage());
		}

		if (!violationsBpi.isEmpty())
			for (final Iterator<ConstraintViolation<Bpi>> it = violationsBpi
					.iterator(); it.hasNext();)
				violationsList.add(it.next().getMessage());

		if (passwordItem.getValue() == null)
			violationsList.add("password-not-null");

		if (passwordItem.getValueAsString() != confirmPasswordItem
				.getValueAsString()) {
			violationsList.add("password-missmatch");
		}

		if (form.validate() && violationsList.isEmpty()) {
			return ssoUser;
		} else {
			GHAAlertManager.alert(violationsList);
		}

		return null;
	}

	private void fill() {
		typeidSelectItem.setValueMap(DocumentTypeEnum.toValueMap());
		genderSelectItem.setValueMap(GenderTypeEnum.toValueMap());

		GHACache.INSTANCE.getBpis(new GHAAsyncCallback<List<Bpi>>() {

			@Override
			public void onSuccess(List<Bpi> result) {
				final LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
				for (final Bpi bpi : result) {
					valueMap.put(bpi.getId() + "", bpi.getInstitution()
							.getName());
				}
				bpiSelectItem.setValueMap(valueMap);
				bpis = result;
			}
		}, false);
	}

	@Override
	public void notifyUser(SSOUser ssoUser) {
		for (final UserSelectionListener listener : listeners)
			listener.select(ssoUser);
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
	public void removeUserSelectionListener(
			UserSelectionListener userSelectionListener) {
		listeners.remove(userSelectionListener);
	}

	@Override
	public void save(final GHAAsyncCallback<SSOUser> callback) {
		final SSOUser ssoUser = extract(false);
		// if the validation fail, return
		if (ssoUser == null)
			return;

		UserModel.save(ssoUser, new GHAAsyncCallback<SSOUser>() {

			@Override
			public void onSuccess(SSOUser result) {
				hasUnCommittedChanges = false;
				notifyUser(result);
				clear();
				if (callback != null)
					callback.onSuccess(result);
			}
		});
	}

	/**
	 * This method fills the userForm with the SSOUser info
	 * 
	 * @param ssoUser
	 */
	@Override
	public void set(SSOUser ssoUser) {
		this.originalEntity = ssoUser;

		if (ssoUser.getUserName() != null) {
			usernameItem.setValue(ssoUser.getUserName());
			usernameItem.setDisabled(true);
		}
		if (ssoUser.getPassword() != null) {
			passwordItem.setValue(ssoUser.getPassword());
			confirmPasswordItem.setValue(ssoUser.getPassword());
		}
		// if (ssoUser.getUserLogonStatus() != null) {
		// // TODO: when is added to the interface
		// }
		if (ssoUser.getBpu() != null) {
			final Bpu bpu = ssoUser.getBpu();
			if (bpu.getBpi() != null) {
				bpiSelectItem.setValue(bpu.getBpi().getId());
			}

			if (bpu.getCitizen() != null) {
				final Citizen citizen = bpu.getCitizen();
				if (citizen.getFirstName() != null) {
					firstNameItem.setValue(citizen.getFirstName());
				}
				if (citizen.getSecondName() != null) {
					secondNameItem.setValue(citizen.getSecondName());
				}
				if (citizen.getFirstLastName() != null) {
					lastNameItem.setValue(citizen.getFirstLastName());
				}
				if (citizen.getSecondLastName() != null) {
					secondLastNameItem.setValue(citizen.getSecondLastName());
				}
				if (citizen.getBirthDate() != null) {
					birthDateItem.setValue(citizen.getBirthDate());
				}
				if (citizen.getIdType() != null) {
					typeidSelectItem.setValue(citizen.getIdType().name());
				}
				if (citizen.getIdNumber() != null) {
					idItem.setValue(citizen.getIdNumber());
				}
				if (citizen.getNationality() != null) {
					nationalityItem.setValue(citizen.getNationality());
				}
				if (citizen.getGender() != null) {
					genderSelectItem.setValue(citizen.getGender().name());
				}
				if (citizen.getPrimaryEmail() != null) {
					primaryEmailItem.setValue(citizen.getPrimaryEmail());
				}
				if (citizen.getAlternativeEmail() != null) {
					alternativeEmailItem
							.setValue(citizen.getAlternativeEmail());
				}
				if (citizen.getLegalEntity() != null
						&& citizen.getLegalEntity().getIdentifier() != null) {
					legalEntityIdentifierItem.setValue(citizen.getLegalEntity()
							.getIdentifier());
				}
			}
		}

	}

	/**
	 * @param activate
	 */
	private void toggleForm(boolean activate) {
		usernameItem.setDisabled(!activate);
		passwordItem.setDisabled(!activate);
		confirmPasswordItem.setDisabled(!activate);
		firstNameItem.setDisabled(!activate);
		secondNameItem.setDisabled(!activate);
		lastNameItem.setDisabled(!activate);
		secondLastNameItem.setDisabled(!activate);
		typeidSelectItem.setDisabled(!activate);
		idItem.setDisabled(!activate);
		genderSelectItem.setDisabled(!activate);
		nationalityItem.setDisabled(!activate);
		birthDateItem.setDisabled(!activate);
		bpiSelectItem.setDisabled(!activate);
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
	public void update(final GHAAsyncCallback<SSOUser> callback) {
		final SSOUser ssoUser = extract(true);
		// if the validation fail, return
		if (ssoUser == null)
			return;
		UserModel.update(ssoUser, new GHAAsyncCallback<SSOUser>() {

			@Override
			public void onSuccess(SSOUser result) {
				hasUnCommittedChanges = false;
				notifyUser(result);
				if (callback != null)
					callback.onSuccess(result);
			}
		});
	}

}
