package org.fourgeeks.gha.webclient.client.eiatype.maintenance;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASubTab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeTab;
import org.fourgeeks.gha.webclient.client.eiatype.maintenance.plan.EIATypeMaintenanceGridPanel;

import com.smartgwt.client.widgets.tab.events.TabDeselectedEvent;
import com.smartgwt.client.widgets.tab.events.TabDeselectedHandler;

/**
 * @author alacret, emiliot
 * 
 */
public class EIATypeMaintenanceSubTab extends GHASubTab implements
		EIATypeSelectionListener {

	private EIATypeMaintenanceGridPanel eiaTypeMaintenanceGridPanel;

	/**
	 * @param tab
	 */
	public EIATypeMaintenanceSubTab(EIATypeTab tab) {
		super("Mantenimiento", tab);
		setDisabled(true);
		
		//register as eiatype listener with the tab
		tab.addEiaTypeSelectionListener(this);
		
		eiaTypeMaintenanceGridPanel = new EIATypeMaintenanceGridPanel();
		addGHAClosableHandler(eiaTypeMaintenanceGridPanel);
		addGHAHideableHandler(eiaTypeMaintenanceGridPanel);
		setPane(eiaTypeMaintenanceGridPanel);
		
		addTabDeselectedHandler(new TabDeselectedHandler() {
			@Override
			public void onTabDeselected(TabDeselectedEvent event) {
				eiaTypeMaintenanceGridPanel.hide();
			}
		});
	}

	@Override
	public void select(EiaType eiaType) {
		eiaTypeMaintenanceGridPanel.select(eiaType);
		setDisabled(false);
	}

}
