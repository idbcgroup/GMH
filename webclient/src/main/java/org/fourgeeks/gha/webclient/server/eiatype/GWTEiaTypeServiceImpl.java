/**
 * 
 */
package org.fourgeeks.gha.webclient.server.eiatype;

import java.util.List;

import javax.ejb.EJB;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.ejb.gmh.EiaTypeServiceRemote;
import org.fourgeeks.gha.webclient.client.eiatype.GWTEiaTypeService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 * 
 */
public class GWTEiaTypeServiceImpl extends RemoteServiceServlet implements
		GWTEiaTypeService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB(name = "gmh.EiaTypeService")
	EiaTypeServiceRemote eiaTypeServiceRemote;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.eia.GWTEiaTypeService#save(org.fourgeeks
	 * .gha.domain.gmh.EiaType)
	 */
	@Override
	public EiaType save(EiaType eiaType) throws GHAEJBException {
		return eiaTypeServiceRemote.save(eiaType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eia.GWTEiaTypeService#find(long)
	 */
	@Override
	public EiaType find(String code) throws GHAEJBException {
		return eiaTypeServiceRemote.find(code);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.eia.GWTEiaTypeService#update(org.fourgeeks
	 * .gha.domain.gmh.EiaType)
	 */
	@Override
	public EiaType update(EiaType eiaType) throws GHAEJBException {
		return eiaTypeServiceRemote.update(eiaType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.eia.GWTEiaTypeService#delete(long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		eiaTypeServiceRemote.delete(Id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eia.GWTEiaTypeService#getAll()
	 */
	@Override
	public List<EiaType> getAll() throws GHAEJBException {
		return eiaTypeServiceRemote.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eia.GWTEiaTypeService#getAll(int,
	 * int)
	 */
	@Override
	public List<EiaType> getAll(int offset, int size) throws GHAEJBException {
		return eiaTypeServiceRemote.getAll(offset, size);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.eia.GWTEiaTypeService#find(org.fourgeeks
	 * .gha.domain.gmh.EiaType)
	 */
	@Override
	public List<EiaType> find(EiaType eiaType) throws GHAEJBException {
		return eiaTypeServiceRemote.find(eiaType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.eia.GWTEiaTypeService#find(org.fourgeeks
	 * .gha.domain.gmh.EiaType, int, int)
	 */
	@Override
	public List<EiaType> find(EiaType eiaType, int offset, int size)
			throws GHAEJBException {
		return eiaTypeServiceRemote.find(eiaType, offset, size);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.GWTEiaTypeService#findByMaintenancePlan(org.fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public List<EiaType> findByMaintenancePlan(MaintenancePlan maintenancePlan)
			throws GHAEJBException {
		return eiaTypeServiceRemote.findByMaintenancePlan(maintenancePlan);
	}
}
