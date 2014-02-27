package org.fourgeeks.gha.ejb.ess.ui;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.fourgeeks.gha.domain.ess.ui.ViewFunction;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.ejb.GHAEJBExceptionService;

/**
 * @author alacret
 * 
 */
@Stateless
public class ViewFunctionService extends GHAEJBExceptionService implements
		ViewFunctionServiceRemote {

	@PersistenceContext
	private EntityManager em;

	private final static Logger logger = Logger
			.getLogger(ViewFunctionService.class.getName());

	@Override
	public List<ViewFunction> getAll() throws GHAEJBException {
		try {
			final TypedQuery<ViewFunction> query = em.createNamedQuery(
					"ViewFunction.getAll", ViewFunction.class);
			return query.getResultList();
		} catch (final Exception e) {
			logger.log(Level.SEVERE, "Error obteniendo todos los functions", e);
			throw super.generateGHAEJBException("function-getall-fail", em);
		}
	}

}
