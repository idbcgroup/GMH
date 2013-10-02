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

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.ess.WorkingArea;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Facility;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.ejb.gmh.EiaServiceRemote;

@WebServlet(urlPatterns = { "/webclient/reporteia" })
public class ReportEiaServelt extends HttpServlet {
	private static final Logger LOG = Logger.getLogger(ReportEiaServelt.class);
	private static final long serialVersionUID = 1L;

	private static final String REPORT_FILE_DIR = "/resources/reportes/eiaReport.jasper";
	private static final String REPORT_GROUP_FILE_DIR = "/resources/reportes/eiaReportGroups.jasper";
	private static final String LOGO_DIR = "/resources/img/logoReport.jpg";

	@EJB(name = "gmh.EiaService", beanInterface = EiaServiceRemote.class)
	EiaServiceRemote service;

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
			// genero el EIA que sirve de filtro para traer los datos deseados
			Eia eiaFilter = generateEiaForReport(req);
			// lista de datos a mostrar en el reporte
			List<Eia> eiaList = service.find(eiaFilter);

			// la fuente de datos de la que se nutre el reporte
			String group = req.getParameter("group");
			EiaDataSource dataSource = new EiaDataSource(eiaList, group);

			// parametros que recibe el reporte
			Map<String, Object> paramsReport = generateParamsMap(req);

			// ubicacion de los archivos de reportes compilados (.jasper)
			String relativePath = group.equals("noAgrup") ? REPORT_FILE_DIR
					: REPORT_GROUP_FILE_DIR;
			String reportFileRealPath = getServletContext().getRealPath(
					relativePath);

			// generacion del archivo .jasper y llenado del reporte (fillReport)
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
	 * Genera un EIA que servira de filtro para traer la lista de EIAs desada
	 * 
	 * @param req
	 *            Objeto con los parametros que recibe el reporte
	 * @return Ojeto EIA con los filtros deseados
	 */
	private Eia generateEiaForReport(HttpServletRequest req) {
		String filterVal = req.getParameter("filter");
		String filterType = req.getParameter("filtertype");

		Eia eia = new Eia();
		if (filterType.equals("edoEquipo"))
			eia.setState(EiaStateEnum.valueOf(filterVal));
		else if (filterType.equals("facility"))
			eia.setFacility(new Facility(Long.parseLong(filterVal)));
		else if (filterType.equals("workingArea"))
			eia.setWorkingArea(new WorkingArea(Long.parseLong(filterVal)));

		return eia;
	}

	/**
	 * @param req
	 *            objeto con los parametros de la peticionO
	 * @return Mapa con los parametros que recibe el reporte
	 */
	private Map<String, Object> generateParamsMap(HttpServletRequest req) {

		// logo del sistema que ha de aparecer como parte del reporte
		Image logoImage = new ImageIcon(getServletContext().getRealPath(
				LOGO_DIR)).getImage();

		String datetimeReport = genDatetimeTimezoneStrRep();
		String user = req.getParameter("user");
		String filterTypeDesc = req.getParameter("filtertypedesc");
		String filterDesc = req.getParameter("filterdesc");

		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("logo", logoImage);
		paramsMap.put("fechaHoraReporte", datetimeReport);
		paramsMap.put("statusOrLoc", filterTypeDesc);
		paramsMap.put("statusOrLocVal", filterDesc);
		paramsMap.put("nombreOperador", user);

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
