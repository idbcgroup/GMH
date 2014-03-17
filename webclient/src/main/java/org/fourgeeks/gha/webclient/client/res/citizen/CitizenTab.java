package org.fourgeeks.gha.webclient.client.res.citizen;

import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATab;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATabHeader;
import org.fourgeeks.gha.webclient.client.citizen.CitizenModel;
import org.fourgeeks.gha.webclient.client.res.citizen.body.CitizenRESInternalTabSet;
import org.fourgeeks.gha.webclient.client.res.citizen.top.CitizenRESTopInformationview;

import com.google.gwt.user.client.History;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class CitizenTab extends GHATab {

	private final String patientId;
	private final CitizenRESTopInformationview top;
	private final CitizenRESInternalTabSet body;
	private final CitizenTabTools tools;

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

		top = new CitizenRESTopInformationview();
		addClosableListener(top);
		addHideableListener(top);
		body = new CitizenRESInternalTabSet();
		addClosableListener(body);
		addHideableListener(body);

		final VLayout verticalLayout = new VLayout();
		verticalLayout.addMembers(top,GHAUiHelper.verticalGraySeparator(GHAUiHelper.V_SEPARATOR_HEIGHT + "px"),body);

		final HLayout bodyLayout = new HLayout();
		tools = new CitizenTabTools();
		addClosableListener(tools);
		addHideableListener(tools);

		bodyLayout.addMembers(verticalLayout,tools);

		addMember(bodyLayout);

		CitizenModel.find(Long.valueOf(citizenId),
				new GHAAsyncCallback<Citizen>() {

			@Override
			public void onSuccess(Citizen citizen) {
				header.setContents(citizen.getFirstName() + " "
						+ citizen.getFirstLastName());
				top.onCitizenSelect(citizen);
				body.onCitizenSelect(citizen);
			}
		});
	}

	@Override
	public void show() {
		super.show();
		top.show();
		body.show();
		tools.show();
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
