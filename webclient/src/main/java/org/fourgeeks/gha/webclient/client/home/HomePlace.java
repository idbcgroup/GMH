package org.fourgeeks.gha.webclient.client.home;

import org.fourgeeks.gha.webclient.client.UI.exceptions.LoginNeededException;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToHideException;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.places.GHAPlaceSet;
import org.fourgeeks.gha.webclient.client.UI.places.NeedLoginPlace;

import com.google.gwt.user.client.ui.RootPanel;

/**
 * @author alacret
 * 
 */
@Deprecated
public class HomePlace extends NeedLoginPlace {
	/**
	 * @param token
	 * @throws LoginNeededException
	 * 
	 */
	// private EIADispatchmentForm dispatchmentForm = new EIADispatchmentForm();
	// private EIAInstallationCertificateForm installationCertificateForm = new
	// EIAInstallationCertificateForm();
	@Deprecated
	public HomePlace(String token) throws LoginNeededException {
		super(token);
	}

	@Override
	public void show() {
		try {
			GHAPlaceSet.closeCurrentPlace(HideCloseAction.ASK);
		} catch (UnavailableToHideException e) {
			return;
		}
		RootPanel.get("main-content").clear();
	}

	@Override
	public String getId() {
		return "home";
	}

	@Override
	public String getAcronym() {
		// TODO Auto-generated method stub
		return null;
	}

}