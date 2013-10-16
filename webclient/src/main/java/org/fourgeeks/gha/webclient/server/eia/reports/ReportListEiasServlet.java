package org.fourgeeks.gha.webclient.server.eia.reports;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.log4j.Level;
import org.fourgeeks.gha.domain.enu.EiaReportOrderByEnum;
import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.ejb.gmh.EiaReportsServiceRemote;
import org.fourgeeks.gha.ejb.gmh.EiaServiceRemote;

@WebServlet(urlPatterns = { "/reports/eia/leqi" })
public class ReportListEiasServlet extends ReportEiaServelt {
	private static final long serialVersionUID = 1L;

	private static final String REPORT_FILE_DIR_1 = "/resources/reportes/compilados/GMH.LEQI.01.jasper";
	private static final String REPORT_FILE_DIR_2 = "/resources/reportes/compilados/GMH.LEQI.02.jasper";
	private static final String LOGO_DIR = "/resources/img/logoReport.jpg";

	private static final String PARAM_EIA = "eia", PARAM_EIATYPE = "eiatype",
			PARAM_FACILS = "facils", PARAM_WORKAREAS = "workareas", PARAM_EDOEIA = "edoeia",
			PARAM_USER = "user", PARAM_ORDEN = "orden";

	@EJB(name = "gmh.EiaReportsService", beanInterface = EiaReportsServiceRemote.class)
	EiaReportsServiceRemote serviceEiaReport;

	@EJB(name = "gmh.EiaService", beanInterface = EiaServiceRemote.class)
	EiaServiceRemote serviceEia;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		try {
			Map<String, Object> searchMap = searchInService(req);

			String reportFileRealPath = null;
			EiaDataSource dataSource = null;

			dataSource = new EiaDataSource((List<Eia>) searchMap.get("data"));
			reportFileRealPath = (String) searchMap.get("reportPath");

			Map<String, Object> paramsReport = generateParamsMap(req);

			JasperPrint fillReport = JasperFillManager.fillReport(reportFileRealPath, paramsReport,
					dataSource);

			exportAsPDF(resp, fillReport, "detalle-equipos.pdf");

		} catch (GHAEJBException e) {
			LOG.log(Level.ERROR, "Problema al obtener los datos para el reporte", e);

		} catch (JRException e) {
			LOG.log(Level.ERROR, "Problema al generar el reporte de JasperReport", e);

		}

	}

	private Map<String, Object> searchInService(HttpServletRequest req) throws GHAEJBException {
		QueryParamsContainer qpc = new QueryParamsContainer(req);
		List<Eia> eiaList = null;
		String reportPath = null;

		if (qpc.eiaId != null) {
			// un equipo
			reportPath = getServletContext().getRealPath(REPORT_FILE_DIR_1);
			eiaList = new ArrayList<Eia>();
			Eia eia = serviceEia.find(qpc.eiaId);
			eiaList.add(eia);
		} else if (qpc.eiaTypeCode == null) {
			// todos los equipos
			reportPath = getServletContext().getRealPath(REPORT_FILE_DIR_1);
			eiaList = serviceEiaReport.findAllEias(qpc.facilsIds, qpc.workingAreasIds,
					qpc.eiaState, qpc.orden);
		} else {
			// equipos por tipo de equipo
			reportPath = getServletContext().getRealPath(REPORT_FILE_DIR_2);
			eiaList = serviceEiaReport.findEiasByEiaType(qpc.eiaTypeCode, qpc.facilsIds,
					qpc.workingAreasIds, qpc.eiaState, qpc.orden);
		}

		HashMap<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("data", eiaList);
		mapa.put("reportPath", reportPath);

		return mapa;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.server.eia.reports.ReportEiaServelt#
	 * generateParamsMap(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected Map<String, Object> generateParamsMap(HttpServletRequest req) {
		// logo del sistema que ha de aparecer como parte del reporte
		Image logoImage = new ImageIcon(getServletContext().getRealPath(LOGO_DIR)).getImage();

		String user = req.getParameter(PARAM_USER);
		String datetimeReport = genDatetimeTimezoneStrRep();

		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("logo", logoImage);
		paramsMap.put("fechaHoraReporte", datetimeReport);
		paramsMap.put("nombreOperador", user);

		return paramsMap;
	}

	/**
	 * Clase contenedora de los parametros para las consultas a realizar a los
	 * EJB
	 * 
	 * @author naramirez
	 */
	private class QueryParamsContainer {
		private Long eiaId;
		private String eiaTypeCode;
		private List<Long> facilsIds;
		private List<Long> workingAreasIds;
		private EiaStateEnum eiaState;
		private EiaReportOrderByEnum orden;

		/**
		 * @param req
		 *            objeto que tiene los parametros de enviadospor el cliente
		 *            al servlet
		 */
		public QueryParamsContainer(HttpServletRequest req) {
			eiaId = null;
			String eiaValue = req.getParameter(PARAM_EIA);
			if (eiaValue != null)
				if (!eiaValue.equals("all"))
					eiaId = Long.valueOf(eiaValue);

			eiaTypeCode = null;
			String eiaTypeValue = req.getParameter(PARAM_EIATYPE);
			if (eiaTypeValue != null)
				eiaTypeCode = eiaTypeValue;

			eiaState = null;
			String eiaStateParam = req.getParameter(PARAM_EDOEIA);
			if (eiaStateParam != null)
				if (!eiaStateParam.equals("all"))
					eiaState = EiaStateEnum.valueOf(eiaStateParam);

			String facsValue = req.getParameter(PARAM_FACILS);
			facilsIds = toList(facsValue);

			String workAreasValue = req.getParameter(PARAM_WORKAREAS);
			workingAreasIds = toList(workAreasValue);

			Boolean orderByUbicEiaType = Boolean.valueOf(req.getParameter(PARAM_ORDEN));
			orden = orderByUbicEiaType ? EiaReportOrderByEnum.UBIC_EIA
					: EiaReportOrderByEnum.EIA_UBIC;
		}

		private List<Long> toList(String paramValue) {
			if (paramValue != null)
				if (!paramValue.equals("all")) {
					ArrayList<Long> list = new ArrayList<Long>();
					String[] arrayLongValues = paramValue.split(",");
					for (String val : arrayLongValues)
						list.add(Long.valueOf(val));
					return list;
				}

			return null;
		}
	}

}
