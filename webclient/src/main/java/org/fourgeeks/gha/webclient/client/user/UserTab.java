package org.fourgeeks.gha.webclient.client.user;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.TabStatus;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.SearchListener;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATab;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATabHeader;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATabHeader.Option;

import com.smartgwt.client.types.Visibility;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.VisibilityChangedEvent;
import com.smartgwt.client.widgets.events.VisibilityChangedHandler;

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
	private UserTopForm topForm;
	private Option searchOption;
	private Option addOption;

	/**
	 * @param token
	 */
	public UserTab(String token) {
		super(token);
		header = new GHATabHeader(this, TITLE);
		searchOption = header.addSearchOption(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				search();
			}
		});
		addOption = header.addAddOption(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				add();
			}
		});

		resultSet = new UserResultSet();
		resultSet.setVisible(false);
		addHideableListener(resultSet);
		addClosableListener(resultSet);
		resultSet.addUserSelectionListener(this);

		topForm = new UserTopForm(resultSet, this);
		addClosableListener(topForm);
		addClosableListener(topForm);
		addUserSelectionListener(topForm);
		topForm.addSearchListener(new SearchListener() {

			@Override
			public void onSearch() {
				currentStatus = TabStatus.SEARCH_RESULTS;
			}
		});

		internalTabSet = new UserInternalTabset(this);
		addHideableListener(internalTabSet);
		addClosableListener(internalTabSet);
		addUserSelectionListener(internalTabSet);

		addForm = new UserAddForm(GHAStrings.get("new-user"));
		addHideableListener(addForm);
		addClosableListener(addForm);
		addForm.addUserSelectionListener(this);
		addForm.addVisibilityChangedHandler(new VisibilityChangedHandler() {

			@Override
			public void onVisibilityChanged(VisibilityChangedEvent event) {
				if (!event.getIsVisible())
					search();

			}
		});

		verticalPanel.addMember(topForm);
		verticalPanel.addMember(GHAUiHelper
				.verticalGraySeparator(GHAUiHelper.V_SEPARATOR_HEIGHT + "px"));
		verticalPanel.addMember(internalTabSet);
		verticalPanel.addMember(resultSet);

		addMember(verticalPanel);
		search();
	}

	protected void add() {
		if (addForm.isVisible())
			return;
		if (internalTabSet.isVisible())
			if (internalTabSet.canBeHidden(HideCloseAction.SAVE))
				internalTabSet.hide();
			else
				return;
		if (topForm.isActivated()) {
			topForm.deactivate();
			topForm.clear();
		}
		if (resultSet.isVisible())
			resultSet.hide();
		addForm.open();
		header.unMarkAllButtons();
		addOption.markSelected();
		currentStatus = TabStatus.ADD;
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

	public void search() {
		if (topForm.isActivated())
			return;
		if (internalTabSet.isVisible())
			if (internalTabSet.canBeHidden(HideCloseAction.SAVE))
				internalTabSet.hide();
			else
				return;
		if (addForm.isVisible())
			addForm.hide();
		if (resultSet.isVisible())
			resultSet.hide();
		topForm.activate();
		header.unMarkAllButtons();
		searchOption.markSelected();
		currentStatus = TabStatus.SEARCH;
		// // GHANotification.info(GHAStrings.get("")); //TODO: Mensaje de
		// // informacion para indicar que se ha actividado el modo de busqueda
	}

	@Override
	public void select(SSOUser ssoUser) {
		notifyUser(ssoUser);
		currentStatus = TabStatus.ENTITY_SELECTED;
	}

	@Override
	public void show() {
		super.show();
		topForm.setVisibility(Visibility.VISIBLE);
		if (currentStatus.equals(TabStatus.ADD))
			return;
		if (currentStatus.equals(TabStatus.SEARCH))
			return;

		if (currentStatus.equals(TabStatus.ENTITY_SELECTED))
			internalTabSet.show();
		else
			resultSet.show();
	}
}