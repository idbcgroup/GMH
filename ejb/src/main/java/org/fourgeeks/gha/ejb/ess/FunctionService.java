package org.fourgeeks.gha.ejb.ess;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.fourgeeks.gha.domain.ess.Function;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;

/**
 * @author alacret
 * 
 */
@Stateless(name = "ess.FunctionService")
public class FunctionService implements FunctionServiceRemote {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Function> getAll() throws GHAEJBException {
		TypedQuery<Function> query = em.createNamedQuery("Function.getAll",
				Function.class);
		return query.getResultList();
	}

}
