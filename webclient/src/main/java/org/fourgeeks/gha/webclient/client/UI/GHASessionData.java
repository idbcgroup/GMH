package org.fourgeeks.gha.webclient.client.UI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.fourgeeks.gha.domain.ess.BpuFunction;
import org.fourgeeks.gha.domain.gar.Bpu;

import com.google.gwt.user.client.History;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

/**
 * @author alacret
 * 
 */
public abstract class GHASessionData {

	private static Bpu loggedUser;

	/**
	 * @return the logged User
	 */
	public static Bpu getLoggedUser() {
		return loggedUser;
	}

	/**
	 * @return the menu for the User
	 */
	public static Menu getUserMenu() {
		Menu menu = new Menu();
		List<BpuFunction> permissions = loggedUser.getPermissions();
		Map<String, String> permissionMap = new HashMap<String, String>();
		for (BpuFunction bpuFunction : permissions)
			permissionMap.put(bpuFunction.getFunction().getView().getScreen()
					.getToken(), bpuFunction.getFunction().getView()
					.getScreen().getName());

		Set<Entry<String, String>> entrySet = permissionMap.entrySet();

		for (final Entry<String, String> entry : entrySet) {
			MenuItem menuItem = new MenuItem(entry.getValue());
			menuItem.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(MenuItemClickEvent event) {
					History.newItem(entry.getKey());

				}
			});
			menu.addItem(menuItem);
		}

		return menu;
	}

	/**
	 * @param loggedUser
	 */
	public static void setLoggedUser(Bpu loggedUser) {
		GHASessionData.loggedUser = loggedUser;
	}
}
