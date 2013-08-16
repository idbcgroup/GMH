package org.fourgeeks.gha.webclient.client.eiatype;

import org.fourgeeks.gha.domain.gmh.EiaType;
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

public class EIATypeInternalTabset extends TabSet implements
		EIATypeSelectionListener, ResizeHandler {

	private EIATypeInformationSubTab infoSubTab;
	private EIATypeEquipmentSubTab equipementsSubTab;
	private EIATypeComponentSubTab partsSubTab;
	// private EIATypeSpareSubTab replacementsSubTab;
	private EIATypeMaterialSubTab materialSubTab;
	private EIATypeUtilitySubTab servicesSubTab;
	private EIATypeMaintenanceSubTab maintenanceSubTab;

	public EIATypeInternalTabset(EIATypeTab tab) {
		super();
		GHAUiHelper.addGHAResizeHandler(this);

		setWidth100();
		setHeight(GHAUiHelper.getBottomSectionHeight());
		equipementsSubTab = new EIATypeEquipmentSubTab(tab);
		infoSubTab = new EIATypeInformationSubTab(tab);
		partsSubTab = new EIATypeComponentSubTab(tab);
		// replacementsSubTab = new EIATypeSpareSubTab(tab);
		materialSubTab = new EIATypeMaterialSubTab(tab);
		servicesSubTab = new EIATypeUtilitySubTab(tab);
		maintenanceSubTab = new EIATypeMaintenanceSubTab(tab);

		// Agregando las Subtabs
		addTab(infoSubTab);
		addTab(equipementsSubTab);
		addTab(partsSubTab);
		// addTab(replacementsSubTab);
		addTab(materialSubTab);
		addTab(servicesSubTab);
		addTab(maintenanceSubTab);
	}

	@Override
	public void select(EiaType eiaType) {
		// Window.alalert("internatl select tab set");
		infoSubTab.select(eiaType);
		equipementsSubTab.select(eiaType);
		partsSubTab.select(eiaType);
		// replacementsSubTab.select(eiaType);
		materialSubTab.select(eiaType);
		servicesSubTab.select(eiaType);
		maintenanceSubTab.select(eiaType);
	}

	// @Override
	// public void close() {
	// infoSubTab.close();
	// equipementsSubTab.close();
	// partsSubTab.close();
	// replacementsSubTab.close();
	// consumablesSubTab.close();
	// servicesSubTab.close();
	// maintenanceSubTab.close();
	// }

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getBottomSectionHeight());
	}
}