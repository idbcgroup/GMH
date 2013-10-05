package org.fourgeeks.gha.webclient.server.eia;

import java.util.List;

import javax.ejb.EJB;

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
public class GWTEiaServiceImpl extends RemoteServiceServlet implements
		GWTEiaService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB(name = "gmh.EiaService")
	EiaServiceRemote eServiceRemote;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eia.GWTEiaService#delete(long)
	 */
	@Override
	public boolean delete(long Id) throws GHAEJBException {
		return eServiceRemote.delete(Id);
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
		return eServiceRemote.find(eia);
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
		return eServiceRemote.findByEiaType(eiaType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eia.GWTEiaService#find(long)
	 */
	@Override
	public Eia find(long Id) throws GHAEJBException {
		return eServiceRemote.find(Id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eia.GWTEiaService#getAll()
	 */
	@Override
	public List<Eia> getAll() throws GHAEJBException {
		return eServiceRemote.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eia.GWTEiaService#getAll(int,
	 * int)
	 */
	@Override
	public List<Eia> getAll(int offset, int size) throws GHAEJBException {
		return eServiceRemote.getAll(offset, size);
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
		return eServiceRemote.save(eia);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eia.GWTEiaService#update(long)
	 */
	@Override
	public Eia update(Eia eia) throws GHAEJBException {
		return eServiceRemote.update(eia);
	}

}
