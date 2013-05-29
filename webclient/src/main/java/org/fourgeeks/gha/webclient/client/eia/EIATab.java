package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.webclient.client.UI.GHATab;
import org.fourgeeks.gha.webclient.client.UI.GHATabHeader;

public class EIATab extends GHATab {

	public static final String ID = "eia-tab";

	private static final String TITLE = "Tipos de equipo";

	private GHATabHeader header;

	public EIATab() {
		super();
		setID(ID);
		header = new GHATabHeader();
		header.setTitle(TITLE);
	}

	@Override
	public void onClose() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public GHATabHeader getHeader() {
		return header;
	}

}
