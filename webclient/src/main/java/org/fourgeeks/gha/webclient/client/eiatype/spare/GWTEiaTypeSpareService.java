package org.fourgeeks.gha.webclient.client.eiatype.spare;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeSpare;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("eiaTypeSpare")
public interface GWTEiaTypeSpareService extends RemoteService {

	public EiaTypeSpare save(EiaTypeSpare eiaTypeSpare) throws EJBException;
	
	public List<EiaTypeSpare> find(EiaType eiaType) throws EJBException;
	
	public void delete(long id) throws EJBException;
}
