package org.fourgeeks.gha.webclient.client.UI.menu;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAImg;

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
import com.smartgwt.client.widgets.Label;
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

		List<GHAMenuOption> options = new ArrayList<GHAMenu.GHAMenuOption>();

		/**
		 * 
		 */
		public GHAMenuBar() {
			setWidth("200px");
			setHeight(GHAUiHelper.getTabHeight() + 15 + "px");
			setMembersMargin(10);
			setLeft(0);
			setTop(60);
			setVisible(false);
			setAnimateTime(400);
			setBackgroundColor("#FFFFFF");
			setScrollbarSize(5);
			setShadowDepth(6);
			setShowShadow(true);
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
			for (GHAMenuOption option : options)
				option.setVisible(true);
			animateShow(AnimationEffect.FLY, new AnimationCallback() {

				@Override
				public void execute(boolean earlyFinish) {
					GHAUiHelper.addDocumentMouseOverHandler(GHAMenuBar.this);
				}
			});
		}

		public void hide() {
			animateHide(AnimationEffect.FLY);
			GHAUiHelper.removeDocumentMouseOverHandler(this);
		}
	}

	/**
	 * @author alacret
	 * 
	 */
	static public class GHAMenuOption extends HLayout {

		private GHAMenuBar bar;

		/**
		 * @param text
		 * @param token
		 * @param imgSrc
		 */
		public GHAMenuOption(final String text, final String token,
				String imgSrc) {
			setWidth100();
			setHeight("30px");
			setMembersMargin(7);
			setDefaultLayoutAlign(VerticalAlignment.CENTER);
			setCursor(Cursor.HAND);
			setVisible(false);
			setStyleName("menu-option");
			GHAImg iconButton = new GHAImg(imgSrc);

			addMembers(new LayoutSpacer(), iconButton);

			Label titulo = new Label(text);
			titulo.setWidth("150px");
			titulo.setHeight("25px");
			titulo.setStyleName("menu-option-title");
			titulo.addStyleName("button-pointer");

			addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					bar.hide();
					History.newItem(token);
				}
			});

			addMouseOverHandler(new MouseOverHandler() {

				@Override
				public void onMouseOver(MouseOverEvent event) {
					setBackgroundColor("#E0E0DF");
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
		 * @param bar
		 */
		public void setBar(GHAMenuBar bar) {
			this.bar = bar;
		}

	}

}
