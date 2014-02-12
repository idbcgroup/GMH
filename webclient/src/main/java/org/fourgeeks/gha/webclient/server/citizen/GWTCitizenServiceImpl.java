/**
 * 
 */
package org.fourgeeks.gha.webclient.server.citizen;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.ejb.mix.CitizenServiceRemote;
import org.fourgeeks.gha.webclient.client.citizen.GWTCitizenService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 * 
 */
@WebServlet(urlPatterns = { "/webclient/citizen" })
public class GWTCitizenServiceImpl extends RemoteServiceServlet implements
		GWTCitizenService {
	@EJB(lookup = "java:global/ear-1/ejb-1/CitizenService")
	CitizenServiceRemote service;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.citizen.GWTCitizenService#delete(long)
	 */
	@Override
	public void delete(List<Citizen> citizens) throws GHAEJBException {
		service.delete(citizens);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.citizen.GWTCitizenService#find(org
	 * .fourgeeks.gha.domain.mix.Citizen)
	 */
	@Override
	public List<Citizen> find(Citizen citizen) throws GHAEJBException {
		return service.find(citizen);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.citizen.GWTCitizenService#find(long)
	 */
	@Override
	public Citizen find(long Id) throws GHAEJBException {
		return service.find(Id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.citizen.GWTCitizenService#getAll()
	 */
	@Override
	public List<Citizen> getAll() throws GHAEJBException {
		return service.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.citizen.GWTCitizenService#save(org
	 * .fourgeeks.gha.domain.mix.Citizen)
	 */
	@Override
	public Citizen save(Citizen citizen) throws GHAEJBException {
		return service.save(citizen);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.citizen.GWTCitizenService#update(org
	 * .fourgeeks.gha.domain.mix.Citizen)
	 */
	@Override
	public Citizen update(Citizen citizen) throws GHAEJBException {
		return service.update(citizen);
	}

}
