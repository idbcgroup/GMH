package org.fourgeeks.gha.webclient.client.emh;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.exceptions.LoginNeededException;
import org.fourgeeks.gha.webclient.client.UI.exceptions.PermissionsNeededException;
import org.fourgeeks.gha.webclient.client.UI.places.GHAPlaceHeader;
import org.fourgeeks.gha.webclient.client.UI.places.NeedPermissionPlace;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATab;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATabPanel;
import org.fourgeeks.gha.webclient.client.emh.patient.PatientTab;

import com.google.gwt.user.client.History;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

/**
 * @author alacret
 * 
 */
public class EMHPlace extends NeedPermissionPlace {
	private final GHATabPanel tabPanel = new GHATabPanel(GHAStrings.get("emh"));

	/**
	 * @param token
	 * @throws PermissionsNeededException
	 * @throws LoginNeededException
	 */
	public EMHPlace(String token) throws LoginNeededException,
			PermissionsNeededException {
		super(token);
		header = new GHAPlaceHeader(this);
		addMember(tabPanel);

		tabPanel.addHeaderOption(GHAStrings.get("search"), "buscarButton",
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						History.newItem("emh/" + Math.round(Math.random() * 10));
					}
				});
	}

	@Override
	public String getId() {
		return "emh";
	}

	@Override
	public String getAcronym() {
		return GHAStrings.get("hme");
	}

	@Override
	public void updateToken(String token) {
		int indexOf = token.indexOf("/");
		if (indexOf == -1)
			return;
		String patientId = token.substring(indexOf + 1);
		GHATab tab = tabPanel.getTabById(patientId);
		if (tab != null)
			tabPanel.showTab(patientId);
		else
			tabPanel.addAndShow(new PatientTab(patientId));
	}

}
