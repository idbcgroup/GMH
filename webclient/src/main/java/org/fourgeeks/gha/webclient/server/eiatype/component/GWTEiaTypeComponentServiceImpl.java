/**
 * 
 */
package org.fourgeeks.gha.webclient.server.eiatype.component;

import java.util.List;

import javax.ejb.EJB;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeComponent;
import org.fourgeeks.gha.ejb.gmh.EiaTypeComponentServiceRemote;
import org.fourgeeks.gha.webclient.client.eiatype.component.GWTEiaTypeComponentService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 *
 */
public class GWTEiaTypeComponentServiceImpl extends RemoteServiceServlet implements
		GWTEiaTypeComponentService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB(name = "gmh.EiaTypeComponentService")
	EiaTypeComponentServiceRemote serviceRemote;

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.component.GWTEiaTypeComponentService#delete(long)
	 */
	@Override
	public void delete(long Id) throws EJBException {
		serviceRemote.delete(Id);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.component.GWTEiaTypeComponentService#find(org.fourgeeks.gha.domain.gmh.EiaType)
	 */
	@Override
	public List<EiaTypeComponent> find(EiaType eiaType) throws EJBException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.component.GWTEiaTypeComponentService#find(org.fourgeeks.gha.domain.gmh.EiaType, int, int)
	 */
	@Override
	public List<EiaTypeComponent> find(EiaType eiaType, int offset, int size)
			throws EJBException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.component.GWTEiaTypeComponentService#find(long)
	 */
	@Override
	public EiaTypeComponent find(long Id) throws EJBException {
		return serviceRemote.find(Id);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.component.GWTEiaTypeComponentService#findByEiaTypeId(long)
	 */
	@Override
	public List<EiaTypeComponent> findByEiaTypeId(long Id) throws EJBException {
		return serviceRemote.findByParentEiaTypeId(Id);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.component.GWTEiaTypeComponentService#getAll()
	 */
	@Override
	public List<EiaTypeComponent> getAll() throws EJBException {
		return serviceRemote.getAll();
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.component.GWTEiaTypeComponentService#getAll(int, int)
	 */
	@Override
	public List<EiaTypeComponent> getAll(int offset, int size)
			throws EJBException {
		return serviceRemote.getAll(offset, size);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.component.GWTEiaTypeComponentService#save(org.fourgeeks.gha.domain.gmh.EiaTypeComponent)
	 */
	@Override
	public EiaTypeComponent save(EiaTypeComponent eiaTypeComponent)
			throws EJBException {
		return serviceRemote.save(eiaTypeComponent);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.component.GWTEiaTypeComponentService#update(org.fourgeeks.gha.domain.gmh.EiaTypeComponent)
	 */
	@Override
	public EiaTypeComponent update(EiaTypeComponent eiaTypeComponent)
			throws EJBException {
		return serviceRemote.update(eiaTypeComponent);
	}

}
