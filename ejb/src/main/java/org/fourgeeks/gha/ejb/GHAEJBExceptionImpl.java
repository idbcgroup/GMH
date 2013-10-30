package org.fourgeeks.gha.ejb;

import javax.persistence.EntityManager;

import org.fourgeeks.gha.domain.enu.LanguageEnum;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.msg.GHAMessage;
import org.fourgeeks.gha.domain.msg.GHAMessageId;

/**
 * @author vivi.torresg
 * 
 */
public class GHAEJBExceptionImpl {

	/**
	 * @param messageCode
	 * @param lang
	 * @param em
	 * @return a ghaejbexception
	 */
	public GHAEJBException generateGHAEJBException(String messageCode,
			LanguageEnum lang, EntityManager em) {
		GHAEJBException ghaejbException = new GHAEJBException();
		try {
			ghaejbException.setGhaMessage(em.find(GHAMessage.class,
					new GHAMessageId(messageCode, lang)));
		} catch (Exception e1) {
			ghaejbException.setGhaMessage(new GHAMessage(lang,
					"generic-error-msg",
					"Unknow system failure, please contact IT support"));
		}
		return ghaejbException;
	}
}
