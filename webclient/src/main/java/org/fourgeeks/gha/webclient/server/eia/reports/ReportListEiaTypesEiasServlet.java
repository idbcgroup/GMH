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
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.ejb.gmh.EiaReportsServiceRemote;

/**
 * Servlet para el reporte que muestra un listado de eiaTypes y sus eias si asi
 * se desea
 * 
 * @author naramirez
 * 
 */
@WebServlet(urlPatterns = { "/reports/eia/leia" })
public class ReportListEiaTypesEiasServlet extends ReportEiaServelt {

	private static final long serialVersionUID = 1L;
	private static final String REPORT_FILE_DIR = "/resources/reportes/compilados/GMH.LEIA.01.jasper";
	private static final String SUBREPORT_FILE_DIR = "/resources/reportes/compilados/GMH.LEIA.01.SR.jasper";

	private static final String LOGO_DIR = "/resources/img/logoReport.jpg";

	private static final String PARAM_EIATYPES = "eiatypes", PARAM_FACILS = "facils",
			PARAM_WORKAREAS = "workareas", PARAM_EDOEIA = "edoeia", PARAM_SHOWEIAS = "showeias",
			PARAM_USER = "user";

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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		try {

			Map<String, Object> searchMap = searchInService(req);
			EiaDataSource eiaDataSource = (EiaDataSource) searchMap.get("eiaDS");
			EiaTypeDataSource eiaTypeDataSource = (EiaTypeDataSource) searchMap.get("eiaTypeDS");

			String reportFileRealPath = getServletContext().getRealPath(REPORT_FILE_DIR);

			Map<String, Object> paramsReport = generateParamsMap(req);
			paramsReport.put("subReportDataSource", eiaDataSource);

			JasperPrint fillReport = JasperFillManager.fillReport(reportFileRealPath, paramsReport,
					eiaTypeDataSource);

			exportAsPDF(resp, fillReport, "detalle-equipos.pdf");

		} catch (GHAEJBException e) {
			LOG.log(Level.ERROR, "Problema al obtener los datos para el reporte", e);

		} catch (JRException e) {
			LOG.log(Level.ERROR, "Problema al generar el reporte de JasperReport", e);

		}

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
		String showEias = req.getParameter(PARAM_SHOWEIAS);
		String datetimeReport = genDatetimeTimezoneStrRep();
		String subReportRealPath = getServletContext().getRealPath(SUBREPORT_FILE_DIR);

		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("logo", logoImage);
		paramsMap.put("fechaHoraReporte", datetimeReport);
		paramsMap.put("nombreOperador", user);
		paramsMap.put("showEias", Boolean.valueOf(showEias));
		paramsMap.put("subReportFileDir", subReportRealPath);

		return paramsMap;
	}

	/**
	 * Busca en el servicio EJB los datos necesarios para el reporte y
	 * subreporte
	 * 
	 * @param req
	 *            El request con los datos para la consulta al EJB
	 * @return Mapa con los DataSource utilizados por el reporte y subreporte
	 * @throws GHAEJBException
	 */
	private Map<String, Object> searchInService(HttpServletRequest req) throws GHAEJBException {
		QueryParamsContainer qpc = new QueryParamsContainer(req);
		List<Eia> eiaList = null;
		List<EiaType> eiaTypeList = null;

		if (qpc.showEias) {
			eiaTypeList = service.findEiaTypes(qpc.eiaTypeCodes);

			List<String> eiaTypeCodeList = toCodeList(eiaTypeList);
			eiaList = service.findEiasByEiaTypes(eiaTypeCodeList, qpc.facilsIds,
					qpc.workingAreasIds, qpc.eiaState);
		} else {
			eiaTypeList = service.findEiaTypes(qpc.eiaTypeCodes);
			eiaList = new ArrayList<Eia>();
		}

		EiaDataSource eiaDS = new EiaDataSource(eiaList);
		EiaTypeDataSource eiaTypeDS = new EiaTypeDataSource(eiaTypeList);

		HashMap<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("eiaDS", eiaDS);
		mapa.put("eiaTypeDS", eiaTypeDS);

		return mapa;
	}

	/**
	 * @param eiaTypeList
	 *            lista de tipos de equipo
	 * @return Lista de codigos de los tipos de equipos
	 */
	private List<String> toCodeList(List<EiaType> eiaTypeList) {
		ArrayList<String> codeList = new ArrayList<String>();
		for (EiaType eiaType : eiaTypeList)
			codeList.add(eiaType.getCode());

		return codeList;
	}

	/**
	 * Clase contenedora de los parametros para las consultas a realizar a los
	 * EJB
	 * 
	 * @author naramirez
	 */
	private class QueryParamsContainer {
		private List<String> eiaTypeCodes;
		private List<Long> facilsIds;
		private List<Long> workingAreasIds;
		private EiaStateEnum eiaState;
		private boolean showEias;

		/**
		 * @param req
		 *            objeto que tiene los parametros de enviadospor el cliente
		 *            al servlet
		 */
		public QueryParamsContainer(HttpServletRequest req) {
			String eiasValue = req.getParameter(PARAM_EIATYPES);
			eiaTypeCodes = toStringList(eiasValue);

			String facsValue = req.getParameter(PARAM_FACILS);
			facilsIds = toLongList(facsValue);

			String workAreasValue = req.getParameter(PARAM_WORKAREAS);
			workingAreasIds = toLongList(workAreasValue);

			eiaState = null;
			String eiaStateParam = req.getParameter(PARAM_EDOEIA);
			if (eiaStateParam != null)
				if (!eiaStateParam.equals("all"))
					eiaState = EiaStateEnum.valueOf(eiaStateParam);

			showEias = Boolean.valueOf(req.getParameter(PARAM_SHOWEIAS));
		}

		private List<Long> toLongList(String paramValue) {
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

		private List<String> toStringList(String paramValue) {
			if (paramValue != null)
				if (!paramValue.equals("all")) {
					ArrayList<String> list = new ArrayList<String>();
					String[] arrayLongValues = paramValue.split(",");
					for (String val : arrayLongValues)
						list.add(val);
					return list;
				}

			return null;
		}
	}
}
