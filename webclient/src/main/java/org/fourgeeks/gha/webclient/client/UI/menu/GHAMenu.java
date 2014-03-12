package org.fourgeeks.gha.webclient.client.UI.menu;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImg;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.History;
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

	private GHAMenu() {
		throw new UnsupportedOperationException(
				"This class mus not be instantiaded");
	}

	/**
	 * @author alacret
	 * 
	 */
	public static class GHAMenuBar extends VLayout implements EventListener,
	ResizeHandler {

		private final GHAImgButton menuButton;
		private final List<GHAMenuOption> options = new ArrayList<GHAMenu.GHAMenuOption>();

		/**
		 * 
		 */
		public GHAMenuBar(GHAImgButton button) {
			menuButton = button;
			setWidth("200px");
			setHeight(GHAUiHelper.getTabHeight() + 15 + "px");
			setMembersMargin(10);
			setLeft(0);
			setTop(GHAUiHelper.HEADER_HEIGTH);
			setVisible(false);
			setAnimateTime(GHAUiHelper.DEFAULT_ANIMATION_TIME);
			setBackgroundColor("#FFFFFF");
			setScrollbarSize(5);
			setShadowDepth(6);
			setShowShadow(true);
		}

		@Override
		public void onBrowserEvent(Event event) {
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
		public void onResize(ResizeEvent event) {
			setHeight(GHAUiHelper.getTabHeight() + 15 + "px");
		}

		/**
		 * @param option
		 */
		public void addOption(GHAMenuOption option) {
			options.add(option);
			option.setVisible(false);
			addMember(option);
		}

		/**
		 * 
		 */
		public void open() {
			for (final GHAMenuOption option : options)
				option.setVisible(true);
			animateShow(AnimationEffect.FLY, new AnimationCallback() {

				@Override
				public void execute(boolean earlyFinish) {
					GHAUiHelper.addDocumentMouseOverHandler(GHAMenuBar.this);
				}
			});
		}

		@Override
		public void hide() {
			animateHide(AnimationEffect.FLY);
			GHAUiHelper.removeDocumentMouseOverHandler(this);
			menuButton.blur();
		}

		/**
		 * @param token
		 * @return the ghamenuoption of this token or null if is not found
		 */
		public GHAMenuOption getByToken(String token) {
			for (final GHAMenuOption option : options)
				if (option.getToken().equals(token))
					return option;
			return null;
		}
	}

	/**
	 * @author alacret
	 * 
	 */
	static public class GHAMenuOption extends HLayout {

		private final String token;
		private final GHAMenuBar parent;

		/**
		 * @param text
		 * @param token
		 * @param imgSrc
		 * @param bar TODO
		 */
		public GHAMenuOption(final String text, final String token,
				String imgSrc, GHAMenuBar bar) {
			this.token = token;
			parent=bar;
			setWidth100();
			setHeight("30px");
			setMembersMargin(7);
			setDefaultLayoutAlign(VerticalAlignment.CENTER);
			setCursor(Cursor.HAND);
			setVisible(false);
			setStyleName("menu-option");
			final GHAImg iconButton = new GHAImg(imgSrc);

			addMembers(new LayoutSpacer(), iconButton);

			final GHALabel titulo = new GHALabel(text);
			titulo.setWidth("150px");
			titulo.setHeight("25px");
			titulo.setStyleName("menu-option-title button-pointer");

			addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					History.newItem(token);
					parent.hide();
				}
			});

			addMouseOverHandler(new MouseOverHandler() {

				@Override
				public void onMouseOver(MouseOverEvent event) {
					setBackgroundColor(GHAUiHelper.DEFAULT_PLACES_BAR_BACKGROUND_COLOR);
				}
			});

			addMouseOutHandler(new MouseOutHandler() {

				@Override
				public void onMouseOut(MouseOutEvent event) {
					setBackgroundColor("#FFFFFF");
				}
			});

			addMember(titulo);
			addMember(new LayoutSpacer());

		}

		/**
		 * @return the token
		 */
		public String getToken() {
			return token;
		}

	}
}
