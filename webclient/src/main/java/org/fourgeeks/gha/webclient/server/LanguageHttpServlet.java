package org.fourgeeks.gha.webclient.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fourgeeks.gha.domain.msg.UiString;
import org.fourgeeks.gha.ejb.language.LanguageServiceRemote;

/**
 * 
 * @author alacret
 * 
 */
@WebServlet(urlPatterns = { "/webclient/language.js" })
public class LanguageHttpServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// private static final Logger LOG = Logger
	// .getLogger(LanguageHttpServlet.class);

	@EJB(name = "language.LanguageService")
	LanguageServiceRemote service;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		PrintWriter writer = resp.getWriter();
		resp.setContentType("application/javascript");
		writer.write("var lang = {");
		List<UiString> languageStringsList = service.getLanguageStringsList();
		for (UiString uiString : languageStringsList)
			writer.write("\"" + uiString.getCode() + "\":" + "\""
					+ uiString.getText() + "\"");
		writer.write("}");

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		throw new UnsupportedOperationException("only HTTP GET supports");
	}
}
