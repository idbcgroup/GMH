package org.fourgeeks.gha.webclient.client.user;

import javax.validation.Validator;

import org.fourgeeks.gha.domain.enu.DocumentTypeEnum;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHACheckboxItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHADateItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASlideInWindow;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.validation.client.impl.Validation;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

public class UserAddForm extends GHASlideInWindow {

	private GHATextItem usernameItem, passwordItem,idItem, firstNameItem,secondNameItem,lastNameItem,secondLastNameItem;
	private GHASelectItem typeidSelectItem, genderSelectItem, nationalitySelectItem;
	private GHACheckboxItem blockedItem;
	private GHADateItem birthDateItem;
	
	private Validator validator;

	{
		usernameItem = new GHATextItem("Nombre de Usuario", GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		passwordItem = new GHATextItem("Contraseña", GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		blockedItem = new GHACheckboxItem("Usuario bloqueado");
		
		firstNameItem = new GHATextItem("Primer Nombre", GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		secondNameItem = new GHATextItem("Segundo Nombre", GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		lastNameItem = new GHATextItem("Apellido", GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		secondLastNameItem = new GHATextItem("Segundo Apellido", GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		
		typeidSelectItem = new GHASelectItem("Tipo ID", GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		idItem = new GHATextItem("No. Identificiación", GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		genderSelectItem = new GHASelectItem("Género", GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		nationalitySelectItem = new GHASelectItem("Nacionalidad", GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		birthDateItem = new GHADateItem("Fecha de Nac.", GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);

		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	public UserAddForm() {
		super(2);
		setHeight(GHAUiHelper.getBottomSectionHeight());
		setTop(240);

		Label title = new Label("<h3>Nuevo Usuario</h3>");
		title.setHeight(30);
		title.setWidth100();
		title.setStyleName("title-label");
		addMember(title);

		final DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(4);
		form.setItems(usernameItem, passwordItem, blockedItem,new GHASpacerItem(),
				  	  firstNameItem,secondNameItem,lastNameItem,secondLastNameItem,
				  	  typeidSelectItem,idItem,new GHASpacerItem(2),
				  	  genderSelectItem,nationalitySelectItem,birthDateItem);

		VLayout sideButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/save.png", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						save();
					}
				}), new GHAImgButton("../resources/icons/cancel.png",
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						cancel();
					}
				}));

		HLayout gridPanel = new HLayout();
		gridPanel.addMembers(form, new LayoutSpacer(), sideButtons);
		addMember(gridPanel);
		
		fillExtras();
	}

	// protected void undo() {
	// select(this.orginalEiaType);
	// save();
	// }

	protected void cancel() {
		hide();
		usernameItem.clearValue();
		passwordItem.clearValue();
		blockedItem.clearValue();
		firstNameItem.clearValue();
		secondNameItem.clearValue();
		lastNameItem.clearValue();
		secondLastNameItem.clearValue();
		typeidSelectItem.clearValue();
		idItem.clearValue();
		genderSelectItem.clearValue();
		nationalitySelectItem.clearValue();
		birthDateItem.clearValue();
	}

	private void fillExtras() {
		typeidSelectItem.setValueMap(DocumentTypeEnum.toValueMap());
//		genderSelectItem.setValueMap(GenderTypeEnum.toValueMap());
//		nationalitySelectItem.setValueMap(NationalityEnum.toValueMap());
	}

	


	private void save() {
//		final EiaType eiaType = new EiaType();
//		if (brandItem.getValue() != null) {
//			if (brandItem.getValueAsString().matches("[1-9]+\\d*")) {
//				eiaType.setBrand(new Brand(Integer.valueOf(brandItem
//						.getValueAsString()), null));
//			} else {
//				eiaType.setBrand(new Brand(brandItem.getValueAsString()));
//			}
//		}
//		if (manItem.getValue() != null) {
//			if (manItem.getValueAsString().matches("[1-9]+\\d*")) {
//				eiaType.setManufacturer(new Manufacturer(Integer
//						.valueOf(manItem.getValueAsString()), null));
//			} else {
//				eiaType.setManufacturer(new Manufacturer(manItem
//						.getValueAsString()));
//			}
//		}
//		eiaType.setCode(codeItem.getValueAsString());
//		eiaType.setName(nameItem.getValueAsString());
//		eiaType.setDescription(descriptionItem.getValueAsString());
//		eiaType.setModel(modelItem.getValueAsString());
//		eiaType.setUseDescription(useDescriptionItem.getValueAsString());
//		eiaType.setEiaUmdns(eiaUmdnsItem.getValueAsString());
//		if (mobilityItem.getValue() != null)
//			eiaType.setMobility(EiaMobilityEnum.valueOf(mobilityItem
//					.getValueAsString()));
//		if (typeItem.getValue() != null)
//			eiaType.setType(EiaTypeEnum.valueOf(typeItem.getValueAsString()));
//		if (subTypeItem.getValue() != null)
//			eiaType.setSubtype(EiaSubTypeEnum.valueOf(subTypeItem
//					.getValueAsString()));
//
//		Set<ConstraintViolation<EiaType>> violations = validator
//				.validate(eiaType);
//		if (violations.isEmpty())
//			EIATypeModel.save(eiaType, new GHAAsyncCallback<EiaType>() {
//
//				@Override
//				public void onSuccess(EiaType result) {
//					select(result);
//					cancel();
//				}
//			});
//		else
//			GHANotification.alert(violations.iterator().next().getMessage());
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getBottomSectionHeight());
	}

	@Override
	public void close() {
		destroy();
	}
}
