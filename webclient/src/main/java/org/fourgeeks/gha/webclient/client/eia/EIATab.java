package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.webclient.client.UI.GHATab;
import org.fourgeeks.gha.webclient.client.UI.GHATabHeader;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.smartgwt.client.widgets.layout.VLayout;

public class EIATab extends GHATab {

	public static final String ID = "eia";
	private static final String TITLE = "Equipos";
	private GHATabHeader header;

	// private Eia eia;

	public EIATab() {
		super();
		header = new GHATabHeader(this);
		header.setTitle(TITLE);
		VLayout verticalPanel = new VLayout();
		verticalPanel.setBackgroundColor("#E0E0E0");
		// Top Section: Search form
		EIATopSection topSection = new EIATopSection();
		// Bottom Section: SubTabs de Info
		EIAInternalTabset bottomTabset = new EIAInternalTabset();
		topSection.AddEIATypeSelectionListener(bottomTabset);
		// Creacion de la tab de EIA
		verticalPanel.addMember(topSection);
		verticalPanel.addMember(GHAUiHelper.verticalGraySeparator("10px"));
		verticalPanel.addMember(bottomTabset);
		addMember(verticalPanel);

		// if (eia == null)
		// topSection.search();
	}

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public GHATabHeader getHeader() {
		return header;
	}

	@Override
	public void close() {
		removeFromParent();
	}

}