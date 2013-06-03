package org.fourgeeks.gha.webclient.client.eiatype;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaType;

public class EIAUtil {

	private EIAUtil() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	public static EIARecord toGridRecord(EiaType eiaType) {
		return new EIARecord(eiaType);
	}

	public static List<EIARecord> toGridRecords(List<EiaType> eiaTypes) {
		List<EIARecord> list = new ArrayList<EIARecord>();
		for (EiaType eiaType : eiaTypes)
			list.add(new EIARecord(eiaType));
		return list;
	}
}