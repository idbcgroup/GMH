package org.fourgeeks.gha.webclient.client.material;

import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;

/**
 * @author alacret
 * 
 */
public class MaterialGrid extends GhaGrid<Material> {

	/**
	 * 
	 */
	public MaterialGrid() {
		setEmptyMessage("No existen Materiales para mostrar");

		GHAGridField codeGridField = new GHAGridField("code", "Código");
		GHAGridField nameGridField = new GHAGridField("name", "Nombre");
		GHAGridField descriptionGridField = new GHAGridField("description",
				"Descripción");
		GHAGridField modelGridField = new GHAGridField("model", "Modelo");
		GHAGridField extCodeGridField = new GHAGridField("extCode",
				"Código externo");
		GHAGridField typeGridField = new GHAGridField("type", "Tipo");
		GHAGridField extProviderGridField = new GHAGridField(
				"externalProvider", "Proveedor");

		setFields(codeGridField, nameGridField, descriptionGridField,
				modelGridField, extCodeGridField, typeGridField,
				extProviderGridField);
	}

}