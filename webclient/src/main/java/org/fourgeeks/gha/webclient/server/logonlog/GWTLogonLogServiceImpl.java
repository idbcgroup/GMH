package org.fourgeeks.gha.webclient.server.logonlog;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.logs.LogonLog;
import org.fourgeeks.gha.ejb.log.LogonLogServiceRemote;
import org.fourgeeks.gha.webclient.client.logonlog.GWTLogonLogService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author alacret
 * 
 */
@WebServlet(urlPatterns = { "/webclient/logonlog" })
public class GWTLogonLogServiceImpl extends RemoteServiceServlet implements
		GWTLogonLogService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB(lookup = "java:global/ear-1/ejb-1/LogonLogService")
	LogonLogServiceRemote logService;

	@Override
	public List<LogonLog> getLogsByBpu(Bpu bpu) throws GHAEJBException {
		return logService.getLogsByBpu(bpu);
	}

}
