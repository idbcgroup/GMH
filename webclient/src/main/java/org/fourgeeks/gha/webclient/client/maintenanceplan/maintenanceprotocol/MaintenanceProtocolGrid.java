package org.fourgeeks.gha.webclient.client.maintenanceplan.maintenanceprotocol;

import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;

import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class MaintenanceProtocolGrid extends GhaGrid<MaintenanceProtocol> {

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
	
	public MaintenanceProtocolGrid() {
		setEmptyMessage("No existen protocolos de mantenimiento asociados al plan.");

		GHAGridField idGridField = new GHAGridField("id", "No. Sec.");
		GHAGridField descriptionGridField = new GHAGridField("description", "Descripción paso Protocolo");
		GHAGridField materialsField = new GHAGridField("mats", "Materiales");
		GHAGridField toolsGridField = new GHAGridField("tools", "Herramientas");
		GHAGridField obsGridField = new GHAGridField("obs", "Observaciones/Notas");
		GHAGridField subProtocolGridField = new GHAGridField("suPat", "Sub-protocolo");
		
		setFields(idGridField, descriptionGridField, materialsField, toolsGridField, obsGridField, subProtocolGridField);
	}

}
