/**
 * 
 */
package org.fourgeeks.gha.webclient.server.eiatype.maintenance.protocol;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.ProtocolActivity;
import org.fourgeeks.gha.domain.gmh.ProtocolActivityComponent;
import org.fourgeeks.gha.ejb.gmh.ProtocolActivityComponentServiceRemote;
import org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.GWTProtocolActivityComponentService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 *
 */
@WebServlet(urlPatterns = {"/webclient/protocolActivityComponent"})
public class GWTProtocolActivityComponentImpl extends RemoteServiceServlet implements
		GWTProtocolActivityComponentService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB(name = "gmh.protocolActivityComponentService")
	ProtocolActivityComponentServiceRemote service;

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.GWTProtocolActivityComponentService#delete(long)
	 */
	@Override
	public void delete(long Id) throws EJBException {
		service.delete(Id);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.GWTProtocolActivityComponentService#findByProtocolActivity(org.fourgeeks.gha.domain.gmh.ProtocolActivity)
	 */
	@Override
	public List<ProtocolActivityComponent> findByProtocolActivity(
			ProtocolActivity protocolActivity) throws EJBException {
		return service.findByProtocolActivity(protocolActivity);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.GWTProtocolActivityComponentService#find(long)
	 */
	@Override
	public ProtocolActivityComponent find(long Id) throws EJBException {
		return service.find(Id);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.GWTProtocolActivityComponentService#getAll()
	 */
	@Override
	public List<ProtocolActivityComponent> getAll() throws EJBException {
		return service.getAll();
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.GWTProtocolActivityComponentService#getAll(int, int)
	 */
	@Override
	public List<ProtocolActivityComponent> getAll(int offset, int size)
			throws EJBException {
		return service.getAll(offset, size);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.GWTProtocolActivityComponentService#save(org.fourgeeks.gha.domain.gmh.ProtocolActivityComponent)
	 */
	@Override
	public ProtocolActivityComponent save(
			ProtocolActivityComponent protocolActivityComponent)
			throws EJBException {
		return service.save(protocolActivityComponent);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.GWTProtocolActivityComponentService#update(org.fourgeeks.gha.domain.gmh.ProtocolActivityComponent)
	 */
	@Override
	public ProtocolActivityComponent update(
			ProtocolActivityComponent protocolActivityComponent)
			throws EJBException {
		return service.update(protocolActivityComponent);
	}

}
