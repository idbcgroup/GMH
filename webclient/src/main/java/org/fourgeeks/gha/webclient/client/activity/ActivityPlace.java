package org.fourgeeks.gha.webclient.client.activity;

import org.fourgeeks.gha.webclient.client.UI.exceptions.LoginNeededException;
import org.fourgeeks.gha.webclient.client.UI.exceptions.PermissionsNeededException;
import org.fourgeeks.gha.webclient.client.UI.places.GHAPlaceHeader;
import org.fourgeeks.gha.webclient.client.UI.places.NeedPermissionPlace;

/**
 * 
 * @author caparicio
 * 
 */
public class ActivityPlace extends NeedPermissionPlace {
	private final ActivityPanel tab = new ActivityPanel();

	/**
	 * @param token
	 * @throws LoginNeededException
	 * @throws PermissionsNeededException
	 */
	public ActivityPlace(String token) throws LoginNeededException,
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
		return "Actividad de mantenimiento";
	}
}