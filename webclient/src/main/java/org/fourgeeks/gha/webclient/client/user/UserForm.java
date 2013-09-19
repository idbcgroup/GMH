package org.fourgeeks.gha.webclient.client.user;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

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
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;

import com.google.gwt.validation.client.impl.Validation;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret, emiliot
 * 
 */
public class UserForm extends VLayout implements UserSelectionProducer{

	private GHATextItem usernameItem, passwordItem, confirmPasswordItem,
			idItem, firstNameItem, secondNameItem, lastNameItem,
			secondLastNameItem, nationalityItem, legalEntityIdentifierItem;
	private GHASelectItem typeidSelectItem, genderSelectItem, bpiSelectItem;
	private GHADateItem birthDateItem;
	
	private List<UserSelectionListener> listeners;

	private Validator validator;

	{
		usernameItem = new GHATextItem("Nombre de Usuario",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		passwordItem = new GHATextItem("Contraseña",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		confirmPasswordItem = new GHATextItem("Confirme contraseña",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		firstNameItem = new GHATextItem("Primer Nombre",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		secondNameItem = new GHATextItem("Segundo Nombre",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		lastNameItem = new GHATextItem("Apellido",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		secondLastNameItem = new GHATextItem("Segundo Apellido",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);

		typeidSelectItem = new GHASelectItem("Tipo ID",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		idItem = new GHATextItem("No. Identificiación",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		genderSelectItem = new GHASelectItem("Género",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		nationalityItem = new GHATextItem("Nacionalidad",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		birthDateItem = new GHADateItem("Fecha de Nac.",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		bpiSelectItem = new GHASelectItem("Tipo ID",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		legalEntityIdentifierItem = new GHATextItem("R.I.F.:",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);

		 validator = Validation.buildDefaultValidatorFactory().getValidator();
		 
		 listeners = new ArrayList<UserSelectionListener>();
	}

	/**
	 * 
	 */
	public UserForm() {
		final DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		// form.setCellPadding(1);
		form.setNumCols(3);
		form.setItems(usernameItem, passwordItem, confirmPasswordItem,
				typeidSelectItem, idItem, genderSelectItem, firstNameItem,
				secondNameItem, lastNameItem, secondLastNameItem,
				nationalityItem, birthDateItem, bpiSelectItem,
				legalEntityIdentifierItem);
		addMember(form);
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

	/**
	 * 
	 */
	public void cancel() {
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
	}

	/**
	 * 
	 */
	public void save() {
		if (passwordItem.getValue() == null
				|| confirmPasswordItem.getValue() == null
				|| passwordItem.getValueAsString() != confirmPasswordItem
						.getValueAsString()) {
			// TODO: mensaje de password no coincide
			return;
		}

		final SSOUser ssoUser = new SSOUser();
		final Bpu bpu = new Bpu();
		final Citizen citizen = new Citizen();
		final LegalEntity legalEntity = new LegalEntity();

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
			bpu.setBpi(new Bpi(Long.valueOf(bpiSelectItem.getValueAsString())));
		}

		// legalentity fields
		if (legalEntityIdentifierItem.getValue() != null) {
			legalEntity.setIdentifier(legalEntityIdentifierItem
					.getValueAsString());
		}

		// build the ssoUser object
		citizen.setLegalEntity(legalEntity);
		bpu.setCitizen(citizen);
		ssoUser.setBpu(bpu);

		Set<ConstraintViolation<LegalEntity>> violationsLegalEntity = validator
				.validate(legalEntity);
		if (violationsLegalEntity.isEmpty()) {
			Set<ConstraintViolation<Citizen>> violationsCitizen = validator
					.validate(citizen);
			if (violationsCitizen.isEmpty()) {
				bpu.setCitizen(citizen);
				Set<ConstraintViolation<Bpu>> violationsBpu = validator
						.validate(bpu);
				if (violationsBpu.isEmpty()) {
					ssoUser.setBpu(bpu);
					Set<ConstraintViolation<SSOUser>> violationsSSOUser = validator
							.validate(ssoUser);
					if (violationsSSOUser.isEmpty()) {
						UserModel.save(ssoUser,
								new GHAAsyncCallback<SSOUser>() {

									@Override
									public void onSuccess(SSOUser result) {
										notifyUser(result);
										cancel();

									}
								});
					}
				}
			}
		}

	}

	/**
	 * 
	 */
	public void update() {
		// TODO
	}

	/**
	 * @param activate
	 */
	public void activateForm(boolean activate) {
		usernameItem.setDisabled(!activate);
		passwordItem.setDisabled(!activate);
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
	}
	
	//Producer stuff
	
	/**
	 * @param ssoUser
	 * this method notify the listeners for new ssoUser selected
	 */
	private void notifyUser(SSOUser ssoUser){
		for(UserSelectionListener listener : listeners){
			listener.select(ssoUser);
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.user.UserSelectionProducer#addUserSelectionListener(org.fourgeeks.gha.webclient.client.user.UserSelectionListener)
	 */
	@Override
	public void addUserSelectionListener(
			UserSelectionListener userSelectionListener) {
		listeners.add(userSelectionListener);
		
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.user.UserSelectionProducer#removeUserSelectionListener(org.fourgeeks.gha.webclient.client.user.UserSelectionListener)
	 */
	@Override
	public void removeUserSelectionListener(
			UserSelectionListener userSelectionListener) {
		listeners.remove(userSelectionListener);
	}

}
