/**
 * 
 */
package org.fourgeeks.gha.webclient.server.eia.component;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaComponent;
import org.fourgeeks.gha.ejb.gmh.EiaComponentServiceRemote;
import org.fourgeeks.gha.webclient.client.eia.component.GWTEiaComponentService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 * 
 */
@WebServlet(urlPatterns = { "/webclient/eiaComponent" })
public class GWTEiaComponentServiceImpl extends RemoteServiceServlet implements
		GWTEiaComponentService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB(lookup = "java:global/ear-1/ejb-1/EiaComponentService")
	EiaComponentServiceRemote serviceRemote;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.eia.component.GWTEiaComponentService
	 * #delete(long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		serviceRemote.delete(Id);
	}

	@Override
	public void delete(List<EiaComponent> eiaComponents) throws GHAEJBException {
		for (EiaComponent eiaComponent : eiaComponents) {
			serviceRemote.delete(eiaComponent.getId());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.eia.component.GWTEiaComponentService
	 * #find(org.fourgeeks.gha.domain.gmh.Eia)
	 */
	@Override
	public List<EiaComponent> findByParentEia(Eia eia) throws GHAEJBException {
		return serviceRemote.findByParentEia(eia);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.eia.component.GWTEiaComponentService
	 * #find(org.fourgeeks.gha.domain.gmh.Eia, int, int)
	 */
	@Override
	public List<EiaComponent> findByParentEia(Eia eia, int offset, int size)
			throws GHAEJBException {
		return serviceRemote.findByParentEia(eia);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.eia.component.GWTEiaComponentService
	 * #find(long)
	 */
	@Override
	public EiaComponent find(long Id) throws GHAEJBException {
		return serviceRemote.find(Id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.eia.component.GWTEiaComponentService
	 * #getAll()
	 */
	@Override
	public List<EiaComponent> getAll() throws GHAEJBException {
		return serviceRemote.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.eia.component.GWTEiaComponentService
	 * #getAll(int, int)
	 */
	@Override
	public List<EiaComponent> getAll(int offset, int size)
			throws GHAEJBException {
		return serviceRemote.getAll(offset, size);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.eia.component.GWTEiaComponentService
	 * #save(org.fourgeeks.gha.domain.gmh.EiaComponent)
	 */
	@Override
	public EiaComponent save(EiaComponent eiaComponent) throws GHAEJBException {
		return serviceRemote.save(eiaComponent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.eia.component.GWTEiaComponentService
	 * #update(org.fourgeeks.gha.domain.gmh.EiaComponent)
	 */
	@Override
	public EiaComponent update(EiaComponent eiaComponent)
			throws GHAEJBException {
		return serviceRemote.update(eiaComponent);
	}

}
