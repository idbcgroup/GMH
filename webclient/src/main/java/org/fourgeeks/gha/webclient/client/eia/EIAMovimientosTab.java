package org.fourgeeks.gha.webclient.client.eia;

import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;

public class EIAMovimientosTab extends Tab {
	
	public EIAMovimientosTab() {
		setTitle("Movimientos");
		setPaneMargin(0);
		
		setPane(new VLayout());
	}
}
