package org.fourgeeks.gha.webclient.client.eia.eiadispatchment;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.imageitems.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASlideInWindow;
import org.fourgeeks.gha.webclient.client.UI.superclasses.labels.GHATopTitleLabel;
import org.fourgeeks.gha.webclient.client.eia.EIAGrid;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIADispatchmentForm extends GHASlideInWindow {

	private EIAGrid dispatchmentGrid;
	{
		dispatchmentGrid = new EIAGrid();
	}

	public EIADispatchmentForm() {
		super();

		// //////Botones laterales
		VLayout sideButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/check.png", new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						// TODO Auto-generated method stub
					}
				}), GHAUiHelper.verticalGraySeparator("2px"), new GHAImgButton(
				"../resources/icons/cancel.png", new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						hide();
					}
				}));

		HLayout topPanel = new HLayout();
		topPanel.setHeight(GHAUiHelper.DEFAULT_INNER_TOP_SECTION_HEIGHT - 30);
		topPanel.setStyleName("sides-padding");

		topPanel.addMembers(new LayoutSpacer(), sideButtons);

		GHATopTitleLabel title = new GHATopTitleLabel("Despacho de equipos");

		HLayout botPanel = new HLayout();
		botPanel.setStyleName("sides-padding padding-top");
		botPanel.addMembers(dispatchmentGrid);

		addMembers(title, topPanel,
				GHAUiHelper
						.verticalGraySeparator(GHAUiHelper.V_SEPARATOR_HEIGHT
								+ "px"), botPanel);
	}
}