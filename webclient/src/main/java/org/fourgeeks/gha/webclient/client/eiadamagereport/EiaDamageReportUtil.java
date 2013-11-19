package org.fourgeeks.gha.webclient.client.eiadamagereport;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaDamageReport;
import org.fourgeeks.gha.webclient.client.eiatype.eiadamagereport.EiaDamageReportRecord;

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

	public static Date getDatetime(Date date, Date time) {
		if (date == null)
			return null;

		LogicalDate logicalDate = DateUtil.getLogicalDateOnly(date);
		LogicalTime logicalTime = DateUtil.getLogicalTimeOnly(time);

		Date datetime = (Date) DateUtil.combineLogicalDateAndTime(logicalDate,
				logicalTime);

		return datetime;
	}
}
