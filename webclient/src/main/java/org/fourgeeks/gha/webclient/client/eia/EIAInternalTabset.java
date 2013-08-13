package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.eia.component.EIAComponentSubTab;
import org.fourgeeks.gha.webclient.client.eia.cost.EIACostSubTab;
import org.fourgeeks.gha.webclient.client.eia.information.EIAInformationSubTab;
import org.fourgeeks.gha.webclient.client.eia.maintenanceplan.EIAMaintPlanSubTab;
import org.fourgeeks.gha.webclient.client.eia.movements.EIAMovementsSubTab;
import org.fourgeeks.gha.webclient.client.eia.spares.EIASpareSubTab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.widgets.tab.TabSet;

public class EIAInternalTabset extends TabSet implements
		EIATypeSelectionListener, ResizeHandler {

	private EIAInformationSubTab eiaInformationSubTab;
	private EIAComponentSubTab eiaPartesSubTab;
	private EIASpareSubTab eiaRepuestosSubTab;
	private EIAMaintPlanSubTab eiaPlanSubTab;
	private EIACostSubTab eiaCostosSubTab;
	private EIAMovementsSubTab eiaMovimientosSubTab;
		
	public EIAInternalTabset(EIATab eiaTab) {
		super();
		setWidth100();
		setHeight(GHAUiHelper.getBottomSectionHeight());

		GHAUiHelper.addGHAResizeHandler(this);

		eiaInformationSubTab = new EIAInformationSubTab(eiaTab);
		eiaPartesSubTab = new EIAComponentSubTab(eiaTab);
		eiaRepuestosSubTab = new EIASpareSubTab(eiaTab);
		eiaPlanSubTab = new EIAMaintPlanSubTab(eiaTab);
		eiaCostosSubTab = new EIACostSubTab(eiaTab);
		eiaMovimientosSubTab = new EIAMovementsSubTab(eiaTab);
		
		// Agregando las Subtabs
		addTab(eiaInformationSubTab);
		addTab(eiaPartesSubTab);
		addTab(eiaRepuestosSubTab);
		addTab(eiaPlanSubTab);
		addTab(eiaCostosSubTab);
		addTab(eiaMovimientosSubTab);

	}

	@Override
	public void select(EiaType eiaType) {
		eiaInformationSubTab.select(eiaType);
		eiaPartesSubTab.select(eiaType);
		eiaRepuestosSubTab.select(eiaType);
		eiaPlanSubTab.select(eiaType);
		eiaCostosSubTab.select(eiaType);
		eiaMovimientosSubTab.select(eiaType);
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getBottomSectionHeight());
	}

}
