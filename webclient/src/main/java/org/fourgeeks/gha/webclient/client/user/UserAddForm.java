package org.fourgeeks.gha.webclient.client.user;

import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHAAlertManager;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACancelButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASaveButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAAddForm;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret, emiliot
 * 
 */
public class UserAddForm extends GHAAddForm<SSOUser> implements
		UserSelectionProducer {
	{
		form = new UserForm();
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
		gridPanel.addMembers(form, new LayoutSpacer(), sideButtons);
		addMember(gridPanel);

	}

	@Override
	public void addUserSelectionListener(
			UserSelectionListener userSelectionListener) {
		((UserSelectionProducer) form)
				.addUserSelectionListener(userSelectionListener);
	}

	@Override
	public boolean canBeClosen(HideCloseAction hideAction) {
		return true;
	}

	@Override
	public boolean canBeHidden(HideCloseAction hideAction) {
		return true;
	}

	@Override
	public void notifyUser(SSOUser ssoUser) {
		return;
	}

	@Override
	public void removeUserSelectionListener(
			UserSelectionListener userSelectionListener) {
		((UserSelectionProducer) form)
				.removeUserSelectionListener(userSelectionListener);
	}

	@Override
	protected void save() {
		form.save(new GHAAsyncCallback<SSOUser>() {

			@Override
			public void onSuccess(SSOUser result) {
				GHAAlertManager.alert("user-save-success");
				hide();
			}
		});
	}

}
