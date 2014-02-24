package org.fourgeeks.gha.webclient.client.user.permissions;

import org.fourgeeks.gha.domain.ess.ui.ViewPermission;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;

import com.smartgwt.client.types.ListGridFieldType;

/**
 * @author alacret
 * 
 */
public class ViewPermissionGrid extends GhaGrid<ViewPermission> {

	private GHAGridField activeField;

	{
		activeField = new GHAGridField("active", "Activo");
		activeField.setCanEdit(true);
		activeField.setType(ListGridFieldType.BOOLEAN);
	}

	/**
	 * 
	 */
	public ViewPermissionGrid() {
		super();
		setEmptyMessage("No existen datos que mostrar");
		setFields(new GHAGridField("module", "Módulo"), new GHAGridField(
				"screen", "Pantalla"), new GHAGridField("view", "Vista"),
				new GHAGridField("function", "Función"), activeField);
	}

	/**
	 * @return the activefield
	 * 
	 */
	public GHAGridField getActiveField() {
		return activeField;
	}

}
