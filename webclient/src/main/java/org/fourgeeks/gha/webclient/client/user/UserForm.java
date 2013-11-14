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
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHADateItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHAEmailItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;

/**
 * @author alacret, emiliot
 * 
 */
public class UserForm extends GHAForm<SSOUser> implements UserSelectionProducer, ResizeHandler {

	private GHATextItem usernameItem, passwordItem, confirmPasswordItem,
			idItem, firstNameItem, secondNameItem, lastNameItem,
			secondLastNameItem, nationalityItem, legalEntityIdentifierItem;
	private GHASelectItem typeidSelectItem, genderSelectItem, bpiSelectItem;
	private GHADateItem birthDateItem;
	private GHAEmailItem primaryEmailItem, alternativeEmailItem;

	private List<UserSelectionListener> listeners;
	private GHADynamicForm form;

	/**
	 * this is used to keep the id of the internal entities of ssouser named
	 * bpu, bpi, citizen, legalentity, etc. is used only for update purposes
	 */
	private SSOUser updateUser;

	{
		usernameItem = new GHATextItem("Nombre de Usuario");
		usernameItem.setRequired(true);
		usernameItem.setLength(20);
		usernameItem.setMask("AAAAAAAAAAAAAAAAAAAA");
		passwordItem = new GHATextItem("Contraseña");
		passwordItem.setRequired(true);
		passwordItem.setLength(20);
		confirmPasswordItem = new GHATextItem("Confirme contraseña");
		confirmPasswordItem.setRequired(true);
		confirmPasswordItem.setLength(20);
		firstNameItem = new GHATextItem("Primer Nombre");
		firstNameItem.setLength(20);
		firstNameItem.setMask(">A<AAAAAAAAAAAAAAAAAAA");
		secondNameItem = new GHATextItem("Segundo Nombre");
		secondNameItem.setLength(20);
		secondNameItem.setMask(">A<AAAAAAAAAAAAAAAAAAA");

		lastNameItem = new GHATextItem("Apellido");
		lastNameItem.setLength(20);
		lastNameItem.setMask(">A<AAAAAAAAAAAAAAAAAAA");

		secondLastNameItem = new GHATextItem("Segundo Apellido");
		secondLastNameItem.setLength(20);
		secondLastNameItem.setMask(">A<AAAAAAAAAAAAAAAAAAA");

		primaryEmailItem = new GHAEmailItem("Email Primario");
		primaryEmailItem.setLength(254);

		alternativeEmailItem = new GHAEmailItem("Email Secundario");
		alternativeEmailItem.setLength(254);

		typeidSelectItem = new GHASelectItem("Tipo ID", true, changedHandler);
		idItem = new GHATextItem("No. Identificiación", true, changedHandler);
		idItem.setLength(20);
		idItem.setMask("####################");
		genderSelectItem = new GHASelectItem("Género");
		genderSelectItem.setRequired(true);
		nationalityItem = new GHATextItem("Nacionalidad");
		nationalityItem.setLength(60);
		nationalityItem.setMask("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		birthDateItem = new GHADateItem("Fecha de Nac.");
		birthDateItem.setStartDate(new java.util.Date(50, 1, 1));
		birthDateItem
			.setEndDate(new java.util.Date(System.currentTimeMillis()));

		bpiSelectItem = new GHASelectItem("Institución");
		bpiSelectItem.setRequired(true);
		legalEntityIdentifierItem = new GHATextItem("R.I.F.");
		legalEntityIdentifierItem.setLength(16);
		legalEntityIdentifierItem.setMask("AAAAAAAAAAAAAAAA");

		listeners = new ArrayList<UserSelectionListener>();

		form = new GHADynamicForm(GHAUiHelper.getNormalFormWidth(30), 4);
	}

	/**
	 * 
	 */
	public UserForm() {
		GHAUiHelper.addGHAResizeHandler(this);
		final HLayout mainPanel = new HLayout();
		
		form.setItems(usernameItem, passwordItem, confirmPasswordItem,new GHASpacerItem(),
					  typeidSelectItem, idItem, genderSelectItem,new GHASpacerItem(),
					  firstNameItem,secondNameItem, lastNameItem,new GHASpacerItem(),
					  secondLastNameItem,nationalityItem, birthDateItem,new GHASpacerItem(),
					  primaryEmailItem,alternativeEmailItem, bpiSelectItem, new GHASpacerItem(),
					  legalEntityIdentifierItem);

		mainPanel.addMembers(form, new LayoutSpacer());
		addMember(mainPanel);
		fill();
	}

	private void fill() {
		typeidSelectItem.setValueMap(DocumentTypeEnum.toValueMap());
		genderSelectItem.setValueMap(GenderTypeEnum.toValueMap());

		GHACache.INSTANCE.getBpis(new GHAAsyncCallback<List<Bpi>>() {

			@Override
			public void onSuccess(List<Bpi> result) {
				LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
				for (Bpi bpi : result) {
					valueMap.put(bpi.getId() + "", bpi.getInstitution()
							.getName());
				}
				bpiSelectItem.setValueMap(valueMap);
			}
		}, false);
	}

	@Override
	public void clear() {
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
	public void save() {
		SSOUser ssoUser = extract(false);

		// if the validation fail, return
		if (ssoUser == null)
			return;
		UserModel.save(ssoUser, new GHAAsyncCallback<SSOUser>() {

			@Override
			public void onSuccess(SSOUser result) {
				notifyUser(result);
				clear();

			}
		});
	}

	@Override
	public void update() {
		SSOUser ssoUser = extract(true);
		// if the validation fail, return
		if (ssoUser == null)
			return;
		UserModel.update(ssoUser, new GHAAsyncCallback<SSOUser>() {

			@Override
			public void onSuccess(SSOUser result) {
				notifyUser(result);
			}
		});
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
		final SSOUser ssoUser = new SSOUser();
		final Bpu bpu = new Bpu();
		final Citizen citizen = new Citizen();
		final LegalEntity legalEntity = new LegalEntity();
		final Bpi bpi = new Bpi();

		if (update) {
			ssoUser.setId(this.updateUser.getId());
			bpu.setId(this.updateUser.getBpu().getId());
			citizen.setId(this.updateUser.getBpu().getCitizen().getId());
			legalEntity.setId(this.updateUser.getBpu().getCitizen()
					.getLegalEntity().getId());
			bpi.setId(this.updateUser.getBpu().getBpi().getId());
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
		citizen.setPrimaryEmail(primaryEmailItem.getValueAsString());
		citizen.setAlternativeEmail(alternativeEmailItem.getValueAsString());

		if (birthDateItem.getValue() != null) {
			citizen.setBirthDate(new Date(birthDateItem.getValueAsDate()
					.getTime()));
		}

		if (typeidSelectItem.getValue() != null) {
			citizen.setIdType(DocumentTypeEnum.valueOf(typeidSelectItem
					.getValueAsString()));
			citizen.setIdNumber(idItem.getValueAsString());
		}

		if (genderSelectItem.getValue() != null) {
			citizen.setGender(GenderTypeEnum.valueOf(genderSelectItem
					.getValueAsString()));
		}

		// bpu fields
		if (bpiSelectItem.getValue() != null) {
			bpi.setId(Long.valueOf(bpiSelectItem.getValueAsString()));
		}

		// legalentity fields
		if (legalEntityIdentifierItem.getValue() != null) {
			legalEntity.setIdentifier(legalEntityIdentifierItem
					.getValueAsString());
		}

		// build the ssoUser object
		citizen.setLegalEntity(legalEntity);
		bpu.setCitizen(citizen);
		bpu.setBpi(bpi);
		ssoUser.setBpu(bpu);

		List<String> violationsList = new ArrayList<String>();

		Set<ConstraintViolation<LegalEntity>> violationsLegalEntity = validator
				.validate(legalEntity);
		Set<ConstraintViolation<Citizen>> violationsCitizen = validator
				.validate(citizen);
		Set<ConstraintViolation<Bpu>> violationsBpu = validator.validate(bpu);
		Set<ConstraintViolation<SSOUser>> violationsSSOUser = validator
				.validate(ssoUser);

		if (violationsLegalEntity.isEmpty()) {
			for (Iterator<ConstraintViolation<LegalEntity>> it = violationsLegalEntity
					.iterator(); it.hasNext();)
				violationsList.add(it.next().getMessage());
		}

		if (!violationsCitizen.isEmpty()) {
			for (Iterator<ConstraintViolation<Citizen>> it = violationsCitizen
					.iterator(); it.hasNext();)
				violationsList.add(it.next().getMessage());
		}

		if (!violationsBpu.isEmpty()) {
			for (Iterator<ConstraintViolation<Bpu>> it = violationsBpu
					.iterator(); it.hasNext();)
				violationsList.add(it.next().getMessage());
		}

		if (!violationsSSOUser.isEmpty()) {
			for (Iterator<ConstraintViolation<SSOUser>> it = violationsSSOUser
					.iterator(); it.hasNext();)
				violationsList.add(it.next().getMessage());
		}

		if (passwordItem.getValue() == null) {
			violationsList.add("password-not-null");
		}

		if (passwordItem.getValueAsString() != confirmPasswordItem
				.getValueAsString()) {
			violationsList.add("password-missmatch");
		}

		if (form.validate() && violationsList.isEmpty()) {
			return ssoUser;
		} else {
			GHANotification.alert(violationsList);
		}

		return null;
	}

	@Override
	public void activate() {
		toggleForm(true);
	}

	@Override
	public void deactivate() {
		toggleForm(false);
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

	/**
	 * This method fills the userForm with the SSOUser info
	 * 
	 * @param ssoUser
	 */
	public void setSSOUser(SSOUser ssoUser) {
		this.updateUser = ssoUser;

		if (ssoUser.getUserName() != null) {
			usernameItem.setValue(ssoUser.getUserName());
		}
		if (ssoUser.getPassword() != null) {
			passwordItem.setValue(ssoUser.getPassword());
			confirmPasswordItem.setValue(ssoUser.getPassword());
		}
		if (ssoUser.getUserLogonStatus() != null) {
			// TODO: when is added to the interface
		}
		if (ssoUser.getBpu() != null) {
			Bpu bpu = ssoUser.getBpu();
			if (bpu.getBpi() != null) {
				bpiSelectItem.setValue(bpu.getBpi().getId());
			}

			if (bpu.getCitizen() != null) {
				Citizen citizen = bpu.getCitizen();
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

	@Override
	public void notifyUser(SSOUser ssoUser) {
		GHANotification.alert("user-save-success");
		for (UserSelectionListener listener : listeners) {
			listener.select(ssoUser);
		}
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
	public void save(GHAAsyncCallback<SSOUser> callback) {
		// TODO Auto-generated method stub

	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(GHAAsyncCallback<SSOUser> callback) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onResize(ResizeEvent event) {
		form.resize(GHAUiHelper.getNormalFormWidth(30), 4);		
	}

}
