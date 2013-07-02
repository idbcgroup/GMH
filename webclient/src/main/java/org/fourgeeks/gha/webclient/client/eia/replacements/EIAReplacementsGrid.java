package org.fourgeeks.gha.webclient.client.eia.replacements;

import org.fourgeeks.gha.webclient.client.UI.GHATextItem;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class EIAReplacementsGrid extends ListGrid {

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
	
	public EIAReplacementsGrid() {
		setWidth100();
		setHeight("300px");
		setEmptyMessage("No existen repuestos para mostrar.");

		setAlternateRecordStyles(false);
		setCanResizeFields(false);
		setShowFilterEditor(true);

		ListGridField idGridField = new ListGridField("id", "No");
		idGridField.setAlign(Alignment.CENTER);

		ListGridField codRepGridField = new ListGridField("codRep", "Cód. Repuesto");
		codRepGridField.setAlign(Alignment.CENTER);
		
		ListGridField nameGridField = new ListGridField("nameRepuesto", "Nombre Repuesto");
		nameGridField.setAlign(Alignment.CENTER);
		
		ListGridField usoRepuestoGridField = new ListGridField("usoRepuesto", "Uso del Repuesto");
		usoRepuestoGridField.setAlign(Alignment.CENTER);
		
		ListGridField typeGridField = new ListGridField("type", "Tipo");
		typeGridField.setAlign(Alignment.CENTER);
		
		ListGridField cantGridField = new ListGridField("cant", "Cantidad");
		cantGridField.setAlign(Alignment.CENTER);

		ListGridField facilityGridField = new ListGridField("facility", "Ubicación");
		facilityGridField.setAlign(Alignment.CENTER);
		
		ListGridField availableGridField = new ListGridField("available", "Disponibles");
		availableGridField.setAlign(Alignment.CENTER);
			

		setFields(idGridField, codRepGridField, nameGridField, usoRepuestoGridField, typeGridField, cantGridField, facilityGridField, availableGridField);
	}

}
