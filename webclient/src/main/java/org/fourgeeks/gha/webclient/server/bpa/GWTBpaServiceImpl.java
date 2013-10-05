/**
 * 
 */
package org.fourgeeks.gha.webclient.server.bpa;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.mix.Bpa;
import org.fourgeeks.gha.ejb.mix.BpaServiceRemote;
import org.fourgeeks.gha.webclient.client.bpa.GWTBpaService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 *
 */
@WebServlet(urlPatterns = { "/webclient/bpa" })
public class GWTBpaServiceImpl extends RemoteServiceServlet implements GWTBpaService {
	@EJB(name = "mix.BpaService")
	BpaServiceRemote service;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.bpa.GWTBpaService#delete(long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		service.delete(Id);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.bpa.GWTBpaService#find(org.fourgeeks.gha.domain.mix.Bpa)
	 */
	@Override
	public List<Bpa> find(Bpa bpa) throws GHAEJBException {
		return service.find(bpa);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.bpa.GWTBpaService#find(long)
	 */
	@Override
	public Bpa find(long Id) throws GHAEJBException {
		return service.find(Id);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.bpa.GWTBpaService#getAll()
	 */
	@Override
	public List<Bpa> getAll() throws GHAEJBException {
		return service.getAll();
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.bpa.GWTBpaService#save(org.fourgeeks.gha.domain.mix.Bpa)
	 */
	@Override
	public Bpa save(Bpa bpa) throws GHAEJBException {
		return service.save(bpa);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.bpa.GWTBpaService#update(org.fourgeeks.gha.domain.mix.Bpa)
	 */
	@Override
	public Bpa update(Bpa bpa) throws GHAEJBException {
		return service.update(bpa);
	}

}
