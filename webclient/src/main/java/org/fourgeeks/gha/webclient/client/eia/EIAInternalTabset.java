package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.eia.component.EIAComponentSubTab;
import org.fourgeeks.gha.webclient.client.eia.cost.EIACostSubTab;
import org.fourgeeks.gha.webclient.client.eia.equipment.EIAEquipmentSubTab;
import org.fourgeeks.gha.webclient.client.eia.maintenance.EIAMaintenanceSubTab;
import org.fourgeeks.gha.webclient.client.eia.maintenance_plan.EIAPlanSubTab;
import org.fourgeeks.gha.webclient.client.eia.movements.EIAMovementsSubTab;
import org.fourgeeks.gha.webclient.client.eia.replacements.EIAReplacementsSubTab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.widgets.tab.TabSet;

public class EIAInternalTabset extends TabSet implements
		EIATypeSelectionListener, ResizeHandler {

	private EIAEquipmentSubTab eiaEquiposSubTab = new EIAEquipmentSubTab();
	private EIAComponentSubTab eiaPartesSubTab = new EIAComponentSubTab();
	private EIAReplacementsSubTab eiaRepuestosSubTab = new EIAReplacementsSubTab();
	private EIAMaintenanceSubTab eiaMantenimientoSubTab = new EIAMaintenanceSubTab();
	private EIAPlanSubTab eiaPlanSubTab = new EIAPlanSubTab();
	private EIACostSubTab eiaCostosSubTab = new EIACostSubTab();
	private EIAMovementsSubTab eiaMovimientosSubTab = new EIAMovementsSubTab();

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
