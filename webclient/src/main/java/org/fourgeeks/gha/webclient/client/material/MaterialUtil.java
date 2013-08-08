package org.fourgeeks.gha.webclient.client.material;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.glm.Material;

/**
 * @author alacret Utilities for Materials
 */
public class MaterialUtil {

	private MaterialUtil() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	/**
	 * Converts a Material into a MaterialRecord
	 * 
	 * @param material
	 * @return the MaterialRecord that represents the Material
	 */
	public static MaterialRecord toGridRecord(Material material) {
		return new MaterialRecord(material);
	}

	/**
	 * Converts a Material list into a MaterialRecord list
	 * 
	 * @param materials
	 * @return the MaterialRecord list that represents the Material list
	 */
	public static List<MaterialRecord> toGridRecords(List<Material> materials) {
		List<MaterialRecord> list = new ArrayList<MaterialRecord>();
		for (Material material : materials)
			list.add(new MaterialRecord(material));
		return list;
	}

}
