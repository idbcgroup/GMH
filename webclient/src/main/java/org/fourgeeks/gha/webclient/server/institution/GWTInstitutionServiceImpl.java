/**
 * 
 */
package org.fourgeeks.gha.webclient.server.institution;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.mix.Institution;
import org.fourgeeks.gha.ejb.mix.InstitutionServiceRemote;
import org.fourgeeks.gha.webclient.client.institution.GWTInstitutionService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 *
 */
@WebServlet(urlPatterns = {"/webclient/institution"})
public class GWTInstitutionServiceImpl extends RemoteServiceServlet implements GWTInstitutionService {
	@EJB(name = "mix.InstitutionService")
	InstitutionServiceRemote service;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.institution.GWTInstitutionService#delete(long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		service.delete(Id);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.institution.GWTInstitutionService#find(org.fourgeeks.gha.domain.mix.Institution)
	 */
	@Override
	public List<Institution> find(Institution institution) throws GHAEJBException {
		return service.find(institution);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.institution.GWTInstitutionService#find(long)
	 */
	@Override
	public Institution find(long Id) throws GHAEJBException {
		return service.find(Id);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.institution.GWTInstitutionService#getAll()
	 */
	@Override
	public List<Institution> getAll() throws GHAEJBException {
		return service.getAll();
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.institution.GWTInstitutionService#save(org.fourgeeks.gha.domain.mix.Institution)
	 */
	@Override
	public Institution save(Institution institution) throws GHAEJBException {
		return service.save(institution);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.institution.GWTInstitutionService#update(org.fourgeeks.gha.domain.mix.Institution)
	 */
	@Override
	public Institution update(Institution institution) throws GHAEJBException {
		return service.update(institution);
	}

}
