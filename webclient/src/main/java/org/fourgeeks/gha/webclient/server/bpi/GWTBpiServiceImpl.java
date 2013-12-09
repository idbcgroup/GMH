/**
 * 
 */
package org.fourgeeks.gha.webclient.server.bpi;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.mix.Bpi;
import org.fourgeeks.gha.ejb.mix.BpiServiceRemote;
import org.fourgeeks.gha.webclient.client.bpi.GWTBpiService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 * 
 */
@WebServlet(urlPatterns = { "/webclient/bpi" })
public class GWTBpiServiceImpl extends RemoteServiceServlet implements
		GWTBpiService {
	@EJB(lookup = "java:global/ear-1/ejb-1/BpiService")
	BpiServiceRemote service;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.bpi.GWTBpiService#delete(long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		service.delete(Id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.bpi.GWTBpiService#find(org.fourgeeks
	 * .gha.domain.mix.Bpi)
	 */
	@Override
	public List<Bpi> find(Bpi bpi) throws GHAEJBException {
		return service.find(bpi);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.bpi.GWTBpiService#find(long)
	 */
	@Override
	public Bpi find(long Id) throws GHAEJBException {
		return service.find(Id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.bpi.GWTBpiService#getAll()
	 */
	@Override
	public List<Bpi> getAll() throws GHAEJBException {
		return service.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.bpi.GWTBpiService#save(org.fourgeeks
	 * .gha.domain.mix.Bpi)
	 */
	@Override
	public Bpi save(Bpi bpi) throws GHAEJBException {
		return service.save(bpi);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.bpi.GWTBpiService#update(org.fourgeeks
	 * .gha.domain.mix.Bpi)
	 */
	@Override
	public Bpi update(Bpi bpi) throws GHAEJBException {
		return service.update(bpi);
	}

}
