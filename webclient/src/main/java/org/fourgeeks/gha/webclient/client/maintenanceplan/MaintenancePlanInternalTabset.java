package org.fourgeeks.gha.webclient.client.maintenanceplan;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.AsociatedEiaTypeSubTab;
import org.fourgeeks.gha.webclient.client.maintenanceplan.information.MaintenancePlanInformationSubTab;
import org.fourgeeks.gha.webclient.client.maintenanceplan.maintenanceprotocol.MaintenanceProtocolSubTab;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.widgets.tab.TabSet;

public class MaintenancePlanInternalTabset extends TabSet implements
		EIATypeSelectionListener, EIASelectionListener, ResizeHandler {

	private MaintenancePlanInformationSubTab maintenancePlanInformationSubTab;
	private AsociatedEiaTypeSubTab maintenancePlanEquipmentSubTab;
	private MaintenanceProtocolSubTab maintenanceProtocolSubTab;
//	private EIAMaintPlanSubTab eiaPlanSubTab;
//	private EIACostSubTab eiaCostsSubTab;
//	private EIAMovementsSubTab eiaMovementsSubTab;
		
	public MaintenancePlanInternalTabset(MaintenancePlanTab mpTab) {
		super();
		setWidth100();
		setHeight(GHAUiHelper.getBottomSectionHeight());

		GHAUiHelper.addGHAResizeHandler(this);

		maintenancePlanInformationSubTab = new MaintenancePlanInformationSubTab(mpTab);
		maintenancePlanEquipmentSubTab = new AsociatedEiaTypeSubTab(mpTab);
		maintenanceProtocolSubTab = new MaintenanceProtocolSubTab(mpTab);
//		eiaPlanSubTab = new EIAMaintPlanSubTab(mpTab);
//		eiaCostsSubTab = new EIACostSubTab(mpTab);
//		eiaMovementsSubTab = new EIAMovementsSubTab(mpTab);
		
		// Agregando las Subtabs
		addTab(maintenancePlanInformationSubTab);
		addTab(maintenancePlanEquipmentSubTab);
		addTab(maintenanceProtocolSubTab);
//		addTab(eiaPlanSubTab);
//		addTab(eiaCostsSubTab);
//		addTab(eiaMovementsSubTab);

	}

	@Override
	public void select(Eia eia) {
		// TODO Implement select(eia) on each tab
//		maintenancePlanInformationSubTab.select(eia);
//		eiaPartesSubTab.select(eia);
//		eiaMaterialSubTab.select(eia);
//		eiaPlanSubTab.select(eia);
//		eiaCostosSubTab.select(eia);
//		eiaMovimientosSubTab.select(eia);
	}
	
	@Override
	public void select(EiaType eiaType) {
		maintenancePlanInformationSubTab.select(eiaType);
		maintenancePlanEquipmentSubTab.select(eiaType);
		maintenanceProtocolSubTab.select(eiaType);
//		eiaPlanSubTab.select(eiaType);
//		eiaCostsSubTab.select(eiaType);
//		eiaMovementsSubTab.select(eiaType);
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getBottomSectionHeight());
	}

}
