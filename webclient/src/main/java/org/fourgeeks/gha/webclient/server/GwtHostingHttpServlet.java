package org.fourgeeks.gha.webclient.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.fourgeeks.gha.domain.enu.LanguageEnum;
import org.fourgeeks.gha.domain.enu.UserLogonStatusEnum;
import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.logs.LogonLog;
import org.fourgeeks.gha.domain.msg.GHAMessage;
import org.fourgeeks.gha.ejb.ess.SSOUserServiceRemote;
import org.fourgeeks.gha.ejb.log.LogonLogServiceRemote;
import org.fourgeeks.gha.ejb.msg.MessageServiceRemote;

/**
 * Dynamic host page
 * 
 * @author caparicio
 * 
 */
@WebServlet(urlPatterns = { "" })
public class GwtHostingHttpServlet extends HttpServlet {

	private static final long serialVersionUID = -6458844147830623507L;
	private final static Logger logger = Logger
			.getLogger(GwtHostingHttpServlet.class.getName());

	@EJB(name = "log.LogonLogService")
	LogonLogServiceRemote logService;

	@EJB(name = "ess.SSOUserService")
	SSOUserServiceRemote ssoUserService;

	@EJB(name = "msg.MessageService")
	MessageServiceRemote messageService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");

		// Print a simple HTML page including a <script> tag referencing your
		// GWT module as the response
		PrintWriter writer = resp.getWriter();

		HttpSession session = req.getSession();
		if (session.getAttribute("user") == null) {
			writer.append("<!DOCTYPE html>");
			writer.append("<html lang=\"en\">");
			writer.append("<head>");
			writer.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");
			writer.append("<link type=\"text/css\" rel=\"stylesheet\"");
			writer.append("href=\"resources/css/gha.css\">");
			writer.append("<script src=\"webclient/language.js\"></script>");
			writer.append("<link type=\"text/css\" rel=\"stylesheet\"");
			writer.append("href=\"resources/css/normalize.css\">");
			writer.append("<link rel=\"icon\" type=\"image/x-icon\"");
			writer.append("href=\"resources/icons/favicon.ico\">");
			writer.append("<title>GHA</title>");
			writer.append("</head>");
			writer.append("<body>");
			writer.append("<section>");
			writer.append("<div class=\"header-bar\" id=\"header-bar\">");
			writer.append("<div class=\"logo\"></div>");
			writer.append("<div id=\"user-info\" class=\"user-info\">");
			writer.append("</div>");
			writer.append("</div>");
			writer.append("</section>");
			writer.append("<div id=\"menu-bar\" class=\"menu-bar\"></div>");
			writer.append("<section class=\"main-content\" id=\"main-content\">");
			writer.append("<div class='login-panel'>");
			writer.append("<div class='logo login-logo'></div>");
			writer.append("<h1 class='login-titulo'>Iniciar Sesion</h1>");
			writer.append("<form class='centered' action=\"\" method=\"post\">");
			writer.append("<input maxlength='20' class='round' name=\"username\" id='username' type='text' placeholder='Nombre de usuario'><br/>");
			writer.append("<input maxlength='20' class='round' name=\"password\" id='password' type='password' placeholder='Clave de acceso'> <br/>");
			writer.append("<input id='login-button' type='submit' value='Iniciar Sesion' class='GHAButton'>");

			Object cause = session.getAttribute("cause");
			if (cause != null)
				writer.append("<h2 style=\"color:red;\">" + cause + "</h2>");

			writer.append("</form>");
			writer.append("</section>");
			writer.append("<div id=\"slideInWindowsBackDiv\" class=\"slideInWindowsBackDiv\"></div>");
			writer.append("<div id=\"notificationsBackDiv\" class=\"notificationsBackDiv\"></div>");
			writer.append("</body>").append("</html>");
		} else {
			writer.append("<!DOCTYPE html><html lang=\"en\"><head>");
			writer.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");
			writer.append("<link type=\"text/css\" rel=\"stylesheet\"");
			writer.append("href=\"resources/css/gha.css\">");
			writer.append("<script src=\"webclient/language.js\"></script>");
			writer.append("<link type=\"text/css\" rel=\"stylesheet\"");
			writer.append("href=\"resources/css/normalize.css\">");
			writer.append("<link rel=\"icon\" type=\"image/x-icon\"");
			writer.append("href=\"resources/icons/favicon.ico\">");
			writer.append("<title>GHA</title>");
			writer.append("<script>");
			writer.append("var isomorphicDir = \"webclient/sc/\";");
			writer.append("</script>");
			writer.append("<script type=\"text/javascript\" src=\"webclient/webclient.nocache.js\">");
			writer.append("</script>");
			writer.append("</head>");
			writer.append("<body>");
			writer.append("<iframe src=\"javascript:''\" id=\"__gwt_historyFrame\" tabIndex='-1'");
			writer.append("style=\"position: absolute; width: 0; height: 0; border: 0\"></iframe>");
			writer.append("<section>");
			writer.append("<div class=\"header-bar\" id=\"header-bar\">");
			writer.append("<div class=\"logo\"></div>");
			writer.append("<div id=\"user-info\" class=\"user-info\">");
			writer.append("</div>");
			writer.append("</div>");
			writer.append("</section>");
			writer.append("<div id=\"menu-bar\" class=\"menu-bar\"></div>");
			writer.append("<section class=\"main-content\" id=\"main-content\"></section>");
			writer.append("<div id=\"slideInWindowsBackDiv\" class=\"slideInWindowsBackDiv\"></div>");
			writer.append("<div id=\"notificationsBackDiv\" class=\"notificationsBackDiv\"></div>");
			writer.append("</body>").append("</html>");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");

		String user = req.getParameter("username");
		if (user == null || user.equals("")) {
			GHAMessage ghaMessage = null;
			try {
				ghaMessage = messageService.find("LOGIN003");
				req.getSession().setAttribute("cause", ghaMessage.getText());
				doGet(req, resp);
				return;
			} catch (GHAEJBException e) {
				e.printStackTrace();
			}
		}
		String password = req.getParameter("password");
		String ipAdd = req.getRemoteAddr().toString();

		SSOUser ssoUser = null;
		try {
			ssoUser = ssoUserService.findByUsername(user);
		} catch (GHAEJBException e1) {
			GHAMessage ghaMessage = null;
			try {
				ghaMessage = messageService.find("LOGIN005");
				req.getSession().setAttribute("cause", ghaMessage.getText());
				doGet(req, resp);
				return;
			} catch (GHAEJBException e2) {
				e2.printStackTrace();
			}
			logService.log(new LogonLog(null, e1.getGhaMessage(), ipAdd));
		}

		if (ssoUser.getUserLogonStatus().equals(UserLogonStatusEnum.BLOCKED)) {
			logService.log(new LogonLog(ssoUser.getBpu(), new GHAMessage(
					"LOGIN003"), ipAdd));
		}
		try {
			req.login(user, password);
			req.getSession().setAttribute("user", user);
			logService.log(new LogonLog(ssoUser.getBpu(), new GHAMessage(
					"LOGIN001", LanguageEnum.ES), ipAdd));
			req.getSession().removeAttribute("cause");
			doGet(req, resp);
		} catch (ServletException e) {
			GHAMessage ghaMessage = null;
			try {
				ghaMessage = messageService.find("LOGIN002");
			} catch (GHAEJBException e1) {
				e1.printStackTrace();
			}

			logService.log(new LogonLog(ssoUser.getBpu(), ghaMessage, ipAdd));
			req.getSession().setAttribute("cause", ghaMessage.getText());
			doGet(req, resp);
		}
	}
}