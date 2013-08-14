package org.fourgeeks.gha.webclient.client.eiatype.component;

import org.fourgeeks.gha.domain.gmh.EiaTypeComponent;
import org.fourgeeks.gha.webclient.client.UI.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.GhaGrid;

import com.smartgwt.client.types.ListGridFieldType;

public class EIATypeComponentGrid extends GhaGrid<EiaTypeComponent> {

	private GHAGridField requiredField;
	private GHAGridField replaceableField;
	
	{
		requiredField = new GHAGridField("required", "Requerido");
		requiredField.setType(ListGridFieldType.BOOLEAN);
		requiredField.setCanEdit(true);
		replaceableField = new GHAGridField("replaceable", "Reemplazable");
		replaceableField.setCanEdit(true);
		replaceableField.setType(ListGridFieldType.BOOLEAN);
	}
	
	public EIATypeComponentGrid() {
		setEmptyMessage("No existen componentes para mostrar");
		
		setFields(new GHAGridField("id", "No"),
				new GHAGridField("code", "Codigo"),
				new GHAGridField("name", "Nombre"),
				new GHAGridField("brand", "Marca"),
				new GHAGridField("model", "Modelo"),
				requiredField,
				replaceableField
				);
	}
	
	public GHAGridField getRequiredField() {
		return requiredField;
	}
	
	public GHAGridField getReplaceableField() {
		return replaceableField;
	}
	
}
