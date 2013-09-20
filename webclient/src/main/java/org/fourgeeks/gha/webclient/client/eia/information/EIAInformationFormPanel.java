package org.fourgeeks.gha.webclient.client.eia.information;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAImgButton;
import org.fourgeeks.gha.webclient.client.eia.EIAForm;

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
public class EIAInformationFormPanel extends VLayout implements GHAClosable,
		GHAHideable {

	/**
	 * @param eiaEquipmentSubTab
	 * 
	 */
	private EIAForm eiaForm = new EIAForm();
	private Eia firstEia;

	public EIAInformationFormPanel() {
		super();
		setWidth100();
		setBackgroundColor("#E0E0E0");
		setStyleName("sides-padding padding-top");// Esto es VUDU!
		Label title = new Label("<h3>Caracteristicas del EIA</h3>");
		title.setHeight(30);
		title.setWidth100();
		title.setStyleName("title-label");

		VLayout sideButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/save.png", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						save();
					}
				}), new GHAImgButton("../resources/icons/undo.png",
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						// undo();
					}
				}));

		HLayout formPanel = new HLayout();
		formPanel.addMembers(eiaForm, sideButtons);

		addMembers(title, formPanel);

	}

	protected void save() {
		eiaForm.update();

	}

	/**
	 * @param array
	 */
	public void setData(ListGridRecord[] array) {
		// eiaGrid.setData(array);
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	public void setEia(Eia eia) {
		this.firstEia = eia;
		eiaForm.setEia(eia);

	}
}