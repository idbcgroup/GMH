package org.fourgeeks.gha.webclient.client.user.information;

import org.fourgeeks.gha.domain.enu.DocumentTypeEnum;
import org.fourgeeks.gha.domain.enu.GenderTypeEnum;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHACheckboxItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHADateItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAImgButton;
import org.fourgeeks.gha.webclient.client.user.UserTab;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class UserInformationFormPanel extends VLayout implements GHAClosable,
		GHAHideable {

	private GHATextItem usernameItem, passwordItem, idItem, firstNameItem,
			secondNameItem, lastNameItem, secondLastNameItem, nationalityItem;
	private GHASelectItem typeidSelectItem, genderSelectItem;
	private GHACheckboxItem blockedItem;
	private GHADateItem birthDateItem;

	private EiaType eiaType, orginalEiaType;
	private UserTab tab;

	{
		usernameItem = new GHATextItem("Nombre de Usuario",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		passwordItem = new GHATextItem("Contraseña",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		blockedItem = new GHACheckboxItem("Usuario bloqueado");

		firstNameItem = new GHATextItem("Primer Nombre",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		secondNameItem = new GHATextItem("Segundo Nombre",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		lastNameItem = new GHATextItem("Apellido",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		secondLastNameItem = new GHATextItem("Segundo Apellido",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);

		typeidSelectItem = new GHASelectItem("Tipo ID",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		idItem = new GHATextItem("No. Identificiación",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		genderSelectItem = new GHASelectItem("Género",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		nationalityItem = new GHATextItem("Nacionalidad",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		birthDateItem = new GHADateItem("Fecha de Nac.",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
	}

	/**
	 * @param tab
	 */
	public UserInformationFormPanel(UserTab tab) {
		activateForm(false);
		this.tab = tab;

		tab.addGHAClosableHandler(this);

		setWidth100();
		setBackgroundColor("#E0E0E0");
		setStyleName("sides-padding padding-top");// Esto es VUDU!
		setAlign(Alignment.CENTER);

		DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(4);
		form.setItems(usernameItem, passwordItem, blockedItem,
				new GHASpacerItem(), firstNameItem, secondNameItem,
				lastNameItem, secondLastNameItem, typeidSelectItem, idItem,
				new GHASpacerItem(2), genderSelectItem, nationalityItem,
				birthDateItem);

		VLayout sideButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/save.png", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						save();
					}
				}), new GHAImgButton("../resources/icons/undo.png",
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						undo();
					}
				}));

		HLayout gridPanel = new HLayout();
		gridPanel.addMembers(form, new LayoutSpacer(), sideButtons);

		addMember(gridPanel);

		fillExtras();
	}

	public void activateForm(boolean activate) {
		usernameItem.setDisabled(!activate);
		passwordItem.setDisabled(!activate);
		blockedItem.setDisabled(!activate);
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

	protected void undo() {
		// reload the original eiatype
		save();
	}

	private void fillExtras() {
		typeidSelectItem.setValueMap(DocumentTypeEnum.toValueMap());
		genderSelectItem.setValueMap(GenderTypeEnum.toValueMap());
	}

	private void save() {

	}

	@Override
	public void close() {

	}

	@Override
	public void hide() {

	}
}
