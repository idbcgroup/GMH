package org.fourgeeks.gha.webclient.server.eia;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.ejb.gmh.EiaServiceRemote;
import org.fourgeeks.gha.webclient.client.eia.GWTEiaService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author alacret
 * 
 */
@WebServlet(urlPatterns = { "/webclient/eia" })
public class GWTEiaServiceImpl extends RemoteServiceServlet implements
		GWTEiaService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB(lookup = "java:global/ear-1/ejb-1/EiaService")
	EiaServiceRemote eiaServiceRemote;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eia.GWTEiaService#countByState()
	 */
	@Override
	public List<Long> countByState() throws GHAEJBException {
		return eiaServiceRemote.countByState();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.eia.GWTEiaService#delete(java.util
	 * .List)
	 */
	@Override
	public void delete(List<Eia> eias) throws GHAEJBException {
		for (Eia eia : eias)
			eiaServiceRemote.delete(eia.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eia.GWTEiaService#delete(long)
	 */
	@Override
	public boolean delete(long Id) throws GHAEJBException {
		return eiaServiceRemote.delete(Id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.eia.GWTEiaService#find(org.fourgeeks
	 * .gha.domain.gmh.Eia)
	 */
	@Override
	public List<Eia> find(Eia eia) throws GHAEJBException {
		return eiaServiceRemote.find(eia);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eia.GWTEiaService#find(long)
	 */
	@Override
	public Eia find(long Id) throws GHAEJBException {
		return eiaServiceRemote.find(Id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.eia.GWTEiaService#find(org.fourgeeks
	 * .gha.domain.gmh.EiaType)
	 */
	@Override
	public List<Eia> findByEiaType(EiaType eiaType) throws GHAEJBException {
		return eiaServiceRemote.findByEiaType(eiaType);
	}

	@Override
	public List<Eia> findComponents(Eia eia, EiaType eiaType)
			throws GHAEJBException {
		return eiaServiceRemote.findComponents(eia, eiaType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eia.GWTEiaService#
	 * findDamagedAndInMaintenance(org.fourgeeks.gha.domain.gmh.EiaType)
	 */
	@Override
	public List<Eia> findDamagedAndInMaintenance(EiaType eiaType)
			throws GHAEJBException {
		return eiaServiceRemote.findDamagedAndInMaintenance(eiaType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eia.GWTEiaService#getAll()
	 */
	@Override
	public List<Eia> getAll() throws GHAEJBException {
		return eiaServiceRemote.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eia.GWTEiaService#getAll(int,
	 * int)
	 */
	@Override
	public List<Eia> getAll(int offset, int size) throws GHAEJBException {
		return eiaServiceRemote.getAll(offset, size);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.eia.GWTEiaService#save(org.fourgeeks
	 * .gha.domain.gmh.Eia)
	 */
	@Override
	public Eia save(Eia eia) throws GHAEJBException {
		return eiaServiceRemote.save(eia);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eia.GWTEiaService#update(long)
	 */
	@Override
	public Eia update(Eia eia) throws GHAEJBException {
		return eiaServiceRemote.update(eia);
	}
}
