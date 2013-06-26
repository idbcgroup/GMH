package org.fourgeeks.gha.webclient.client.eiatype;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.eiatype.caracteristicas.EIATypeCaracteristicasSubTab;
import org.fourgeeks.gha.webclient.client.eiatype.equipos.EIATypeEquiposSubTab;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.widgets.tab.TabSet;

public class EIATypeInternalTabset extends TabSet implements
		EIATypeSelectionListener, GHAClosable, ResizeHandler {

	private EIATypeCaracteristicasSubTab caracteristicasSubTab;
	private EIATypeEquiposSubTab equiposSubTab;

	public EIATypeInternalTabset(EIATypeTab tab) {
		super();
		GHAUiHelper.addResizeHandler(this);
		
		setWidth100();
		setHeight(GHAUiHelper.getBottomSectionHeight());
		equiposSubTab = new EIATypeEquiposSubTab(tab);
		caracteristicasSubTab = new EIATypeCaracteristicasSubTab(tab);

		// Agregando las Subtabs
		addTab(caracteristicasSubTab);
		addTab(equiposSubTab);
	}

	@Override
	public void select(EiaType eiaType) {
		caracteristicasSubTab.select(eiaType);
		equiposSubTab.select(eiaType);
	}

	@Override
	public void close() {
		equiposSubTab.close();
		caracteristicasSubTab.close();		
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getBottomSectionHeight());		
	}
}