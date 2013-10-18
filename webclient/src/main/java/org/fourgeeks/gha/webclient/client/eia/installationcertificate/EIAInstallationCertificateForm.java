package org.fourgeeks.gha.webclient.client.eia.installationcertificate;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASlideInWindow;
import org.fourgeeks.gha.webclient.client.eia.EIAGrid;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIAInstallationCertificateForm extends GHASlideInWindow {

	private EIAGrid installationGrid;
	{
		installationGrid = new EIAGrid();
	}
	public EIAInstallationCertificateForm() {
		super(1);
		setTop(GHAUiHelper.getTopSpace());
		setHeight(GHAUiHelper.getTabHeight()-4);
		
////////Botones laterales
		VLayout sideButtons = GHAUiHelper.createBar(
	    		new GHAImgButton("../resources/icons/check.png", new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						// TODO Auto-generated method stub
					}
				}),
				GHAUiHelper.verticalGraySeparator("2px"),
	    		new GHAImgButton("../resources/icons/cancel.png", new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						hide();
					}
				}));
		
		HLayout topPanel = new HLayout();
		topPanel.setHeight(GHAUiHelper.INNER_TOP_SECTION_HEIGHT-30);
		topPanel.setStyleName("sides-padding");
		
		topPanel.addMembers(new LayoutSpacer(), sideButtons);
		
		GHALabel title = new GHALabel("Acta de Instalación");
		
		HLayout botPanel = new HLayout();
		botPanel.setStyleName("sides-padding padding-top");
		botPanel.addMembers(installationGrid);
		
		addMembers(title,topPanel,GHAUiHelper.verticalGraySeparator(GHAUiHelper.V_SEPARATOR_HEIGHT+"px"), botPanel);
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getTabHeight()-4);
	}

}
