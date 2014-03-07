/**
 * 
 */
package org.fourgeeks.gha.webclient.client.materialbrand;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.glm.MaterialBrand;

/**
 * @author emiliot
 * 
 */
public class MaterialBrandUtil {
	public static MaterialBrandRecord toGridRecord(MaterialBrand materialBrand) {
		return null;
	}

	public static List<MaterialBrandRecord> toGridRecords(
			List<MaterialBrand> materialBrands) {
		List<MaterialBrandRecord> list = new ArrayList<MaterialBrandRecord>();
		for (MaterialBrand materialBrand : materialBrands)
			list.add(new MaterialBrandRecord(materialBrand));
		return list;
	}

	private MaterialBrandUtil() {
		throw new UnsupportedOperationException(
				"This class should not be instantiated");
	}
}
