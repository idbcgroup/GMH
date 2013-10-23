package org.fourgeeks.gha.webclient.client.eia.material;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.material.MaterialSearchForm;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author jfuentes
 * 
 */
public class EIAMaterialGridPanel extends VLayout {

	private EIAMaterialGrid eiaMaterialGrid = new EIAMaterialGrid();

	public EIAMaterialGridPanel() {
		setWidth100();
		setBackgroundColor("#E0E0E0");
		setStyleName("sides-padding padding-top");// Esto es VUDU!

		GHALabel title = new GHALabel("Materiales");

		// //////Botones laterales

		VLayout sideButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/new.png", new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						new MaterialSearchForm().open();
					}
				}), new GHAImgButton("../resources/icons/edit.png"),
				new GHAImgButton("../resources/icons/delete.png"),
				new GHAImgButton("../resources/icons/set.png",
						new ClickHandler() {
							@Override
							public void onClick(ClickEvent event) {
							}
						}));

		HLayout mainPanel = new HLayout();
		mainPanel.addMembers(eiaMaterialGrid, sideButtons);

		addMembers(title, mainPanel);
	}
}