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

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.log4j.Level;
import org.fourgeeks.gha.domain.enu.EiaReportFiltersEnum;
import org.fourgeeks.gha.domain.enu.EiaReportOrderByEnum;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaTypeComponent;
import org.fourgeeks.gha.domain.gmh.EiaTypeComponentReportEntity;
import org.fourgeeks.gha.domain.gmh.EiaTypeCompsEiasReportEntity;
import org.fourgeeks.gha.ejb.gmh.EiaReportsServiceRemote;
import org.fourgeeks.gha.ejb.gmh.EiaTypeComponentServiceRemote;

@WebServlet(urlPatterns = { "/reports/eia/lcrd" })
public class ReportListEiaTypeCompsServlet extends ReportEiaServelt {
	private static final long serialVersionUID = 1L;

	private static final String REPORT_FILE_DIR_1 = "/resources/reportes/compilados/GMH.LCRD.01.jasper";
	private static final String REPORT_FILE_DIR_2 = "/resources/reportes/compilados/GMH.LCRD.02.jasper";
	private static final String LOGO_DIR = "/resources/img/logoReport.jpg";

	private static final String PARAM_EIATYPES = "eiatypes",
			PARAM_COMPONENT = "component", PARAM_FILTER = "filter",
			PARAM_USER = "user", PARAM_ORDEN = "orden";

	@EJB(lookup = "java:global/ear-1/ejb-1/EiaReportsService")
	EiaReportsServiceRemote serviceReport;

	@EJB(lookup = "java:global/ear-1/ejb-1/EiaTypeComponentService!org.fourgeeks.gha.ejb.gmh.EiaTypeComponentServiceRemote")
	EiaTypeComponentServiceRemote serviceEiaTypeComponent;

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
			Map<String, Object> searchMap = searchInService(req);

			String reportFileRealPath = null;
			JRDataSource dataSource = null;

			dataSource = (JRDataSource) searchMap.get("dataSource");
			reportFileRealPath = (String) searchMap.get("reportPath");

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.server.eia.reports.ReportEiaServelt#
	 * searchInService(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected Map<String, Object> searchInService(HttpServletRequest req)
			throws GHAEJBException {
		QueryParamsContainer qpc = new QueryParamsContainer(req);
		List<EiaTypeCompsEiasReportEntity> eiaList = null;
		List<EiaTypeComponentReportEntity> eiaTypeCompList = null;
		String reportPath = null;

		HashMap<String, Object> mapa = new HashMap<String, Object>();

		// TIPOS DE EQUIPO Y SUS COMPONENTES
		if (qpc.filter == EiaReportFiltersEnum.EIATYPE_AND_COMPONENTS) {
			if (qpc.componentId == null) {
				// Componentes de uno, varios o todos los tipos de equipo
				eiaTypeCompList = serviceReport.findComponentsByEiaTypes(
						qpc.eiaTypeCodes, qpc.orden);
			} else {
				// un componente especifico de un tipo de equipo
				eiaTypeCompList = new ArrayList<EiaTypeComponentReportEntity>();
				EiaTypeComponent comp = serviceEiaTypeComponent
						.find(qpc.componentId);
				eiaTypeCompList.add(new EiaTypeComponentReportEntity(comp));
			}

			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(
					eiaTypeCompList);
			mapa.put("dataSource", dataSource);
			reportPath = getServletContext().getRealPath(REPORT_FILE_DIR_1);
		}
		// COMPONENTES Y LOS EQUIPOS QUE LO TIENEN DEFINIDO
		else if (qpc.filter == EiaReportFiltersEnum.COMPONENTS_AND_EIA) {
			eiaList = serviceReport.findEiasByEiaTypeComponents(
					qpc.eiaTypeCodes, qpc.componentId, qpc.orden);

			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(
					eiaList);
			mapa.put("dataSource", dataSource);
			reportPath = getServletContext().getRealPath(REPORT_FILE_DIR_2);
		}

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
		private final List<String> eiaTypeCodes;
		private final Long componentId;
		private final EiaReportFiltersEnum filter;
		private final EiaReportOrderByEnum orden;

		/**
		 * @param req
		 *            objeto que tiene los parametros de enviadospor el cliente
		 *            al servlet
		 */
		public QueryParamsContainer(HttpServletRequest req) {

			String eiaTypesValue = req.getParameter(PARAM_EIATYPES);
			eiaTypeCodes = validateToStringList(eiaTypesValue);

			String filterValue = req.getParameter(PARAM_FILTER);
			filter = EiaReportFiltersEnum.valueOf(filterValue);

			String compValue = req.getParameter(PARAM_COMPONENT);
			componentId = validateToLong(compValue);

			Boolean orderByUbicEiaType = Boolean.valueOf(req
					.getParameter(PARAM_ORDEN));
			orden = orderByUbicEiaType ? EiaReportOrderByEnum.EIATYPE_COMPONENT
					: EiaReportOrderByEnum.COMPONENT_EIATYPE;
		}

		private Long validateToLong(String paramValue) {
			if (paramValue != null)
				if (!paramValue.equals("all"))
					return Long.valueOf(paramValue);
			return null;
		}

		private List<String> validateToStringList(String paramValue) {
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
