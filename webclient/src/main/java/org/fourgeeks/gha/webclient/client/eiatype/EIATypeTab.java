package org.fourgeeks.gha.webclient.client.eiatype;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHATab;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.smartgwt.client.widgets.layout.VLayout;

public class EIATypeTab extends GHATab implements EIATypeSelectionListener {

	public static final String ID = "eiatype";
	private static final String TITLE = "Tipos de equipo";
	private EIATypeTopSection topSection;
	private EiaType eiaType;
	private EIATypeInternalTabset bottomTabset;

	public EIATypeTab(EiaType eiaType) {
		super();
		this.eiaType = eiaType;
		getHeader().setTitle(TITLE);

		bottomTabset = new EIATypeInternalTabset(this);
		topSection = new EIATypeTopSection(this);

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

	@Override
	public void select(EiaType eiaType) {
		topSection.select(eiaType);
		bottomTabset.select(eiaType);
	}
}