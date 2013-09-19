package org.fourgeeks.gha.webclient.server.login;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.fourgeeks.gha.domain.enu.UserLogonStatusEnum;
import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.logs.LogonLog;
import org.fourgeeks.gha.domain.msg.Message;
import org.fourgeeks.gha.ejb.ess.SSOUserServiceRemote;
import org.fourgeeks.gha.ejb.gar.BpuFunctionServiceRemote;
import org.fourgeeks.gha.ejb.log.LogonLogServiceRemote;
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
	private final static Logger logger = Logger
			.getLogger(GWTLoginServiceImpl.class.getName());

	@EJB(name = "log.LogonLogService")
	LogonLogServiceRemote logService;

	@EJB(name = "ess.SSOUserService")
	SSOUserServiceRemote ssoUserService;

	@EJB(name = "gar.BpuFunctionService")
	BpuFunctionServiceRemote bpuFunctionService;

	/**
	 * @return true if there is a user logged in
	 * 
	 */
	public boolean isLogged() {
		HttpServletRequest request = this.perThreadRequest.get();
		return !(request.getUserPrincipal() == null);
	}

	@Override
	public Bpu login(String user, String password)
			throws IllegalArgumentException {
		HttpServletRequest request = this.perThreadRequest.get();
		String ipAdd = request.getRemoteAddr().toString();

		if (user.equals("") || password.equals(""))
			throw new IllegalArgumentException(
					"Debe indicar usuario y contraseña");

		HttpSession session = request.getSession();
		if (session != null)
			session.invalidate();

		SSOUser ssoUser = null;
		try {
			ssoUser = ssoUserService.findByUsername(user);
		} catch (EJBException e1) {
			logService.log(new LogonLog(null, new Message("LOGIN-002"), ipAdd));
		}

		try {
			request.login(user, password);
			if (ssoUser.getUserLogonStatus()
					.equals(UserLogonStatusEnum.BLOCKED)) {
				logService.log(new LogonLog(ssoUser.getBpu(), new Message(
						"LOGIN-003"), ipAdd));
			} else {

				logService.log(new LogonLog(ssoUser.getBpu(), new Message(
						"LOGIN-001"), ipAdd));
				Bpu bpu = ssoUser.getBpu();
				bpu.setPermissions(bpuFunctionService.getFunctionsByBpu(bpu));
				return bpu;
			}

			return new Bpu();
		} catch (ServletException e) {
			logService.log(new LogonLog(ssoUser.getBpu(), new Message(
					"LOGIN-004"), ipAdd));
			return null;
		} catch (Exception e) {
			logService.log(new LogonLog(ssoUser.getBpu(), new Message(
					"LOGIN-005"), ipAdd));
			return null;
		}
	}

	@Override
	public void logOut() {
		HttpServletRequest request = this.perThreadRequest.get();
		try {
			request.logout();
		} catch (ServletException e) {
			logger.info(e.getMessage());
		}
	}
}