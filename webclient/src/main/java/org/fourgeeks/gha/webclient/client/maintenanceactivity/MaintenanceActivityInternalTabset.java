package org.fourgeeks.gha.webclient.client.maintenanceactivity;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.activities.ActivitiesSubTab;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.asociatedprotocols.AsociatedMaintenanceProtocolsSubTab;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.information.MaintenanceActivityInformationSubTab;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.widgets.tab.TabSet;

public class MaintenanceActivityInternalTabset extends TabSet implements
		EIATypeSelectionListener, EIASelectionListener, ResizeHandler {

	private MaintenanceActivityInformationSubTab maintenanceActivityInformationSubTab;
	private AsociatedMaintenanceProtocolsSubTab asociatedMaintenanceProtocolSubTab;
	private ActivitiesSubTab activitySubTab;
//	private EIAMaintPlanSubTab eiaPlanSubTab;
//	private EIACostSubTab eiaCostsSubTab;
//	private EIAMovementsSubTab eiaMovementsSubTab;
		
	public MaintenanceActivityInternalTabset(MaintenanceActivityTab mpTab) {
		super();
		setWidth100();
		setHeight(GHAUiHelper.getBottomSectionHeight());

		GHAUiHelper.addGHAResizeHandler(this);

		maintenanceActivityInformationSubTab = new MaintenanceActivityInformationSubTab(mpTab);
		asociatedMaintenanceProtocolSubTab = new AsociatedMaintenanceProtocolsSubTab(mpTab);
		activitySubTab = new ActivitiesSubTab(mpTab);
//		eiaPlanSubTab = new EIAMaintPlanSubTab(mpTab);
//		eiaCostsSubTab = new EIACostSubTab(mpTab);
//		eiaMovementsSubTab = new EIAMovementsSubTab(mpTab);
		
		// Agregando las Subtabs
		addTab(maintenanceActivityInformationSubTab);
		addTab(asociatedMaintenanceProtocolSubTab);
		addTab(activitySubTab);
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
		maintenanceActivityInformationSubTab.select(eiaType);
		asociatedMaintenanceProtocolSubTab.select(eiaType);
		activitySubTab.select(eiaType);
//		eiaPlanSubTab.select(eiaType);
//		eiaCostsSubTab.select(eiaType);
//		eiaMovementsSubTab.select(eiaType);
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getBottomSectionHeight());
	}

}
