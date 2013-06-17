package org.fourgeeks.gha.webclient.client.eia.repuestos;

import com.google.gwt.user.client.Event;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.BackgroundRepeat;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIARepuestosGridPanel extends HLayout {

	private EIARepuestosGrid eiaRepuestosGrid = new EIARepuestosGrid();
	private EIAConsumiblesGrid eiaConsumiblesGrid = new EIAConsumiblesGrid();
	private EIAServiciosGrid eiaServiciosGrid = new EIAServiciosGrid();
	private EIAMaterialEspecialGrid eiaMaterialEspecialGrid= new EIAMaterialEspecialGrid();
	
	public EIARepuestosGridPanel() {
		setWidth100();
		setBackgroundColor("#E0E0E0");
		setStyleName("sides-padding top-padding");// Esto es VUDU!
		
		VLayout mainPanel = new VLayout();
		Label title = new Label("<h3>Repuestos/Consumibles/Servicios de EIA/Materiales Especiales</h3>");
		title.setHeight(35);
		title.setWidth100();
		title.setStyleName("title-label");
		
		DynamicForm options = new DynamicForm();
		options.setWidth("500px");
		
		RadioGroupItem optionsRB = new RadioGroupItem();
		optionsRB.setTitle("Tipo");
		optionsRB.setValueMap("Repuesto","Consumible", "Servicios", "Material Especial");
		optionsRB.setValue("Repuesto");
		optionsRB.setVertical(false);
		options.setItems(optionsRB);
		
		final VLayout gridContainer = new VLayout();
        gridContainer.addMembers(eiaRepuestosGrid,eiaConsumiblesGrid, eiaServiciosGrid, eiaMaterialEspecialGrid);
        eiaConsumiblesGrid.hide();
        eiaServiciosGrid.hide();
        eiaMaterialEspecialGrid.hide();
        
        optionsRB.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				String type= (String) event.getValue();
				if (type.equals("Repuesto")){
					eiaRepuestosGrid.show();
					eiaConsumiblesGrid.hide();
					eiaServiciosGrid.hide();
					eiaMaterialEspecialGrid.hide();
				}else if(type.equals("Consumible")){
					eiaRepuestosGrid.hide();
					eiaConsumiblesGrid.show();
					eiaServiciosGrid.hide();
					eiaMaterialEspecialGrid.hide();
				}else if(type.equals("Servicios")){
					eiaRepuestosGrid.hide();
					eiaConsumiblesGrid.hide();
					eiaServiciosGrid.show();
					eiaMaterialEspecialGrid.hide();
				}else if(type.equals("Material Especial")){
					eiaRepuestosGrid.hide();
					eiaConsumiblesGrid.hide();
					eiaServiciosGrid.hide();
					eiaMaterialEspecialGrid.show();
				}				
			}
		});
        
	    mainPanel.addMembers(title,options,gridContainer);
		
// //////Botones laterales
		VLayout sideButtons = new VLayout();
		sideButtons.setWidth(30);
		sideButtons.setLayoutMargin(5);
		// botones1.setBackgroundImage("../resources/img/botonBox.jpg");
		sideButtons.setBackgroundColor("#E0E0E0");
		sideButtons.setBackgroundRepeat(BackgroundRepeat.REPEAT_Y);
		sideButtons.setMembersMargin(10);
		sideButtons.setDefaultLayoutAlign(Alignment.CENTER);

		ImgButton addButton = new ImgButton();
		// addButton.sinkEvents(Event.MOUSEEVENTS);
		addButton.setSrc("../resources/icons/new.png");
		addButton.setShowRollOver(false);
		addButton.setSize("20px", "20px");
		
		addButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
//				form.animateShow(AnimationEffect.FLY);
			}
		});
		Img editButton = new Img("../resources/icons/edit.png");
		editButton.setSize("20px", "20px");
		Img deleteButton = new Img("../resources/icons/delete.png");
		deleteButton.setSize("20px", "20px");

		ImgButton setsButton = new ImgButton();
		setsButton.sinkEvents(Event.MOUSEEVENTS);
		setsButton.setSrc("../resources/icons/set.png");
		setsButton.setShowRollOver(false);
		setsButton.setSize("20px", "20px");
		setsButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
//				EIARecord selectedRecord = (EIARecord) eiaTypeGrid.getSelectedRecord();
//				History.newItem("eia/" + selectedRecord.getCode());
			}
		});

		sideButtons.addMembers(addButton, editButton, deleteButton, setsButton);
		
		addMembers(mainPanel, sideButtons);
		
	}

}
