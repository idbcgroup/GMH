/**
 * 
 */
package org.fourgeeks.gha.webclient.server.maintenanceplanification;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaDamageReport;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.ejb.gmh.EiaDamageReportServiceRemote;
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

	@EJB(lookup = "java:global/ear-1/ejb-1/EiaDamageReportService")
	EiaDamageReportServiceRemote serviceRemote;

	@Override
	public boolean delete(long Id) throws GHAEJBException {
		boolean resp = serviceRemote.delete(Id);
		return resp;
	}

	@Override
	public List<EiaDamageReport> findByEiaType(EiaType eiaType)
			throws GHAEJBException {
		List<EiaDamageReport> eiaDamageReports = serviceRemote
				.findByEiaType(eiaType);

		return eiaDamageReports;
	}

	@Override
	public List<EiaDamageReport> getAll() throws GHAEJBException {
		List<EiaDamageReport> eiaDamageReports = serviceRemote.getAll();
		return eiaDamageReports;
	}

	@Override
	public EiaDamageReport save(EiaDamageReport eiaDamageReport)
			throws GHAEJBException {
		EiaDamageReport savedDamageReport = serviceRemote.save(eiaDamageReport);
		return savedDamageReport;
	}

	@Override
	public EiaDamageReport update(EiaDamageReport eiaDamageReport)
			throws GHAEJBException {
		EiaDamageReport updatedEiaDamageReport = serviceRemote
				.update(eiaDamageReport);

		return updatedEiaDamageReport;
	}
}
