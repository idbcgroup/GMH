package org.fourgeeks.gha.webclient.server;

import java.io.IOException;
import java.util.HashMap;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.fourgeeks.gha.domain.gmh.EiaDamageReport;
import org.fourgeeks.gha.ejb.PDTMessageProducerRemote;

/**
 * @author nramirez
 * 
 */
@WebServlet(urlPatterns = { "/msgtest" })
public class MessageTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger
			.getLogger(MessageTestServlet.class);

	@EJB(lookup = "java:global/ear-1/ejb-1/PDTMessageProducer")
	PDTMessageProducerRemote service;

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
			EiaDamageReport damageReport = new EiaDamageReport();
			damageReport.setDamageMotive("sin motivo alguno");

			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("texto", "Esto es una prueba");
			params.put("eiaDamageReport", damageReport);

			service.sendMessage("corrective-maintenance", params);

		} catch (Exception e) {
			LOG.log(Level.ERROR, "Problema al enviar datos a cola de mensajes",
					e);
		}
	}
}
