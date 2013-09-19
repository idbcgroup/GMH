/**
 * 
 */
package org.fourgeeks.gha.webclient.client.logonlog;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.logs.LogonLog;

/**
 * @author alacret
 * 
 */
public class LogonLogUtil {
	private LogonLogUtil() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	/**
	 * @param logonLog
	 * @return a LogonRecord represented by this LogonLog
	 */
	public static LogonLogRecord toGridRecord(LogonLog logonLog) {
		return new LogonLogRecord(logonLog);
	}

	/**
	 * @param entities
	 * @return a list of logonlogREcords
	 */
	public static List<LogonLogRecord> toGridRecords(List<LogonLog> entities) {
		List<LogonLogRecord> list = new ArrayList<LogonLogRecord>();
		for (LogonLog logonLog : entities)
			list.add(new LogonLogRecord(logonLog));
		return list;
	}
}
