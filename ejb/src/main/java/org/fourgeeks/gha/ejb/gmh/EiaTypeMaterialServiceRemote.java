package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.glm.MaterialTypeEnum;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaterialBrand;

/**
 * @author emiliot
 * 
 */

@Remote
public interface EiaTypeMaterialServiceRemote {

	public void delete(long id) throws GHAEJBException;

	public List<EiaTypeMaterialBrand> findByEiaType(EiaType eiaType,
			MaterialTypeEnum type) throws GHAEJBException;

	public EiaTypeMaterialBrand save(EiaTypeMaterialBrand eiaTypeMaterial)
			throws GHAEJBException;

	public EiaTypeMaterialBrand update(EiaTypeMaterialBrand eiaTypeMaterial)
			throws GHAEJBException;
}
