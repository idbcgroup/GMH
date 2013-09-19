/**
 * 
 */
package org.fourgeeks.gha.webclient.server.legalentity;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.mix.LegalEntity;
import org.fourgeeks.gha.ejb.mix.LegalEntityServiceRemote;
import org.fourgeeks.gha.webclient.client.legalentity.GWTLegalEntityService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 *
 */
@WebServlet(urlPatterns = { "/webclient/legalEntity" })
public class GWTLegalEntityServiceImpl extends RemoteServiceServlet implements GWTLegalEntityService {
	@EJB(name = "mix.LegalEntityService")
	LegalEntityServiceRemote service;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.legalentity.GWTLegalEntityService#delete(long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		service.delete(Id);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.legalentity.GWTLegalEntityService#find(org.fourgeeks.gha.domain.mix.LegalEntity)
	 */
	@Override
	public List<LegalEntity> find(LegalEntity legalEntity) throws GHAEJBException {
		return service.find(legalEntity);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.legalentity.GWTLegalEntityService#find(long)
	 */
	@Override
	public LegalEntity find(long Id) throws GHAEJBException {
		return service.find(Id);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.legalentity.GWTLegalEntityService#getAll()
	 */
	@Override
	public List<LegalEntity> getAll() throws GHAEJBException {
		return service.getAll();
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.legalentity.GWTLegalEntityService#save(org.fourgeeks.gha.domain.mix.LegalEntity)
	 */
	@Override
	public LegalEntity save(LegalEntity legalEntity) throws GHAEJBException {
		return service.save(legalEntity);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.legalentity.GWTLegalEntityService#update(org.fourgeeks.gha.domain.mix.LegalEntity)
	 */
	@Override
	public LegalEntity update(LegalEntity legalEntity) throws GHAEJBException {
		return service.update(legalEntity);
	}

}
