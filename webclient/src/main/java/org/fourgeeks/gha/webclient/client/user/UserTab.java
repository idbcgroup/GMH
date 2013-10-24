package org.fourgeeks.gha.webclient.client.user;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATab;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATabHeader;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

/**
 * @author alacret
 * 
 */
public class UserTab extends GHATab implements UserSelectionListener,
		UserSelectionProducer {

	/**
	 * 
	 */
	public static final String ID = "user";
	private static final String TITLE = GHAStrings.get("users");
	private UserTopForm topForm;
	private UserInternalTabset internalTabset;
	private List<UserSelectionListener> listeners = new ArrayList<UserSelectionListener>();
	private UserResultSet resultSet;;

	/**
	 * @param token
	 * 
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
		addGHAHideableHandler(resultSet);
		addGHAClosableHandler(resultSet);
		resultSet.addUserSelectionListener(this);

		topForm = new UserTopForm(resultSet);
		// internalTabset = new UserInternalTabset(this);

		verticalPanel.addMember(topForm);
		verticalPanel.addMember(GHAUiHelper
				.verticalGraySeparator(GHAUiHelper.V_SEPARATOR_HEIGHT + "px"));
		// verticalPanel.addMember(internalTabset);
		addMember(verticalPanel);

	}

	protected void search() {
		// if (topForm.isActivate())
		// return;
		// if (internalTabSet.isVisible())
		// if (internalTabSet.canBeHidden())
		// internalTabSet.hide();
		// else
		// return;
		// if (addForm.isVisible())
		// addForm.hide();
		// if (resultSet.isVisible())
		// resultSet.hide();
		// topForm.activate();
		// // GHANotification.info(GHAStrings.get("")); //TODO: Mensaje de
		// // informacion para indicar que se ha actividado el modo de busqueda
	}

	protected void add() {
		// if (addForm.isVisible())
		// return;
		// if (internalTabSet.isVisible())
		// if (internalTabSet.canBeHidden())
		// internalTabSet.hide();
		// else
		// return;
		// if (topForm.isActivate())
		// topForm.deactivate();
		// if (resultSet.isVisible())
		// resultSet.hide();
		// addForm.open();
	}

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public void addUserSelectionListener(
			UserSelectionListener userSelectionListener) {
		listeners.add(userSelectionListener);

	}

	@Override
	public void removeUserSelectionListener(
			UserSelectionListener userSelectionListener) {
		listeners.remove(userSelectionListener);

	}

	@Override
	public void select(SSOUser ssoUser) {
		for (UserSelectionListener listener : listeners)
			listener.select(ssoUser);
	}

	@Override
	public void notifyUser(SSOUser ssoUser) {
		for (UserSelectionListener userSelectionListener : listeners)
			userSelectionListener.select(ssoUser);

	}
}