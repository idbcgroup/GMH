package org.fourgeeks.gha.webclient.client.eia.material;

import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;

/**
 * @author alacret
 * 
 */
public class EIAMaterialGrid extends GhaGrid<Material> {

	/**
	 * 
	 */
	public EIAMaterialGrid() {
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
		GHAGridField quantityGridField = new GHAGridField("cant", "Cantidad");

		setFields(idGridField, codeGridField, nameGridField,
				descriptionGridField, modelGridField, extCodeGridField,
				typeGridField,extProviderGridField, quantityGridField);
	}

}