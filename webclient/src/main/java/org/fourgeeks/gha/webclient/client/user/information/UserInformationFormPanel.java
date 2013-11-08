package org.fourgeeks.gha.webclient.client.user.information;

import java.util.LinkedList;
import java.util.List;

import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAVerticalLayout;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATabSet;
import org.fourgeeks.gha.webclient.client.user.UserForm;
import org.fourgeeks.gha.webclient.client.user.UserSelectionListener;
import org.fourgeeks.gha.webclient.client.user.UserSelectionProducer;
import org.fourgeeks.gha.webclient.client.user.UserTab;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class UserInformationFormPanel extends GHAVerticalLayout implements
		ClosableListener, HideableListener, UserSelectionProducer,
		UserSelectionListener {

	private UserForm form;
	private List<UserSelectionListener> listeners;

	private SSOUser originalSSOUser;

	{
		form = new UserForm();
		listeners = new LinkedList<UserSelectionListener>();
		originalSSOUser = null;
	}

	/**
	 * @param tab
	 */
	public UserInformationFormPanel(UserTab tab) {
		activateForm(false);
		tab.addClosableHandler(this);

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
		gridPanel.addMembers(form, sideButtons);

		addMember(gridPanel);

		// register as user selected listener from userForm
		form.addUserSelectionListener(this);
	}

	public void activateForm(boolean activate) {
		form.activate();
	}

	protected void undo() {
		select(this.originalSSOUser);
		// save();
	}

	private void save() {
		form.update();
	}

	@Override
	public void close() {

	}

	@Override
	public void hide() {

	}

	public void setSSOUser(SSOUser ssoUser) {
		this.originalSSOUser = ssoUser;
		form.setSSOUser(ssoUser);
		form.activate();
	}

	// Producer/Consumer stuff

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.user.UserSelectionListener#select(
	 * org.fourgeeks.gha.domain.ess.SSOUser)
	 */
	@Override
	public void select(SSOUser ssoUser) {
		notifyUser(ssoUser);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.user.UserSelectionProducer#
	 * addUserSelectionListener
	 * (org.fourgeeks.gha.webclient.client.user.UserSelectionListener)
	 */
	@Override
	public void addUserSelectionListener(
			UserSelectionListener userSelectionListener) {
		listeners.add(userSelectionListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.user.UserSelectionProducer#
	 * removeUserSelectionListener
	 * (org.fourgeeks.gha.webclient.client.user.UserSelectionListener)
	 */
	@Override
	public void removeUserSelectionListener(
			UserSelectionListener userSelectionListener) {
		listeners.remove(userSelectionListener);
	}

	@Override
	public boolean canBeClosen(HideCloseAction hideAction) {
		if (hideAction.equals(HideCloseAction.DISCARD))
			return true;

		if (form.hasUnCommittedChanges()) {
			if (hideAction.equals(HideCloseAction.SAVE)) {
				form.update();
				return true;
			}

			GHANotification.askYesNoCancel(GHAStrings.get("information"),
					GHAStrings.get("unsaved-changes"), new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {
							GHATabSet.closeCurrentTab(HideCloseAction.SAVE);

						}
					}, new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {
							GHATabSet.closeCurrentTab(HideCloseAction.DISCARD);

						}
					}, null);
			return false;
		}
		return true;
	}

	@Override
	public boolean canBeHidden(HideCloseAction hideAction) {
		if (hideAction.equals(HideCloseAction.DISCARD))
			return true;

		if (form.hasUnCommittedChanges()) {
			if (hideAction.equals(HideCloseAction.SAVE)) {
				form.update();
				return true;
			}

			GHANotification.askYesNoCancel(GHAStrings.get("information"),
					GHAStrings.get("unsaved-changes"), new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {
							GHATabSet.hideCurrentTab(HideCloseAction.SAVE);

						}
					}, new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {
							GHATabSet.hideCurrentTab(HideCloseAction.DISCARD);

						}
					}, null);
			return false;
		}
		return true;
	}

	@Override
	public void notifyUser(SSOUser ssoUser) {
		for (UserSelectionListener listener : listeners)
			listener.select(ssoUser);
	}
}
