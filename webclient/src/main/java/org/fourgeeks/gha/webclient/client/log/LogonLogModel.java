package org.fourgeeks.gha.webclient.client.log;

import java.util.List;

import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.logs.LogonLog;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.client.GWT;

/**
 * @author alacret
 * 
 */
public class LogonLogModel {
	private static final GWTLogonLogServiceAsync logonLogservice = GWT
			.create(GWTLogonLogService.class);

	/**
	 * @param bpu
	 * @param callback
	 */
	public static void getLogsByBpu(Bpu bpu,
			GHAAsyncCallback<List<LogonLog>> callback) {
		logonLogservice.getLogsByBpu(bpu, callback);
	}

}
