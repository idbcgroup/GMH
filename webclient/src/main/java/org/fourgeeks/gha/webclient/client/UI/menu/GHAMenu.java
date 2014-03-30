package org.fourgeeks.gha.webclient.client.UI.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.fourgeeks.gha.domain.ess.ui.App;
import org.fourgeeks.gha.domain.ess.ui.MenuLevel;
import org.fourgeeks.gha.webclient.client.UI.GHASessionData;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.exceptions.LoginNeededException;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImgButton;
import org.fourgeeks.gha.webclient.client.util.TreeNode;

import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

/**
 * @author alacret The Menu
 */
public class GHAMenu {
	private static List<GHAMenuBar> bars = new ArrayList<GHAMenuBar>();
	private static GHAMenuBar firstMenuBar = new GHAMenuBar();
	final private static int BAR_DISTANCE = 22;
	private static Map<String, TreeNode<String, GHAMenuOption>> map;
	private static final GHAImgButton menuButton = new GHAImgButton(
			"../resources/icons/menu.png");
	/**
	 * The width for the bar and the bar components
	 */
	final public static int BAR_WIDTH = 200;
	private static EventListener clickHandler = new EventListener() {

		@Override
		public void onBrowserEvent(final Event event) {
			final int mouseX = event.getClientX();
			final int mouseY = event.getClientY();
			final Rectangle rect = firstMenuBar.getRect();
			final int menuMinX = rect.getLeft();
			final int menuMaxX = rect.getLeft() + rect.getWidth()
					+ (BAR_DISTANCE * (bars.size() - 1));
			final int menuMinY = rect.getTop();
			final int menuMaxY = rect.getTop() + rect.getHeight();

			if (!(mouseX >= menuMinX && mouseX <= menuMaxX
					&& mouseY >= menuMinY && mouseY <= menuMaxY))
				hide();
		}
	};
	static {
		bars.add(firstMenuBar);
		menuButton.setSize("34px", "22px");
		menuButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(final ClickEvent event) {
				firstMenuBar.bringToFront();
				firstMenuBar.open();
				GHAUiHelper.addDocumentClickHandler(clickHandler);
			}
		});
	}

	private static void arrangeBars() {
		int size = bars.size();
		for (int i = 0; i < size; i++)
			bars.get(i).animateMove(((size - 1) - i) * BAR_DISTANCE, null);
	}

	/**
	 * 
	 */
	public static void build() {
		Map<String, App> appMap;
		try {
			appMap = GHASessionData.getAppsMapp();
		} catch (LoginNeededException e) {
			return;
		}

		map = new HashMap<String, TreeNode<String, GHAMenuOption>>();

		for (Entry<String, App> entry : appMap.entrySet()) {
			App app = entry.getValue();
			TreeNode<String, GHAMenuOption> appAsTreeNode = new TreeNode<String, GHAMenuOption>(
					app.getCode(),
					new GHAMenuOption(app.getName(), "../resources/icons/menu/"
							+ app.getCode() + ".png"));
			// siempre meto la app
			map.put(appAsTreeNode.getCode(), appAsTreeNode);
			MenuLevel menuLevel = app.getMenuLevel();
			// si el menu es nulo, salgo, no tengo nada mas que hacer
			if (menuLevel == null)
				continue;
			TreeNode<String, GHAMenuOption> menuLevelAsTreeNode = new TreeNode<String, GHAMenuOption>(
					menuLevel.getCode(), new GHAMenuOption(menuLevel.getText(),
							"../resources/icons/menu/" + menuLevel.getCode()
									+ ".png"));
			TreeNode<String, GHAMenuOption> menuLevelAsTreeNodeInMap = map
					.get(menuLevelAsTreeNode.getCode());
			// Reviso si el menu ya esta dentro del mapa
			if (menuLevelAsTreeNodeInMap == null)// No esta
				map.put(menuLevelAsTreeNode.getCode(), menuLevelAsTreeNode);// Lo
																			// meto
			else
				// Si esta
				menuLevelAsTreeNode = menuLevelAsTreeNodeInMap;// lo igualo
			// Seteo el menu como padre de la app
			appAsTreeNode.setParent(menuLevelAsTreeNode);
			// Agrego la app como hija del menu
			menuLevelAsTreeNode.addChild(appAsTreeNode);
			while (menuLevel.getParentMenu() != null) {// Si hay un menu padre
				TreeNode<String, GHAMenuOption> parentMenuLevelAsTreeNode = new TreeNode<String, GHAMenuOption>(
						menuLevel.getParentMenu().getCode(), new GHAMenuOption(
								menuLevel.getParentMenu().getText(),
								"../resources/icons/menu/"
										+ menuLevel.getParentMenu().getCode()
										+ ".png"));
				TreeNode<String, GHAMenuOption> parentMenuLevelAsTreeNodeInMap = map
						.get(parentMenuLevelAsTreeNode.getCode());
				// Reviso si el menu ya esta dentro del papa
				if (parentMenuLevelAsTreeNodeInMap == null) // No esta
					map.put(parentMenuLevelAsTreeNode.getCode(),
							parentMenuLevelAsTreeNode);
				else
					// Si esta
					parentMenuLevelAsTreeNode = parentMenuLevelAsTreeNodeInMap; // lo
																				// igualo
				// seteo el menu de afuera como hijo
				parentMenuLevelAsTreeNode.addChild(menuLevelAsTreeNode);
				// seteo al node actual como padre del de afuera
				menuLevelAsTreeNode.setParent(parentMenuLevelAsTreeNode);
				//
				// Digo que el menu de afuera es el de esta iteracion
				menuLevelAsTreeNode = parentMenuLevelAsTreeNode;
				// digo que el menu de esta iteracion es el de afuera
				menuLevel = menuLevel.getParentMenu();
			}
		}

		firstMenuBar.addTitle(GHAStrings.get("menu"),
				"../resources/icons/menu.png", new ClickHandler() {

					@Override
					public void onClick(final ClickEvent event) {
						Window.alert("menuclic");
						hide();
					}
				});

		for (Entry<String, TreeNode<String, GHAMenuOption>> entry : map
				.entrySet()) {
			final TreeNode<String, GHAMenuOption> value = entry.getValue();
			if (value.getParent() == null)
				firstMenuBar.addOption(createMenuOption(value));
		}
	}

	private static void createMenuBar(final TreeNode<String, GHAMenuOption> node) {
		final GHAMenuBar newMenuBar = new GHAMenuBar();
		newMenuBar.addTitle(node.getObject().getText(),
				"../resources/icons/menu/" + node.getCode() + ".png",
				new ClickHandler() {

					@Override
					public void onClick(final ClickEvent event) {
						newMenuBar.hide();
						bars.remove(newMenuBar);
						arrangeBars();
					}
				});

		Set<TreeNode<String, GHAMenuOption>> childs = node.getChilds();
		for (TreeNode<String, GHAMenuOption> treeNode : childs)
			newMenuBar.addOption(createMenuOption(treeNode));

		bars.add(newMenuBar);
		arrangeBars();
		newMenuBar.bringToFront();
		newMenuBar.open();
	}

	private static GHAMenuOption createMenuOption(
			final TreeNode<String, GHAMenuOption> value) {
		GHAMenuOption menuOption = value.getObject();
		ClickHandler optionClickHandler = new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				if (value.getChilds().size() == 0)
					History.newItem(value.getCode());
				else
					createMenuBar(map.get(value.getCode()));
			}
		};
		menuOption.addClickHandler(optionClickHandler);
		return menuOption;
	}

	/**
	 * @return the button that opens the menu
	 */
	public static Widget getMenuButton() {
		return menuButton;
	}

	protected static void hide() {
		GHAUiHelper.removeDocumentClickHandler(clickHandler);
		menuButton.blur();
		for (int i = bars.size() - 1; i >= 0; i--)
			bars.get(i).moveTo(0, GHAUiHelper.HEADER_HEIGTH);
		for (int i = bars.size() - 1; i >= 0; i--)
			bars.get(i).hide();
		bars.clear();
		bars.add(firstMenuBar);
	}

	private GHAMenu() {
		throw new UnsupportedOperationException(
				"This class mus not be instantiaded");
	}
}
