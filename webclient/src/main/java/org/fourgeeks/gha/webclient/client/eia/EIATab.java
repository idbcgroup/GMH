package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHATab;
import org.fourgeeks.gha.webclient.client.UI.GHATabHeader;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.smartgwt.client.widgets.layout.VLayout;

public class EIATab extends GHATab {

	public static final String ID = "eia";
	private static final String TITLE = "Equipos";
	private GHATabHeader header;
	private EIATopSection topSection;
	private EIAInternalTabset bottomTabset;
	
	private EiaType eiaType;

	public EIATab() {
		super();
		header = new GHATabHeader(this);
		header.setTitle(TITLE);
		
		// Top Section: form de muestra de la info del EIA
		topSection = new EIATopSection();
		// Bottom Section: SubTabs de Info
		bottomTabset = new EIAInternalTabset();
		
		topSection.AddEIATypeSelectionListener(bottomTabset);
		
		// Creacion de la tab de EIA
		VLayout verticalPanel = new VLayout();
		verticalPanel.setBackgroundColor("#E0E0E0");
		
		verticalPanel.addMember(topSection);
		verticalPanel.addMember(GHAUiHelper.verticalGraySeparator(GHAUiHelper.V_SEPARATOR_HEIGHT+"px"));
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
	public GHATabHeader getHeader() {
		return header;
	}

	@Override
	public void close() {
		removeFromParent();
	}

}