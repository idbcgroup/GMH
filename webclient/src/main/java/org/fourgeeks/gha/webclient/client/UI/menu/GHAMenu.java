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
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAImgButton;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.types.Cursor;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.types.Visibility;
import com.smartgwt.client.widgets.AnimationCallback;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
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
		for (GHAMenuOption ghaMenuOption : menuOptions)
			verticalMenu.addOption(ghaMenuOption);

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
		for (final Entry<String, String> entry : entrySet)
			menuOptions.add(new GHAMenuOption(entry.getValue(), entry.getKey(),
					"../resources/icons/menu/" + entry.getKey() + ".png"));

		return menuOptions;
	}

	private static class GHAMenuBar extends VLayout implements EventListener,
			ResizeHandler {
		List<GHAMenuOption> options = new ArrayList<GHAMenu.GHAMenuOption>();

		public GHAMenuBar() {
			setLeft(0);
			setTop(70);
			setVisibility(Visibility.HIDDEN);
			setAnimateTime(600);
			setBackgroundColor("#E0E0DF");
			setHeight(GHAUiHelper.getTabHeight() + "px");
			setWidth(200);
			setScrollbarSize(1);
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
				animateHide(AnimationEffect.FLY);
				GHAUiHelper.removeDocumentClickHandler(this);
			}
		}

		@Override
		public void onResize(ResizeEvent event) {
			setHeight(GHAUiHelper.getTabHeight() + "px");
		}

		public void addOption(GHAMenuOption option) {
			options.add(option);
			option.setVisibility(Visibility.HIDDEN);
			addMember(option);
		}

		public void open() {
			for (GHAMenuOption option : options)
				option.setVisibility(Visibility.VISIBLE);
			animateShow(AnimationEffect.FLY, new AnimationCallback() {

				@Override
				public void execute(boolean earlyFinish) {
					GHAUiHelper.addDocumentClickHandler(GHAMenuBar.this);
				}
			});
		}

	}

	/**
	 * @author alacret
	 * 
	 */
	static private class GHAMenuOption extends HLayout {

		/**
		 * @param text
		 * @param token
		 * @param imgSrc
		 */
		public GHAMenuOption(String text, final String token, String imgSrc) {
			setStylePrimaryName("tab-header");
			setWidth(170);
			setHeight(12);
			setAlign(Alignment.LEFT);
			setDefaultLayoutAlign(VerticalAlignment.CENTER);
			setCursor(Cursor.HAND);
			setVisibility(Visibility.HIDDEN);
			// Window.alert(imgSrc);
			addMember(new GHAImgButton(imgSrc));

			HTML titulo = new HTML(text);
			titulo.setWidth("160px");
			titulo.setHeight("12px");
			titulo.setStylePrimaryName("tab-header-title");
			addMember(titulo);
			// * titulo.addClickHandler(new
			// * com.google.gwt.event.dom.client.ClickHandler() {
			// *
			// * @Override public void onClick(
			// * com.google.gwt.event.dom.client.ClickEvent event) {
			// * History.newItem(token); } }); addMember(titulo);
			// *
			// * addMouseOverHandler(new MouseOverHandler() {
			// *
			// * @Override public void onMouseOver(MouseOverEvent event) {
			// * getElement().addClassName("tab-header-over"); } });
			// *
			// * addMouseOutHandler(new MouseOutHandler() {
			// *
			// * @Override public void onMouseOut(MouseOutEvent event) {
			// * getElement().removeClassName("tab-header-over"); } });
			// */

		}
	}

}
