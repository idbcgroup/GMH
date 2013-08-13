package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.eia.component.EIAComponentSubTab;
import org.fourgeeks.gha.webclient.client.eia.cost.EIACostSubTab;
import org.fourgeeks.gha.webclient.client.eia.information.EIAInformationSubTab;
import org.fourgeeks.gha.webclient.client.eia.maintenance.EIAMaintenanceSubTab;
import org.fourgeeks.gha.webclient.client.eia.maintenance_plan.EIAMantPlanSubTab;
import org.fourgeeks.gha.webclient.client.eia.material.EIAMaterialSubTab;
import org.fourgeeks.gha.webclient.client.eia.movements.EIAMovementsSubTab;
import org.fourgeeks.gha.webclient.client.eia.replacements.EIAReplacementsSubTab;
import org.fourgeeks.gha.webclient.client.eia.services.EIAServicesSubTab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.widgets.tab.TabSet;

public class EIAInternalTabset extends TabSet implements
		EIATypeSelectionListener, EIASelectionListener, ResizeHandler {

	private EIAInformationSubTab eiaInformationSubTab;
	private EIAComponentSubTab eiaPartesSubTab;
	private EIAReplacementsSubTab eiaRepuestosSubTab;
	private EIAMaterialSubTab eiaMaterialSubTab;
	private EIAServicesSubTab eiaServicesSubTab;
	private EIAMaintenanceSubTab eiaMantenimientoSubTab;
	private EIAMantPlanSubTab eiaPlanSubTab;
	private EIACostSubTab eiaCostosSubTab;
	private EIAMovementsSubTab eiaMovimientosSubTab;
		
	public EIAInternalTabset(EIATab eiaTab) {
		super();
		setWidth100();
		setHeight(GHAUiHelper.getBottomSectionHeight());

		GHAUiHelper.addGHAResizeHandler(this);

		eiaInformationSubTab = new EIAInformationSubTab(eiaTab);
		eiaPartesSubTab = new EIAComponentSubTab(eiaTab);
		eiaRepuestosSubTab = new EIAReplacementsSubTab(eiaTab);
		eiaMaterialSubTab = new EIAMaterialSubTab(eiaTab);
		eiaServicesSubTab = new EIAServicesSubTab(eiaTab);
		eiaMantenimientoSubTab = new EIAMaintenanceSubTab(eiaTab);
		eiaPlanSubTab = new EIAMantPlanSubTab(eiaTab);
		eiaCostosSubTab = new EIACostSubTab(eiaTab);
		eiaMovimientosSubTab = new EIAMovementsSubTab(eiaTab);
		
		// Agregando las Subtabs
		addTab(eiaInformationSubTab);
		addTab(eiaPartesSubTab);
		addTab(eiaRepuestosSubTab);
		addTab(eiaMaterialSubTab);
		addTab(eiaServicesSubTab);
		addTab(eiaMantenimientoSubTab);
		addTab(eiaPlanSubTab);
		addTab(eiaCostosSubTab);
		addTab(eiaMovimientosSubTab);

	}

	@Override
	public void select(Eia eia) {
		// TODO Implement select(eia) on each tab
//		eiaInformationSubTab.select(eia);
//		eiaPartesSubTab.select(eia);
//		eiaRepuestosSubTab.select(eia);
//		eiaMaterialSubTab.select(eia);
//		eiaServicesSubTab.select(eia);
//		eiaMantenimientoSubTab.select(eia);
//		eiaPlanSubTab.select(eia);
//		eiaCostosSubTab.select(eia);
//		eiaMovimientosSubTab.select(eia);
	}
	
	@Override
	public void select(EiaType eiaType) {
		eiaInformationSubTab.select(eiaType);
		eiaPartesSubTab.select(eiaType);
		eiaRepuestosSubTab.select(eiaType);
		eiaMaterialSubTab.select(eiaType);
		eiaServicesSubTab.select(eiaType);
		eiaMantenimientoSubTab.select(eiaType);
		eiaPlanSubTab.select(eiaType);
		eiaCostosSubTab.select(eiaType);
		eiaMovimientosSubTab.select(eiaType);
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getBottomSectionHeight());
	}

}
