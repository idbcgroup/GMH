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
	public GHAEJBException generateGHAEJBException(String messageCode,
			EntityManager em) {
		final GHAEJBException ghaejbException = new GHAEJBException();
		final LanguageEnum lang = RuntimeParameters.getLang();
		try {// the messague throw by the method
			final GHAMessage message = em.find(GHAMessage.class,
					new GHAMessageId(messageCode, lang));
			// service.log(new UILog(null, message));
			ghaejbException.setGhaMessage(message);
		} catch (final NoResultException e) {
			try {// the messague throw if the key for the method message is not
					// found
				final GHAMessage message = em.find(GHAMessage.class,
						new GHAMessageId("message-find-fail", lang));
				// service.log(new UILog(null, message));
				ghaejbException.setGhaMessage(message);
			} catch (final Exception e1) {// the message throw if the default
											// key message is not found
				final GHAMessage message = new GHAMessage(lang,
						"generic-error-msg",
						"Unknow system failure, please contact IT support");
				// try {
				// service.log(new UILog(null, message));
				// } catch (final GHAEJBException e2) {
				// logger.log(Level.SEVERE, "Error logueando el error", e2);
				// }

			}
		} catch (final Exception e1) {
			ghaejbException.setGhaMessage(new GHAMessage(lang,
					"generic-error-msg",
					"Unknow system failure, please contact IT support"));
		}
		return ghaejbException;
	}

	/**
	 * @param messageCode
	 * @param lang
	 * @param em
	 * @return a ghaejbexception
	 */
	@Deprecated
	public GHAEJBException generateGHAEJBException(String messageCode,
			LanguageEnum lang, EntityManager em) {
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
