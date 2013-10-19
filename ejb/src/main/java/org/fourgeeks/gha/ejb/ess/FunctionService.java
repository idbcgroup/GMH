package org.fourgeeks.gha.ejb.ess;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.fourgeeks.gha.domain.ess.Function;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.ejb.GHAEJBExceptionImpl;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * @author alacret
 * 
 */
@Stateless(name = "ess.FunctionService")
public class FunctionService extends GHAEJBExceptionImpl implements
		FunctionServiceRemote {

	@PersistenceContext
	private EntityManager em;

	private final static Logger logger = Logger.getLogger(FunctionService.class
			.getName());

	@Override
	public List<Function> getAll() throws GHAEJBException {
		try {
			TypedQuery<Function> query = em.createNamedQuery("Function.getAll",
					Function.class);
			return query.getResultList();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error obteniendo todos los function", e);
			throw super.generateGHAEJBException("function-getAll-fail",
					RuntimeParameters.getLang(), em);
		}
	}

}
