package org.fourgeeks.gha.webclient.client.eiatype;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.exceptions.LoginNeededException;
import org.fourgeeks.gha.webclient.client.UI.exceptions.PermissionsNeededException;
import org.fourgeeks.gha.webclient.client.UI.places.GHAPlaceHeader;
import org.fourgeeks.gha.webclient.client.UI.places.NeedPermissionPlace;

/**
 * @author alacret
 * 
 */
public class EIATypePlace extends NeedPermissionPlace {
	private final EIATypePanel tab = new EIATypePanel();

	/**
	 * @param token
	 * @throws LoginNeededException
	 * @throws PermissionsNeededException
	 */
	public EIATypePlace(String token) throws LoginNeededException,
			PermissionsNeededException {
		super(token);
		header = new GHAPlaceHeader(this);
		addHideableListener(tab);
		addClosableListener(tab);
		// addHideableListener(tabHeader);
		// addClosableListener(tabHeader);
		addMember(tab.getHeader());
		addMember(tab);		
	}
	
	@Override
	public String getId() {
		return "eiatype";
	}

	@Override
	public void show() {
		super.show();
		tab.show();
		tab.getHeader().show();
	}

	@Override
	public String getAcronym() {
		return GHAStrings.get("eiatype");
	}
}