package org.fourgeeks.gha.webclient.client.logonlog;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.logs.LogonLog;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author alacret
 * 
 */
@RemoteServiceRelativePath("logonlog")
public interface GWTLogonLogService extends RemoteService {

	/**
	 * @param bpu
	 * @return a list of logs by bpu
	 * @throws GHAEJBException
	 */
	public List<LogonLog> getLogsByBpu(Bpu bpu) throws GHAEJBException;
}
