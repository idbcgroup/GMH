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
		setFields(new GHAGridField("module", "Módulo"), new GHAGridField(
				"screen", "Pantalla"), new GHAGridField("view", "Vista"),
				new GHAGridField("function", "Función"), activeField);
	}

	public GHAGridField getActiveField() {
		return activeField;
	}

}
