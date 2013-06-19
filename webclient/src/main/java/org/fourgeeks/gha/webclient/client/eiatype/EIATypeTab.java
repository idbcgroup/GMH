package org.fourgeeks.gha.webclient.client.eiatype;

import org.fourgeeks.gha.webclient.client.UI.GHATab;
import org.fourgeeks.gha.webclient.client.UI.GHATabHeader;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.smartgwt.client.widgets.layout.VLayout;

public class EIATypeTab extends GHATab {

	public static final String ID = "eiatype";
	private static final String TITLE = "Tipos de equipo";
	private GHATabHeader header;
	private EIATypeTopSection topSection;
	private EIATypeInternalTabset bottomTabset;

	public EIATypeTab() {
		super();
		header = new GHATabHeader(this);
		header.setTitle(TITLE);

		// Bottom Section: SubTabs de Info
		bottomTabset = new EIATypeInternalTabset();
		// Top Section: form de muestra de la info del EIA
		topSection = new EIATypeTopSection(bottomTabset.getEquiposSubTab().getEquiposGrid());
		
		// Creacion de la tab de EIA
		VLayout verticalPanel = new VLayout();
		verticalPanel.setBackgroundColor("#E0E0E0");
		
		verticalPanel.addMember(topSection);
		verticalPanel.addMember(GHAUiHelper.verticalGraySeparator(GHAUiHelper.V_SEPARATOR_HEIGHT+"px"));
		verticalPanel.addMember(bottomTabset);
		addMember(verticalPanel);
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