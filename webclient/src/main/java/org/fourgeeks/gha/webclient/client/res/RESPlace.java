package org.fourgeeks.gha.webclient.client.res;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.exceptions.LoginNeededException;
import org.fourgeeks.gha.webclient.client.UI.exceptions.PermissionsNeededException;
import org.fourgeeks.gha.webclient.client.UI.places.GHAPlaceHeader;
import org.fourgeeks.gha.webclient.client.UI.places.NeedPermissionPlace;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATab;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATabPanel;
import org.fourgeeks.gha.webclient.client.citizen.CitizenAddForm;
import org.fourgeeks.gha.webclient.client.citizen.CitizenSearchForm;
import org.fourgeeks.gha.webclient.client.res.citizen.CitizenTab;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

/**
 * @author alacret
 * 
 */
public class RESPlace extends NeedPermissionPlace {
	private final GHATabPanel tabPanel = new GHATabPanel(GHAStrings.get("res"));
	private final CitizenAddForm citizenAddForm = new CitizenAddForm(
			GHAStrings.get("citizen-new"));
	private final CitizenSearchForm citizenSearchForm = new CitizenSearchForm(
			GHAStrings.get("citizen-search"));

	/**
	 * @param token
	 * @throws PermissionsNeededException
	 * @throws LoginNeededException
	 */
	public RESPlace(String token) throws LoginNeededException,
			PermissionsNeededException {
		super(token);
		header = new GHAPlaceHeader(this);
		addMember(tabPanel);
		tabPanel.addHeaderOption(GHAStrings.get("citizen-search"),
				"buscarButton", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						citizenSearchForm.open();
					}
				});
		tabPanel.addHeaderOption(GHAStrings.get("citizen-new"),
				"agregarButton", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						citizenAddForm.open();
					}
				});
		tabPanel.addHeaderOption(GHAStrings.get("close"), "cerrarButton",
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						// History.newItem("res/" + Math.round(Math.random() *
						// 10));
					}
				});

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
