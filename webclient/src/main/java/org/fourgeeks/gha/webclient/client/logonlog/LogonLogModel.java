package org.fourgeeks.gha.webclient.client.logonlog;

import java.util.List;

import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.logs.LogonLog;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.log.GWTLogonLogServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;

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
		Window.alert("antes de llamar");
		logonLogservice.getLogsByBpu(bpu, callback);
	}

}
