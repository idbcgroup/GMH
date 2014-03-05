package org.fourgeeks.gha.webclient.client.res.citizen;

import org.fourgeeks.gha.webclient.client.UI.tabs.GHATab;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATabHeader;
import org.fourgeeks.gha.webclient.client.res.citizen.body.CitizenRESBottomBodyForm;
import org.fourgeeks.gha.webclient.client.res.citizen.top.CitizenRESTopInformationview;

import com.google.gwt.user.client.History;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

/**
 * @author alacret
 * 
 */
public class CitizenTab extends GHATab {

	private final String patientId;

	/**
	 * @param citizenId
	 * 
	 */
	public CitizenTab(final String citizenId) {
		this.patientId = citizenId;
		setBackgroundColor("cyan");
		header = new GHATabHeader(this);
		header.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				History.newItem("res/" + citizenId);
			}
		});
		addMember(new CitizenRESTopInformationview());
		addMember(new CitizenRESBottomBodyForm());
	}

	@Override
	public String getId() {
		return patientId;
	}

	@Override
	public void search() {
		//TODO:
	}

	@Override
	public String getTitleForHeader() {
		return "Patient : " + patientId;
	}

}
