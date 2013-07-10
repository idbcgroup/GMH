package org.fourgeeks.gha.webclient.client.eiatype.maintenance;

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

public class EIATypeMaintenanceProtocolGrid extends ListGrid implements ResizeHandler{

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
	
	public EIATypeMaintenanceProtocolGrid() {
		GHAUiHelper.addResizeHandler(this);
		setWidth100();
		setHeight(GHAUiHelper.getGridSize(30));
		setEmptyMessage("No existe material para mostrar.");

		setAlternateRecordStyles(false);
		setCanResizeFields(false);
		setShowFilterEditor(true);

		ListGridField idGridField = new ListGridField("id", "No. Sec.");
		idGridField.setAlign(Alignment.CENTER);

		ListGridField descriptionGridField = new ListGridField("description", "Descripción paso Protocolo");
		descriptionGridField.setAlign(Alignment.CENTER);
		
		ListGridField materialsField = new ListGridField("mats", "Materiales");
		materialsField.setAlign(Alignment.CENTER);
		
		ListGridField toolsGridField = new ListGridField("tools", "Herramientas");
		toolsGridField.setAlign(Alignment.CENTER);
		
		ListGridField obsGridField = new ListGridField("obs", "Observaciones/Notas");
		obsGridField.setAlign(Alignment.CENTER);
		
		ListGridField subProtocolGridField = new ListGridField("suPat", "Sub-protocolo");
		obsGridField.setAlign(Alignment.CENTER);
		
		setFields(idGridField, descriptionGridField, materialsField, toolsGridField, obsGridField, subProtocolGridField);
	}

	@Override
	public void onResize(ResizeEvent event) {
		// TODO Auto-generated method stub
		setHeight(GHAUiHelper.getGridSize(30));
	}

}
