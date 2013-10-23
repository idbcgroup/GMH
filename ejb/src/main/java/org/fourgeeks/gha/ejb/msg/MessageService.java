package org.fourgeeks.gha.ejb.msg;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.msg.GHAMessage;
import org.fourgeeks.gha.domain.msg.GHAMessageId;
import org.fourgeeks.gha.ejb.GHAEJBExceptionImpl;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * @author emiliot, vivi.torresg
 * 
 */
@Stateless(name = "msg.messageService")
public class MessageService extends GHAEJBExceptionImpl implements
		MessageServiceRemote {
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
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding GHAMessage", e);
			throw super.generateGHAEJBException("message-find-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.msg.MessageServiceRemote#find(java.util.List)
	 */
	@Override
	public List<GHAMessage> find(List<String> messages) throws GHAEJBException {
		try {
			return em
					.createNamedQuery("GHAMessage.getAllByCodes",
							GHAMessage.class).setParameter("codes", messages)
					.setParameter("language", RuntimeParameters.getLang())
					.getResultList();

		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding GHAMessages", e);
			throw super.generateGHAEJBException("message-find-fail",
					RuntimeParameters.getLang(), em);
		}
	}
}