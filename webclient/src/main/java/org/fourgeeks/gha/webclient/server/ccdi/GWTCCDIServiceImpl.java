package org.fourgeeks.gha.webclient.server.ccdi;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gom.CCDIDefinition;
import org.fourgeeks.gha.domain.gom.CCDILevelDefinition;
import org.fourgeeks.gha.domain.gom.CCDILevelValue;
import org.fourgeeks.gha.ejb.gom.CCDIServiceRemote;
import org.fourgeeks.gha.webclient.client.ccdi.GWTCCDIService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 * 
 */
@WebServlet(urlPatterns = { "/webclient/ccdi" })
public class GWTCCDIServiceImpl extends RemoteServiceServlet implements
		GWTCCDIService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB(lookup = "java:global/ear-1/ejb-1/CCDIService!org.fourgeeks.gha.ejb.gom.CCDIServiceRemote")
	CCDIServiceRemote ccdiServiceRemote;

	@Override
	public CCDIDefinition createCCDIDefinition(CCDIDefinition definition)
			throws GHAEJBException {
		return ccdiServiceRemote.createCCDIDefinition(definition);
	}

	@Override
	public CCDILevelDefinition createCCDILevelDefinition(
			CCDILevelDefinition levelDefinition) throws GHAEJBException {
		return ccdiServiceRemote.createLevelDefinition(levelDefinition);
	}

	@Override
	public CCDILevelValue createCCDILevelValue(CCDILevelValue levelValue)
			throws GHAEJBException {
		return ccdiServiceRemote.createLevelValue(levelValue);
	}

	@Override
	public void deleteByCode(String code) throws GHAEJBException {
		ccdiServiceRemote.deleteByCode(code);
	}

	@Override
	public CCDIDefinition findCCDIDefinitionByCode(String code)
			throws GHAEJBException {
		return ccdiServiceRemote.findCCDIDefinitionByCode(code);
	}

	@Override
	public CCDILevelDefinition findCCDILevelDefinitionByLevel(
			CCDIDefinition definition, int level) throws GHAEJBException {
		return ccdiServiceRemote.findCCDILevelDefinitionByLevel(definition,
				level);
	}

	@Override
	public String getNextCCDILevelValue(String code) throws GHAEJBException {
		// return ccdiServiceRemote.getNextCategoryValueCode(code);
		// TODO
		return null;
	}

}
