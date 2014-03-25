package org.fourgeeks.gha.webclient.server.message;

import java.util.List;
import java.util.logging.Logger;

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

	private final static Logger logger = Logger
			.getLogger(GWTMessageServiceImpl.class.getName());

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB(lookup = "java:global/ear-1/ejb-1/MessageService!"
			+ "org.fourgeeks.gha.ejb.msg.MessageServiceRemote")
	MessageServiceRemote ejbService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.message.GWTMessageService#find(java
	 * .util.List)
	 */
	@Override
	public List<GHAMessage> find(List<String> messages) throws GHAEJBException {
		try {
			final String user = String.valueOf(getThreadLocalRequest()
					.getSession().getAttribute("user"));
			return ejbService.findAndLog(user, messages);
		} catch (final NullPointerException e) {
			logger.info("ERROR:user not found where it should be found");
			return ejbService.findAndLog(null, messages);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.message.GWTMessageService#find(java
	 * .lang.String)
	 */
	@Override
	public GHAMessage find(String id) throws GHAEJBException {
		try {
			final String user = String.valueOf(getThreadLocalRequest()
					.getSession().getAttribute("user"));
			return ejbService.findAndLog(user, id);
		} catch (final NullPointerException e) {
			logger.info("ERROR:user not found where it should be found");
			return ejbService.findAndLog(null, id);
		}
	}

}
