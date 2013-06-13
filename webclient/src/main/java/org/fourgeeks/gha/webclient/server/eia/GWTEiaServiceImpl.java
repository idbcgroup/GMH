package org.fourgeeks.gha.webclient.server.eia;

import java.util.List;

import javax.ejb.EJB;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.ejb.gmh.EiaServiceRemote;
import org.fourgeeks.gha.webclient.client.eia.GWTEiaService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class GWTEiaServiceImpl extends RemoteServiceServlet implements GWTEiaService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB(name = "gmh.EiaService")
	EiaServiceRemote eServiceRemote;

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eia.GWTEiaService#save(org.fourgeeks.gha.domain.gmh.Eia)
	 */
	@Override
	public void save(Eia eia) {
		eServiceRemote.save(eia);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eia.GWTEiaService#find(long)
	 */
	@Override
	public Eia find(long Id) {
		return eServiceRemote.find(Id);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eia.GWTEiaService#find(org.fourgeeks.gha.domain.gmh.Eia)
	 */
	@Override
	public List<Eia> find(Eia eia) {
		//TODO: implementar en el ejb primero
		return null;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eia.GWTEiaService#find(org.fourgeeks.gha.domain.gmh.EiaType)
	 */
	@Override
	public List<Eia> find(EiaType eiaType) {
		return eServiceRemote.find(eiaType);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eia.GWTEiaService#getAll()
	 */
	@Override
	public List<Eia> getAll() {
		return eServiceRemote.getAll();
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eia.GWTEiaService#getAll(int, int)
	 */
	@Override
	public List<Eia> getAll(int offset, int size) {
		return eServiceRemote.getAll(offset, size);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eia.GWTEiaService#delete(long)
	 */
	@Override
	public void delete(long Id) {
		eServiceRemote.delete(Id);
	}

}
