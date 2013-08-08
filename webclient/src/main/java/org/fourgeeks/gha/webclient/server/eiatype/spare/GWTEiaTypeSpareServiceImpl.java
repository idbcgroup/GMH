package org.fourgeeks.gha.webclient.server.eiatype.spare;

import java.util.List;

import javax.ejb.EJB;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeSpare;
import org.fourgeeks.gha.ejb.gmh.EiaTypeSpareServiceRemote;
import org.fourgeeks.gha.webclient.client.eiatype.spare.GWTEiaTypeSpareService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class GWTEiaTypeSpareServiceImpl extends RemoteServiceServlet
		implements GWTEiaTypeSpareService {

	private static final long serialVersionUID = 1L;

	@EJB(name = "gmh.EiaTypeSpareService")
	private EiaTypeSpareServiceRemote serviceRemote;
	
	@Override
	public EiaTypeSpare save(EiaTypeSpare eiaTypeSpare) throws EJBException {
		return serviceRemote.save(eiaTypeSpare);
	}

	@Override
	public List<EiaTypeSpare> find(EiaType eiaType) throws EJBException {
		return serviceRemote.find(eiaType);
	}

	@Override
	public void delete(long id) throws EJBException {
		serviceRemote.delete(id);
	}
	
}
