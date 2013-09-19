package org.fourgeeks.gha.webclient.client.log;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.EJBException;
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
	 * @throws EJBException
	 */
	public List<LogonLog> getLogsByBpu(Bpu bpu) throws EJBException;
}
