package org.fourgeeks.gha.webclient.client.UI.dropdownmenus;

import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImg;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.login.GWTLoginService;
import org.fourgeeks.gha.webclient.client.login.GWTLoginServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.Window;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.types.Positioning;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.AnimationCallback;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class UserDropdownMenu extends VLayout implements HideableListener,
		ResizeHandler, EventListener {

	int posX, picoPos;
	private final int width = 280;
	GHAImg pico;

	/**
	 * @param user
	 */
	public UserDropdownMenu(Bpu user) {

		GHAUiHelper.addGHAResizeHandler(this);
		posX = (Window.getClientWidth() - 20) - width;
		// setAnimateFadeTime(300);
		setSize(width + "px", "*");
		setLeft(posX);
		setTop(50);
		setMembersMargin(10);
		setPosition(Positioning.ABSOLUTE);
		setBackgroundColor("#FFFFFF");
		setBorder("1px solid #E0E0E0");
		setCanFocus(true);
		setVisible(false);
		setShadowDepth(6);
		setShowShadow(true);
		setZIndex(444444444);

		pico = new GHAImg("../resources/img/pico.png", 27, 15);
		picoPos = Window.getClientWidth() - 64;
		pico.setTop(42);
		pico.setLeft(picoPos);
		pico.setPosition(Positioning.ABSOLUTE);

		// TITLE LAYOUT
		HLayout titleLayout = GHAUiHelper.verticalGraySeparatorLabel("40px",
				user.getCitizen().getFirstName() + " "
						+ user.getCitizen().getFirstLastName());
		// USER DATA
		GHALabel mailText;
		if (user.getCitizen().getPrimaryEmail() != null) {
			mailText = new GHALabel("<br>Correo electrónico:<br><br> "
					+ user.getCitizen().getPrimaryEmail());
		} else {
			mailText = new GHALabel("<br>Correo electrónico:<br><br> No posee.");
		}

		VLayout userdataLayout = new VLayout();
		userdataLayout.setHeight("70px");
		userdataLayout.setStyleName("sides-padding");
		userdataLayout.setDefaultLayoutAlign(VerticalAlignment.CENTER);
		userdataLayout.addMembers(mailText);

		GHAButton logoutButton = new GHAButton("Cerrar Sesion",
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						final GWTLoginServiceAsync service = GWT
								.create(GWTLoginService.class);
						service.logOut(new GHAAsyncCallback<Void>() {

							@Override
							public void onSuccess(Void result) {
								// Window.Location.reload();
								Window.Location.assign("");
							}
						});
						UserDropdownMenu.this.hide();
					}
				});
		// logoutButton.setSize("17px", "24px");
		// logoutButton.setAlign(Alignment.RIGHT);
		// logoutButton.setPadding(20);

		// LOGOUT LAYOUT
		HLayout logoutLayout = new HLayout();
		logoutLayout.setHeight("40px");
		logoutLayout.setBackgroundColor("#E0E0E0");
		logoutLayout.setStyleName("sides-padding");
		logoutLayout.setDefaultLayoutAlign(VerticalAlignment.CENTER);
		logoutLayout.addMembers(new LayoutSpacer(), logoutButton);

		addMembers(titleLayout, userdataLayout, logoutLayout);
	}

	@Override
	public void show() {
		setCanFocus(true);
		animateShow(AnimationEffect.FADE, new AnimationCallback() {

			@Override
			public void execute(boolean earlyFinish) {
				GHAUiHelper.addDocumentClickHandler(UserDropdownMenu.this);
			}
		});
		setVisible(true);
		pico.setVisible(true);
		pico.animateShow(AnimationEffect.FADE);
		bringToFront();
	}

	@Override
	public void hide() {
		animateHide(AnimationEffect.FADE);
		setVisible(false);
		pico.setVisible(false);
		pico.animateHide(AnimationEffect.FADE);
		setCanFocus(false);
		GHAUiHelper.removeDocumentClickHandler(UserDropdownMenu.this);
	}

	@Override
	public void onResize(ResizeEvent event) {
		posX = (Window.getClientWidth() - 20) - width;
		picoPos = Window.getClientWidth() - 64;
		setLeft(posX);
		pico.setLeft(picoPos);
	}

	@Override
	public void onBrowserEvent(Event event) {
		int mouseX = event.getClientX();
		int mouseY = event.getClientY();
		Rectangle rect = getRect();
		int menuMinX = rect.getLeft();
		int menuMaxX = rect.getLeft() + rect.getWidth();
		int menuMinY = rect.getTop();
		int menuMaxY = rect.getTop() + rect.getHeight();

		if (!(mouseX >= menuMinX && mouseX <= menuMaxX && mouseY >= menuMinY && mouseY <= menuMaxY)) {
			hide();
		}
	}

	@Override
	public boolean canBeHidden(HideCloseAction hideAction) {
		return true;
	}
}
