package org.fourgeeks.gha.webclient.client.maintenanceprotocol;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceprotocol.asociatedmaintenanceplan.AsociatedMaintenancePlanSubTab;
import org.fourgeeks.gha.webclient.client.maintenanceprotocol.information.MaintenanceProtocolInformationSubTab;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.widgets.tab.TabSet;

public class MaintenanceProtocolInternalTabset extends TabSet implements
		EIATypeSelectionListener, EIASelectionListener, ResizeHandler {

	private MaintenanceProtocolInformationSubTab maintenanceProtocolInformationSubTab;
	private AsociatedMaintenancePlanSubTab asociatedMaintenancePlanSubTab;
//	private MaintenanceProtocolSubTab maintenanceProtocolSubTab;
//	private EIAMaintPlanSubTab eiaPlanSubTab;
//	private EIACostSubTab eiaCostsSubTab;
//	private EIAMovementsSubTab eiaMovementsSubTab;
		
	public MaintenanceProtocolInternalTabset(MaintenanceProtocolTab mpTab) {
		super();
		setWidth100();
		setHeight(GHAUiHelper.getBottomSectionHeight());

		GHAUiHelper.addGHAResizeHandler(this);

		maintenanceProtocolInformationSubTab = new MaintenanceProtocolInformationSubTab(mpTab);
		asociatedMaintenancePlanSubTab = new AsociatedMaintenancePlanSubTab(mpTab);
//		maintenanceProtocolSubTab = new MaintenanceProtocolSubTab(mpTab);
//		eiaPlanSubTab = new EIAMaintPlanSubTab(mpTab);
//		eiaCostsSubTab = new EIACostSubTab(mpTab);
//		eiaMovementsSubTab = new EIAMovementsSubTab(mpTab);
		
		// Agregando las Subtabs
		addTab(maintenanceProtocolInformationSubTab);
		addTab(asociatedMaintenancePlanSubTab);
//		addTab(maintenanceProtocolSubTab);
//		addTab(eiaPlanSubTab);
//		addTab(eiaCostsSubTab);
//		addTab(eiaMovementsSubTab);

	}

	@Override
	public void select(Eia eia) {
//		maintenanceProtocolInformationSubTab.select(eia);
//		eiaPartesSubTab.select(eia);
//		eiaMaterialSubTab.select(eia);
//		eiaPlanSubTab.select(eia);
//		eiaCostosSubTab.select(eia);
//		eiaMovimientosSubTab.select(eia);
	}
	
	@Override
	public void select(EiaType eiaType) {
		maintenanceProtocolInformationSubTab.select(eiaType);
		asociatedMaintenancePlanSubTab.select(eiaType);
//		maintenanceProtocolSubTab.select(eiaType);
//		eiaPlanSubTab.select(eiaType);
//		eiaCostsSubTab.select(eiaType);
//		eiaMovementsSubTab.select(eiaType);
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getBottomSectionHeight());
	}

}
