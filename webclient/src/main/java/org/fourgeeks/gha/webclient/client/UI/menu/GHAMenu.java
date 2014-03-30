package org.fourgeeks.gha.webclient.client.UI.menu;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.fourgeeks.gha.domain.ess.ui.App;
import org.fourgeeks.gha.domain.ess.ui.MenuLevel;
import org.fourgeeks.gha.webclient.client.UI.GHASessionData;
import org.fourgeeks.gha.webclient.client.UI.exceptions.LoginNeededException;
import org.fourgeeks.gha.webclient.client.util.TreeNode;

import com.google.gwt.user.client.ui.Widget;

/**
 * @author alacret The Menu
 */
public class GHAMenu {

	private static GHAMenuBar verticalMenu = new GHAMenuBar();

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
		Map<String, TreeNode<String, GHAMenuOption>> map = new HashMap<String, TreeNode<String, GHAMenuOption>>();

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

		// Set<Entry<String, TreeNode<String, GHAMenuOption>>> entrySet = map
		// .entrySet();
		// StringBuilder sb = new StringBuilder();
		// for (Entry<String, TreeNode<String, GHAMenuOption>> entry : entrySet)
		// {
		// sb.append(entry.getKey() + ": ");
		// TreeNode<String, GHAMenuOption> value = entry.getValue();
		// Set<TreeNode<String, GHAMenuOption>> childs = value.getChilds();
		// for (TreeNode<String, GHAMenuOption> treeNode : childs)
		// sb.append(treeNode.getCode() + ", ");
		// sb.append("\n");
		// }
		//
		// Window.alert(sb.toString());

		for (Entry<String, TreeNode<String, GHAMenuOption>> entry : map
				.entrySet()) {
			TreeNode<String, GHAMenuOption> value = entry.getValue();
			if (value.getParent() == null) {
				verticalMenu.addOption(value.getObject());
			}
		}

	}

	/**
	 * @return the button that opens the menu
	 */
	public static Widget getMenuButton() {
		return verticalMenu.getMenuButton();
	}

	private GHAMenu() {
		throw new UnsupportedOperationException(
				"This class mus not be instantiaded");
	}
}
