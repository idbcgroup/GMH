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

		resp.setContentType("application/javascript");
		List<UiString> languageStringsList = service.getLanguageStringsList();

		StringBuilder stringBuilder = new StringBuilder();
		for (UiString uiString : languageStringsList)
			stringBuilder.append("\"" + uiString.getCode() + "\":" + "\""
					+ uiString.getText() + "\",");

		PrintWriter writer = resp.getWriter();
		writer.write("var lang = {");
		writer.write(stringBuilder.substring(0, stringBuilder.length() - 1));
		writer.write("}");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		throw new UnsupportedOperationException("only HTTP GET supports");
	}
}
