/**
 * 
 */
package org.fourgeeks.gha.webclient.server.eiatype.maintenance.protocol;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenanceProtocol;
import org.fourgeeks.gha.domain.gmh.ProtocolActivity;
import org.fourgeeks.gha.ejb.gmh.ProtocolActivityServiceRemote;
import org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.GWTProtocolActivityService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 *
 */
@WebServlet(urlPatterns = {"/webclient/protocolActivityService"})
public class GWTProtocolActivityServiceImpl extends RemoteServiceServlet 
		implements GWTProtocolActivityService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB(name = "gmh.protocolActivityService")
	ProtocolActivityServiceRemote ejbService;

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.GWTProtocolActivityService#delete(long)
	 */
	@Override
	public void delete(long Id) throws EJBException {
		ejbService.delete(Id);		
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.GWTProtocolActivityService#findByEiaTypeMaintenanceProtocol(org.fourgeeks.gha.domain.gmh.EiaTypeMaintenanceProtocol)
	 */
	@Override
	public List<ProtocolActivity> findByEiaTypeMaintenanceProtocol(
			EiaTypeMaintenanceProtocol eiaTypeMaintenanceProtocol)
			throws EJBException {
		return ejbService.findByEiaTypeMaintenanceProtocol(eiaTypeMaintenanceProtocol);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.GWTProtocolActivityService#findByEiaTypeMaintenanceProtocol(org.fourgeeks.gha.domain.gmh.EiaTypeMaintenanceProtocol, int, int)
	 */
	@Override
	public List<ProtocolActivity> findByEiaTypeMaintenanceProtocol(
			EiaTypeMaintenanceProtocol eiaTypeMaintenanceProtocol, int offset,
			int size) throws EJBException {
		return ejbService.findByEiaTypeMaintenanceProtocol(eiaTypeMaintenanceProtocol, offset, size);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.GWTProtocolActivityService#find(long)
	 */
	@Override
	public ProtocolActivity find(long Id) throws EJBException {
		return ejbService.find(Id);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.GWTProtocolActivityService#getAll()
	 */
	@Override
	public List<ProtocolActivity> getAll() throws EJBException {
		return ejbService.getAll();
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.GWTProtocolActivityService#getAll(int, int)
	 */
	@Override
	public List<ProtocolActivity> getAll(int offset, int size)
			throws EJBException {
		return ejbService.getAll(offset, size);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.GWTProtocolActivityService#save(org.fourgeeks.gha.domain.gmh.ProtocolActivity)
	 */
	@Override
	public ProtocolActivity save(ProtocolActivity protocolActivity)
			throws EJBException {
		return ejbService.save(protocolActivity	);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.GWTProtocolActivityService#update(org.fourgeeks.gha.domain.gmh.ProtocolActivity)
	 */
	@Override
	public ProtocolActivity update(ProtocolActivity protocolActivity)
			throws EJBException {
		return ejbService.update(protocolActivity);
	}

}
