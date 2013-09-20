package org.fourgeeks.gha.webclient.server;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.ejb.gmh.BrandServiceRemote;

@WebServlet(urlPatterns = { "/webclient/reportBrand" })
public class ReportBrandServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB(name = "gmh.BrandService", beanInterface = BrandServiceRemote.class)
	BrandServiceRemote service;

	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			List<Brand> brands = service.getAll();

		} catch (EJBException e) {
			e.printStackTrace();
		}
	}
}
