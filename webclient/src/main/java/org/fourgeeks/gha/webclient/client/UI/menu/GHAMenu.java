package org.fourgeeks.gha.webclient.client.UI.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.fourgeeks.gha.webclient.client.UI.GHASessionData;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.exceptions.LoginNeededException;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImg;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.types.Cursor;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.AnimationCallback;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.MouseOutEvent;
import com.smartgwt.client.widgets.events.MouseOutHandler;
import com.smartgwt.client.widgets.events.MouseOverEvent;
import com.smartgwt.client.widgets.events.MouseOverHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret The Menu
 */
public class GHAMenu {
	/**
	 * @author alacret
	 * 
	 */
	public static class GHAMenuBar extends VLayout implements EventListener,
			ResizeHandler {

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

			if (!(mouseX >= menuMinX && mouseX <= menuMaxX
					&& mouseY >= menuMinY && mouseY <= menuMaxY)) {
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

	/**
	 * @author alacret
	 * 
	 */
	static public class GHAMenuOption extends HLayout {

		// private final String token;

		/**
		 * 
		 */
		public GHAMenuOption() {
			setWidth100();
			setHeight("30px");
			setMembersMargin(7);
			setDefaultLayoutAlign(VerticalAlignment.CENTER);
			setCursor(Cursor.HAND);
			setVisible(false);
			setStyleName("menu-option");
			addMember(new LayoutSpacer());
		}

		/**
		 * @param text
		 * @param token
		 * @param imgSrc
		 */
		public GHAMenuOption(final String text, final String token,
				final String imgSrc) {
			this();
			final GHAImg iconButton = new GHAImg(imgSrc);
			addMember(iconButton);
			final GHALabel titulo = new GHALabel(text);
			titulo.setWidth("150px");
			titulo.setHeight("25px");
			titulo.setStyleName("menu-option-title button-pointer");

			addClickHandler(new ClickHandler() {

				@Override
				public void onClick(final ClickEvent event) {
					History.newItem(token);
				}
			});

			addMouseOverHandler(new MouseOverHandler() {

				@Override
				public void onMouseOver(final MouseOverEvent event) {
					setBackgroundColor(GHAUiHelper.DEFAULT_PLACES_BAR_BACKGROUND_COLOR);
				}
			});

			addMouseOutHandler(new MouseOutHandler() {

				@Override
				public void onMouseOut(final MouseOutEvent event) {
					setBackgroundColor("#FFFFFF");
				}
			});

			addMember(titulo);
			addMember(new LayoutSpacer());

		}

	}

	private static GHAMenuBar verticalMenu = new GHAMenuBar();

	/**
	 * 
	 */
	public static void build() {
		List<GHAMenuOption> menuOptions = getMenuOptions();
		for (GHAMenuOption ghaMenuOption : menuOptions)
			verticalMenu.addOption(ghaMenuOption);

	}

	/**
	 * @return the button that opens the menu
	 */
	public static Widget getMenuButton() {
		return verticalMenu.getMenuButton();
	}

	private static List<GHAMenuOption> getMenuOptions() {
		final List<GHAMenuOption> menuOptions = new ArrayList<GHAMenuOption>();
		try {
			final Map<String, String> appMap = GHASessionData.getAppsMapp();
			final Set<Entry<String, String>> entrySet = appMap.entrySet();

			for (final Entry<String, String> entry : entrySet) {
				menuOptions.add(new GHAMenuOption(GHAStrings.get(entry
						.getValue()), entry.getKey(),
						"../resources/icons/menu/" + entry.getKey() + ".png"));
			}
			return menuOptions;
		} catch (final LoginNeededException e) {
			return menuOptions;
		}

	}

	private GHAMenu() {
		throw new UnsupportedOperationException(
				"This class mus not be instantiaded");
	}
}
