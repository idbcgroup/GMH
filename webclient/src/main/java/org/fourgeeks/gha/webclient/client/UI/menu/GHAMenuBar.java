package org.fourgeeks.gha.webclient.client.UI.menu;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImg;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;

import com.google.gwt.user.client.Event;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class GHAMenuBar extends VLayout {

	private final List<GHAMenuOption> options = new ArrayList<GHAMenuOption>();

	/**
	 * creates a menu bar
	 */
	public GHAMenuBar() {

		// setHeight(GHAUiHelper.getTabHeight() + 15 + "px");
		setHeight100();
		setMinHeight(GHAUiHelper.MIN_HEIGHT);
		setLeft(0);
		setTop(GHAUiHelper.HEADER_HEIGTH);
		setVisible(false);
		setAnimateTime(GHAUiHelper.DEFAULT_ANIMATION_TIME);
		setBackgroundColor("#FFFFFF");
		setScrollbarSize(5);
		setShadowDepth(6);
		setShowShadow(true);

		// Titulo
		GHAMenuOption option = new GHAMenuOption();
		GHAImg menuImg = new GHAImg("../resources/icons/menu.png");
		// menuImg.setSize("34px", "22px");
		GHALabel title = new GHALabel(GHAStrings.get("menu"));
		title.setWidth100();
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

	@Override
	public void hide() {
		animateHide(AnimationEffect.FLY);
		GHAUiHelper.removeDocumentMouseOverHandler(this);
	}

	@Override
	public void onBrowserEvent(final Event event) {
	}

	/**
		 * 
		 */
	public void open() {
		for (final GHAMenuOption option : options)
			option.setVisible(true);
		animateShow(AnimationEffect.FLY);
	}
}