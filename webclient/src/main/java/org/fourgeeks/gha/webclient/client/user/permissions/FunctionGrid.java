package org.fourgeeks.gha.webclient.client.user.permissions;

import org.fourgeeks.gha.domain.ess.Function;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;

import com.smartgwt.client.types.ListGridFieldType;

/**
 * @author alacret
 * 
 */
public class FunctionGrid extends GhaGrid<Function> {

	private GHAGridField activeField;

	{
		activeField = new GHAGridField("active", "Activo");
		activeField.setCanEdit(true);
		activeField.setType(ListGridFieldType.BOOLEAN);
	}

	/**
	 * 
	 */
	public FunctionGrid() {
		super();
		setEmptyMessage("No existen datos que mostrar");

		GHAGridField moduleField = new GHAGridField("module", "Módulo");
		GHAGridField screenField = new GHAGridField("screen", "Pantalla");
		GHAGridField viewField = new GHAGridField("view", "Vista");
		GHAGridField functionField = new GHAGridField("function", "Función");

		setFields(moduleField, screenField, viewField, functionField,
				activeField);
	}

}
