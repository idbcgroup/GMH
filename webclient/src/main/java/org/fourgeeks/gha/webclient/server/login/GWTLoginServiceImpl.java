package org.fourgeeks.gha.webclient.server.login;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.fourgeeks.gha.domain.enu.UserLogonStatusEnum;
import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.ejb.ess.SSOUserServiceRemote;
import org.fourgeeks.gha.ejb.gar.BpuFunctionServiceRemote;
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

		if (user.equals("") || password.equals("")) {
			// TODO : Aqui se debe hookear el guardado del log de login
			throw new IllegalArgumentException(
					"Debe indicar usuario y contraseña");
		}

		HttpSession session = request.getSession();
		if (session != null)
			session.invalidate();

		try {
			request.login(user, password);
			// TODO : Aqui se debe hookear el guardado del log de login

			// get the bpu for the authenticated user
			SSOUser ssoUser = ssoUserService.findByUsername(user);
			if (ssoUser.getUserLogonStatus()
					.equals(UserLogonStatusEnum.BLOCKED)) {
				// TODO: Usuario bloqueado intentando acceder a la aplicacion
			} else {
				// usuario valido
				Bpu bpu = ssoUser.getBpu();
				bpu.setPermissions(bpuFunctionService
						.getFunctionsAsStringListByBpu(bpu));
				return bpu;
			}

			return new Bpu();
		} catch (ServletException e) {
			System.out.println("Error iniciando sesión " + e.getMessage());
			// TODO : Aqui se debe hookear el guardado del log de login
			return null;
		} catch (Exception e) {
			System.out.println("aca se deberia capturar el error"
					+ e.getMessage());
			// TODO : Aqui se debe hookear el guardado del log de login
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