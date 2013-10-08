package org.fourgeeks.gha.webclient.server.eia.reports;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.log4j.Level;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.ejb.gmh.EiaReportsServiceRemote;

public class ReportEquiposServlet extends ReportEiaServelt {
	private static final long serialVersionUID = 1L;

	private static final String REPORT_FILE_DIR = "/resources/reportes/eiaReport.jasper";

	@EJB(name = "gmh.EiaReportsService", beanInterface = EiaReportsServiceRemote.class)
	EiaReportsServiceRemote service;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			ArrayList<Long> arrayList = new ArrayList<Long>();
			arrayList.add(2L);
			arrayList.add(3L);
			List<Eia> eiaListByIds = service.findByEiaIds(arrayList);

			Eia eiaFilter = generateEiaForReport(req);
			List<Eia> eiaList = service.findByEiaIds(arrayList);

			LOG.info("eiaListByIds.size() = " + eiaListByIds.size());

			String reportFileRealPath = null;
			EiaDataSource dataSource = null;

			dataSource = new EiaDataSource(eiaList);
			reportFileRealPath = getServletContext().getRealPath(
					REPORT_FILE_DIR);

			Map<String, Object> paramsReport = generateParamsMap(req);

			JasperPrint fillReport = JasperFillManager.fillReport(
					reportFileRealPath, paramsReport, dataSource);

			exportAsPDF(resp, fillReport, "equipos-edo-ubic.pdf");

		} catch (GHAEJBException e) {
			LOG.log(Level.ERROR,
					"Problema al obtener los datos para el reporte", e);

		} catch (JRException e) {
			LOG.log(Level.ERROR,
					"Problema al generar el reporte de JasperReport", e);

		}

	}

	private Eia generateEiaForReport(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Map<String, Object> generateParamsMap(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

}
