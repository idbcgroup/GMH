package org.fourgeeks.gha.ejb;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.fourgeeks.gha.domain.enu.LanguageEnum;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.logs.UILog;
import org.fourgeeks.gha.domain.msg.GHAMessage;
import org.fourgeeks.gha.domain.msg.GHAMessageId;
import org.fourgeeks.gha.ejb.log.UILogServiceLocal;

/**
 * @author vivi.torresg
 * 
 */
public class GHAEJBExceptionService {

	@EJB
	UILogServiceLocal service;

	private final static Logger logger = Logger
			.getLogger(GHAEJBExceptionService.class.getName());

	/**
	 * @param messageCode
	 * @param em
	 * @return a ghaejbexception
	 */
	public GHAEJBException generateGHAEJBException(final String messageCode,
			final EntityManager em) {
		final LanguageEnum lang = RuntimeParameters.getLang();

		GHAMessage message = em.find(GHAMessage.class, new GHAMessageId(
				messageCode, lang));

		if (message == null) {
			logger.log(Level.SEVERE,
					"Unavailable to obtain the error message: " + messageCode);
			message = em.find(GHAMessage.class, new GHAMessageId(
					"message-find-fail", lang));
			if (message == null) {
				logger.log(
						Level.SEVERE,
						"Unavailable to obtain the default error message that should be in the database");
				message = new GHAMessage(lang, "generic-error-msg",
						"Unknow system failure, please contact IT support");
			}
		}

		try {
			service.log(new UILog(null, message));
		} catch (GHAEJBException e) {
			logger.log(Level.SEVERE, "the service could not log");
		}
		final GHAEJBException ghaejbException = new GHAEJBException();
		ghaejbException.setGhaMessage(message);

		return ghaejbException;
	}

	/**
	 * @param messageCode
	 * @param lang
	 * @param em
	 * @return a ghaejbexception
	 * 
	 *         this version is deprecated an error proun
	 */
	@Deprecated
	public GHAEJBException generateGHAEJBException(final String messageCode,
			final LanguageEnum lang, final EntityManager em) {
		final GHAEJBException ghaejbException = new GHAEJBException();
		try {// the messague throw by the method
			final GHAMessage message = em.find(GHAMessage.class,
					new GHAMessageId(messageCode, lang));
			service.log(new UILog(null, message));
			ghaejbException.setGhaMessage(message);
		} catch (final NoResultException e) {
			try {// the messague throw if the key for the method message is not
					// found
				final GHAMessage message = em.find(GHAMessage.class,
						new GHAMessageId("message-find-fail", lang));
				service.log(new UILog(null, message));
				ghaejbException.setGhaMessage(message);
			} catch (final Exception e1) {// the message throw if the default
											// key message is not found
				final GHAMessage message = new GHAMessage(lang,
						"generic-error-msg",
						"Unknow system failure, please contact IT support");
				try {
					service.log(new UILog(null, message));
				} catch (final GHAEJBException e2) {
					logger.log(Level.SEVERE, "Error logueando el error", e2);
				}
				ghaejbException.setGhaMessage(message);
			}
		} catch (final Exception e1) {
			final GHAMessage message = new GHAMessage(lang,
					"generic-error-msg",
					"Unknow system failure, please contact IT support");
			try {
				service.log(new UILog(null, message));
			} catch (final GHAEJBException e2) {
				System.out
						.println("\n\n\nERROR2 trying logging the message\n\n\n");
			}
			ghaejbException.setGhaMessage(message);
		}

		return ghaejbException;
	}
}
