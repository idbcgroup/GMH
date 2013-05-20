package org.fourgeeks.gha.webclient.server;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginService extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// private static final String LDAPRealm = "ldapRealm";

	// @EJB(name = "ManejaUsuarioBean", beanInterface = ManejaUsuario.class)
	// ManejaUsuario manejaUsuario;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			request.login("alacret", "alacret");
			System.out.println("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String user = request.getParameter("user");
		String pass = request.getParameter("pass");

		request.login(user, pass);

		RequestDispatcher rd = this.getServletContext().getRequestDispatcher(
				"/Login.ftl");

		if (nada(user) || nada(pass)) {
			request.setAttribute("mensaje",
					"Debe indicar el nombre de usuario y contraseña");
			rd.forward(request, response);
			return;
		}

	}

	/**
	 * Verifica que los campos no se nulos, ni vacios
	 * 
	 * @param campo
	 *            String con el valor
	 * @return true si no es vacio de lo contrario false
	 */
	protected boolean nada(String campo) {
		return campo == null || campo.trim().length() == 0;
	}

}
