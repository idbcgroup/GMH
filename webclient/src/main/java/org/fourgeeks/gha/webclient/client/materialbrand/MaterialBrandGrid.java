/**
 * 
 */
package org.fourgeeks.gha.webclient.client.materialbrand;

import org.fourgeeks.gha.domain.glm.MaterialBrand;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;

/**
 * @author emiliot
 * 
 */
public class MaterialBrandGrid extends GhaGrid<MaterialBrand> {
	public MaterialBrandGrid() {
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
		GHAGridField brandGridField = new GHAGridField("brand",
				GHAStrings.get("brand"));
		GHAGridField categoryGridField = new GHAGridField("category",
				GHAStrings.get("category"));
		setFields(codeGridField, categoryGridField, nameGridField,
				descriptionGridField, modelGridField, extCodeGridField,
				brandGridField);
	}
}
