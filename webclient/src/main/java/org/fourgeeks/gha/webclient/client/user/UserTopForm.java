package org.fourgeeks.gha.webclient.client.user;

import java.util.List;

import org.fourgeeks.gha.domain.enu.GenderTypeEnum;
import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHATopForm;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
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
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;

import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class UserTopForm extends GHATopForm<UserResultSet, SSOUser> implements
		UserSelectionListener {

	private long selectedUserId;
	private UserTab userTab;
	private VLayout sideButtons;
	private GHATextItem usernameItem, typeidSelectItem;
	private GHASelectItem stateItem;
	private GHATextItem firstNameItem, secondNameItem, firstLastNameItem,
			secondLastNameItem, emailItem, alterEmailItem, idItem;
	private GHASelectItem genderSelectItem;
	private GHASearchButton searchButton;
	private GHADeleteButton deleteButton;
	private GHACleanButton cleanButton;
	{
		usernameItem = new GHAUserNameItem(300);
		usernameItem.setColSpan(2);
		firstNameItem = new GHANameItem(GHAStrings.get("first-name"), 150);
		stateItem = new GHAUserStateSelectItem(150);
		typeidSelectItem = new GHATextItem(GHAStrings.get("id-type"), 150);
		idItem = new GHATextItem(GHAStrings.get("id-number"), 150);
		secondNameItem = new GHANameItem(GHAStrings.get("second-name"), 150);
		firstLastNameItem = new GHANameItem(GHAStrings.get("first-lastname"),
				150);
		secondLastNameItem = new GHANameItem(GHAStrings.get("second-lastname"),
				150);
		emailItem = new GHAEmailItem(GHAStrings.get("mail"), 150);
		alterEmailItem = new GHAEmailItem(GHAStrings.get("alternative-mail"),
				150);
		idItem = new GHATextItem(GHAStrings.get("id-number"), 150);
		idItem.setLength(20);
		idItem.setMask("####################");
		genderSelectItem = new GHAGenreSelectItem(150);
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
	 * @param resultSet
	 * @param userTab
	 * @param tab
	 */
	public UserTopForm(UserResultSet resultSet, UserTab userTab) {
		super(resultSet);
		this.userTab = userTab;
		DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(6);
		form.setItems(usernameItem, stateItem, firstNameItem, secondNameItem,
				firstLastNameItem, secondLastNameItem, typeidSelectItem,
				idItem, emailItem, alterEmailItem, genderSelectItem);

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
		deactivate();
	}

	@Override
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

	@Override
	public void search(final SSOUser ssoU) {
		UserModel.find(ssoU, new GHAAsyncCallback<List<SSOUser>>() {

			@Override
			public void onSuccess(List<SSOUser> ssoUsers) {
				resultSet.setRecords(ssoUsers);
			}
		});
	}

	@Override
	public void select(SSOUser ssoUser) {
		selectedUserId = ssoUser.getId();

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

	private void toggleForm(boolean disabled) {
		usernameItem.setDisabled(disabled);
		stateItem.setDisabled(disabled);
		typeidSelectItem.setDisabled(disabled);
		idItem.setDisabled(disabled);
		firstNameItem.setDisabled(disabled);
		secondNameItem.setDisabled(disabled);
		firstLastNameItem.setDisabled(disabled);
		secondLastNameItem.setDisabled(disabled);
		emailItem.setDisabled(disabled);
		alterEmailItem.setDisabled(disabled);
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
	}

	public void deactivate() {
		toggleForm(true);
	}

	public void activate() {
		toggleForm(false);
		usernameItem.focusInItem();
	}

	@Override
	public void clear() {
		usernameItem.clearValue();
		stateItem.clearValue();
		typeidSelectItem.clearValue();
		idItem.clearValue();
		firstNameItem.clearValue();
		secondNameItem.clearValue();
		firstLastNameItem.clearValue();
		secondLastNameItem.clearValue();
		emailItem.clearValue();
		alterEmailItem.clearValue();
		genderSelectItem.clearValue();
		searchButton.setDisabled(false);
		cleanButton.setDisabled(false);
		activated = true;

	}

	private void delete() {
		GHANotification.confirm(GHAStrings.get("user"),
				GHAStrings.get("ssoSser-delete-confirm"),
				new BooleanCallback() {
					@Override
					public void execute(Boolean value) {
						if (value) {
							UserModel.delete(selectedUserId,
									new GHAAsyncCallback<Void>() {
										@Override
										public void onSuccess(Void result) {
											userTab.search();
											clear();
											GHANotification
													.alert("ssoUser-delete-success");
										}
									});
						}
					}
				});
	}
}