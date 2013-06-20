package org.fourgeeks.gha.webclient.client.eiatype;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.eiatype.caracteristicas.EIATypeCaracteristicasSubTab;
import org.fourgeeks.gha.webclient.client.eiatype.equipos.EIATypeEquiposSubTab;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.widgets.tab.TabSet;

public class EIATypeInternalTabset extends TabSet implements
		EIATypeSelectionListener {

	private EIATypeCaracteristicasSubTab caracteristicasSubTab = new EIATypeCaracteristicasSubTab();
	private EIATypeEquiposSubTab equiposSubTab = new EIATypeEquiposSubTab();

	public EIATypeEquiposSubTab getEquiposSubTab() {
		return equiposSubTab;
	}

	public EIATypeInternalTabset() {
		super();
		setWidth100();
		setHeight(GHAUiHelper.getBottomSectionHeight());
		Window.addResizeHandler(new ResizeHandler() {
			@Override
			public void onResize(ResizeEvent event) {
				setHeight(GHAUiHelper.getBottomSectionHeight());
				RootPanel.get("main-content").setHeight(
						GHAUiHelper.calculateTabHeight() + "px");
			}
		});

		// Agregando las Subtabs
		addTab(caracteristicasSubTab);
		addTab(equiposSubTab);
	}

	@Override
	public void select(EiaType eiaType) {
		caracteristicasSubTab.select(eiaType);
		equiposSubTab.select(eiaType);
	}

}
