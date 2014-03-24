package org.fourgeeks.gha.ejb.log;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.logs.UILog;
import org.fourgeeks.gha.domain.msg.GHAMessage;
import org.fourgeeks.gha.ejb.GHAEJBExceptionService;
import org.fourgeeks.gha.ejb.msg.MessageService;

/**
 * @author alacret
 * 
 */
@Stateless
public class UILogService extends GHAEJBExceptionService implements
		UILogServiceRemote, UILogServiceLocal {

	private final static Logger logger = Logger.getLogger(MessageService.class
			.getName());

	@PersistenceContext
	private EntityManager em;

	@Override
	public void delete(final UILog log) throws GHAEJBException {
		try {
			final UILog entity = em.find(UILog.class, log.getId());
			em.remove(entity);
		} catch (final Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete ="
					+ log.getClass().getName() + " with id = " + log.getId(), e);
			throw super.generateGHAEJBException("message-delete-fail", em);
		}
	}

	@Override
	public List<UILog> getAll() throws GHAEJBException {
		try {
			return em.createNamedQuery("UILog.getAll", UILog.class)
					.getResultList();
		} catch (final Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all uilogs", ex);
			throw super.generateGHAEJBException("uilog-getall-fail", em);
		}
	}

	@Override
	@Asynchronous
	public void log(final UILog log) throws GHAEJBException {
		GHAMessage message = log.getMessage();
		if (message == null) {
			logger.log(Level.INFO,
					"ERROR: logging, the message could not be null =");
			throw super.generateGHAEJBException("log-fail", em);
		}
		try {
			em.merge(message);
			em.persist(log);
		} catch (final Exception e) {
			logger.log(Level.INFO, "ERROR: unable to log ="
					+ log.getClass().getName() + " with id = " + log.getId(), e);
			throw super.generateGHAEJBException("log-fail", em);
		}
	}

}