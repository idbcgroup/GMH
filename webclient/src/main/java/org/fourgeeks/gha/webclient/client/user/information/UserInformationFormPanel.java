package org.fourgeeks.gha.webclient.client.user.information;

import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASaveButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAUndoButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.places.GHAPlaceSet;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAVerticalLayout;
import org.fourgeeks.gha.webclient.client.user.UserForm;
import org.fourgeeks.gha.webclient.client.user.UserSelectionListener;
import org.fourgeeks.gha.webclient.client.user.UserSelectionProducer;

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

	private final UserForm form = new UserForm();

	public UserInformationFormPanel() {
		super();
		VLayout sideButtons = GHAUiHelper.createBar(new GHASaveButton(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						save();
					}
				}), new GHAUndoButton(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				undo();
			}
		}));

		HLayout gridPanel = new HLayout();
		gridPanel.addMembers(form, sideButtons);

		addMember(gridPanel);
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
		form.addUserSelectionListener(userSelectionListener);
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
							GHAPlaceSet.closeCurrentPlace(HideCloseAction.SAVE);

						}
					}, new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {
							GHAPlaceSet
									.closeCurrentPlace(HideCloseAction.DISCARD);

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
							GHAPlaceSet.hideCurrentPlace(HideCloseAction.SAVE);

						}
					}, new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {
							GHAPlaceSet
									.hideCurrentPlace(HideCloseAction.DISCARD);

						}
					}, null);
			return false;
		}
		return true;
	}

	@Override
	public void close() {
		destroy();
	}

	// Producer/Consumer stuff

	@Override
	public void notifyUser(SSOUser ssoUser) {
		return;
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
		form.removeUserSelectionListener(userSelectionListener);
	}

	private void save() {
		form.update(new GHAAsyncCallback<SSOUser>() {

			@Override
			public void onSuccess(SSOUser result) {
				GHANotification.alert("user-save-success");
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.user.UserSelectionListener#select(
	 * org.fourgeeks.gha.domain.ess.SSOUser)
	 */
	@Override
	public void select(SSOUser ssoUser) {
		form.set(ssoUser);
	}

	protected void undo() {
		form.undo();
	}
}
