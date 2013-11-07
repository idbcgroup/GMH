package org.fourgeeks.gha.webclient.client.user.information;

import java.util.LinkedList;
import java.util.List;

import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.user.UserForm;
import org.fourgeeks.gha.webclient.client.user.UserSelectionListener;
import org.fourgeeks.gha.webclient.client.user.UserSelectionProducer;
import org.fourgeeks.gha.webclient.client.user.UserTab;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class UserInformationFormPanel extends VLayout implements ClosableListener,
		HideableListener, UserSelectionProducer, UserSelectionListener {

	private UserForm userForm;
	private List<UserSelectionListener> listeners;

	private SSOUser originalSSOUser;

	{
		userForm = new UserForm();
		listeners = new LinkedList<UserSelectionListener>();
		originalSSOUser = null;
	}

	/**
	 * @param tab
	 */
	public UserInformationFormPanel(UserTab tab) {
		activateForm(false);
		tab.addGHAClosableHandler(this);

		setWidth100();
		setBackgroundColor("#E0E0E0");
		setStyleName("sides-padding padding-top");// Esto es VUDU!
		setAlign(Alignment.CENTER);

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
		gridPanel.addMembers(userForm, sideButtons);

		addMember(gridPanel);

		// register as user selected listener from userForm
		userForm.addUserSelectionListener(this);
	}

	public void activateForm(boolean activate) {
		userForm.activateForm(activate);
	}

	protected void undo() {
		select(this.originalSSOUser);
		// save();
	}

	private void save() {
		userForm.update();
	}

	@Override
	public void close() {

	}

	@Override
	public void hide() {

	}

	public void setSSOUser(SSOUser ssoUser) {
		this.originalSSOUser = ssoUser;
		userForm.setSSOUser(ssoUser);
		userForm.activateForm(true);
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
	public boolean canBeHidden() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canBeClosen() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void notifyUser(SSOUser ssoUser) {
		for (UserSelectionListener listener : listeners)
			listener.select(ssoUser);
	}
}
