package org.fourgeeks.gha.webclient.client.eiatype;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.eiatype.component.EIATypeComponentSubTab;
import org.fourgeeks.gha.webclient.client.eiatype.equipment.EIATypeEquipmentSubTab;
import org.fourgeeks.gha.webclient.client.eiatype.information.EIATypeInformationSubTab;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.widgets.tab.TabSet;

public class EIATypeInternalTabset extends TabSet implements
		EIATypeSelectionListener, GHAClosable, ResizeHandler {

	private EIATypeInformationSubTab caracteristicasSubTab;
	private EIATypeEquipmentSubTab equiposSubTab;
	private EIATypeComponentSubTab partesSubTab;

	public EIATypeInternalTabset(EIATypeTab tab) {
		super();
		GHAUiHelper.addResizeHandler(this);
		
		setWidth100();
		setHeight(GHAUiHelper.getBottomSectionHeight());
		equiposSubTab = new EIATypeEquipmentSubTab(tab);
		caracteristicasSubTab = new EIATypeInformationSubTab(tab);
		partesSubTab = new EIATypeComponentSubTab();

		// Agregando las Subtabs
		addTab(caracteristicasSubTab);
		addTab(equiposSubTab);
		addTab(partesSubTab);
	}

	@Override
	public void select(EiaType eiaType) {
		caracteristicasSubTab.select(eiaType);
		equiposSubTab.select(eiaType);
		partesSubTab.select(eiaType);
	}

	@Override
	public void close() {
		equiposSubTab.close();
		caracteristicasSubTab.close();	
		partesSubTab.close();
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getBottomSectionHeight());		
	}
}