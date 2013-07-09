package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHATab;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.smartgwt.client.widgets.layout.VLayout;

public class EIATab extends GHATab {

	public static final String ID = "eia";
	private static final String TITLE = "Equipos";
	private EIATopSection topSection;
	private EIAInternalTabset bottomTabset;

	private EiaType eiaType;

	public EIATab(EiaType eiaType) {
		super();
		this.eiaType = eiaType;
		getHeader().setTitle(TITLE);

		topSection = new EIATopSection(this);
		bottomTabset = new EIAInternalTabset(this);
		topSection.AddEIATypeSelectionListener(bottomTabset);

		// Creacion de la tab de EIA
		VLayout verticalPanel = new VLayout();
		verticalPanel.setBackgroundColor("#E0E0E0");

		verticalPanel.addMember(topSection);
		verticalPanel.addMember(GHAUiHelper
				.verticalGraySeparator(GHAUiHelper.V_SEPARATOR_HEIGHT + "px"));
		verticalPanel.addMember(bottomTabset);
		addMember(verticalPanel);

	}

	@Override
	protected void onDraw() {
		if (eiaType == null)
			topSection.search();
	}

	@Override
	public String getId() {
		return ID;
	}

}