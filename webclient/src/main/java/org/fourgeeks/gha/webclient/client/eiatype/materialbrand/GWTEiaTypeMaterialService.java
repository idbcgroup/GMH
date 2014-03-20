package org.fourgeeks.gha.webclient.client.eiatype.materialbrand;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.glm.MaterialTypeEnum;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaterialBrand;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 * 
 */
@RemoteServiceRelativePath("eiaTypeMaterial")
public interface GWTEiaTypeMaterialService extends RemoteService {
	public void delete(List<EiaTypeMaterialBrand> entities)
			throws GHAEJBException;

	public void delete(long id) throws GHAEJBException;

	public List<EiaTypeMaterialBrand> findByEiaType(EiaType eiaType,
			MaterialTypeEnum type) throws GHAEJBException;

	public EiaTypeMaterialBrand save(EiaTypeMaterialBrand eiaTypeMaterial)
			throws GHAEJBException;

	public EiaTypeMaterialBrand update(EiaTypeMaterialBrand eiaTypeMaterial)
			throws GHAEJBException;
}
