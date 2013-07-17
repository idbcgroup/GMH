package org.fourgeeks.gha.webclient.client.eia.equipment;

import org.fourgeeks.gha.webclient.client.UI.GHAButton;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.eia.EIAAddForm;

import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class EIAEquipmentGridPanel extends VLayout {

	private EIAAddForm form;
	private EIAEquipmentGrid eiaGrid;
	{
		eiaGrid = new EIAEquipmentGrid();
	}

	/**
	 * @param eiaEquipmentSubTab
	 * 
	 */
	public EIAEquipmentGridPanel(EIAEquipmentSubTab eiaEquipmentSubTab) {
		super();
		form = new EIAAddForm();
		form.addEiaSelectionListener(eiaEquipmentSubTab);
		setWidth100();
		setBackgroundColor("#E0E0E0");
		setStyleName("sides-padding top-padding");// Esto es VUDU!
		Label title = new Label("<h3>Lista de Equipos por Tipo EIA</h3>");
		title.setHeight(30);
		title.setWidth100();
		title.setStyleName("title-label");
		addMember(title);

		VLayout sideButtons = GHAUiHelper.createBar(new GHAButton(
				"../resources/icons/new.png", new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						form.animateShow(AnimationEffect.FLY);
					}
				}), new GHAButton("../resources/icons/edit.png"),
				new GHAButton("../resources/icons/delete.png"));

		HLayout gridPanel = new HLayout();
		gridPanel.addMembers(eiaGrid, sideButtons);

		addMembers(gridPanel);

	}

	/**
	 * @param array
	 */
	public void setData(ListGridRecord[] array) {
		eiaGrid.setData(array);
	}
}