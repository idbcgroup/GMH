package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.eia.component.EIAComponentSubTab;
import org.fourgeeks.gha.webclient.client.eia.information.EIAInformationSubTab;
import org.fourgeeks.gha.webclient.client.eia.reports.EIAReportsSubTab;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.widgets.tab.TabSet;

/**
 * @author alacret
 * 
 */
public class EIAInternalTabset extends TabSet implements ResizeHandler {

	private EIAInformationSubTab eiaInformationSubTab;
	private EIAComponentSubTab eiaPartsSubTab;
	private EIAReportsSubTab eiaReportsSubTab;

	// private EIAMaintPlanSubTab eiaPlanSubTab;
	// private EIACostSubTab eiaCostsSubTab;
	// private EIAMovementsSubTab eiaMovementsSubTab;

	/**
	 * @param eiaTab
	 */
	public EIAInternalTabset(EIATab eiaTab) {
		super();
		setWidth100();
		setHeight(GHAUiHelper.getBottomSectionHeight());
		GHAUiHelper.addGHAResizeHandler(this);
		eiaInformationSubTab = new EIAInformationSubTab(eiaTab);
		eiaPartsSubTab = new EIAComponentSubTab(eiaTab);
		eiaReportsSubTab = new EIAReportsSubTab(eiaTab);
		// eiaPlanSubTab = new EIAMaintPlanSubTab(eiaTab);
		// eiaCostsSubTab = new EIACostSubTab(eiaTab);
		// eiaMovementsSubTab = new EIAMovementsSubTab(eiaTab);

		// Agregando las Subtabs
		addTab(eiaInformationSubTab);
		addTab(eiaPartsSubTab);
		addTab(eiaReportsSubTab);
		// addTab(eiaPlanSubTab);
		// addTab(eiaCostsSubTab);
		// addTab(eiaMovementsSubTab);

	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getBottomSectionHeight());
	}

}
