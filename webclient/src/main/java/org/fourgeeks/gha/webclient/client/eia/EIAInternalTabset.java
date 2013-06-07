package org.fourgeeks.gha.webclient.client.eia;

import com.smartgwt.client.widgets.tab.TabSet;

public class EIAInternalTabset extends TabSet {

	private EIACaracteristicasTab eiaCaracteristicasTab = new EIACaracteristicasTab();	
	
	public EIAInternalTabset() {
		super();		
		setWidth100();
		setHeight100();
		
		addTab(new EIACaracteristicasTab());
		addTab(new EIAPartesTab());
		addTab(new EIARepuestosTab());
		addTab(new EIAMantenimientoTab());
		addTab(new EIAPlanTab());
		addTab(new EIACostosTab());
		addTab(new EIAMovimientosTab());
		
	}

	public EIACaracteristicasTab getEiaCaracteristicasTab() {
		return eiaCaracteristicasTab;
	}
}
