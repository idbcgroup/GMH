package org.fourgeeks.gha.ejb.log;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.logs.UILog;
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
	public void log(UILog log) throws GHAEJBException {
		try {
			em.persist(log);
		} catch (final Exception e) {
			logger.log(Level.INFO, "ERROR: unable to log ="
					+ log.getClass().getName() + " with id = " + log.getId(), e);
			throw super.generateGHAEJBException("log-fail", em);
		}
	}

	@Override
	public void delete(UILog log) throws GHAEJBException {
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

}