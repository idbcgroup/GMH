package org.fourgeeks.gha.ejb.language;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.fourgeeks.gha.domain.msg.UiString;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * @author alacret
 * 
 */
@Stateless(name = "language.LanguageService")
public class LanguageService implements LanguageServiceRemote {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<UiString> getLanguageStringsList() {
		TypedQuery<UiString> query = em.createNamedQuery(
				"UiString.getByLanguage", UiString.class);
		query.setParameter("language", RuntimeParameters.getLang());
		return query.getResultList();
	}

}