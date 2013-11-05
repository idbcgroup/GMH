package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaterialCategory;

@Remote
public interface EiaTypeMaterialCategoryServiceRemote {

	public List<EiaTypeMaterialCategory> findByEiaType(EiaType eiaType)
			throws GHAEJBException;

	public EiaTypeMaterialCategory save(EiaTypeMaterialCategory eiaTypeMaterial)
			throws GHAEJBException;

	public void delete(long id) throws GHAEJBException;

	public EiaTypeMaterialCategory update(
			EiaTypeMaterialCategory eiaTypeMaterialCategory)
			throws GHAEJBException;

}
