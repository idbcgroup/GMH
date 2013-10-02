package org.fourgeeks.gha.webclient.server.eia.reports;

import java.awt.Image;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

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
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.ejb.gmh.BrandServiceRemote;
import org.fourgeeks.gha.webclient.client.UI.GHASessionData;

@WebServlet(urlPatterns = { "/webclient/reporteia" })
public class ReportEiaServelt extends HttpServlet {
	private static final Logger LOG = Logger.getLogger(ReportEiaServelt.class);
	private static final long serialVersionUID = 1L;

	private static final String REPORT_FILE_DIR = "/resources/reportes/eiaReport.jasper";
	private static final String LOGO_DIR = "/resources/img/logoReport.jpg";

	@EJB(name = "gmh.BrandService", beanInterface = BrandServiceRemote.class)
	BrandServiceRemote service;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			// lista de datos a mostrar en el reporte
			List<Brand> brands = service.getAll();

			// la fuente de datos de la que se nutre el reporte (este DataSource
			// se nutre de la lista "brands")
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(
					brands);

			// parametros que recibe el reporte
			Map<String, Object> paramsReport = generateParamsMap();

			// generacion del archivo .jasper (reporte compilado) y el llenado
			// del reporte (fillReport)
			String reportFileRealPath = getServletContext().getRealPath(
					REPORT_FILE_DIR);
			JasperPrint fillReport = JasperFillManager.fillReport(
					reportFileRealPath, paramsReport, dataSource);

			// exportacion como PDF
			exportAsPDF(resp, fillReport);

		} catch (GHAEJBException e) {
			LOG.log(Level.ERROR,
					"Problema al obtener los datos para el reporte", e);

		} catch (JRException e) {
			LOG.log(Level.ERROR,
					"Problema al generar el reporte de JasperReport", e);
		}
	}

	/**
	 * Envia como respuesta al cliente el reporte generado como PDF a traves de
	 * streaming
	 * 
	 * @param response
	 *            El objeto que permite escribir la respuesta al cliente
	 * @param fillReport
	 *            El objeto con el reporte ya lleno con los datos
	 * @throws JRException
	 * @throws IOException
	 */
	private void exportAsPDF(HttpServletResponse response,
			JasperPrint fillReport) throws JRException, IOException {

		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition",
				"inline; filename=brandReport.pdf");

		// se exporta en reporte lleno con los datos como PDF hacia la salida
		// que ofrece el objeto response
		JasperExportManager.exportReportToPdfStream(fillReport,
				response.getOutputStream());
	}

	/**
	 * @return {@link String} con la fecha, hora y zona horaria actual para el
	 *         reporte
	 */
	private String genDatetimeTimezoneStrRep() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yy z h:mm a");
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT-4:30"));
		return dateFormat.format(new Date()).toLowerCase();
	}

	/**
	 * @return Mapa con los parametros que recibe el reporte
	 */
	private Map<String, Object> generateParamsMap() {

		// logo del sistema que ha de aparecer como parte del reporte
		Image logoImage = new ImageIcon(getServletContext().getRealPath(
				LOGO_DIR)).getImage();

		// usuario logeado en el sistema
		Citizen user = GHASessionData.getLoggedUser().getCitizen();
		String userName = user.getFirstName() + " " + user.getFirstLastName();

		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("logo", logoImage);
		paramsMap.put("fechaHoraReporte", genDatetimeTimezoneStrRep());
		paramsMap.put("statusOrLoc", "Estatus");
		paramsMap.put("statusOrLocVal", "Nuevo");
		paramsMap.put("nombreOperador", userName);

		return paramsMap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
	}
}
