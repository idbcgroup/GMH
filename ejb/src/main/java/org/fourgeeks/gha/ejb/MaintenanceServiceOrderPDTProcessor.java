package org.fourgeeks.gha.ejb;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;

import org.fourgeeks.gha.domain.gmh.EiaDamageReport;
import org.fourgeeks.gha.ejb.ess.MaintenanceServiceOrderService;

/**
 * Session Bean implementation class MaintenanceServiceOrderPDTProcessor
 */
@Stateless
public class MaintenanceServiceOrderPDTProcessor implements PDTProcessor {

	private final static Logger logger = Logger
			.getLogger(MaintenanceServiceOrderService.class.getName());

	@Override
	public void processMessage(HashMap<String, Object> data) {

		String texto = (String) data.get("texto");
		EiaDamageReport damageReport = (EiaDamageReport) data
				.get("eiaDamageReport");

		logger.log(Level.INFO, "data.texto = " + texto);
		logger.log(Level.INFO, "data.eiaDamageReport.damageMotive = "
				+ damageReport.getDamageMotive());
	}

}
