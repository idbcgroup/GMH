package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.eia.component.EIAPartesSubTab;
import org.fourgeeks.gha.webclient.client.eia.costos.EIACostosSubTab;
import org.fourgeeks.gha.webclient.client.eia.equipos.EIAEquiposSubTab;
import org.fourgeeks.gha.webclient.client.eia.mantenimiento.EIAMantenimientoSubTab;
import org.fourgeeks.gha.webclient.client.eia.movimientos.EIAMovimientosSubTab;
import org.fourgeeks.gha.webclient.client.eia.plan_mantenimiento.EIAPlanSubTab;
import org.fourgeeks.gha.webclient.client.eia.repuestos.EIARepuestosSubTab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.widgets.tab.TabSet;

public class EIAInternalTabset extends TabSet implements
		EIATypeSelectionListener, ResizeHandler {

	private EIAEquiposSubTab eiaEquiposSubTab = new EIAEquiposSubTab();
	private EIAPartesSubTab eiaPartesSubTab = new EIAPartesSubTab();
	private EIARepuestosSubTab eiaRepuestosSubTab = new EIARepuestosSubTab();
	private EIAMantenimientoSubTab eiaMantenimientoSubTab = new EIAMantenimientoSubTab();
	private EIAPlanSubTab eiaPlanSubTab = new EIAPlanSubTab();
	private EIACostosSubTab eiaCostosSubTab = new EIACostosSubTab();
	private EIAMovimientosSubTab eiaMovimientosSubTab = new EIAMovimientosSubTab();

	public EIAInternalTabset(EIATab eiaTab) {
		super();
		setWidth100();
		setHeight(GHAUiHelper.getBottomSectionHeight());

		GHAUiHelper.addResizeHandler(this);

		// TODO : pasar la referencia del eiaTab a los constructores de los
		// subtabs

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

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getBottomSectionHeight());
		RootPanel.get("main-content").setHeight(
				GHAUiHelper.getTabHeight() + "px");
	}

}
