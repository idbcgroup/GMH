package org.fourgeeks.gha.webclient.server.eiatype.utility;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeUtility;
import org.fourgeeks.gha.ejb.gmh.EiaTypeUtilityServiceRemote;
import org.fourgeeks.gha.webclient.client.eiatype.utility.GWTEiaTypeUtilityService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author alacret
 * 
 */
@WebServlet(urlPatterns = { "/webclient/eiaTypeUtility" })
public class GWTEiaTypeUtilityServiceImpl extends RemoteServiceServlet
		implements GWTEiaTypeUtilityService {

	private static final long serialVersionUID = 1L;

	@EJB(name = "gmh.EiaTypeUtilityService")
	private EiaTypeUtilityServiceRemote serviceRemote;

	@Override
	public void delete(long id) throws GHAEJBException {
		serviceRemote.delete(id);
	}

	@Override
	public EiaTypeUtility save(EiaTypeUtility eiaTypeUtility)
			throws GHAEJBException {
		return serviceRemote.save(eiaTypeUtility);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.utility.GWTEiaTypeUtilityService#findByEiaType(org.fourgeeks.gha.domain.gmh.EiaType)
	 */
	@Override
	public List<EiaTypeUtility> findByEiaType(EiaType eiaType)
			throws GHAEJBException {
		return serviceRemote.findByEiaType(eiaType);
	}

}
