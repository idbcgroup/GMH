/**
 * 
 */
package org.fourgeeks.gha.webclient.server.eia.component;

import java.util.List;

import javax.ejb.EJB;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaComponent;
import org.fourgeeks.gha.ejb.gmh.EiaComponentServiceRemote;
import org.fourgeeks.gha.webclient.client.eia.component.GWTEiaComponentService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 *
 */
public class GWTEiaComponentServiceImpl extends RemoteServiceServlet implements GWTEiaComponentService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB(name = "gmh.EiaComponentService")
	EiaComponentServiceRemote serviceRemote;

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eia.component.GWTEiaComponentService#delete(long)
	 */
	@Override
	public void delete(long Id) throws EJBException {
		serviceRemote.delete(Id);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eia.component.GWTEiaComponentService#find(org.fourgeeks.gha.domain.gmh.Eia)
	 */
	@Override
	public List<EiaComponent> find(Eia eia) throws EJBException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eia.component.GWTEiaComponentService#find(org.fourgeeks.gha.domain.gmh.Eia, int, int)
	 */
	@Override
	public List<EiaComponent> find(Eia eia, int offset, int size)
			throws EJBException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eia.component.GWTEiaComponentService#find(long)
	 */
	@Override
	public EiaComponent find(long Id) throws EJBException {
		return serviceRemote.find(Id);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eia.component.GWTEiaComponentService#findByEiaId(long)
	 */
	@Override
	public List<EiaComponent> findByEiaId(long Id) throws EJBException {
		return serviceRemote.findByParentEiaId(Id);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eia.component.GWTEiaComponentService#getAll()
	 */
	@Override
	public List<EiaComponent> getAll() throws EJBException {
		return serviceRemote.getAll();
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eia.component.GWTEiaComponentService#getAll(int, int)
	 */
	@Override
	public List<EiaComponent> getAll(int offset, int size) throws EJBException {
		return serviceRemote.getAll(offset, size);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eia.component.GWTEiaComponentService#save(org.fourgeeks.gha.domain.gmh.EiaComponent)
	 */
	@Override
	public EiaComponent save(EiaComponent eiaComponent) throws EJBException {
		return serviceRemote.save(eiaComponent);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eia.component.GWTEiaComponentService#update(org.fourgeeks.gha.domain.gmh.EiaComponent)
	 */
	@Override
	public EiaComponent update(EiaComponent eiaComponent) throws EJBException {
		return serviceRemote.update(eiaComponent);
	}

}
