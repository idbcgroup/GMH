package org.fourgeeks.gha.webclient.server.eiatype.material;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaterial;
import org.fourgeeks.gha.ejb.gmh.EiaTypeMaterialServiceRemote;
import org.fourgeeks.gha.webclient.client.eiatype.material.GWTEiaTypeMaterialService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 * 
 */

@WebServlet(urlPatterns = { "/webclient/eiaTypeMaterial" })
public class GWTEiaTypeMaterialServiceImpl extends RemoteServiceServlet
		implements GWTEiaTypeMaterialService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB(lookup = "java:global/ear-1/ejb-1/EiaTypeMaterialService")
	private EiaTypeMaterialServiceRemote serviceRemote;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.eiatype.material.GWTEiaTypeMaterialService
	 * #findByEiaType(org.fourgeeks.gha.domain.gmh.EiaType)
	 */
	@Override
	public List<EiaTypeMaterial> findByEiaType(EiaType eiaType)
			throws GHAEJBException {
		return serviceRemote.findByEiaType(eiaType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.eiatype.material.GWTEiaTypeMaterialService
	 * #save(org.fourgeeks.gha.domain.gmh.EiaTypeMaterial)
	 */
	@Override
	public EiaTypeMaterial save(EiaTypeMaterial eiaTypeMaterial)
			throws GHAEJBException {
		return serviceRemote.save(eiaTypeMaterial);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.eiatype.material.GWTEiaTypeMaterialService
	 * #delete(long)
	 */
	@Override
	public void delete(long id) throws GHAEJBException {
		serviceRemote.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.eiatype.material.GWTEiaTypeMaterialService
	 * #update(org.fourgeeks.gha.domain.gmh.EiaTypeMaterial)
	 */
	@Override
	public EiaTypeMaterial update(EiaTypeMaterial eiaTypeMaterial)
			throws GHAEJBException {
		return serviceRemote.update(eiaTypeMaterial);
	}

}
