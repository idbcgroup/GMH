package org.fourgeeks.gha.webclient.client.eia.installationcertificate;

import org.fourgeeks.gha.webclient.client.UI.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.GHASlideInWindow;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.eia.EIAGrid;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIAInstallationCertificateForm extends GHASlideInWindow {

	private EIAGrid installationGrid;
	{
		installationGrid = new EIAGrid(){
			@Override
			public void onResize(ResizeEvent event) {
				setHeight(GHAUiHelper.getGridSize(30));
			}
		};
		installationGrid.setHeight(GHAUiHelper.getGridSize(30));
		
	}
	public EIAInstallationCertificateForm() {
		super();
		setTop(110);
		setHeight(GHAUiHelper.getTabHeight());
				
		Label title = new Label("<h3>Acta de Instalación</h3>");
		title.setHeight(35);
		title.setWidth100();
		title.setStyleName("title-label");
		
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
		
		HLayout mainPanel = new HLayout();
		mainPanel.addMembers(installationGrid,sideButtons);
		
		addMembers(title, mainPanel);
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getTabHeight());
	}

}
