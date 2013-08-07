package org.fourgeeks.gha.webclient.client.eiatype.spare;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaTypeSpare;

public class EIATypeSpareUtil {

	private EIATypeSpareUtil() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	public static EIATypeSpareRecord toGridRecord(EiaTypeSpare eiaTypeSpare) {
		return new EIATypeSpareRecord(eiaTypeSpare);
	}

	public static List<EIATypeSpareRecord> toGridRecords(List<EiaTypeSpare> eiaTypeSpares) {
		List<EIATypeSpareRecord> list = new ArrayList<EIATypeSpareRecord>();
		for (EiaTypeSpare eiaTypeRecord : eiaTypeSpares)
			list.add(toGridRecord(eiaTypeRecord));
		return list;
	}
	
}
