/**
 * 
 */
package org.fourgeeks.gha.webclient.server.eiatype.maintenance.protocol;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenanceProtocol;
import org.fourgeeks.gha.ejb.gmh.EiaTypeMaintenanceProtocolServiceRemote;
import org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.GWTEiaTypeMaintenanceProtocolService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 *
 */
@WebServlet(urlPatterns = {"/webclient/eiaTypeMaintenanceProtocol"})
public class GWTEiaTypeMaintenanceProtocolServiceImpl extends RemoteServiceServlet implements
		GWTEiaTypeMaintenanceProtocolService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB(name = "gmh.eiaTypeMaintenanceProtocolServiceRemote")
	EiaTypeMaintenanceProtocolServiceRemote eiaTypeMantProtocolService;

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.GWTEiaTypeMaintenanceProtocolService#delete(long)
	 */
	@Override
	public void delete(long Id) throws EJBException {
		eiaTypeMantProtocolService.delete(Id);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.GWTEiaTypeMaintenanceProtocolService#findByEiaTypeMaintenancePlan(org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan)
	 */
	@Override
	public List<EiaTypeMaintenanceProtocol> findByEiaTypeMaintenancePlan(
			EiaTypeMaintenancePlan eiaTypeMaintenancePlan) throws EJBException {
		return eiaTypeMantProtocolService.findByEiaTypeMaintenancePlan(eiaTypeMaintenancePlan);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.GWTEiaTypeMaintenanceProtocolService#find(long)
	 */
	@Override
	public EiaTypeMaintenanceProtocol find(long Id) throws EJBException {
		return eiaTypeMantProtocolService.find(Id);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.GWTEiaTypeMaintenanceProtocolService#getAll()
	 */
	@Override
	public List<EiaTypeMaintenanceProtocol> getAll() throws EJBException {
		return eiaTypeMantProtocolService.getAll();
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.GWTEiaTypeMaintenanceProtocolService#save(org.fourgeeks.gha.domain.gmh.EiaTypeMaintenanceProtocol)
	 */
	@Override
	public EiaTypeMaintenanceProtocol save(
			EiaTypeMaintenanceProtocol eiaTypeMaintenanceProtocol)
			throws EJBException {
		return eiaTypeMantProtocolService.save(eiaTypeMaintenanceProtocol);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.GWTEiaTypeMaintenanceProtocolService#update(org.fourgeeks.gha.domain.gmh.EiaTypeMaintenanceProtocol)
	 */
	@Override
	public EiaTypeMaintenanceProtocol update(
			EiaTypeMaintenanceProtocol eiaTypeMaintenanceProtocol)
			throws EJBException {
		return eiaTypeMantProtocolService.update(eiaTypeMaintenanceProtocol);
	}

}
