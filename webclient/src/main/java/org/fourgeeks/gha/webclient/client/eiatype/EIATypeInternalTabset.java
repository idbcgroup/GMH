package org.fourgeeks.gha.webclient.client.eiatype;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.eiatype.component.EIATypeComponentSubTab;
import org.fourgeeks.gha.webclient.client.eiatype.equipment.EIATypeEquipmentSubTab;
import org.fourgeeks.gha.webclient.client.eiatype.information.EIATypeInformationSubTab;
import org.fourgeeks.gha.webclient.client.eiatype.maintenance.EIATypeMaintenanceSubTab;
import org.fourgeeks.gha.webclient.client.eiatype.material.EIATypeMaterialSubTab;
import org.fourgeeks.gha.webclient.client.eiatype.utility.EIATypeUtilitySubTab;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.widgets.tab.TabSet;

/**
 * @author alacret
 * 
 */
public class EIATypeInternalTabset extends TabSet implements ResizeHandler {

	private EIATypeInformationSubTab infoSubTab;
	private EIATypeEquipmentSubTab equipementsSubTab;
	private EIATypeComponentSubTab partsSubTab;
	private EIATypeMaterialSubTab materialSubTab;
	private EIATypeUtilitySubTab servicesSubTab;
	private EIATypeMaintenanceSubTab maintenanceSubTab;

	/**
	 * @param tab
	 */
	public EIATypeInternalTabset(EIATypeTab tab) {
		super();
		GHAUiHelper.addGHAResizeHandler(this);
		setWidth100();
		setHeight(GHAUiHelper.getBottomSectionHeight());
		equipementsSubTab = new EIATypeEquipmentSubTab(tab);
		infoSubTab = new EIATypeInformationSubTab(tab);
		partsSubTab = new EIATypeComponentSubTab(tab);
		materialSubTab = new EIATypeMaterialSubTab(tab);
		servicesSubTab = new EIATypeUtilitySubTab(tab);
		maintenanceSubTab = new EIATypeMaintenanceSubTab(tab);

		// Agregando las Subtabs
		addTab(infoSubTab);
		addTab(equipementsSubTab);
		addTab(partsSubTab);
		addTab(materialSubTab);
		addTab(servicesSubTab);
		addTab(maintenanceSubTab);
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getBottomSectionHeight());
	}
}