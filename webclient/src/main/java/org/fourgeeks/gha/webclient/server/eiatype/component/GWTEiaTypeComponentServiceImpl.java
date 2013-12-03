/**
 * 
 */
package org.fourgeeks.gha.webclient.server.eiatype.component;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeComponent;
import org.fourgeeks.gha.ejb.gmh.EiaTypeComponentServiceRemote;
import org.fourgeeks.gha.webclient.client.eiatype.component.GWTEiaTypeComponentService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 * 
 */
@WebServlet(urlPatterns = { "/webclient/eiaTypeComponent" })
public class GWTEiaTypeComponentServiceImpl extends RemoteServiceServlet
		implements GWTEiaTypeComponentService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB(lookup = "java:global/ear-1/ejb-1/EiaTypeComponentService!org.fourgeeks.gha.ejb.gmh.EiaTypeComponentServiceRemote")
	EiaTypeComponentServiceRemote serviceRemote;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eiatype.component.
	 * GWTEiaTypeComponentService#delete(long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		serviceRemote.delete(Id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eiatype.component.
	 * GWTEiaTypeComponentService#find(long)
	 */
	@Override
	public EiaTypeComponent find(long Id) throws GHAEJBException {
		return serviceRemote.find(Id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eiatype.component.
	 * GWTEiaTypeComponentService#getAll()
	 */
	@Override
	public List<EiaTypeComponent> getAll() throws GHAEJBException {
		return serviceRemote.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eiatype.component.
	 * GWTEiaTypeComponentService#getAll(int, int)
	 */
	@Override
	public List<EiaTypeComponent> getAll(int offset, int size)
			throws GHAEJBException {
		return serviceRemote.getAll(offset, size);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eiatype.component.
	 * GWTEiaTypeComponentService
	 * #save(org.fourgeeks.gha.domain.gmh.EiaTypeComponent)
	 */
	@Override
	public EiaTypeComponent save(EiaTypeComponent eiaTypeComponent)
			throws GHAEJBException {
		return serviceRemote.save(eiaTypeComponent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eiatype.component.
	 * GWTEiaTypeComponentService
	 * #update(org.fourgeeks.gha.domain.gmh.EiaTypeComponent)
	 */
	@Override
	public EiaTypeComponent update(EiaTypeComponent eiaTypeComponent)
			throws GHAEJBException {
		return serviceRemote.update(eiaTypeComponent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eiatype.component.
	 * GWTEiaTypeComponentService
	 * #findByParentEiaType(org.fourgeeks.gha.domain.gmh.EiaType)
	 */
	@Override
	public List<EiaTypeComponent> findByParentEiaType(EiaType eiaType)
			throws GHAEJBException {
		return serviceRemote.findByParentEiaType(eiaType);
	}

}
