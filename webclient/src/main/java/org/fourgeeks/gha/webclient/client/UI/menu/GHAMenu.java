package org.fourgeeks.gha.webclient.client.UI.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.fourgeeks.gha.domain.ess.BpuFunction;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.webclient.client.UI.GHASessionData;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAImg;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAImgButton;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.types.Cursor;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.AnimationCallback;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.HoverEvent;
import com.smartgwt.client.widgets.events.HoverHandler;
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
	 * Build the Menu
	 */
	public static void buildMenu() {
		final GHAMenuBar verticalMenu = new GHAMenuBar();
		GHAUiHelper.addGHAResizeHandler(verticalMenu);

		GHAImgButton menu = new GHAImgButton("../resources/icons/menu.png");
		menu.setSize("34px", "24px");
		menu.addHoverHandler(new HoverHandler() {

			@Override
			public void onHover(HoverEvent event) {
				verticalMenu.bringToFront();
				if (!verticalMenu.isVisible()) {
					// verticalMenu.animateHide(AnimationEffect.FLY);
					// GHAUiHelper.removeDocumentClickHandler(verticalMenu);
					verticalMenu.open();
				} else {
					verticalMenu.animateHide(AnimationEffect.FLY);
					GHAUiHelper.removeDocumentClickHandler(verticalMenu);
				}
			}
		});
		menu.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				verticalMenu.bringToFront();
				if (!verticalMenu.isVisible()) {
					// verticalMenu.animateHide(AnimationEffect.FLY);
					// GHAUiHelper.removeDocumentClickHandler(verticalMenu);
					verticalMenu.open();
				} else {
					verticalMenu.animateHide(AnimationEffect.FLY);
					GHAUiHelper.removeDocumentClickHandler(verticalMenu);
				}
			}
		});

		Bpu user = GHASessionData.getLoggedUser();
		List<GHAMenuOption> menuOptions = getMenuOptions(user);
		for (GHAMenuOption ghaMenuOption : menuOptions) {
			ghaMenuOption.setBar(verticalMenu);
			verticalMenu.addOption(ghaMenuOption);
		}

		RootPanel.get("menu-bar").add(menu);
	}

	/**
	 * @return the list of menu option
	 */
	private static List<GHAMenuOption> getMenuOptions(Bpu loggedUser) {
		List<BpuFunction> permissions = loggedUser.getPermissions();
		Map<String, String> permissionMap = new HashMap<String, String>();
		for (BpuFunction bpuFunction : permissions)
			permissionMap.put(bpuFunction.getFunction().getView().getScreen()
					.getToken(), bpuFunction.getFunction().getView()
					.getScreen().getName());

		Set<Entry<String, String>> entrySet = permissionMap.entrySet();
		List<GHAMenuOption> menuOptions = new ArrayList<GHAMenuOption>();
		for (final Entry<String, String> entry : entrySet) {
			menuOptions.add(new GHAMenuOption(entry.getValue(), entry.getKey(),
					"../resources/icons/menu/" + entry.getKey() + ".png"));
		}
		return menuOptions;
	}

	private static class GHAMenuBar extends VLayout implements EventListener,
			ResizeHandler {
		
		GHAMenuOption selected;
		List<GHAMenuOption> options = new ArrayList<GHAMenu.GHAMenuOption>();

		public GHAMenuBar() {
			setWidth("200px");
			setHeight(GHAUiHelper.getTabHeight() + 15 + "px");
			setMembersMargin(10);
			// setStyleName("menu-padding");
			setLeft(0);
			setTop(60);
			setVisible(false);
			setAnimateTime(600);
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

		public void addOption(GHAMenuOption option) {
			options.add(option);
			option.setVisible(false);
			addMember(option);
		}

		public void open() {
			for (GHAMenuOption option : options)
				option.setVisible(true);
			animateShow(AnimationEffect.FLY, new AnimationCallback() {

				@Override
				public void execute(boolean earlyFinish) {
					GHAUiHelper.addDocumentClickHandler(GHAMenuBar.this);
				}
			});
		}

		public void hide() {
			animateHide(AnimationEffect.FLY);
			GHAUiHelper.removeDocumentClickHandler(this);
		}
	}

	/**
	 * @author alacret
	 * 
	 */
	static private class GHAMenuOption extends HLayout {

		private GHAMenuBar bar;
		
		/**
		 * @param text
		 * @param token
		 * @param imgSrc
		 */
		public GHAMenuOption(String text, final String token, String imgSrc) {
			setWidth100();
			setHeight("24px");
			setMembersMargin(7);
			setDefaultLayoutAlign(VerticalAlignment.CENTER);
			setCursor(Cursor.HAND);
			setVisible(false);
			setStyleName("menu-option");
			// Window.alert(imgSrc);
			GHAImg iconButton = new GHAImg(imgSrc);
//			iconButton.setStyleName("left-menu-padding");

			addMembers(new LayoutSpacer(), iconButton);

			Label titulo = new Label(text);
			titulo.setWidth("150px");
			titulo.setHeight("20px");
			titulo.setStyleName("menu-option-title");
			titulo.addStyleName("button-pointer");

			addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					History.newItem(token);
//					for (GHAMenuOption opt: bar.options)
//						opt.setSelected(false);
//					setSelected(true);
					bar.hide();
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
		
		public void setSelected(boolean sel) {
			if(sel)
				addStyleName("side-option-selected");
			else
				removeStyleName("side-option-selected");
		}

		public void setBar(GHAMenuBar bar) {
			this.bar = bar;
		}

	}

}
