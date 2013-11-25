package org.fourgeeks.gha.webclient.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;

/**
 * Dynamic host page
 * 
 * @author caparicio
 * 
 */
@WebServlet(urlPatterns = { "" })
public class GwtHostingHttpServlet extends HttpServlet {

	private static final long serialVersionUID = 3900034745518327128L;
	private static final Logger LOG = Logger.getLogger(GwtHostingHttpServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");

		// Print a simple HTML page including a <script> tag referencing your
		// GWT module as the response
		PrintWriter writer = resp.getWriter();
		writer.append("<div class='login-panel'>");
		writer.append("<div class='logo login-logo'></div>");
		writer.append("<h1 class='login-titulo'>Iniciar Sesion</h1>");
		// html.append("<div class='smallfont full'>Ubicado en:<span id='ubicacion'>Sotano enfermeria</span></div>");
		writer.append("<form class='centered'>");
		writer.append("<input maxlength='20' class='round' id='username' type='text' placeholder='Nombre de usuario'><br/>");
		writer.append("<input maxlength='20' class='round' id='password' type='password' placeholder='"
				+ "password" + "'> <br/>");
//		writer.append("<!DOCTYPE html><html lang=\"en\"><head>")
//				.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">")
//				.append("<link type=\"text/css\" rel=\"stylesheet\"")
//				.append("href=\"resources/css/gha.css\">")
//				.append("<script src=\"webclient/language.js\"></script>")
//				.append("<link type=\"text/css\" rel=\"stylesheet\"")
//				.append("href=\"resources/css/normalize.css\">")
//				.append("<link rel=\"icon\" type=\"image/x-icon\"")
//				.append("href=\"resources/icons/favicon.ico\">")
//				.append("<title>GHA</title>")
//				.append("<script>")
//				.append("var isomorphicDir = \"webclient/sc/\";")
//				.append("</script>")
//				.append("<script type=\"text/javascript\" src=\"webclient/webclient.nocache.js\">")
//				.append("</script>")
//				.append("</head>")
//				.append("<body>")
//				.append("<iframe src=\"javascript:''\" id=\"__gwt_historyFrame\" tabIndex='-1'")
//				.append("style=\"position: absolute; width: 0; height: 0; border: 0\"></iframe>")
//				.append("<section>")
//				.append("<div class=\"header-bar\" id=\"header-bar\">")
//				.append("<div class=\"logo\"></div>")
//				.append("<div id=\"user-info\" class=\"user-info\">")
//				.append("</div>")
//				.append("</div>")
//				.append("</section>")
//				.append("<div id=\"menu-bar\" class=\"menu-bar\"></div>")
//				.append("<section class=\"main-content\" id=\"main-content\"></section>")
//				.append("<div id=\"slideInWindowsBackDiv\" class=\"slideInWindowsBackDiv\"></div>")
//				.append("<div id=\"notificationsBackDiv\" class=\"notificationsBackDiv\"></div>")
//				.append("</body>").append("</html>");
	}
}
