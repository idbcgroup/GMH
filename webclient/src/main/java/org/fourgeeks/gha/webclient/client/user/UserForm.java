package org.fourgeeks.gha.webclient.client.user;

import org.fourgeeks.gha.domain.enu.DocumentTypeEnum;
import org.fourgeeks.gha.domain.enu.GenderTypeEnum;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHADateItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;

import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class UserForm extends VLayout {

	private GHATextItem usernameItem, passwordItem, confirmPasswordItem,
			idItem, firstNameItem, secondNameItem, lastNameItem,
			secondLastNameItem, nationalityItem;
	private GHASelectItem typeidSelectItem, genderSelectItem;
	private GHADateItem birthDateItem;

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

		// validator = Validation.buildDefaultValidatorFactory().getValidator();
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
				nationalityItem, birthDateItem);
		addMember(form);
		fill();
	}

	private void fill() {
		typeidSelectItem.setValueMap(DocumentTypeEnum.toValueMap());
		genderSelectItem.setValueMap(GenderTypeEnum.toValueMap());
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
	}

	/**
	 * 
	 */
	public void save() {
		// TODO
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
	}

}
