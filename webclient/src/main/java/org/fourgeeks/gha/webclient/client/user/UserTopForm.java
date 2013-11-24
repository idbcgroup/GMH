package org.fourgeeks.gha.webclient.client.user;

import java.util.List;

import org.fourgeeks.gha.domain.enu.DocumentTypeEnum;
import org.fourgeeks.gha.domain.enu.GenderTypeEnum;
import org.fourgeeks.gha.domain.enu.UserLogonStatusEnum;
import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHATopForm;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHADoumentTypeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHAEmailItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHAGenreSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHANameItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHAUserNameItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHAUserStateSelectItem;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACleanButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHADeleteButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASearchButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.user.client.Window;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class UserTopForm extends GHATopForm<UserResultSet, SSOUser> implements
		UserSelectionListener {

	private SSOUser selectedUser;
	private final VLayout sideButtons;
	private GHATextItem usernameItem;
	private GHADoumentTypeSelectItem idTypeSelectItem;
	private GHASelectItem stateItem;
	private GHANameItem firstNameItem, secondNameItem, firstLastNameItem,
			secondLastNameItem;
	private GHATextItem idItem;
	private GHAEmailItem emailItem;
	private GHASelectItem genderSelectItem;
	private final GHASearchButton searchButton;
	private final GHADeleteButton deleteButton;
	private final GHACleanButton cleanButton;
	private GHADynamicForm form;
	{
		usernameItem = new GHAUserNameItem();
		stateItem = new GHAUserStateSelectItem();
		firstNameItem = new GHANameItem(GHAStrings.get("first-name"));
		secondNameItem = new GHANameItem("Segundo Nombre");
		firstLastNameItem = new GHANameItem(GHAStrings.get("first-lastname"));
		secondLastNameItem = new GHANameItem(GHAStrings.get("second-lastname"));
		emailItem = new GHAEmailItem(GHAStrings.get("mail"));
		idTypeSelectItem = new GHADoumentTypeSelectItem();
		idItem = new GHATextItem(GHAStrings.get("id-number"));
		idItem.setLength(20);
		idItem.setMask("####################");
		genderSelectItem = new GHAGenreSelectItem();

		usernameItem.addKeyUpHandler(searchKeyUpHandler);
		firstNameItem.addKeyUpHandler(searchKeyUpHandler);
		secondNameItem.addKeyUpHandler(searchKeyUpHandler);
		firstLastNameItem.addKeyUpHandler(searchKeyUpHandler);
		secondLastNameItem.addKeyUpHandler(searchKeyUpHandler);
		emailItem.addKeyUpHandler(searchKeyUpHandler);
		genderSelectItem.addKeyUpHandler(searchKeyUpHandler);

		form = new GHADynamicForm(GHAUiHelper.getNormalFormWidth(30), 5);
	}

	/**
	 * @param resultSet
	 * @param userTab
	 */
	public UserTopForm(UserResultSet resultSet, UserTab userTab) {
		super(resultSet, userTab);

		form.setItems(usernameItem, firstNameItem, secondNameItem,
				firstLastNameItem, secondLastNameItem, idTypeSelectItem,
				idItem, emailItem, genderSelectItem, stateItem);

		deleteButton = new GHADeleteButton(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				delete();
			}
		});
		searchButton = new GHASearchButton(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				search();
			}
		});
		cleanButton = new GHACleanButton(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				clear();
			}
		});

		sideButtons = GHAUiHelper.createBar(searchButton, cleanButton);
		addMembers(form, new LayoutSpacer(), sideButtons);
	}

	@Override
	public void search() {
		super.search();
		SSOUser ssoUser = new SSOUser();
		if (usernameItem.getValue() != null)
			ssoUser.setUserName(usernameItem.getValueAsString());
		if (stateItem.getValue() != null)
			ssoUser.setUserLogonStatus(UserLogonStatusEnum.valueOf(stateItem
					.getValueAsString()));

		Citizen citizen = new Citizen();
		if (firstNameItem.getValue() != null)
			citizen.setFirstName(firstNameItem.getValueAsString());
		if (secondNameItem.getValue() != null)
			citizen.setSecondName(secondNameItem.getValueAsString());
		if (firstLastNameItem.getValue() != null)
			citizen.setFirstLastName(firstLastNameItem.getValueAsString());
		if (secondLastNameItem.getValue() != null)
			citizen.setSecondLastName(secondLastNameItem.getValueAsString());
		if (idTypeSelectItem.getValue() != null) {
			citizen.setIdType(DocumentTypeEnum.valueOf(idTypeSelectItem
					.getValueAsString()));
		}
		if (idItem.getValue() != null)
			citizen.setIdNumber(idItem.getValueAsString());
		if (genderSelectItem.getValue() != null)
			citizen.setGender(GenderTypeEnum.valueOf(genderSelectItem
					.getValueAsString()));
		if (emailItem.getValue() != null)
			citizen.setPrimaryEmail(emailItem.getValueAsString());

		Bpu bpu = new Bpu();
		bpu.setCitizen(citizen);
		ssoUser.setBpu(bpu);
		search(ssoUser);
	}

	@Override
	public void search(final SSOUser ssoU) {
		super.search();
		UserModel.find(ssoU, new GHAAsyncCallback<List<SSOUser>>() {

			@Override
			public void onSuccess(List<SSOUser> ssoUsers) {
				resultSet.setRecords(ssoUsers, true);
			}
		});
	}

	@Override
	public void select(SSOUser ssoUser) {
		selectedUser = ssoUser;

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
				if (citizen.getSecondName() != null)
					secondNameItem.setValue(citizen.getSecondName());
				if (citizen.getIdType() != null)
					idTypeSelectItem.setValue(citizen.getIdType().name());
				if (citizen.getIdNumber() != null)
					idItem.setValue(citizen.getIdNumber());
				if (citizen.getFirstLastName() != null)
					firstLastNameItem.setValue(citizen.getFirstLastName());
				if (citizen.getSecondLastName() != null)
					secondLastNameItem.setValue(citizen.getSecondLastName());
				if (citizen.getPrimaryEmail() != null)
					emailItem.setValue(citizen.getPrimaryEmail());
				if (citizen.getGender() != null)
					genderSelectItem.setValue(citizen.getGender().name());

			}
		}
		deactivate();
	}

	private void toggleForm(boolean disabled) {
		usernameItem.setDisabled(disabled);
		stateItem.setDisabled(disabled);
		idTypeSelectItem.setDisabled(disabled);
		idItem.setDisabled(disabled);
		firstNameItem.setDisabled(disabled);
		secondNameItem.setDisabled(disabled);
		firstLastNameItem.setDisabled(disabled);
		secondLastNameItem.setDisabled(disabled);
		emailItem.setDisabled(disabled);
		genderSelectItem.setDisabled(disabled);
		cleanButton.setDisabled(disabled);
		activated = !disabled;
		if (disabled) {
			sideButtons.removeMember(searchButton);
			sideButtons.addMember(deleteButton, 0);
		} else {
			sideButtons.removeMember(deleteButton);
			sideButtons.addMember(searchButton, 0);
		}
		activated = !disabled;
	}

	@Override
	public void deactivate() {
		toggleForm(true);
	}

	@Override
	public void activate() {
		toggleForm(false);
		usernameItem.focusInItem();
	}

	@Override
	public void clear() {
		usernameItem.clearValue();
		stateItem.clearValue();
		idTypeSelectItem.clearValue();
		idItem.clearValue();
		firstNameItem.clearValue();
		secondNameItem.clearValue();
		firstLastNameItem.clearValue();
		secondLastNameItem.clearValue();
		emailItem.clearValue();
		genderSelectItem.clearValue();
		searchButton.setDisabled(false);
		cleanButton.setDisabled(false);
		activated = true;
		selectedUser = null;
	}

	@Override
	protected void delete() {
		Window.alert("delete");
		GHANotification.confirm(GHAStrings.get("user"),
				GHAStrings.get("ssoUser-delete-confirm"),
				new BooleanCallback() {
					@Override
					public void execute(Boolean value) {
						if (value) {
							Window.alert((selectedUser == null) + "");
							UserModel.delete(selectedUser.getId(),
									new GHAAsyncCallback<Void>() {
										@Override
										public void onSuccess(Void result) {
											containerTab.search();
											clear();
											GHANotification
													.alert("ssoUser-delete-success");
										}
									});
						}
					}
				});
	}

	@Override
	public void onResize(ResizeEvent event) {
		super.onResize(event);
		form.resize(GHAUiHelper.getNormalFormWidth(30), 5);
	}
}