package org.fourgeeks.gha.webclient.client.eiadamagereport;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaDamageReport;
import org.fourgeeks.gha.webclient.client.eiatype.eiadamagereport.EiaDamageReportRecord;

public class EiaDamageReportUtil {
	public static List<EiaDamageReportRecord> toGridRecords(
			List<EiaDamageReport> eiaDamageReportRecords) {
		List<EiaDamageReportRecord> list = new ArrayList<EiaDamageReportRecord>();
		for (EiaDamageReport eia : eiaDamageReportRecords)
			list.add(new EiaDamageReportRecord(eia));
		return list;
	}
}
