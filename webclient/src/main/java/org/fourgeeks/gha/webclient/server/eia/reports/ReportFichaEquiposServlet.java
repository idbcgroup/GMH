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
import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.ejb.gmh.EiaReportsServiceRemote;

@WebServlet(urlPatterns = { "/reports/eia/feia" })
public class ReportFichaEquiposServlet extends ReportEiaServelt {
	private static final long serialVersionUID = 1L;

	private static final String REPORT_FILE_DIR = "/resources/reportes/GHM.FEIA.01.jasper";
	private static final String LOGO_DIR = "/resources/img/logoReport.jpg";

	private static final String PARAM_EIAS = "eias", PARAM_FACILS = "facils",
			PARAM_WORKAREAS = "workareas", PARAM_EDOEIA = "edoeia",
			PARAM_USER = "user", PARAM_ORDEN = "orden";

	@EJB(name = "gmh.EiaReportsService", beanInterface = EiaReportsServiceRemote.class)
	EiaReportsServiceRemote service;

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
			List<Eia> eiaList = searchInService(req);

			LOG.info("eiaList.size() = " + eiaList.size());

			String reportFileRealPath = null;
			EiaDataSource dataSource = null;

			dataSource = new EiaDataSource(eiaList);
			reportFileRealPath = getServletContext().getRealPath(
					REPORT_FILE_DIR);

			Map<String, Object> paramsReport = generateParamsMap(req);

			JasperPrint fillReport = JasperFillManager.fillReport(
					reportFileRealPath, paramsReport, dataSource);

			exportAsPDF(resp, fillReport, "detalle-equipos.pdf");

		} catch (GHAEJBException e) {
			LOG.log(Level.ERROR,
					"Problema al obtener los datos para el reporte", e);

		} catch (JRException e) {
			LOG.log(Level.ERROR,
					"Problema al generar el reporte de JasperReport", e);

		}

	}

	private List<Eia> searchInService(HttpServletRequest req)
			throws GHAEJBException {
		QueryParamsContainer qpc = new QueryParamsContainer(req);
		List<Eia> eiaList = null;

		if (qpc.eiaIds == null)
			eiaList = service.findAll(qpc.facilsIds, qpc.workingAreasIds,
					qpc.eiaState, qpc.orden);
		else
			eiaList = service.find(qpc.eiaIds, qpc.orden);

		return eiaList;
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
		Image logoImage = new ImageIcon(getServletContext().getRealPath(
				LOGO_DIR)).getImage();

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
		private List<Long> eiaIds;
		private List<Long> facilsIds;
		private List<Long> workingAreasIds;
		private EiaStateEnum eiaState;
		private boolean orden;

		/**
		 * @param req
		 *            objeto que tiene los parametros de enviadospor el cliente
		 *            al servlet
		 */
		public QueryParamsContainer(HttpServletRequest req) {
			String eiasValue = req.getParameter(PARAM_EIAS);
			eiaIds = toList(eiasValue);

			String facsValue = req.getParameter(PARAM_FACILS);
			facilsIds = toList(facsValue);

			String workAreasValue = req.getParameter(PARAM_WORKAREAS);
			workingAreasIds = toList(workAreasValue);

			eiaState = null;
			String eiaStateParam = req.getParameter(PARAM_EDOEIA);
			if (eiaStateParam != null)
				if (!eiaStateParam.equals("all"))
					eiaState = EiaStateEnum.valueOf(eiaStateParam);

			orden = Boolean.valueOf(req.getParameter(PARAM_ORDEN));
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
