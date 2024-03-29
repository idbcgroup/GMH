package org.fourgeeks.gha.webclient.client.eiatype.component;

import org.fourgeeks.gha.domain.gmh.EiaTypeComponent;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;

import com.smartgwt.client.types.ListGridFieldType;

/**
 * @author emiliot
 * 
 */
public class EIATypeComponentGrid extends GhaGrid<EiaTypeComponent> {

	private GHAGridField requiredField;
	private GHAGridField replaceableField;
	private GHAGridField amountField;

	{
		requiredField = new GHAGridField("required", "Requerido");
		requiredField.setType(ListGridFieldType.BOOLEAN);
		requiredField.setCanEdit(true);
		replaceableField = new GHAGridField("replaceable", "Reemplazable");
		replaceableField.setCanEdit(true);
		replaceableField.setType(ListGridFieldType.BOOLEAN);
		amountField = new GHAGridField("amount", GHAStrings.get("amount"));
		amountField.setCanEdit(true);
		amountField.setType(ListGridFieldType.INTEGER);
		GHATextItem numerico = new GHATextItem();
		numerico.setKeyPressFilter("[0-9]");
		amountField.setEditorType(numerico);

	}

	/**
	 * 
	 */
	public EIATypeComponentGrid() {
		setEmptyMessage("No existen componentes para mostrar");

		setFields(new GHAGridField("code", "Codigo"), new GHAGridField("name",
				"Nombre"), new GHAGridField("parent", "Pertenece"),
				new GHAGridField("brand", "Marca"), new GHAGridField("model",
						"Modelo"), requiredField, replaceableField, amountField);
	}

	/**
	 * @return the required field
	 */
	public GHAGridField getRequiredField() {
		return requiredField;
	}

	/**
	 * @return the replaceable field
	 */
	public GHAGridField getReplaceableField() {
		return replaceableField;
	}

	public GHAGridField getAmountField() {
		return amountField;
	}

}
