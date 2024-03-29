package org.fourgeeks.gha.webclient.client.maintenanceactivity;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.exceptions.LoginNeededException;
import org.fourgeeks.gha.webclient.client.UI.exceptions.PermissionsNeededException;
import org.fourgeeks.gha.webclient.client.UI.places.GHAPlaceHeader;
import org.fourgeeks.gha.webclient.client.UI.places.NeedPermissionPlace;

/**
 * 
 * @author caparicio
 * 
 */
public class MaintenanceActivityPlace extends NeedPermissionPlace {
	private final MaintenanceActivityPanel tab = new MaintenanceActivityPanel();

	// TODO Verificar dónde explota el código...
	/**
	 * @param token
	 * @throws LoginNeededException
	 * @throws PermissionsNeededException
	 */
	public MaintenanceActivityPlace(String token) throws LoginNeededException,
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
		return "activity";
	}

	@Override
	public void show() {
		super.show();
		tab.show();
		tab.getHeader().show();
	}

	@Override
	public String getAcronym() {
		return GHAStrings.get("maintenance-activity-tab");
	}
}