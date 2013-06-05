package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.webclient.client.UI.GHATab;
import org.fourgeeks.gha.webclient.client.UI.GHATabHeader;

import com.smartgwt.client.widgets.Label;
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
		verticalPanel.addMember(new Label("EIA"));
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