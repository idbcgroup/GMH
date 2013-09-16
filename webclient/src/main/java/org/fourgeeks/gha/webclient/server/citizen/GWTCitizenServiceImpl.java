/**
 * 
 */
package org.fourgeeks.gha.webclient.server.citizen;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.ejb.mix.CitizenServiceRemote;
import org.fourgeeks.gha.webclient.client.citizen.GWTCitizenService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 *
 */
@WebServlet(urlPatterns = { "/webclient/citizen" })
public class GWTCitizenServiceImpl extends RemoteServiceServlet implements GWTCitizenService {
	@EJB(name = "mix.CitizenService")
	CitizenServiceRemote service;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.citizen.GWTCitizenService#delete(long)
	 */
	@Override
	public void delete(long Id) throws EJBException {
		service.delete(Id);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.citizen.GWTCitizenService#find(org.fourgeeks.gha.domain.mix.Citizen)
	 */
	@Override
	public List<Citizen> find(Citizen citizen) throws EJBException {
		return service.find(citizen);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.citizen.GWTCitizenService#find(long)
	 */
	@Override
	public Citizen find(long Id) throws EJBException {
		return service.find(Id);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.citizen.GWTCitizenService#getAll()
	 */
	@Override
	public List<Citizen> getAll() throws EJBException {
		return service.getAll();
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.citizen.GWTCitizenService#save(org.fourgeeks.gha.domain.mix.Citizen)
	 */
	@Override
	public Citizen save(Citizen citizen) throws EJBException {
		return service.save(citizen);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.citizen.GWTCitizenService#update(org.fourgeeks.gha.domain.mix.Citizen)
	 */
	@Override
	public Citizen update(Citizen citizen) throws EJBException {
		return service.update(citizen);
	}

}
