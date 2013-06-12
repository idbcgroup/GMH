package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.eia.caracteristicas.EIAEquiposSubTab;
import org.fourgeeks.gha.webclient.client.eia.costos.EIACostosSubTab;
import org.fourgeeks.gha.webclient.client.eia.mantenimiento.EIAMantenimientoSubTab;
import org.fourgeeks.gha.webclient.client.eia.movimientos.EIAMovimientosSubTab;
import org.fourgeeks.gha.webclient.client.eia.partes.EIAPartesSubTab;
import org.fourgeeks.gha.webclient.client.eia.plan_mantenimiento.EIAPlanSubTab;
import org.fourgeeks.gha.webclient.client.eia.repuestos.EIARepuestosSubTab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.widgets.tab.TabSet;

public class EIAInternalTabset extends TabSet implements
		EIATypeSelectionListener {

	private EIAEquiposSubTab eiaEquiposSubTab = new EIAEquiposSubTab();
	private EIAPartesSubTab eiaPartesSubTab = new EIAPartesSubTab();
	private EIARepuestosSubTab eiaRepuestosSubTab = new EIARepuestosSubTab();
	private EIAMantenimientoSubTab eiaMantenimientoSubTab = new EIAMantenimientoSubTab();
	private EIAPlanSubTab eiaPlanSubTab = new EIAPlanSubTab();
	private EIACostosSubTab eiaCostosSubTab = new EIACostosSubTab();
	private EIAMovimientosSubTab eiaMovimientosSubTab = new EIAMovimientosSubTab();

	public EIAInternalTabset() {
		super();
		setWidth100();
		setHeight100();

		// Agregando las Subtabs
		addTab(eiaEquiposSubTab);
		addTab(eiaPartesSubTab);
		addTab(eiaRepuestosSubTab);
		addTab(eiaMantenimientoSubTab);
		addTab(eiaPlanSubTab);
		addTab(eiaCostosSubTab);
		addTab(eiaMovimientosSubTab);

	}

	@Override
	public void select(EiaType eiaType) {
		eiaEquiposSubTab.select(eiaType);
		eiaPartesSubTab.select(eiaType);
		eiaRepuestosSubTab.select(eiaType);
		eiaMantenimientoSubTab.select(eiaType);
		eiaPlanSubTab.select(eiaType);
		eiaCostosSubTab.select(eiaType);
		eiaMovimientosSubTab.select(eiaType);
	}

}
