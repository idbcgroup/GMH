package org.fourgeeks.gha.webclient.server.message;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.msg.GHAMessage;
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
	@EJB(name = "msg.messageService")
	MessageServiceRemote ejbService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.message.GWTMessageService#find(java
	 * .lang.String)
	 */
	@Override
	public GHAMessage find(String Id) throws GHAEJBException {
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