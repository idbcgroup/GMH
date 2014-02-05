package org.fourgeeks.gha.ejb;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.fourgeeks.gha.domain.enu.LanguageEnum;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.msg.GHAMessage;
import org.fourgeeks.gha.domain.msg.GHAMessageId;

/**
 * @author vivi.torresg
 * 
 */
public class GHAEJBExceptionService {

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
		try {
			ghaejbException.setGhaMessage(em.find(GHAMessage.class,
					new GHAMessageId(messageCode, lang)));
		} catch (final NoResultException e) {
			try {
				ghaejbException.setGhaMessage(em.find(GHAMessage.class,
						new GHAMessageId("message-find-fail", lang)));
			} catch (final Exception e1) {
				ghaejbException.setGhaMessage(new GHAMessage(lang,
						"generic-error-msg",
						"Unknow system failure, please contact IT support"));
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
	 * @param em
	 * @return a ghaejbexception
	 */
	public GHAEJBException generateGHAEJBException(String messageCode,
			EntityManager em) {
		final GHAEJBException ghaejbException = new GHAEJBException();
		final LanguageEnum lang = RuntimeParameters.getLang();
		try {
			ghaejbException.setGhaMessage(em.find(GHAMessage.class,
					new GHAMessageId(messageCode, lang)));
		} catch (final NoResultException e) {
			try {
				ghaejbException.setGhaMessage(em.find(GHAMessage.class,
						new GHAMessageId("message-find-fail", lang)));
			} catch (final Exception e1) {
				ghaejbException.setGhaMessage(new GHAMessage(lang,
						"generic-error-msg",
						"Unknow system failure, please contact IT support"));
			}
		} catch (final Exception e1) {
			ghaejbException.setGhaMessage(new GHAMessage(lang,
					"generic-error-msg",
					"Unknow system failure, please contact IT support"));
		}
		return ghaejbException;
	}
}
