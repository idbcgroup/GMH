package org.fourgeeks.gha.webclient.server.eia.reports;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.log4j.Logger;

public abstract class ReportEiaServelt extends HttpServlet {
	protected static final Logger LOG = Logger
			.getLogger(ReportEiaServelt.class);
	private static final long serialVersionUID = 1L;

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
	protected void exportAsPDF(HttpServletResponse response,
			JasperPrint fillReport, String nombreReportFile)
			throws JRException, IOException {

		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "inline; filename="
				+ nombreReportFile);

		JasperExportManager.exportReportToPdfStream(fillReport,
				response.getOutputStream());
	}

	/**
	 * @return {@link String} con la fecha, hora y zona horaria actual para el
	 *         reporte
	 */
	protected String genDatetimeTimezoneStrRep() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yy z h:mm a");
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT-4:30"));
		return dateFormat.format(new Date()).toLowerCase();
	}

	/**
	 * @param req
	 *            objeto con los parametros de la peticionO
	 * @return Mapa con los parametros que recibe el reporte
	 */
	protected abstract Map<String, Object> generateParamsMap(
			HttpServletRequest req);

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
