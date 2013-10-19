package org.fourgeeks.gha.ejb;

import javax.persistence.EntityManager;

import org.fourgeeks.gha.domain.enu.LanguageEnum;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.msg.GHAMessage;
import org.fourgeeks.gha.domain.msg.GHAMessageId;

/*
 * @author vivi.torresg 
 * 
 */
public class GHAEJBExceptionImpl {

	private GHAEJBException ghaejbException = new GHAEJBException();

	public GHAEJBException generateGHAEJBException(String messageCode,
			LanguageEnum lang, EntityManager em) {
		try {
			ghaejbException.setGhaMessage(em.find(GHAMessage.class,
					new GHAMessageId(messageCode, lang)));
		} catch (Exception e1) {
			ghaejbException
					.setGhaMessage(new GHAMessage(lang, "generic-error-msg",
							"Error de sistema, intente más tarde."));
		}
		return ghaejbException;
	}
}
