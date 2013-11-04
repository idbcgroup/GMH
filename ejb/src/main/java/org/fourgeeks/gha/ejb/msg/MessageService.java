package org.fourgeeks.gha.ejb.msg;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
			List<GHAMessage> resultList = em
					.createNamedQuery("GHAMessage.getAllByCodes",
							GHAMessage.class).setParameter("codes", messages)
					.setParameter("language", RuntimeParameters.getLang())
					.getResultList();

			// if not all the messages are returned
			if (messages.size() != resultList.size()) {
				Map<String, Integer> keysFound = new TreeMap<String, Integer>();
				List<GHAMessage> res = new ArrayList<GHAMessage>();

				// build a map with the keys found, map code, pos
				for (int i = 0; i < resultList.size(); ++i) {
					keysFound.put(resultList.get(i).getCode(), i);
				}

				// for each key to find
				for (String key : messages) {
					// if it was found, add it to the final set
					if (keysFound.containsKey(key)) {
						res.add(resultList.get(keysFound.get(key)));
					} else {
						// else add a not found message with the key
						GHAMessage next = super.generateGHAEJBException(
								"message-find-fail",
								RuntimeParameters.getLang(), em)
								.getGhaMessage();

						// if it is not a generic message
						if (next.getCode().equals("message-find-fail")) {
							// add the key to the message
							next.setText(next.getText() + " " + key);
						}
						res.add(next);
					}
				}
				return res;
			}
			return resultList;

		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding GHAMessages", e);
			throw super.generateGHAEJBException("message-find-fail",
					RuntimeParameters.getLang(), em);
		}
	}
}