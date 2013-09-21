package org.fourgeeks.gha.webclient.server;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.ejb.gmh.BrandServiceRemote;

@WebServlet(urlPatterns = { "/webclient/reportbrand" })
public class ReportBrandServelt extends HttpServlet {
	private static final Logger LOG = Logger.getLogger(ReportBrandServelt.class);
	private static final long serialVersionUID = 1L;
	private static final String REPORT_FILE_DIR = "/resources/reportes/prueba.jasper";

	@EJB(name = "gmh.BrandService", beanInterface = BrandServiceRemote.class)
	BrandServiceRemote service;

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {

		try {
			List<Brand> brands = service.getAll();
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(brands);
			Map<String, Object> paramsReport = new HashMap<String, Object>();

			JasperPrint fillReport = JasperFillManager.fillReport(
					getServletContext().getRealPath(REPORT_FILE_DIR), paramsReport, dataSource);

			exportAsPDF(resp, fillReport);

		} catch (EJBException e) {
			LOG.log(Level.ERROR, "Problema al obtener los datos para el reporte", e);

		} catch (JRException e) {
			LOG.log(Level.ERROR, "Problema al generar el reporte de JasperReport", e);
		}
	}

	private void exportAsPDF(HttpServletResponse response, JasperPrint impresion)
			throws JRException, IOException {

		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "inline; filename=brandReport.pdf");

		JasperExportManager.exportReportToPdfStream(impresion, response.getOutputStream());
	}
}
