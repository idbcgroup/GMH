package org.fourgeeks.gha.webclient.client.eiatype.material;

import org.fourgeeks.gha.domain.gmh.EiaTypeMaterial;
import org.fourgeeks.gha.webclient.client.UI.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.GhaGrid;

/**
 * @author alacret
 * 
 */
public class EiaTypeMaterialGrid extends GhaGrid<EiaTypeMaterial> {

	public EiaTypeMaterialGrid() {
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