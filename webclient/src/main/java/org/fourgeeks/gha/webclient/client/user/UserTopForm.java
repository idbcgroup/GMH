package org.fourgeeks.gha.webclient.client.user;

import java.util.List;

import org.fourgeeks.gha.domain.enu.GenderTypeEnum;
import org.fourgeeks.gha.domain.enu.UserLogonStatusEnum;
import org.fourgeeks.gha.domain.ess.BpuFunction;
import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHAUserNameItem;
import org.fourgeeks.gha.webclient.client.UI.icons.GHADeleteButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASearchButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.events.KeyUpEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyUpHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class UserTopForm extends HLayout implements GHAClosable, ResizeHandler,
		GHAHideable, UserSelectionListener {

	private List<BpuFunction> permissions;
	private UserTab userTab;
	private VLayout sideButtons;
	private GHAImgButton deleteImgButton, searchImgButton;
	private GHATextItem usernameItem, typeidSelectItem;
	private GHASelectItem stateItem;
	private GHATextItem firstNameItem, secondNameItem, firstLastNameItem,
			secondLastNameItem, emailItem, alterEmailItem, idItem;
	private GHASelectItem genderSelectItem;
	private UserResultSet resultSet;
	private boolean activated = false;
	private KeyUpHandler searchKeyUpHandler = new KeyUpHandler() {
		@Override
		public void onKeyUp(KeyUpEvent event) {
			if (event.getKeyName().equals("Enter")) {
				search();
			}
		}
	};

	{
		usernameItem = new GHAUserNameItem(300);
		usernameItem.setColSpan(2);
		firstNameItem = new GHATextItem("Primer nombre", 150);
		firstNameItem.setLength(20);
		firstNameItem.setMask(">A<AAAAAAAAAAAAAAAAAAA");
		stateItem = new GHASelectItem(GHAStrings.get("state"), 150);
		stateItem.setValueMap(UserLogonStatusEnum.toValueMap());
		typeidSelectItem = new GHATextItem(GHAStrings.get("id-type"), 150);
		idItem = new GHATextItem(GHAStrings.get("id-number"), 150);
		secondNameItem = new GHATextItem("Segundo nombre", 150);
		secondNameItem.setLength(20);
		secondNameItem.setMask(">A<AAAAAAAAAAAAAAAAAAA");
		firstLastNameItem = new GHATextItem("Primer apellido", 150);
		firstLastNameItem.setLength(20);
		firstLastNameItem.setMask(">A<AAAAAAAAAAAAAAAAAAA");
		secondLastNameItem = new GHATextItem("Segundo apellido", 150);
		secondLastNameItem.setLength(20);
		secondLastNameItem.setMask(">A<AAAAAAAAAAAAAAAAAAA");
		emailItem = new GHATextItem("Correo", 150);
		emailItem.setLength(254);
		alterEmailItem = new GHATextItem("Correo alternativo", 150);
		alterEmailItem.setLength(254);
		idItem = new GHATextItem("No. Identificación", 150);
		idItem.setLength(20);
		idItem.setMask("####################");
		genderSelectItem = new GHASelectItem("Género", 150);
		genderSelectItem.setValueMap(GenderTypeEnum.toValueMap());
		usernameItem.addKeyUpHandler(searchKeyUpHandler);
		firstNameItem.addKeyUpHandler(searchKeyUpHandler);
		secondNameItem.addKeyUpHandler(searchKeyUpHandler);
		firstLastNameItem.addKeyUpHandler(searchKeyUpHandler);
		secondLastNameItem.addKeyUpHandler(searchKeyUpHandler);
		emailItem.addKeyUpHandler(searchKeyUpHandler);
		genderSelectItem.addKeyUpHandler(searchKeyUpHandler);
		alterEmailItem.addKeyUpHandler(searchKeyUpHandler);
	}

	/**
	 * @param userTab
	 * @param tab
	 */
	public UserTopForm(UserResultSet resultSet, UserTab userTab) {
		super();
		this.resultSet = resultSet;
		this.userTab = userTab;

		GHAUiHelper.addGHAResizeHandler(this);
		setStyleName("sides-padding padding-top");
		setWidth100();
		setHeight(GHAUiHelper.DEFAULT_INNER_TOP_SECTION_HEIGHT + "px");
		setDefaultLayoutAlign(VerticalAlignment.CENTER);
		setBackgroundColor(GHAUiHelper.DEFAULT_BACKGROUND_COLOR);

		DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(6);
		form.setItems(usernameItem, firstNameItem, stateItem, secondNameItem,
				firstLastNameItem, secondLastNameItem, typeidSelectItem,
				idItem, emailItem, alterEmailItem, genderSelectItem);

		deleteImgButton = new GHADeleteButton(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				delete();
			}
		});
		searchImgButton = new GHASearchButton(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				search();
			}
		});

		sideButtons = GHAUiHelper.createBar(searchImgButton);
		addMembers(form, new LayoutSpacer(), sideButtons);
		deactivate();
	}

	public void activate() {
		usernameItem.enable();
		stateItem.enable();
		typeidSelectItem.enable();
		idItem.enable();
		firstNameItem.enable();
		secondNameItem.enable();
		firstLastNameItem.enable();
		secondLastNameItem.enable();
		emailItem.enable();
		alterEmailItem.enable();
		genderSelectItem.enable();
		sideButtons.removeMember(deleteImgButton);
		sideButtons.addMember(searchImgButton, 0);
		activated = true;
	}

	@Override
	public boolean canBeClosen() {
		return true;
	}

	@Override
	public boolean canBeHidden() {
		return true;
	}

	private void clearFields() {

	}

	@Override
	public void close() {
		destroy();
	}

	public void deactivate() {
		usernameItem.disable();
		stateItem.disable();
		typeidSelectItem.disable();
		idItem.disable();
		firstNameItem.disable();
		secondNameItem.disable();
		firstLastNameItem.disable();
		secondLastNameItem.disable();
		emailItem.disable();
		alterEmailItem.disable();
		genderSelectItem.disable();
		sideButtons.removeMember(searchImgButton);
		sideButtons.addMember(deleteImgButton, 0);
		activated = false;
	}

	private void delete() {
		GHANotification.confirm(GHAStrings.get("user"),
				GHAStrings.get("eia-delete-confirm"), new BooleanCallback() {
					@Override
					public void execute(Boolean value) {
						if (value) {
							// UserModel.delete(permissions,
							// new GHAAsyncCallback<Void>() {
							// @Override
							// public void onSuccess(Void result) {
							// userTab.search();
							// clearFields();
							// GHANotification
							// .alert("eia-delete-success");
							// }
							// });
						}
					}
				});
	}

	public boolean isActivate() {
		return activated;
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.DEFAULT_INNER_TOP_SECTION_HEIGHT + "px");
	}

	/**
	 * 
	 */
	public void search() {
		SSOUser ssoUser = new SSOUser();
		if (usernameItem.getValue() != null)
			ssoUser.setUserName(usernameItem.getValueAsString());

		Citizen citizen = new Citizen();
		if (firstNameItem.getValue() != null)
			citizen.setFirstName(firstNameItem.getValueAsString());
		if (secondNameItem.getValue() != null)
			citizen.setSecondName(secondNameItem.getValueAsString());
		if (firstLastNameItem.getValue() != null)
			citizen.setFirstLastName(firstLastNameItem.getValueAsString());
		if (secondLastNameItem.getValue() != null)
			citizen.setSecondLastName(secondLastNameItem.getValueAsString());
		if (idItem.getValue() != null)
			citizen.setIdNumber(idItem.getValueAsString());
		if (genderSelectItem.getValue() != null)
			citizen.setGender(GenderTypeEnum.valueOf(genderSelectItem
					.getValueAsString()));
		if (emailItem.getValue() != null)
			citizen.setPrimaryEmail(emailItem.getValueAsString());
		if (alterEmailItem.getValue() != null)
			citizen.setAlternativeEmail(alterEmailItem.getValueAsString());

		Bpu bpu = new Bpu();
		bpu.setCitizen(citizen);
		ssoUser.setBpu(bpu);
		search(ssoUser);
	}

	private void search(final SSOUser ssoU) {
		UserModel.find(ssoU, new GHAAsyncCallback<List<SSOUser>>() {

			@Override
			public void onSuccess(List<SSOUser> ssoUsers) {
				resultSet.setRecords(ssoUsers);
			}
		});
	}

	@Override
	public void select(SSOUser ssoUser) {
		// TODO seleccionar identificador

		if (ssoUser.getUserName() != null)
			usernameItem.setValue(ssoUser.getUserName());

		if (ssoUser.getUserLogonStatus() != null)
			stateItem.setValue(ssoUser.getUserLogonStatus().name());

		if (ssoUser.getBpu() != null) {
			Bpu bpu = ssoUser.getBpu();

			if (bpu.getCitizen() != null) {
				Citizen citizen = bpu.getCitizen();
				if (citizen.getFirstName() != null)
					firstNameItem.setValue(citizen.getFirstName());
				if (citizen.getIdType() != null)
					typeidSelectItem.setValue(citizen.getIdType().name());
				if (citizen.getIdNumber() != null)
					idItem.setValue(citizen.getIdNumber());
				if (citizen.getSecondName() != null)
					secondNameItem.setValue(citizen.getSecondName());
				if (citizen.getFirstLastName() != null)
					firstLastNameItem.setValue(citizen.getFirstLastName());
				if (citizen.getSecondLastName() != null)
					secondLastNameItem.setValue(citizen.getSecondLastName());
				if (citizen.getPrimaryEmail() != null)
					emailItem.setValue(citizen.getPrimaryEmail());
				if (citizen.getAlternativeEmail() != null)
					alterEmailItem.setValue(citizen.getAlternativeEmail());
				if (citizen.getGender() != null)
					genderSelectItem.setValue(citizen.getGender().name());

			}
		}
		deactivate();
	}
}