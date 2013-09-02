package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaterial;

@Remote
public interface EiaTypeMaterialServiceRemote {

	public List<EiaTypeMaterial> findByEiaType(EiaType eiaType) throws EJBException;

	public EiaTypeMaterial save(EiaTypeMaterial eiaTypeMaterial) throws EJBException;

	public void delete(long id) throws EJBException;
	
}
