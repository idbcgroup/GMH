package org.fourgeeks.gha.webclient.client.eiatype;

import org.fourgeeks.gha.webclient.client.UI.GHATab;
import org.fourgeeks.gha.webclient.client.UI.GHATabHeader;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.smartgwt.client.widgets.layout.VLayout;

public class EIATypeTab extends GHATab {

	public static final String ID = "eia-tab";

	private static final String TITLE = "Tipos de equipo";

	private GHATabHeader header;

	public EIATypeTab() {
		super();
		setID(ID);
		header = new GHATabHeader();
		header.setTitle(TITLE);
		// setPaneMargin(0);
		VLayout verticalPanel = new VLayout();
		verticalPanel.setBackgroundColor("#E0E0E0");
		verticalPanel.addMember(new EIATypeSearchFormSection());
		verticalPanel.addMember(GHAUiHelper.verticalGraySeparator());
		verticalPanel.addMember(new EIATypeGridPanel());
		addMember(verticalPanel);
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
