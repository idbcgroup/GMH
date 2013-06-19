package org.fourgeeks.gha.webclient.client.eiatype;

import org.fourgeeks.gha.webclient.client.UI.GHATab;
import org.fourgeeks.gha.webclient.client.UI.GHATabHeader;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.smartgwt.client.widgets.layout.VLayout;

public class EIATypeTab extends GHATab {

	public static final String ID = "eiatype";
	private static final String TITLE = "Tipos de equipo";
	private GHATabHeader header;

	public EIATypeTab() {
		super();
		header = new GHATabHeader(this);

		header.setTitle(TITLE);
		VLayout verticalPanel = new VLayout();
		EIATypeGridPanel gridPanel = new EIATypeGridPanel();
		verticalPanel.setBackgroundColor("#E0E0E0");
		verticalPanel.addMember(new EIATypeTopSection(gridPanel
				.getEiaTypeGrid()));
		verticalPanel.addMember(GHAUiHelper.verticalGraySeparator(GHAUiHelper.V_SEPARATOR_HEIGHT+"px"));
		verticalPanel.addMember(gridPanel);
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