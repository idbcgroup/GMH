package org.fourgeeks.gha.webclient.client.user;

import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACancelButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASaveButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAAddForm;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret, emiliot
 * 
 */
public class UserAddForm extends GHAAddForm implements UserSelectionProducer {
	private UserForm userForm;
	{
		userForm = new UserForm();
	}

	/**
	 * @param title
	 * 
	 */
	public UserAddForm(String title) {
		super(title);
		VLayout sideButtons = GHAUiHelper.createBar(new GHASaveButton(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						save();
					}
				}), new GHACancelButton(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				cancel();
			}
		}));

		HLayout gridPanel = new HLayout();
		gridPanel.addMembers(userForm, new LayoutSpacer(), sideButtons);
		addMember(gridPanel);

	}

	protected void cancel() {
		userForm.hide();
		super.hide();
	}

	private void save() {
		userForm.save();
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getBottomSectionHeight());
	}

	@Override
	public void close() {
		userForm.destroy();
		destroy();
	}

	@Override
	public void open() {
		animateShow(AnimationEffect.FLY);
	}

	public void show() {
		userForm.show();
		super.show();
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
		userForm.addUserSelectionListener(userSelectionListener);
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
		userForm.removeUserSelectionListener(userSelectionListener);
	}

	// /*
	// * (non-Javadoc)
	// *
	// * @see
	// * org.fourgeeks.gha.webclient.client.user.UserSelectionListener#select(
	// * org.fourgeeks.gha.domain.ess.SSOUser)
	// */
	// @Override
	// public void select(SSOUser ssoUser) {
	// notifyUser(ssoUser);
	// }

	@Override
	public boolean canBeClosen(HideCloseAction hideAction) {
		return false;
	}

	@Override
	public boolean canBeHidden(HideCloseAction hideAction) {
		return false;
	}

	@Override
	public void notifyUser(SSOUser ssoUser) {
		cancel();
	}
}
