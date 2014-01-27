package org.fourgeeks.gha.webclient.server.ccdi;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gom.Concept;
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
	@EJB(lookup = "java:global/ear-1/ejb-1/CCDIService")
	CCDIServiceRemote ccdiServiceRemote;

	@Override
	public String createCCDIDefinition(String code, String name, int length,
			int levels, String status, Concept concept, String type,
			boolean addVerify, String verificationMethod)
			throws GHAEJBException {
		return ccdiServiceRemote.createCCDIDefinition(code, name, length,
				levels, status, concept, type, addVerify, verificationMethod);
	}

	@Override
	public String createCCDILevelDefinition(String definition, int level,
			String name, int length, String valueType, String fixedValue,
			int initialValue, int incValue, String separator,
			String valueAtEndAction) throws GHAEJBException {
		return ccdiServiceRemote.createCCDILevelDefinition(definition, level,
				name, length, valueType, fixedValue, initialValue, incValue,
				separator, valueAtEndAction);
	}

	@Override
	public String getNextCCDILevelValue(String code) throws GHAEJBException {
		return ccdiServiceRemote.getNextCCDILevelValue(code);
	}

}
