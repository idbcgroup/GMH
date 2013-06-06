package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.webclient.client.UI.GHATab;
import org.fourgeeks.gha.webclient.client.UI.GHATabHeader;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.smartgwt.client.widgets.layout.VLayout;

public class EIATab extends GHATab {

	public static final String ID = "eia";
	private static final String TITLE = "Equipos";
	private GHATabHeader header;
	{

	}

	public EIATab() {
		super();
		header = new GHATabHeader(this);
		header.setTitle(TITLE);
		VLayout verticalPanel = new VLayout();
		EIAGridPanel grid = new EIAGridPanel();

		verticalPanel.setBackgroundColor("#E0E0E0");
		verticalPanel
				.addMember(new EIATopSection(grid.getEiaTypeGrid()));
		verticalPanel.addMember(GHAUiHelper.verticalGraySeparator("10px"));
		verticalPanel.addMember(grid);

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