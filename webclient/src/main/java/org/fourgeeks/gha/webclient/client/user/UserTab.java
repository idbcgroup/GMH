package org.fourgeeks.gha.webclient.client.user;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATab;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATabHeader;

import com.smartgwt.client.types.Visibility;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

/**
 * @author alacret
 * 
 */
public class UserTab extends GHATab implements UserSelectionListener,
		UserSelectionProducer {

	/**
	 * The ID of the Tab
	 */
	public static final String ID = "user";
	private static final String TITLE = GHAStrings.get("users");
	private UserAddForm addForm;
	private UserInternalTabset internalTabSet;
	private List<UserSelectionListener> listeners = new ArrayList<UserSelectionListener>();
	private UserResultSet resultSet;
	private UserTopForm topForm;;

	/**
	 * @param token
	 */
	public UserTab(String token) {
		super(token);
		header = new GHATabHeader(this, TITLE);
		header.addSearchOption(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				search();
			}
		});
		header.addAddOption(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				add();
			}
		});

		resultSet = new UserResultSet();
		resultSet.setVisible(false);
		addGHAHideableHandler(resultSet);
		addGHAClosableHandler(resultSet);
		resultSet.addUserSelectionListener(this);

		topForm = new UserTopForm(resultSet, this);
		topForm.activate();
		addGHAClosableHandler(topForm);
		addGHAClosableHandler(topForm);
		addUserSelectionListener(topForm);

		internalTabSet = new UserInternalTabset(this);
		addGHAHideableHandler(internalTabSet);
		addGHAClosableHandler(internalTabSet);
		addUserSelectionListener(internalTabSet);

		addForm = new UserAddForm(GHAStrings.get("new-user"));
		addGHAHideableHandler(addForm);
		addGHAClosableHandler(addForm);
		addForm.addUserSelectionListener(this);

		verticalPanel.addMember(topForm);
		verticalPanel.addMember(GHAUiHelper
				.verticalGraySeparator(GHAUiHelper.V_SEPARATOR_HEIGHT + "px"));
		verticalPanel.addMember(internalTabSet);
		verticalPanel.addMember(resultSet);

		addMember(verticalPanel);
	}

	protected void add() {
		if (addForm.isVisible())
			return;
		if (internalTabSet.isVisible())
			if (internalTabSet.canBeHidden())
				internalTabSet.hide();
			else
				return;
		if (topForm.isActivated())
			topForm.deactivate();
		if (resultSet.isVisible())
			resultSet.hide();
		addForm.open();
		// GHANotification.info(GHAStrings.get("")); //TODO: Mensaje de
		// informacion para indicar que se ha actividado el modo de busqueda
	}

	@Override
	public void addUserSelectionListener(
			UserSelectionListener userSelectionListener) {
		listeners.add(userSelectionListener);
	}

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public void notifyUser(SSOUser ssoUser) {
		for (UserSelectionListener userSelectionListener : listeners)
			userSelectionListener.select(ssoUser);
	}

	@Override
	public void removeUserSelectionListener(
			UserSelectionListener userSelectionListener) {
		listeners.remove(userSelectionListener);
	}

	protected void search() {
		if (topForm.isActivated())
			return;
		if (internalTabSet.isVisible())
			if (internalTabSet.canBeHidden())
				internalTabSet.hide();
			else
				return;
		if (addForm.isVisible())
			addForm.hide();
		if (resultSet.isVisible())
			resultSet.hide();
		topForm.activate();
		// // GHANotification.info(GHAStrings.get("")); //TODO: Mensaje de
		// // informacion para indicar que se ha actividado el modo de busqueda
	}

	@Override
	public void select(SSOUser ssoUser) {
		notifyUser(ssoUser);
	}

	@Override
	public void show() {
		super.show();
		topForm.setVisibility(Visibility.VISIBLE);
	}
}