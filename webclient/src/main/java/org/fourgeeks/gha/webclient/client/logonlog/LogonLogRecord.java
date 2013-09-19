/**
 * 
 */
package org.fourgeeks.gha.webclient.client.logonlog;

import org.fourgeeks.gha.domain.logs.LogonLog;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;

/**
 * @author alacret
 * 
 */
public class LogonLogRecord extends GHAGridRecord<LogonLog> {
	private LogonLog logonLog;

	/**
	 * @param logonLog
	 */
	public LogonLogRecord(LogonLog logonLog) {
		this.logonLog = logonLog;
		setAttribute("time", logonLog.getTimestamp());
		setAttribute("msg", logonLog.getMessage().getMessage());
		setAttribute("ipAdd", logonLog.getIpAdd());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord#toEntity()
	 */
	@Override
	public LogonLog toEntity() {
		return this.logonLog;
	}

}
