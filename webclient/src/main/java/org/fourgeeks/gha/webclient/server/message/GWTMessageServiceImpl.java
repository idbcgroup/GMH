package org.fourgeeks.gha.webclient.server.message;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.msg.GHAMessage;
import org.fourgeeks.gha.ejb.ess.SSOUserServiceRemote;
import org.fourgeeks.gha.ejb.msg.MessageServiceRemote;
import org.fourgeeks.gha.webclient.client.message.GWTMessageService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 * 
 */

@WebServlet(urlPatterns = { "/webclient/messageService" })
public class GWTMessageServiceImpl extends RemoteServiceServlet implements
		GWTMessageService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB(lookup = "java:global/ear-1/ejb-1/MessageService")
	MessageServiceRemote ejbService;

	@EJB(lookup = "java:global/ear-1/ejb-1/SSOUserService")
	SSOUserServiceRemote ssoUserService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.message.GWTMessageService#find(java
	 * .lang.String)
	 */
	@Override
	public GHAMessage find(String Id) throws GHAEJBException {
		System.out.println(getThreadLocalRequest().getSession().getAttribute(
				"user"));
		return ejbService.find(Id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.message.GWTMessageService#find(java
	 * .util.List)
	 */
	@Override
	public List<GHAMessage> find(List<String> messages) throws GHAEJBException {
		return ejbService.find(messages);
	}

}
