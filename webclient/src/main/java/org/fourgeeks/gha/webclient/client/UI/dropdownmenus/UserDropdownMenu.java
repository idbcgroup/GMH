package org.fourgeeks.gha.webclient.client.UI.dropdownmenus;

import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAImgButton;
import org.fourgeeks.gha.webclient.client.login.GWTLoginService;
import org.fourgeeks.gha.webclient.client.login.GWTLoginServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.types.Positioning;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class UserDropdownMenu extends VLayout implements GHAHideable,
		ResizeHandler {

	int posX;
	private int width = 280;

	/**
	 * @param user
	 */
	public UserDropdownMenu(Bpu user) {

		GHAUiHelper.addGHAResizeHandler(this);
		posX = (Window.getClientWidth() - 40) - width;

		setAnimateTime(300);
		setPosition(Positioning.ABSOLUTE);
		setLeft(posX);
		setTop(70);
		setSize(width + "px", "*");
		setBackgroundColor("#FFFFFF");
		setBorder("1px solid #E0E0E0");
		setCanFocus(true);
		setVisible(false);

		Label mailText;
		if (user.getCitizen().getPrimaryEmail() != null) {
			mailText = new Label("<br>Correo electrónico:<br><br> "
					+ user.getCitizen().getPrimaryEmail());
		} else {
			mailText = new Label("<br>Correo electrónico:<br><br> No posee.");
		}
		mailText.setHeight("20px");
		mailText.setWidth100();
		mailText.setStyleName("title-label");

		GHAImgButton logoutButton = new GHAImgButton(
				"../resources/icons/logout.png", new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						final GWTLoginServiceAsync service = GWT
								.create(GWTLoginService.class);
						service.logOut(new GHAAsyncCallback<Void>() {

							@Override
							public void onSuccess(Void result) {
								Window.Location.reload();
							}
						});
						UserDropdownMenu.this.hide();
					}
				});
		logoutButton.setSize("17px", "24px");
		// logoutButton.setAlign(Alignment.RIGHT);
		// logoutButton.setPadding(20);

		VLayout userdataLayout = new VLayout();
		userdataLayout.setStyleName("sides-padding");

		userdataLayout.addMembers(mailText);

		HLayout titleLayout = GHAUiHelper.verticalGraySeparatorLabel("30px",
				user.getCitizen().getFirstName() + " "
						+ user.getCitizen().getFirstLastName());
		titleLayout.addMembers(new LayoutSpacer(), logoutButton);

		addMembers(titleLayout, userdataLayout);
	}

	/**
	 * @param x
	 * @param y
	 */
	public void show(int x, int y) {
		setCanFocus(true);
		animateShow(AnimationEffect.FADE);
		setVisible(true);
		focus();
	}

	@Override
	public void hide() {
		blur();
		animateHide(AnimationEffect.FADE);
		setVisible(false);
		setCanFocus(false);
	}

	@Override
	public void onResize(ResizeEvent event) {
		posX = (Window.getClientWidth() - 40) - width;
		setLeft(posX);
	}
}
