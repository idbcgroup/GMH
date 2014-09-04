/**
 * 
 */
package org.fourgeeks.gha.webclient.server.maintenanceplanification;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.GlaLog;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.ejb.gmh.GlaLogServiceRemote;
import org.fourgeeks.gha.webclient.client.eiadamagereport.GWTEiaDamageReportService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 * 
 */
@WebServlet(urlPatterns = { "/webclient/eiaDamageReport" })
public class GWTEiaDamageReportServiceImpl extends RemoteServiceServlet
		implements GWTEiaDamageReportService {

	private static final long serialVersionUID = 1L;

	@EJB(lookup = "java:global/ear-1/ejb-1/GlaLogService")
	GlaLogServiceRemote serviceRemote;

	@Override
	public boolean delete(long Id) throws GHAEJBException {
		boolean resp = serviceRemote.delete(Id);
		return resp;
	}

	@Override
	public List<GlaLog> findByEiaType(EiaType eiaType)
			throws GHAEJBException {
		List<GlaLog> glaLogs = serviceRemote
				.findByEiaType(eiaType);

		return glaLogs;
	}

	@Override
	public List<GlaLog> getAll() throws GHAEJBException {
		List<GlaLog> glaLogs = serviceRemote.getAll();
		return glaLogs;
	}

	@Override
	public GlaLog save(GlaLog glaLog)
			throws GHAEJBException {
		GlaLog savedDamageReport = serviceRemote.save(glaLog);
		return savedDamageReport;
	}

	@Override
	public GlaLog update(GlaLog glaLog)
			throws GHAEJBException {
		GlaLog updatedEiaDamageReport = serviceRemote
				.update(glaLog);

		return updatedEiaDamageReport;
	}
}
