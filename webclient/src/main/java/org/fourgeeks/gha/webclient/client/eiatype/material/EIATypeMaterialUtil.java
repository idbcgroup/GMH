package org.fourgeeks.gha.webclient.client.eiatype.material;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaTypeMaterial;

public class EIATypeMaterialUtil {

	private EIATypeMaterialUtil() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	public static EIATypeMaterialRecord toGridRecord(
			EiaTypeMaterial eiaTypeMaterial) {
		return new EIATypeMaterialRecord(eiaTypeMaterial);
	}
	
	public static List<EIATypeMaterialRecord> toGridRecords(
			List<EiaTypeMaterial> eiaTypeMaterials) {
		List<EIATypeMaterialRecord> list = new ArrayList<EIATypeMaterialRecord>();
		for (EiaTypeMaterial eiaTypeMaterial : eiaTypeMaterials)
			list.add(toGridRecord(eiaTypeMaterial));
		return list;
	}
	
}
