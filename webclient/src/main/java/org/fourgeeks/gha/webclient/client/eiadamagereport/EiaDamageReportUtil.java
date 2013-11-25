package org.fourgeeks.gha.webclient.client.eiadamagereport;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaDamageReport;
import org.fourgeeks.gha.webclient.client.eiatype.damageandplanification.EiaDamageReportRecord;

import com.smartgwt.client.util.DateUtil;
import com.smartgwt.client.util.LogicalDate;
import com.smartgwt.client.util.LogicalTime;

public class EiaDamageReportUtil {
	public static List<EiaDamageReportRecord> toGridRecords(
			List<EiaDamageReport> eiaDamageReportRecords) {
		List<EiaDamageReportRecord> list = new ArrayList<EiaDamageReportRecord>();
		for (EiaDamageReport eia : eiaDamageReportRecords)
			list.add(new EiaDamageReportRecord(eia));
		return list;
	}

	public static Date getDatetime(LogicalDate date, LogicalTime time) {
		if (date == null)
			return null;

		Date datetime = new Date(DateUtil.combineLogicalDateAndTime(date, time)
				.getTime());

		return datetime;
	}
}
