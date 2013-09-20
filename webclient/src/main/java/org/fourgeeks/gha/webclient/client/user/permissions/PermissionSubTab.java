package org.fourgeeks.gha.webclient.client.user.permissions;

import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASubTab;
import org.fourgeeks.gha.webclient.client.user.UserSelectionListener;
import org.fourgeeks.gha.webclient.client.user.UserTab;

import com.google.gwt.user.client.Window;

/**
 * @author alacret
 * 
 */
public class PermissionSubTab extends GHASubTab implements
		UserSelectionListener {

	private FunctionGridPanel gridPanel;

	/**
	 * @param tab
	 */
	public PermissionSubTab(UserTab tab) {
		super("Permisos", tab);
		tab.addUserSelectionListener(this);
		gridPanel = new FunctionGridPanel(this);
		setPane(gridPanel);
	}

	@Override
	public void select(SSOUser ssoUser) {
		Window.alert("3");
		gridPanel.loadData(ssoUser);
	}

}