package org.fourgeeks.gha.webclient.server;

import java.awt.Image;
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
import javax.swing.ImageIcon;

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
	private static final String LOGO_DIR = "/resources/img/logoReport.jpg";

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
			// lista de datos a mostrar en el reporte
			List<Brand> brands = service.getAll();

			// logo del sistema que ha de aparecer como parte del reporte
			Image logoImage = new ImageIcon(getServletContext().getRealPath(LOGO_DIR)).getImage();
			// la fuente de datos de la que se nutre el reporte (este DataSource
			// se nutre de la lista "brands")
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(brands);

			// parametros que recibe el reporte
			Map<String, Object> paramsReport = new HashMap<String, Object>();
			paramsReport.put("logo", logoImage);
			paramsReport.put("statusOrLoc", "Estatus");
			paramsReport.put("statusOrLocVal", "Nuevo");

			// generacion del archivo .jasper (reporte compilado) y el llenado
			// del reporte (fillReport)
			String reportFileRealPath = getServletContext().getRealPath(REPORT_FILE_DIR);
			JasperPrint fillReport = JasperFillManager.fillReport(reportFileRealPath, paramsReport,
					dataSource);

			// exportacion como PDF
			exportAsPDF(resp, fillReport);

		} catch (EJBException e) {
			LOG.log(Level.ERROR, "Problema al obtener los datos para el reporte", e);

		} catch (JRException e) {
			LOG.log(Level.ERROR, "Problema al generar el reporte de JasperReport", e);
		}
	}

	/**
	 * Envia como respuesta al cliente el reporte generado como PDF a traves de
	 * streaming
	 * 
	 * @param response
	 *            El objeto {@link HttpServletResponse} que permite escribir la
	 *            respuesta al cliente
	 * @param fillReport
	 *            El objeto con el reporte ya lleno con los datos
	 * @throws JRException
	 * @throws IOException
	 */
	private void exportAsPDF(HttpServletResponse response, JasperPrint fillReport)
			throws JRException, IOException {

		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "inline; filename=brandReport.pdf");

		// se exporta en reporte lleno con los datos como PDF hacia la salida
		// que ofrece el objeto response
		JasperExportManager.exportReportToPdfStream(fillReport, response.getOutputStream());
	}
}
