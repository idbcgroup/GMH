package org.fourgeeks.gha.webclient.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
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
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;

/**
 * Dynamic host page
 * 
 * @author caparicio
 * 
 */
@WebServlet(urlPatterns = { "/test" })
public class GwtHostingHttpServlet extends HttpServlet {

	private static final long serialVersionUID = 3900034745518327128L;
	private static final Logger LOG = Logger
			.getLogger(GwtHostingHttpServlet.class);

	@EJB(name = "log.LogonLogService")
	LogonLogServiceRemote logService;

	@EJB(name = "ess.SSOUserService")
	SSOUserServiceRemote ssoUserService;

	@EJB(name = "ess.AppFormViewFunctionBpuService")
	AppFormViewFunctionBpuServiceRemote bpuFunctionService;

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
		writer.append("</form>");
		writer.append("</section>");
		writer.append("<div id=\"slideInWindowsBackDiv\" class=\"slideInWindowsBackDiv\"></div>");
		writer.append("<div id=\"notificationsBackDiv\" class=\"notificationsBackDiv\"></div>");
		writer.append("</body>").append("</html>");

		// writer.append("<!DOCTYPE html><html lang=\"en\"><head>")
		// .append("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">")
		// .append("<link type=\"text/css\" rel=\"stylesheet\"")
		// .append("href=\"resources/css/gha.css\">")
		// .append("<script src=\"webclient/language.js\"></script>")
		// .append("<link type=\"text/css\" rel=\"stylesheet\"")
		// .append("href=\"resources/css/normalize.css\">")
		// .append("<link rel=\"icon\" type=\"image/x-icon\"")
		// .append("href=\"resources/icons/favicon.ico\">")
		// .append("<title>GHA</title>")
		// .append("<script>")
		// .append("var isomorphicDir = \"webclient/sc/\";")
		// .append("</script>")
		// .append("<script type=\"text/javascript\" src=\"webclient/webclient.nocache.js\">")
		// .append("</script>")
		// .append("</head>")
		// .append("<body>")
		// .append("<iframe src=\"javascript:''\" id=\"__gwt_historyFrame\" tabIndex='-1'")
		// .append("style=\"position: absolute; width: 0; height: 0; border: 0\"></iframe>")
		// .append("<section>")
		// .append("<div class=\"header-bar\" id=\"header-bar\">")
		// .append("<div class=\"logo\"></div>")
		// .append("<div id=\"user-info\" class=\"user-info\">")
		// .append("</div>")
		// .append("</div>")
		// .append("</section>")
		// .append("<div id=\"menu-bar\" class=\"menu-bar\"></div>")
		// .append("<section class=\"main-content\" id=\"main-content\"></section>")
		// .append("<div id=\"slideInWindowsBackDiv\" class=\"slideInWindowsBackDiv\"></div>")
		// .append("<div id=\"notificationsBackDiv\" class=\"notificationsBackDiv\"></div>")
		// .append("</body>").append("</html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");

		String user = req.getParameter("username");
		String password = req.getParameter("password");
		String ipAdd = req.getRemoteAddr().toString();

		PrintWriter writer = resp.getWriter();
		// writer.append("username: " + req.getParameter("username"));
		// writer.append("password: " + req.getParameter("password"));
		// writer.append("req.getRemoteAddr(): " + ipAdd);

		HttpSession session = req.getSession();
		if (session != null) {
			try {
				req.logout();
			} catch (ServletException e) {
				e.printStackTrace();
			}
			session.invalidate();
		}

		if (!user.equals("admin")) {
			req.setAttribute("error", "Unknown user, please try again");
			req.getRequestDispatcher("/gha").forward(req, resp);
		} else {
			req.getSession().setAttribute("user", user);
			writer.append("<script type=\"text/javascript\" src=\"webclient/webclient.nocache.js\"></script>");
			// resp.sendRedirect("#home");
		}
		// SSOUser ssoUser = null;
		// try {
		// ssoUser = ssoUserService.findByUsername(user);
		// } catch (GHAEJBException e1) {
		// logService.log(new LogonLog(null, e1.getGhaMessage(), ipAdd));
		// throw e1;
		// }
		//
		// try {
		// req.login(user, password);
		// logService.log(new LogonLog(ssoUser.getBpu(), new GHAMessage(
		// "LOGIN001", LanguageEnum.ES), ipAdd));
		// Bpu bpu = ssoUser.getBpu();
		// bpu.setPermissions(bpuFunctionService.getFunctionsByBpu(bpu));
		// return bpu;
		// } catch (ServletException e) {
		// GHAMessage ghaMessage = messageService.find("LOGIN002");
		// logService.log(new LogonLog(ssoUser.getBpu(), ghaMessage, ipAdd));
		// throw new GHAEJBException(ghaMessage);
		// } catch (Exception e) {
		// GHAMessage ghaMessage = messageService.find("LOGIN005");
		// logService.log(new LogonLog(ssoUser.getBpu(), ghaMessage, ipAdd));
		// throw new GHAEJBException(ghaMessage);
		// }
	}
}