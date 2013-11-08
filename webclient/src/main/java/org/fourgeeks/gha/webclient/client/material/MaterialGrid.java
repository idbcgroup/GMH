package org.fourgeeks.gha.webclient.client.material;

import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;

/**
 * @author emiliot
 * 
 */
public class MaterialGrid extends GhaGrid<Material> {
	/**
	 * 
	 */
	public MaterialGrid() {
		setEmptyMessage(GHAStrings.get("no-materials-to-show"));

		GHAGridField codeGridField = new GHAGridField("code",
				GHAStrings.get("code"));
		GHAGridField nameGridField = new GHAGridField("name",
				GHAStrings.get("name"));
		GHAGridField descriptionGridField = new GHAGridField("description",
				GHAStrings.get("description"));
		GHAGridField modelGridField = new GHAGridField("model",
				GHAStrings.get("model"));
		GHAGridField extCodeGridField = new GHAGridField("extCode",
				GHAStrings.get("external-code"));
		GHAGridField typeGridField = new GHAGridField("type",
				GHAStrings.get("type"));

		setFields(codeGridField, nameGridField, descriptionGridField,
				modelGridField, extCodeGridField, typeGridField);
	}
}
