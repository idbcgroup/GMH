package org.fourgeeks.gha.ejb.msg;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.msg.GHAMessage;
import org.fourgeeks.gha.domain.msg.GHAMessageId;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * @author emiliot
 * 
 */
@Stateless(name = "msg.messageService")
public class MessageService implements MessageServiceRemote {
	@PersistenceContext
	private EntityManager em;

	private final static Logger logger = Logger.getLogger(MessageService.class
			.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.message.MessageServiceRemote#find(java.lang.String)
	 */
	@Override
	public GHAMessage find(String Id) throws GHAEJBException {
		try {
			return em.find(GHAMessage.class, new GHAMessageId(Id,
					RuntimeParameters.getLang()));
		} catch (NoResultException e1) {

			try {
				return em.createNamedQuery("GHAMessage.find", GHAMessage.class)
						.setParameter("code", "no-message")
						.setParameter("language", RuntimeParameters.getLang())
						.getSingleResult();
			} catch (Exception e) {
				logger.log(Level.INFO, "ERROR: finding GHAMessage", e);
				throw new GHAEJBException("ERROR: finding GHAMessage "
						+ e.getCause().getMessage());
			}
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding GHAMessage", e);
			throw new GHAEJBException("ERROR: finding GHAMessage "
					+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.msg.MessageServiceRemote#find(java.util.List)
	 */
	@Override
	public List<GHAMessage> find(List<String> messages) throws GHAEJBException {
		List<GHAMessage> result = new ArrayList<GHAMessage>();
		for (String message : messages)
			result.add(find(message));

		return result;
	}

}
