package org.fourgeeks.gha.webclient.client.material;

import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.webclient.client.UI.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.GhaGrid;

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
		
		GHAGridField idGridField = new GHAGridField("id", "No");
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

		setFields(idGridField, codeGridField, nameGridField,
				descriptionGridField, modelGridField, extCodeGridField);
	}

}