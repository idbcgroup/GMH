package org.fourgeeks.gha.webclient.server.login;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.fourgeeks.gha.domain.ess.auth.SSOUser;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.logs.LogonLog;
import org.fourgeeks.gha.ejb.ess.auth.FunctionBpuServiceRemote;
import org.fourgeeks.gha.ejb.ess.auth.SSOUserServiceRemote;
import org.fourgeeks.gha.ejb.log.LogonLogServiceRemote;
import org.fourgeeks.gha.ejb.msg.MessageServiceRemote;
import org.fourgeeks.gha.webclient.client.login.GWTLoginService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author alacret
 * 
 */
@WebServlet(urlPatterns = { "/webclient/login" })
public class GWTLoginServiceImpl extends RemoteServiceServlet implements
		GWTLoginService {

	private static final long serialVersionUID = 1L;
	private Bpu bpu;
	private final static Logger logger = Logger
			.getLogger(GWTLoginServiceImpl.class.getName());

	@EJB(lookup = "java:global/ear-1/ejb-1/LogonLogService")
	LogonLogServiceRemote logService;

	@EJB(lookup = "java:global/ear-1/ejb-1/SSOUserService")
	SSOUserServiceRemote ssoUserService;

	@EJB(lookup = "java:global/ear-1/ejb-1/AppFormViewFunctionBpuService")
	FunctionBpuServiceRemote bpuFunctionService;

	@EJB(lookup = "java:global/ear-1/ejb-1/MessageService!"
			+ "org.fourgeeks.gha.ejb.msg.MessageServiceRemote")
	MessageServiceRemote messageService;

	/**
	 * @return true if there is a user logged in
	 * 
	 */
	@Override
	public boolean isLogged() {
		final HttpServletRequest request = getThreadLocalRequest();
		return !(request.getSession().getAttribute("user") == null);
	}

	@Override
	public void logOut() {
		final HttpServletRequest request = this.perThreadRequest.get();
		bpu = null;
		try {
			final HttpSession session = request.getSession();
			session.removeAttribute("user");
			session.removeAttribute("cause");
			request.logout();
		} catch (final ServletException e) {
			logger.info(e.getMessage());
		}
	}

	@Override
	public Bpu getLoggedUser() throws GHAEJBException {
		final HttpServletRequest request = getThreadLocalRequest();
		final String ipAdd = request.getRemoteAddr().toString();

		final HttpSession session = request.getSession();
		SSOUser ssoUser = null;
		try {
			ssoUser = ssoUserService.findByUsername((String) session
					.getAttribute("user"));
		} catch (final GHAEJBException e1) {
			logService.log(new LogonLog(null, e1.getGhaMessage(), ipAdd));
			throw e1;
		}
		bpu = ssoUser.getBpu();
		bpu.setSessionId(session.getId());
		bpu.setPermissions(bpuFunctionService.getFunctionByBpu(bpu));
		return bpu;
	}
}