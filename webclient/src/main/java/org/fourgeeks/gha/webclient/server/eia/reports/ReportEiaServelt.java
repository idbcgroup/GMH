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
import org.fourgeeks.gha.domain.enu.EiaReportFiltersEnum;
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

	// parametros de la URI
	private static final String PARAM_GROUP = "group",
			PARAM_GROUPDESC = "groupdesc", PARAM_FILTER = "filter",
			PARAM_FILTERTYPE = "filtertype", PARAM_USER = "user",
			PARAM_FILTERTYPEDESC = "filtertypedesc",
			PARAM_FILTERDESC = "filterdesc";

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
			Eia eiaFilter = generateEiaForReport(req);
			List<Eia> eiaList = service.find(eiaFilter);

			String reportFileRealPath = null;
			EiaDataSource dataSource = null;

			String group = req.getParameter(PARAM_GROUP);
			if (group.equals("noAgrup")) {
				dataSource = new EiaDataSource(eiaList);
				reportFileRealPath = getServletContext().getRealPath(
						REPORT_FILE_DIR);
			} else {
				dataSource = new EiaDataSource(eiaList, group);
				reportFileRealPath = getServletContext().getRealPath(
						REPORT_GROUP_FILE_DIR);
			}

			Map<String, Object> paramsReport = generateParamsMap(req);

			JasperPrint fillReport = JasperFillManager.fillReport(
					reportFileRealPath, paramsReport, dataSource);

			exportAsPDF(resp, fillReport);

		} catch (GHAEJBException e) {
			LOG.log(Level.ERROR,
					"Problema al obtener los datos para el reporte", e);

		} catch (JRException e) {
			LOG.log(Level.ERROR,
					"Problema al generar el reporte de JasperReport", e);

		} catch (ClassNotFoundException e) {
			LOG.log(Level.ERROR,
					"No existe la clase para agrupar los datos del reporte", e);
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
				"inline; filename=eiaReport.pdf");

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
		String filterValParam = req.getParameter(PARAM_FILTER);
		String filterParam = req.getParameter(PARAM_FILTERTYPE);

		Eia eia = new Eia();
		eia.setState(null);

		EiaReportFiltersEnum filter = EiaReportFiltersEnum.valueOf(filterParam);

		if (filter == EiaReportFiltersEnum.EDO_EQUIPO)
			eia.setState(EiaStateEnum.valueOf(filterValParam));

		else if (filter == EiaReportFiltersEnum.FACILITY)
			eia.setFacility(new Facility(Long.parseLong(filterValParam)));

		else if (filter == EiaReportFiltersEnum.WORKING_AREA)
			eia.setWorkingArea(new WorkingArea(Long.parseLong(filterValParam)));

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

		String user = req.getParameter(PARAM_USER);
		String filterTypeDesc = req.getParameter(PARAM_FILTERTYPEDESC);
		String filterDesc = req.getParameter(PARAM_FILTERDESC);
		final String groupDesc = req.getParameter(PARAM_GROUPDESC);
		String datetimeReport = genDatetimeTimezoneStrRep();

		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("logo", logoImage);
		paramsMap.put("fechaHoraReporte", datetimeReport);
		paramsMap.put("statusOrLoc", filterTypeDesc);
		paramsMap.put("statusOrLocVal", filterDesc);
		paramsMap.put("groupDesc", groupDesc);
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
