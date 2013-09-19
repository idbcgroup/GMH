/**
 * 
 */
package org.fourgeeks.gha.webclient.server.bpu;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.ejb.gar.BpuServiceRemote;
import org.fourgeeks.gha.webclient.client.bpu.GWTBpuService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 *
 */
@WebServlet(urlPatterns = { "/webclient/bpu" })
public class GWTBpuServiceImpl extends RemoteServiceServlet implements GWTBpuService {
	@EJB(name = "gar.BpuService")
	BpuServiceRemote service;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.bpu.GWTBpuService#delete(long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		service.delete(Id);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.bpu.GWTBpuService#find(org.fourgeeks.gha.domain.gar.Bpu)
	 */
	@Override
	public List<Bpu> find(Bpu bpu) throws GHAEJBException {
		return service.find(bpu);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.bpu.GWTBpuService#find(long)
	 */
	@Override
	public Bpu find(long Id) throws GHAEJBException {
		return service.find(Id);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.bpu.GWTBpuService#getAll()
	 */
	@Override
	public List<Bpu> getAll() throws GHAEJBException {
		return service.getAll();
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.bpu.GWTBpuService#save(org.fourgeeks.gha.domain.gar.Bpu)
	 */
	@Override
	public Bpu save(Bpu bpu) throws GHAEJBException {
		return service.save(bpu);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.bpu.GWTBpuService#update(org.fourgeeks.gha.domain.gar.Bpu)
	 */
	@Override
	public Bpu update(Bpu bpu) throws GHAEJBException {
		return service.update(bpu);
	}

}
