package org.fourgeeks.gha.webclient.client.eiatype.materialbrand;

import org.fourgeeks.gha.domain.gmh.EiaTypeMaterialBrand;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;

import com.smartgwt.client.types.ListGridFieldType;

/**
 * @author emiliot
 * 
 */
public class EiaTypeMaterialBrandGrid extends GhaGrid<EiaTypeMaterialBrand> {
	private GHAGridField codeGridField;
	private GHAGridField nameGridField;
	private GHAGridField descriptionGridField;
	private GHAGridField modelGridField;
	private GHAGridField extCodeGridField;
	private GHAGridField amountGridField;
	private GHAGridField brandGridField;
	private GHAGridField categoryGridField;

	{
		codeGridField = new GHAGridField("code", GHAStrings.get("code"));
		nameGridField = new GHAGridField("name", GHAStrings.get("name"));
		descriptionGridField = new GHAGridField("description",
				GHAStrings.get("description"));
		modelGridField = new GHAGridField("model", GHAStrings.get("model"));
		extCodeGridField = new GHAGridField("extCode",
				GHAStrings.get("external-code"));
		amountGridField = new GHAGridField("amount", GHAStrings.get("amount"));
		amountGridField.setCanEdit(true);
		amountGridField.setType(ListGridFieldType.INTEGER);
		GHATextItem numerico = new GHATextItem();
		numerico.setKeyPressFilter("[0-9]");
		amountGridField.setEditorType(numerico);

		categoryGridField = new GHAGridField("category",
				GHAStrings.get("category"));
		brandGridField = new GHAGridField("brand", GHAStrings.get("brand"));
	}

	public EiaTypeMaterialBrandGrid() {
		setEmptyMessage(GHAStrings.get("empty-material-grid"));

		setFields(codeGridField, categoryGridField, nameGridField,
				descriptionGridField, modelGridField, extCodeGridField,
				brandGridField, amountGridField);
	}

	/**
	 * @return the amountGridField
	 */
	public GHAGridField getAmountGridField() {
		return amountGridField;
	}

}
