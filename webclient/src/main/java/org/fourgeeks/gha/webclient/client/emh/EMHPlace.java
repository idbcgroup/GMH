package org.fourgeeks.gha.webclient.client.emh;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.exceptions.LoginNeededException;
import org.fourgeeks.gha.webclient.client.UI.exceptions.PermissionsNeededException;
import org.fourgeeks.gha.webclient.client.UI.places.GHAPlaceHeader;
import org.fourgeeks.gha.webclient.client.UI.places.NeedPermissionPlace;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATabPanel;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

/**
 * @author alacret
 * 
 */
public class EMHPlace extends NeedPermissionPlace {
	GHATabPanel tabPanel = new GHATabPanel(GHAStrings.get("emh"));

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
						tabPanel.addTab(new PatientTab("B"));
					}
				});
		tabPanel.addTab(new PatientTab("A"));
	}

	@Override
	public String getId() {
		return "emh";
	}

	@Override
	public String getAcronym() {
		return GHAStrings.get("hme");
	}

}
