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
import org.fourgeeks.gha.domain.mix.Institution;
import org.fourgeeks.gha.domain.mix.LegalEntity;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHADateItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;

import com.google.gwt.validation.client.impl.Validation;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret, emiliot
 * 
 */
public class UserForm extends VLayout implements UserSelectionProducer {

	private GHATextItem usernameItem, passwordItem, confirmPasswordItem,
			idItem, firstNameItem, secondNameItem, lastNameItem,
			secondLastNameItem, nationalityItem, legalEntityIdentifierItem;
	private GHASelectItem typeidSelectItem, genderSelectItem, bpiSelectItem;
	private GHADateItem birthDateItem;

	private List<UserSelectionListener> listeners;
	
	/**
	 * this is used to keep the id of the internal entities of ssouser
	 * named bpu, bpi, citizen, legalentity, etc.
	 * is used only for update purposes
	 */
	private SSOUser updateUser;

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
		bpiSelectItem = new GHASelectItem("Institución",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		legalEntityIdentifierItem = new GHATextItem("R.I.F.",
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
		SSOUser ssoUser = extract(false);
		
		//if the validation fail, return
		if(ssoUser == null)
			return;
		UserModel.save(ssoUser, new GHAAsyncCallback<SSOUser>() {

			@Override
			public void onSuccess(SSOUser result) {
				notifyUser(result);
				cancel();

			}
		});
	}

	/**
	 * 
	 */
	public void update() {
		SSOUser ssoUser = extract(true);
		//if the validation fail, return
		if(ssoUser == null)
			return;
		UserModel.update(ssoUser, new GHAAsyncCallback<SSOUser>() {

			@Override
			public void onSuccess(SSOUser result) {
				notifyUser(result);
			}
		});
	}
	
	/**
	 * This method extract the values from the fields.
	 * the param is used to decide whether or not, the id values
	 * from the inner entities should be added in order to update
	 * or in the case of new ssoUser should not be added because
	 * is new.
	 * @param addIdsToUpdate
	 * @return the SSOUser to save/update
	 */
	private SSOUser extract(boolean update){
		final SSOUser ssoUser = new SSOUser();
		final Bpu bpu  = new Bpu();
		final Citizen citizen =  new Citizen();
		final LegalEntity legalEntity = new LegalEntity();
		final Bpi bpi = new Bpi();
		
		if(update){
			ssoUser.setId(this.updateUser.getId());
			bpu.setId(this.updateUser.getBpu().getId());
			citizen.setId(this.updateUser.getBpu().getCitizen().getId());
			legalEntity.setId(this.updateUser.getBpu().getCitizen().getLegalEntity().getId());
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

		Set<ConstraintViolation<LegalEntity>> violationsLegalEntity = validator
				.validate(legalEntity);
		Set<ConstraintViolation<Citizen>> violationsCitizen = validator
				.validate(citizen);
		Set<ConstraintViolation<Bpu>> violationsBpu = validator.validate(bpu);
		Set<ConstraintViolation<SSOUser>> violationsSSOUser = validator
				.validate(ssoUser);
		
		if (violationsSSOUser.isEmpty() && violationsBpu.isEmpty() &&
				violationsCitizen.isEmpty() && violationsLegalEntity.isEmpty()) {
			
			if (passwordItem.getValue() == null
					|| confirmPasswordItem.getValue() == null
					|| passwordItem.getValueAsString() != confirmPasswordItem
							.getValueAsString()) {
				GHANotification.alert("Las Constraseñas no coinciden");
				return null;
			}
			
			//everything ok, go save/update
			return ssoUser;
			
		}else{
			GHANotification.alert("Error en los datos de usuario, verifique los campos requeridos");
			return null;
		}
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
	
	/**
	 * This method fills the userForm with the SSOUser info
	 * @param ssoUser
	 */
	public void setSSOUser(SSOUser ssoUser){
		this.updateUser = ssoUser;
		
		if(ssoUser.getUserName() != null){
			usernameItem.setValue(ssoUser.getUserName());
		}
		if(ssoUser.getPassword() != null){
			passwordItem.setValue(ssoUser.getPassword());
			confirmPasswordItem.setValue(ssoUser.getPassword());
		}
		if(ssoUser.getUserLogonStatus() != null){
			//TODO: when is added to the interface
		}
		if(ssoUser.getBpu() != null){
			Bpu bpu = ssoUser.getBpu();
			if(bpu.getBpi() != null){
				Bpi bpi = bpu.getBpi();
				if(bpi.getInstitution() != null){
					Institution institution = bpi.getInstitution();
					bpiSelectItem.setValue(institution.getId());
				}
			}
			
			if(bpu.getCitizen() != null){
				Citizen citizen = bpu.getCitizen();
				if(citizen.getFirstName() != null){
					firstNameItem.setValue(citizen.getFirstName());
				}
				if(citizen.getSecondName()!=null){
					secondNameItem.setValue(citizen.getSecondName());
				}
				if(citizen.getFirstLastName() != null){
					lastNameItem.setValue(citizen.getFirstLastName());
				}
				if(citizen.getSecondLastName() != null){
					secondLastNameItem.setValue(citizen.getSecondLastName());
				}
				if(citizen.getBirthDate() != null){
					birthDateItem.setValue(citizen.getBirthDate());
				}
				if(citizen.getIdType() != null){
					typeidSelectItem.setValue(citizen.getIdType().name());
				}
				if(citizen.getIdNumber() != null){
					idItem.setValue(citizen.getIdNumber());
				}
				if(citizen.getNationality() != null){
					nationalityItem.setValue(citizen.getNationality());
				}
				if(citizen.getGender() != null){
					genderSelectItem.setValue(citizen.getGender().name());
				}
				if(citizen.getLegalEntity() != null && citizen.getLegalEntity().getIdentifier() != null){
					legalEntityIdentifierItem.setValue(citizen.getLegalEntity().getIdentifier());
				}
			}
		}
		
	}

	// Producer stuff

	/**
	 * @param ssoUser
	 *            this method notify the listeners for new ssoUser selected
	 */
	private void notifyUser(SSOUser ssoUser) {
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

}
