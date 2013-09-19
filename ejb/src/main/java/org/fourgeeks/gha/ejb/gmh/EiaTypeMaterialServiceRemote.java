package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaterial;

@Remote
public interface EiaTypeMaterialServiceRemote {

	public List<EiaTypeMaterial> findByEiaType(EiaType eiaType) throws GHAEJBException;

	public EiaTypeMaterial save(EiaTypeMaterial eiaTypeMaterial) throws GHAEJBException;

	public void delete(long id) throws GHAEJBException;
	
}
