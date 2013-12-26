package org.fourgeeks.gha.webclient.client.maintenanceplan;

import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAInternalTabSet;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.information.MaintenanceActivitySubTab;
import org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.AsociatedEiaTypeSubTab;
import org.fourgeeks.gha.webclient.client.maintenanceplan.eianoservice.EiaNoServiceSubTab;
import org.fourgeeks.gha.webclient.client.maintenanceplan.eiaplanification.EiaPlanificationSubTab;
import org.fourgeeks.gha.webclient.client.maintenanceplan.information.MaintenancePlanInformationSubTab;
import org.fourgeeks.gha.webclient.client.maintenanceplan.maintenanceprotocols.MaintenanceProtocolsSubTab;

import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.widgets.tab.Tab;

/**
 * @author emiliot, naramirez
 */
public class MaintenancePlanInternalTabset extends GHAInternalTabSet implements
		ResizeHandler, MaintenancePlanSelectionListener {

	private final MaintenancePlanInformationSubTab maintenancePlanInfoSubTab;
	private final AsociatedEiaTypeSubTab maintenancePlanEquipmentTypeSubTab;
	private final EiaPlanificationSubTab maintenancePlanEquipmentSubTab;
	private final EiaNoServiceSubTab maintenancePlanEquipmentNoServiceSubTab;
	private final MaintenanceProtocolsSubTab maintenancePlanProtocolsSubTab;
	private final MaintenanceActivitySubTab maintenanceActivitySubTab;

	/**
	 * @param panel
	 */
	public MaintenancePlanInternalTabset(MaintenancePlanPanel panel) {
		super(panel);
		maintenancePlanInfoSubTab = new MaintenancePlanInformationSubTab(panel);
		maintenancePlanEquipmentTypeSubTab = new AsociatedEiaTypeSubTab(panel);
		maintenancePlanEquipmentSubTab = new EiaPlanificationSubTab(panel);
		maintenancePlanEquipmentNoServiceSubTab = new EiaNoServiceSubTab(panel);
		maintenancePlanProtocolsSubTab = new MaintenanceProtocolsSubTab(panel);
		maintenanceActivitySubTab = new MaintenanceActivitySubTab(panel);

		maintenancePlanProtocolsSubTab
				.addMaintenanceProtocolsSelectionListener(maintenancePlanInfoSubTab);

		// Agregando las Subtabs
		addTab(maintenancePlanInfoSubTab);
		addTab(maintenancePlanProtocolsSubTab);
		addTab(maintenanceActivitySubTab);
		addTab(maintenancePlanEquipmentTypeSubTab);
		addTab(maintenancePlanEquipmentSubTab);
		addTab(maintenancePlanEquipmentNoServiceSubTab);

	}

	@Override
	public void select(MaintenancePlan maintenancePlan) {
		show();
	}

	@Override
	public void show() {
		Tab selectedTab = getSelectedTab();
		if (selectedTab == maintenancePlanInfoSubTab)
			maintenancePlanInfoSubTab.show();
		else if (selectedTab == maintenancePlanEquipmentTypeSubTab) {
			maintenancePlanEquipmentTypeSubTab.getPane().show();
		} else if (selectedTab == maintenancePlanEquipmentSubTab) {
			maintenancePlanEquipmentSubTab.getPane().show();
		} else if (selectedTab == maintenancePlanProtocolsSubTab) {
			maintenancePlanProtocolsSubTab.getPane().show();
		} else if (selectedTab == maintenanceActivitySubTab) {
			maintenanceActivitySubTab.openFirstSection();
		}
		animateShow(AnimationEffect.FADE);
	}
}
