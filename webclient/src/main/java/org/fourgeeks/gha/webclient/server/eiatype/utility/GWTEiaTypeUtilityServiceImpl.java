package org.fourgeeks.gha.webclient.server.eiatype.utility;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.EJBException;
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
	public void delete(long id) throws EJBException {
		serviceRemote.delete(id);
	}

	@Override
	public EiaTypeUtility save(EiaTypeUtility eiaTypeUtility)
			throws EJBException {
		return serviceRemote.save(eiaTypeUtility);
	}

	@Override
	public List<EiaTypeUtility> findByEiaType(String code)
			throws EJBException {
		return serviceRemote.findByEiaType(code);
	}

}
