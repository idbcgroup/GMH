package org.fourgeeks.gha.webclient.client.eiatype;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaType;

public class EIAUtil {

	private EIAUtil() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	public static EIATypeRecord toGridRecord(EiaType eiaType) {
		return new EIATypeRecord(eiaType);
	}

	public static List<EIATypeRecord> toGridRecords(List<EiaType> eiaTypes) {
		List<EIATypeRecord> list = new ArrayList<EIATypeRecord>();
		for (EiaType eiaType : eiaTypes)
			list.add(new EIATypeRecord(eiaType));
		return list;
	}
}