package org.fourgeeks.gha.webclient.client.eia;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.Eia;

public class EIAUtil {

	private EIAUtil() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	public static EIARecord toGridRecord(Eia eia) {
		return new EIARecord(eia);
	}

	public static List<EIARecord> toGridRecords(List<Eia> eias) {
		List<EIARecord> list = new ArrayList<EIARecord>();
		for (Eia eia : eias)
			list.add(new EIARecord(eia));
		return list;
	}

}