package org.fourgeeks.gha.webclient.server.eiatype.material;

import java.util.List;

import javax.ejb.EJB;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaterial;
import org.fourgeeks.gha.ejb.gmh.EiaTypeMaterialServiceRemote;
import org.fourgeeks.gha.webclient.client.eiatype.material.GWTEiaTypeMaterialService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class GWTEiaTypeMaterialServiceImpl extends RemoteServiceServlet
		implements GWTEiaTypeMaterialService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@EJB(name = "gmh.EiaTypeMaterialService")
	private EiaTypeMaterialServiceRemote serviceRemote;
	
	@Override
	public List<EiaTypeMaterial> findByEiaType(EiaType eiaType) throws GHAEJBException {
		return serviceRemote.findByEiaType(eiaType);
	}

	@Override
	public EiaTypeMaterial save(EiaTypeMaterial eiaTypeMaterial)
			throws GHAEJBException {
		return serviceRemote.save(eiaTypeMaterial);
	}

	@Override
	public void delete(long id) throws GHAEJBException {
		serviceRemote.delete(id);
	}

}
