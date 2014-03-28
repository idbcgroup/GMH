package org.fourgeeks.gha.webclient.client.UI.menu;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImg;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.widgets.AnimationCallback;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class GHAMenuBar extends VLayout implements EventListener, ResizeHandler {

	private final GHAImgButton menuButton;
	private final List<GHAMenuOption> options = new ArrayList<GHAMenuOption>();

	/**
	 * creates a menu bar
	 */
	public GHAMenuBar() {
		menuButton = new GHAImgButton("../resources/icons/menu.png");
		menuButton.setSize("34px", "22px");
		menuButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(final ClickEvent event) {
				bringToFront();
				if (!isVisible()) {
					open();
				} else {
					hide();
				}
			}
		});
		setHeight(GHAUiHelper.getTabHeight() + 15 + "px");
		// setMembersMargin(10);
		setLeft(0);
		setTop(GHAUiHelper.HEADER_HEIGTH);
		setVisible(false);
		setAnimateTime(GHAUiHelper.DEFAULT_ANIMATION_TIME);
		setBackgroundColor("#FFFFFF");
		setScrollbarSize(5);
		setShadowDepth(6);
		setShowShadow(true);

		GHAUiHelper.addGHAResizeHandler(this);
		// Titulo
		GHAMenuOption option = new GHAMenuOption();
		GHAImg menuImg = new GHAImg("../resources/icons/menu.png");
		// menuImg.setSize("34px", "22px");
		GHALabel title = new GHALabel(GHAStrings.get("menu"));
		title.setWidth("150px");
		title.setHeight("25px");

		option.addMember(title);
		option.addMember(new LayoutSpacer());
		option.addMember(menuImg);
		addOption(option);

	}

	/**
	 * @param option
	 */
	public void addOption(final GHAMenuOption option) {
		options.add(option);
		option.setVisible(false);
		addMember(option);
	}

	/**
	 * @return the menu button
	 */
	public GHAImgButton getMenuButton() {
		return menuButton;
	}

	@Override
	public void hide() {
		animateHide(AnimationEffect.FLY);
		GHAUiHelper.removeDocumentMouseOverHandler(this);
		menuButton.blur();
	}

	@Override
	public void onBrowserEvent(final Event event) {
		final int mouseX = event.getClientX();
		final int mouseY = event.getClientY();
		final Rectangle rect = getRect();
		final int menuMinX = rect.getLeft();
		final int menuMaxX = rect.getLeft() + rect.getWidth();
		final int menuMinY = rect.getTop();
		final int menuMaxY = rect.getTop() + rect.getHeight();

		if (!(mouseX >= menuMinX && mouseX <= menuMaxX && mouseY >= menuMinY && mouseY <= menuMaxY)) {
			hide();
		}
	}

	@Override
	public void onResize(final ResizeEvent event) {
		setHeight(GHAUiHelper.getTabHeight() + 15 + "px");
	}

	/**
		 * 
		 */
	public void open() {
		for (final GHAMenuOption option : options)
			option.setVisible(true);
		animateShow(AnimationEffect.FLY, new AnimationCallback() {

			@Override
			public void execute(final boolean earlyFinish) {
				// GHAUiHelper.addDocumentMouseOverHandler(GHAMenuBar.this);
			}
		});
	}
}