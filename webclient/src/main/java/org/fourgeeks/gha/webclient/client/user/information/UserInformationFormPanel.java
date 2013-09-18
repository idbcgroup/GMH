package org.fourgeeks.gha.webclient.client.user.information;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAImgButton;
import org.fourgeeks.gha.webclient.client.user.UserForm;
import org.fourgeeks.gha.webclient.client.user.UserTab;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class UserInformationFormPanel extends VLayout implements GHAClosable,
		GHAHideable {

	private UserTab tab;
	private UserForm userForm;

	{
		userForm = new UserForm();
	}

	/**
	 * @param tab
	 */
	public UserInformationFormPanel(UserTab tab) {
		activateForm(false);
		this.tab = tab;

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
		gridPanel.addMembers(userForm, new LayoutSpacer(), sideButtons);

		addMember(gridPanel);
	}

	public void activateForm(boolean activate) {
		userForm.activateForm(activate);
	}

	protected void undo() {
		// reload the original eiatype
		save();
	}

	private void save() {

	}

	@Override
	public void close() {

	}

	@Override
	public void hide() {

	}
}
