package org.fourgeeks.gha.webclient.client.edt;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.exceptions.LoginNeededException;
import org.fourgeeks.gha.webclient.client.UI.exceptions.PermissionsNeededException;
import org.fourgeeks.gha.webclient.client.UI.places.NeedPermissionPlace;

/**
 * @author alacret
 * 
 */
public class EDTPlace extends NeedPermissionPlace {
	private final EDTTab tab = new EDTTab();

	/**
	 * @param token
	 * @throws LoginNeededException
	 * @throws PermissionsNeededException
	 */
	public EDTPlace(String token) throws LoginNeededException,
			PermissionsNeededException {
		super(token);
		addMember(tab);
	}

	@Override
	public String getId() {
		return "edt";
	}

	@Override
	public String getAcronym() {
		return GHAStrings.get("edt");
	}
}