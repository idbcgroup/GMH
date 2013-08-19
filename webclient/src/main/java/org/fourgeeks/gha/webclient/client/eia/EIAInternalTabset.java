package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.eia.component.EIAComponentSubTab;
import org.fourgeeks.gha.webclient.client.eia.cost.EIACostSubTab;
import org.fourgeeks.gha.webclient.client.eia.information.EIAInformationSubTab;
import org.fourgeeks.gha.webclient.client.eia.maintenanceplan.EIAMaintPlanSubTab;
import org.fourgeeks.gha.webclient.client.eia.movements.EIAMovementsSubTab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.widgets.tab.TabSet;

public class EIAInternalTabset extends TabSet implements
		EIATypeSelectionListener, EIASelectionListener, ResizeHandler {

	private EIAInformationSubTab eiaInformationSubTab;
	private EIAComponentSubTab eiaPartsSubTab;
//	private EIAMaterialSubTab eiaMaterialsSubTab;
	private EIAMaintPlanSubTab eiaPlanSubTab;
	private EIACostSubTab eiaCostsSubTab;
	private EIAMovementsSubTab eiaMovementsSubTab;
		
	public EIAInternalTabset(EIATab eiaTab) {
		super();
		setWidth100();
		setHeight(GHAUiHelper.getBottomSectionHeight());

		GHAUiHelper.addGHAResizeHandler(this);

		eiaInformationSubTab = new EIAInformationSubTab(eiaTab);
		eiaPartsSubTab = new EIAComponentSubTab(eiaTab);
//		eiaMaterialsSubTab = new EIAMaterialSubTab(eiaTab);
		eiaPlanSubTab = new EIAMaintPlanSubTab(eiaTab);
		eiaCostsSubTab = new EIACostSubTab(eiaTab);
		eiaMovementsSubTab = new EIAMovementsSubTab(eiaTab);
		
		// Agregando las Subtabs
		addTab(eiaInformationSubTab);
		addTab(eiaPartsSubTab);
//		addTab(eiaMaterialsSubTab);
		addTab(eiaPlanSubTab);
		addTab(eiaCostsSubTab);
		addTab(eiaMovementsSubTab);

	}

	@Override
	public void select(Eia eia) {
		// TODO Implement select(eia) on each tab
//		eiaInformationSubTab.select(eia);
//		eiaPartesSubTab.select(eia);
//		eiaMaterialSubTab.select(eia);
//		eiaPlanSubTab.select(eia);
//		eiaCostosSubTab.select(eia);
//		eiaMovimientosSubTab.select(eia);
	}
	
	@Override
	public void select(EiaType eiaType) {
		eiaInformationSubTab.select(eiaType);
		eiaPartsSubTab.select(eiaType);
//		eiaMaterialsSubTab.select(eiaType);
		eiaPlanSubTab.select(eiaType);
		eiaCostsSubTab.select(eiaType);
		eiaMovementsSubTab.select(eiaType);
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getBottomSectionHeight());
	}

}
