package org.fourgeeks.gha.webclient.client.res.citizen.body.personalinformation.basicinformation;

import org.fourgeeks.gha.domain.enu.DocumentTypeEnum;
import org.fourgeeks.gha.domain.enu.GenderTypeEnum;
import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHADateItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.dateitems.GHABirthDateItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHABpiSelectItem;
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

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author jfuentes
 *
 */
public class RESCitizenBasicInformationForm extends GHAForm<Citizen> {

	private GHATextItem idItem, firstNameItem, secondNameItem, lastNameItem,
	secondLastNameItem,ageTextItem;
	private GHASelectItem typeidSelectItem, genderSelectItem,bpiSelectItem;
	private GHADateItem birthDateItem;
	private GHAEmailTextItem primaryEmailItem;

	private GHADynamicForm form;

	{
		typeidSelectItem = new GHADoumentTypeSelectItem();
		typeidSelectItem.setColSpan(2);
		idItem = new GHATextItem(GHAStrings.get("user-identification"));
		idItem.setColSpan(2);
		genderSelectItem = new GHAGenderSelectItem();
		firstNameItem = new GHANameTextItem(GHAStrings.get("first-name"));
		secondNameItem = new GHANameTextItem(GHAStrings.get("second-name"));
		lastNameItem = new GHANameTextItem(GHAStrings.get("first-lastname"));
		lastNameItem.setTooltip(GHAStrings.get("user-tooltip-last-name"));
		secondLastNameItem = new GHANameTextItem(
				GHAStrings.get("second-lastname"));
		primaryEmailItem = new GHAEmailTextItem(GHAStrings.get("user-email-primary"));

		bpiSelectItem = new GHABpiSelectItem();
		birthDateItem = new GHABirthDateItem();
		birthDateItem.setColSpan(2);
		ageTextItem = new GHAAgeTextItem(GHAStrings.get("N/A"));


		typeidSelectItem.setValueMap(DocumentTypeEnum.toValueMap());
		genderSelectItem.setValueMap(GenderTypeEnum.toValueMap());

		form = new GHADynamicForm(5, FormType.SECTIONFORM_MINIFORM);
	}
	/**
	 * 
	 */
	public RESCitizenBasicInformationForm() {
		super();
		GHAUiHelper.addGHAResizeHandler(this);
		form.setItems(typeidSelectItem, idItem, new GHASpacerItem(),
				genderSelectItem, firstNameItem, secondNameItem, lastNameItem, secondLastNameItem,
				birthDateItem, ageTextItem,primaryEmailItem, bpiSelectItem);
		form.setAutoFocus(true);

		final HLayout formLayout = new HLayout();
		formLayout.setPadding(10);
		//		formLayout.setHeight(GHAUiHelper.DEFAULT_INNER_TOP_SECTION_HEIGHT+ "px");
		formLayout.setDefaultLayoutAlign(VerticalAlignment.CENTER);
		formLayout.addMembers(form, getRightPhotoLayout(), new LayoutSpacer());
		addMember(formLayout);
	}

	// Right Side Layout
	private HLayout getRightPhotoLayout() {
		final HLayout rightSideLayout = new HLayout();
		rightSideLayout.setWidth(170);
		rightSideLayout.setLayoutMargin(5);
		rightSideLayout.setMembersMargin(5);

		final VLayout alertLayout = new VLayout();
		setDefaultLayoutAlign(Alignment.CENTER);
		final GHAAlertLabel citizenAlertLabel = new GHAAlertLabel(GHAStrings.get("citizen"),"#D9D9D9");
		citizenAlertLabel.setBorder("1px solid #A9A9A9");
		alertLayout.addMember(citizenAlertLabel);

		rightSideLayout.addMembers(new GHAImg("../resources/img/photo.jpg", 75,
				75), alertLayout);

		return rightSideLayout;
	}

	@Override
	public void onResize(ResizeEvent arg0) {
		form.resize();
	}

	@Override
	public void activate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void save(GHAAsyncCallback<Citizen> callback) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(GHAAsyncCallback<Citizen> callback) {
		// TODO Auto-generated method stub

	}

	@Override
	public void set(Citizen entity) {
		// TODO Auto-generated method stub

	}

}
