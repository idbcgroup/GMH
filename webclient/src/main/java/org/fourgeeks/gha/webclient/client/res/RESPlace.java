package org.fourgeeks.gha.webclient.client.res;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.exceptions.LoginNeededException;
import org.fourgeeks.gha.webclient.client.UI.exceptions.PermissionsNeededException;
import org.fourgeeks.gha.webclient.client.UI.places.GHAPlaceHeader;
import org.fourgeeks.gha.webclient.client.UI.places.NeedPermissionPlace;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATab;
import org.fourgeeks.gha.webclient.client.res.citizen.CitizenTab;

/**
 * @author alacret
 * 
 */
public class RESPlace extends NeedPermissionPlace {
	private final RESTabbedPanel tabPanel;

	/**
	 * @param token
	 * @throws PermissionsNeededException
	 * @throws LoginNeededException
	 */
	public RESPlace(String token) throws LoginNeededException,
	PermissionsNeededException {
		super(token);
		tabPanel = new RESTabbedPanel(GHAStrings.get("res"), this);
		header = new GHAPlaceHeader(this);
		addHideableListener(tabPanel);
		addClosableListener(tabPanel);
		addMember(tabPanel);
	}

	@Override
	public String getId() {
		return "res";
	}

	@Override
	public String getAcronym() {
		return GHAStrings.get("res");
	}

	@Override
	public void updateToken(String token) {
		final int indexOf = token.indexOf("/");
		if (indexOf == -1)
			return;
		final String patientId = token.substring(indexOf + 1);
		final GHATab tab = tabPanel.getTabById(patientId);
		if (tab != null)
			tabPanel.showTab(patientId);
		else
			tabPanel.addAndShow(new CitizenTab(patientId));
	}

}
