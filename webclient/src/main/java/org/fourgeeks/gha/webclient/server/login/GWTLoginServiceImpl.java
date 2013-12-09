package org.fourgeeks.gha.webclient.server.login;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.fourgeeks.gha.domain.enu.LanguageEnum;
import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.logs.LogonLog;
import org.fourgeeks.gha.domain.msg.GHAMessage;
import org.fourgeeks.gha.ejb.ess.AppFormViewFunctionBpuServiceRemote;
import org.fourgeeks.gha.ejb.ess.SSOUserServiceRemote;
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
	AppFormViewFunctionBpuServiceRemote bpuFunctionService;

	@EJB(lookup = "java:global/ear-1/ejb-1/MessageService")
	MessageServiceRemote messageService;

	/**
	 * @return true if there is a user logged in
	 * 
	 */
	@Override
	public boolean isLogged() {
		HttpServletRequest request = getThreadLocalRequest();
		return !(request.getSession().getAttribute("user") == null);
	}

//	@Override
//	public Bpu login(String user, String password) throws GHAEJBException {
//		HttpServletRequest request = getThreadLocalRequest();
//		String ipAdd = request.getRemoteAddr().toString();
//
//		HttpSession session = request.getSession();
//		// if (session != null) {
//		// try {
//		// request.logout();
//		// } catch (ServletException e) {
//		// e.printStackTrace();
//		// }
//		// session.invalidate();
//		// }
//
//		SSOUser ssoUser = null;
//		try {
//			ssoUser = ssoUserService.findByUsername(user);
//		} catch (GHAEJBException e1) {
//			logService.log(new LogonLog(null, e1.getGhaMessage(), ipAdd));
//			throw e1;
//		}
//
//		// if (ssoUser.getUserLogonStatus().equals(UserLogonStatusEnum.BLOCKED))
//		// {
//		// logService.log(new LogonLog(ssoUser.getBpu(), new GHAMessage(
//		// "LOGIN003"), ipAdd));
//		// throw new GHAEJBException("Usuario bloqueado.");
//		// }
//
//		try {
//			// request.login(user, password);
//			// session.setAttribute("user", user);
//
//			logService.log(new LogonLog(ssoUser.getBpu(), new GHAMessage(
//					"LOGIN001", LanguageEnum.ES), ipAdd));
//			bpu = ssoUser.getBpu();
//			bpu.setSessionId(session.getId());
//			bpu.setPermissions(bpuFunctionService.getFunctionsByBpu(bpu));
//			return bpu;
//			// } catch (ServletException e) {
//			// GHAMessage ghaMessage = messageService.find("LOGIN002");
//			// logService.log(new LogonLog(ssoUser.getBpu(), ghaMessage,
//			// ipAdd));
//			// throw new GHAEJBException(ghaMessage);
//		} catch (Exception e) {
//			logger.info("e.toString(): " + e.toString());
//			GHAMessage ghaMessage = messageService.find("LOGIN005");
//			logService.log(new LogonLog(ssoUser.getBpu(), ghaMessage, ipAdd));
//			throw new GHAEJBException(ghaMessage);
//		}
//	}

	@Override
	public void logOut() {
		HttpServletRequest request = this.perThreadRequest.get();
		bpu = null;
		try {
			HttpSession session = request.getSession();
			session.removeAttribute("user");
			request.logout();
		} catch (ServletException e) {
			logger.info(e.getMessage());
		}
	}

	// @Override
	// public Bpu userLogged() {
	// return bpu;
	// }

	@Override
	public Bpu getLoggedUser() throws GHAEJBException {
		HttpServletRequest request = getThreadLocalRequest();
		String ipAdd = request.getRemoteAddr().toString();

		HttpSession session = request.getSession();
		SSOUser ssoUser = null;
		try {
			ssoUser = ssoUserService.findByUsername((String) session
					.getAttribute("user"));
		} catch (GHAEJBException e1) {
			logService.log(new LogonLog(null, e1.getGhaMessage(), ipAdd));
			throw e1;
		}
		try {
			logService.log(new LogonLog(ssoUser.getBpu(), new GHAMessage(
					"LOGIN001", LanguageEnum.ES), ipAdd));
			bpu = ssoUser.getBpu();
			bpu.setSessionId(session.getId());
			bpu.setPermissions(bpuFunctionService.getFunctionsByBpu(bpu));
			return bpu;
		} catch (Exception e) {
			GHAMessage ghaMessage = messageService.find("LOGIN005");
			logService.log(new LogonLog(ssoUser.getBpu(), ghaMessage, ipAdd));
			throw new GHAEJBException(ghaMessage);
		}
	}
}