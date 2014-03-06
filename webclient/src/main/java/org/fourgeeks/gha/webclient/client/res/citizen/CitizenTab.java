package org.fourgeeks.gha.webclient.client.res.citizen;

import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATab;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATabHeader;
import org.fourgeeks.gha.webclient.client.citizen.CitizenModel;
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
	private CitizenRESTopInformationview top;
	private CitizenRESBottomBodyForm body;
	{
		top = new CitizenRESTopInformationview();
		body = new CitizenRESBottomBodyForm();
	}

	/**
	 * @param citizenId
	 * 
	 */
	public CitizenTab(final String citizenId) {
		this.patientId = citizenId;
		header = new GHATabHeader(this);
		header.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				History.newItem("res/" + citizenId);
			}
		});

		addMember(top);
		addMember(body);

		CitizenModel.find(Long.valueOf(citizenId),
				new GHAAsyncCallback<Citizen>() {

			@Override
			public void onSuccess(Citizen citizen) {
				top.onCitizenSelect(citizen);
				body.onCitizenSelect(citizen);
			}
		});
	}

	@Override
	public String getId() {
		return patientId;
	}

	@Override
	public String getTitleForHeader() {
		return "Patient : " + patientId;
	}

	@Override
	public void search() {
		// TODO:
	}

}
