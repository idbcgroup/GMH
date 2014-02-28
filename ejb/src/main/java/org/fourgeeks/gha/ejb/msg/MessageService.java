package org.fourgeeks.gha.ejb.msg;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.ess.auth.SSOUser;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.logs.UILog;
import org.fourgeeks.gha.domain.msg.GHAMessage;
import org.fourgeeks.gha.domain.msg.GHAMessageId;
import org.fourgeeks.gha.ejb.GHAEJBExceptionService;
import org.fourgeeks.gha.ejb.RuntimeParameters;
import org.fourgeeks.gha.ejb.ess.auth.SSOUserServiceRemote;
import org.fourgeeks.gha.ejb.log.UILogServiceLocal;

/**
 * @author alacret
 * 
 */
@Stateless
public class MessageService extends GHAEJBExceptionService implements
		MessageServiceRemote, MessageServiceLocal {
	@PersistenceContext
	private EntityManager em;

	private final static Logger logger = Logger.getLogger(MessageService.class
			.getName());

	@EJB
	UILogServiceLocal service;

	@EJB
	SSOUserServiceRemote ssoUserService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.message.MessageServiceRemote#find(java.lang.String)
	 */
	@Override
	public GHAMessage find(String id) throws GHAEJBException {
		try {
			final GHAMessage message = em.find(GHAMessage.class,
					new GHAMessageId(id, RuntimeParameters.getLang()));
			service.log(new UILog(null, message));
			return message;
		} catch (final Exception e) {
			logger.log(Level.INFO, "ERROR: finding GHAMessage", e);
			throw super.generateGHAEJBException("message-find-fail", em);
		}
	}

	@Override
	public GHAMessage find(String user, String id) throws GHAEJBException {
		try {
			final GHAMessage find = em.find(GHAMessage.class, new GHAMessageId(
					id, RuntimeParameters.getLang()));
			log(user, find);
			return find;
		} catch (final Exception e) {
			logger.log(Level.INFO, "ERROR: finding GHAMessage", e);
			throw super.generateGHAEJBException("message-find-fail", em);
		}
	}

	private void log(String user, final GHAMessage find) throws GHAEJBException {
		try {
			final SSOUser ssoUser = ssoUserService.findByUsername(user);
			service.log(new UILog(ssoUser.getBpu(), find));
		} catch (final Exception e) {
			logger.info("ERROR:user not found where it should be found");
			service.log(new UILog(null, find));
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
			final List<GHAMessage> resultList = em
					.createNamedQuery("GHAMessage.getAllByCodes",
							GHAMessage.class).setParameter("codes", messages)
					.setParameter("language", RuntimeParameters.getLang())
					.getResultList();

			if (messages.size() == resultList.size())
				return resultList;

			// if not all the messages are returned
			final Map<String, Integer> keysFound = new TreeMap<String, Integer>();
			final List<GHAMessage> res = new ArrayList<GHAMessage>();

			// build a map with the keys found, map code, pos
			for (int i = 0; i < resultList.size(); ++i) {
				keysFound.put(resultList.get(i).getCode(), i);
			}

			// for each key to find
			for (final String key : messages) {
				// if it was found, add it to the final set
				if (keysFound.containsKey(key)) {
					res.add(resultList.get(keysFound.get(key)));
				} else {
					// else add a not found message with the key
					final GHAMessage next = super.generateGHAEJBException(
							"message-find-fail", em).getGhaMessage();

					// if it is not a generic message
					if (next.getCode().equals("message-find-fail")) {
						// add the key to the message
						next.setText(next.getText() + " " + key);
					}
					res.add(next);
				}
			}
			return res;

		} catch (final Exception e) {
			logger.log(Level.INFO, "ERROR: finding GHAMessages", e);
			throw super.generateGHAEJBException("message-find-fail", em);
		}
	}

	@Override
	public List<GHAMessage> find(String user, List<String> messages)
			throws GHAEJBException {
		try {
			final List<GHAMessage> resultList = em
					.createNamedQuery("GHAMessage.getAllByCodes",
							GHAMessage.class).setParameter("codes", messages)
					.setParameter("language", RuntimeParameters.getLang())
					.getResultList();

			for (final GHAMessage ghaMessage : resultList)
				log(user, ghaMessage);

			if (messages.size() == resultList.size())
				return resultList;

			// if not all the messages are returned
			final Map<String, Integer> keysFound = new TreeMap<String, Integer>();
			final List<GHAMessage> res = new ArrayList<GHAMessage>();

			// build a map with the keys found, map code, pos
			for (int i = 0; i < resultList.size(); ++i) {
				keysFound.put(resultList.get(i).getCode(), i);
			}

			// for each key to find
			for (final String key : messages) {
				// if it was found, add it to the final set
				if (keysFound.containsKey(key)) {
					res.add(resultList.get(keysFound.get(key)));
				} else {
					// else add a not found message with the key
					final GHAMessage next = super.generateGHAEJBException(
							"message-find-fail", em).getGhaMessage();

					// if it is not a generic message
					if (next.getCode().equals("message-find-fail")) {
						// add the key to the message
						next.setText(next.getText() + " " + key);
					}
					res.add(next);
				}
			}
			return res;

		} catch (final Exception e) {
			logger.log(Level.INFO, "ERROR: finding GHAMessages", e);
			throw super.generateGHAEJBException("message-find-fail", em);
		}
	}

	@Override
	// @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public GHAMessage save(GHAMessage ghaMessage) throws GHAEJBException {
		try {
			em.persist(ghaMessage);
			em.flush();
			return em
					.find(GHAMessage.class,
							new GHAMessageId(ghaMessage.getCode(), ghaMessage
									.getLang()));
		} catch (final Exception e) {
			logger.log(Level.INFO, "ERROR: saving message", e);
			throw super.generateGHAEJBException("message-save-fail", em);
		}
	}

	@Override
	// @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void delete(GHAMessage entity) throws GHAEJBException {
		try {
			final GHAMessage mesg = em.find(GHAMessage.class, new GHAMessageId(
					entity.getCode(), entity.getLang()));
			em.remove(mesg);
		} catch (final Exception e) {
			logger.log(Level.INFO,
					"ERROR: unable to delete =" + entity.getClass().getName()
							+ " with id = " + entity.getCode(), e);
			throw super.generateGHAEJBException("messague-delete-fail", em);
		}
	}
}