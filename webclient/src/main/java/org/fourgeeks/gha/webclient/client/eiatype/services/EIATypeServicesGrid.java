package org.fourgeeks.gha.webclient.client.eiatype.services;

import org.fourgeeks.gha.webclient.client.UI.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class EIATypeServicesGrid extends ListGrid implements ResizeHandler {

	@Override
	protected Canvas createRecordComponent(final ListGridRecord record, Integer colNum) {  
		  
        String fieldName = this.getFieldName(colNum);  
        
        if (fieldName.equals("nParte")) {
        	DynamicForm recordform = new DynamicForm();
        	final GHATextItem textbox = new GHATextItem();
            recordform.setItems(textbox);
        	
            textbox.addChangeHandler(new ChangeHandler() {
				@Override
				public void onChange(ChangeEvent event) {
					// TODO Auto-generated method stub
                    SC.say("Textbox value changed:"+textbox.getValue());  
				}
			});  
            return recordform;  
        } else {  
            return null;  
        }  

    }
	
	public EIATypeServicesGrid() {
		GHAUiHelper.addResizeHandler(this);
		setWidth100();
		setHeight(GHAUiHelper.getGridSize(30));	
		setEmptyMessage("No existen servicios para mostrar.");

		setAlternateRecordStyles(false);
		setCanResizeFields(false);
//		setShowFilterEditor(true);

		ListGridField idGridField = new ListGridField("id", "No");
		idGridField.setAlign(Alignment.CENTER);

		ListGridField codServGridField = new ListGridField("codServ", "Cód. Servicio");
		codServGridField.setAlign(Alignment.CENTER);
		
		ListGridField nameGridField = new ListGridField("nameServicio", "Nombre Servicio");
		nameGridField.setAlign(Alignment.CENTER);
		
		ListGridField usoServicioGridField = new ListGridField("usoServicio", "Uso del Servicio");
		usoServicioGridField.setAlign(Alignment.CENTER);
		
		ListGridField typeGridField = new ListGridField("type", "Tipo");
		typeGridField.setAlign(Alignment.CENTER);
		
		ListGridField idInstGridField = new ListGridField("idInst", "ID Instalación");
		idInstGridField.setAlign(Alignment.CENTER);

		ListGridField nameInstGridField = new ListGridField("nameInst", "Nombre de Instalación que suministra el Servicio");
		nameInstGridField.setAlign(Alignment.CENTER);
			

		setFields(idGridField, codServGridField, nameGridField, usoServicioGridField, typeGridField, idInstGridField, nameInstGridField);
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getGridSize(30));	
	}

}
