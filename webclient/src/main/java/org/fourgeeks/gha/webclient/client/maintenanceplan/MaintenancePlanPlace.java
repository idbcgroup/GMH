package org.fourgeeks.gha.webclient.client.maintenanceplan;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.exceptions.LoginNeededException;
import org.fourgeeks.gha.webclient.client.UI.exceptions.PermissionsNeededException;
import org.fourgeeks.gha.webclient.client.UI.places.GHAPlaceHeader;
import org.fourgeeks.gha.webclient.client.UI.places.NeedPermissionPlace;

/**
 * @author alacret
 * 
 */
public class MaintenancePlanPlace extends NeedPermissionPlace {
	private final MaintenancePlanPanel tab = new MaintenancePlanPanel();

	/**
	 * @param token
	 * @throws LoginNeededException
	 * @throws PermissionsNeededException
	 */
	public MaintenancePlanPlace(String token) throws LoginNeededException,
			PermissionsNeededException {
		super(token);
		header = new GHAPlaceHeader(this);
		addHideableListener(tab);
		addClosableListener(tab);
		addMember(tab.getHeader());
		addMember(tab);
	}

	@Override
	public String getId() {
		return "mplan";
	}

	@Override
	public void show() {
		super.show();
		tab.show();
		tab.getHeader().show();
	}

	@Override
	public String getAcronym() {
		return GHAStrings.get("maintenance");
	}
}