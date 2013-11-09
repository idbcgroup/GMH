package org.fourgeeks.gha.webclient.client.materialcategory;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.glm.MaterialCategory;

/**
 * @author alacret Utilities for MaterialCategories
 */
public class MaterialCategoryUtil {

	private MaterialCategoryUtil() {
		throw new UnsupportedOperationException(
				"This class should not be instantiated");
	}

	/**
	 * Converts a Material into a MaterialRecord
	 * 
	 * @param material
	 * @return the MaterialRecord that represents the Material
	 */
	public static MaterialCategoryRecord toGridRecord(
			MaterialCategory materialCategory) {
		return new MaterialCategoryRecord(materialCategory);
	}

	/**
	 * Converts a Material list into a MaterialRecord list
	 * 
	 * @param materialsCategories
	 * @return the MaterialRecord list that represents the Material list
	 */
	public static List<MaterialCategoryRecord> toGridRecords(
			List<MaterialCategory> materialsCategories) {
		List<MaterialCategoryRecord> list = new ArrayList<MaterialCategoryRecord>();
		for (MaterialCategory materialCategory : materialsCategories)
			list.add(new MaterialCategoryRecord(materialCategory));
		return list;
	}

}
