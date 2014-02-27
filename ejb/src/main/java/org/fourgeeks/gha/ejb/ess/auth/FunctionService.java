package org.fourgeeks.gha.ejb.ess.auth;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.ess.auth.Function;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.ejb.GHAEJBExceptionService;

/**
 * @author alacret
 * 
 *         permission service
 */
@Stateless
public class FunctionService extends GHAEJBExceptionService implements
		FunctionServiceRemote {

	private final static Logger logger = Logger.getLogger(FunctionService.class
			.getName());

	@PersistenceContext
	private EntityManager em;

	@Override
	public void delete(Function function) throws GHAEJBException {
		try {
			final Function localFunction = em.find(Function.class,
					function.getCode());
			em.remove(localFunction);
		} catch (final Exception e) {
			logger.log(Level.INFO, "ERROR: delete function", e);
			throw super.generateGHAEJBException("function-delete-fail", em);
		}
	}

	@Override
	public Function save(Function function) throws GHAEJBException {
		try {
			em.persist(function);
			em.flush();
			return em.find(Function.class, function.getCode());
		} catch (final Exception e) {
			logger.log(Level.INFO, "ERROR: saving function ", e);
			throw super.generateGHAEJBException("function-save-fail", em);
		}
	}

}