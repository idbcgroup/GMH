package org.fourgeeks.gha.webclient.client.eiatype.material;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaterial;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("eiaTypeMaterial")
public interface GWTEiaTypeMaterialService extends RemoteService {

	public List<EiaTypeMaterial> findByEiaType(EiaType eiaType) throws EJBException;
	
	public EiaTypeMaterial save(EiaTypeMaterial eiaTypeMaterial) throws EJBException;
	
	public void delete(long id) throws EJBException;
	
}
