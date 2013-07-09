package org.fourgeeks.gha.webclient.client.eiatype;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.eiatype.component.EIATypeComponentSubTab;
import org.fourgeeks.gha.webclient.client.eiatype.equipment.EIATypeEquipmentSubTab;
import org.fourgeeks.gha.webclient.client.eiatype.information.EIATypeInformationSubTab;
import org.fourgeeks.gha.webclient.client.eiatype.maintenance.EIATypeMaintenanceSubTab;
import org.fourgeeks.gha.webclient.client.eiatype.replacements.EIATypeReplacementsSubTab;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.widgets.tab.TabSet;

public class EIATypeInternalTabset extends TabSet implements
		EIATypeSelectionListener, GHAClosable, ResizeHandler {

	private EIATypeInformationSubTab infoSubTab;
	private EIATypeEquipmentSubTab equipementsSubTab;
	private EIATypeComponentSubTab partsSubTab;
	private EIATypeReplacementsSubTab replacementsSubTab;
	private EIATypeMaintenanceSubTab maintenanceSubTab;

	public EIATypeInternalTabset(EIATypeTab tab) {
		super();
		GHAUiHelper.addResizeHandler(this);
		
		setWidth100();
		setHeight(GHAUiHelper.getBottomSectionHeight());
		equipementsSubTab = new EIATypeEquipmentSubTab(tab);
		infoSubTab = new EIATypeInformationSubTab(tab);
		partsSubTab = new EIATypeComponentSubTab();
		replacementsSubTab = new EIATypeReplacementsSubTab();
		maintenanceSubTab = new EIATypeMaintenanceSubTab();
		
		// Agregando las Subtabs
		addTab(infoSubTab);
		addTab(equipementsSubTab);
		addTab(partsSubTab);
		addTab(replacementsSubTab);
		addTab(maintenanceSubTab);
	}

	@Override
	public void select(EiaType eiaType) {
		infoSubTab.select(eiaType);
		equipementsSubTab.select(eiaType);
		partsSubTab.select(eiaType);
		replacementsSubTab.select(eiaType);
		maintenanceSubTab.select(eiaType);
	}

	@Override
	public void close() {
		infoSubTab.close();	
		equipementsSubTab.close();
		partsSubTab.close();
		replacementsSubTab.close();
		maintenanceSubTab.close();
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getBottomSectionHeight());		
	}
}