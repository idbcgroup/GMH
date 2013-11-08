package org.fourgeeks.gha.webclient.client.eiatype.material;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaterial;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 * 
 */
@RemoteServiceRelativePath("eiaTypeMaterial")
public interface GWTEiaTypeMaterialService extends RemoteService {
	public List<EiaTypeMaterial> findByEiaType(EiaType eiaType)
			throws GHAEJBException;

	public EiaTypeMaterial save(EiaTypeMaterial eiaTypeMaterial)
			throws GHAEJBException;

	public void delete(long id) throws GHAEJBException;

	public EiaTypeMaterial update(EiaTypeMaterial eiaTypeMaterial)
			throws GHAEJBException;
}
