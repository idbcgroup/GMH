package org.fourgeeks.gha.webclient.client.UI.menu;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImg;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;

import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class GHAMenuBar extends VLayout {

	private final List<GHAMenuOption> options = new ArrayList<GHAMenuOption>();
	private String title;

	/**
	 * creates a menu bar
	 */
	public GHAMenuBar() {
		setHeight100();
		setMinHeight(GHAUiHelper.MIN_HEIGHT - GHAUiHelper.HEADER_HEIGTH);
		setLeft(0);
		setTop(GHAUiHelper.HEADER_HEIGTH);
		setVisible(false);
		setAnimateTime(GHAUiHelper.DEFAULT_ANIMATION_TIME);
		setBackgroundColor("#FFFFFF");
		setScrollbarSize(5);
		setShadowDepth(2);
		setShowShadow(true);
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
	 * Adds the title bar with an optional clic handler
	 * 
	 * @param title
	 * @param imgSrc
	 * @param clickHandler
	 */
	public void addTitle(final String title, final String imgSrc,
			final ClickHandler clickHandler) {
		this.title = title;
		GHAMenuOption option = new GHAMenuOption();
		GHAImg menuImg = new GHAImg(imgSrc);
		GHALabel titleLabel = new GHALabel(title);
		titleLabel.setWidth(GHAMenu.BAR_WIDTH);
		titleLabel.setHeight(25);
		if (clickHandler != null)
			titleLabel.addClickHandler(clickHandler);
		option.addMember(titleLabel);
		option.addMember(new LayoutSpacer());
		option.addMember(menuImg);
		addOption(option);
	}

	/**
	 * Get the title
	 */
	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void hide() {
		animateHide(AnimationEffect.FLY);
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