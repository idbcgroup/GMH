package org.fourgeeks.gha.ejb.ess;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.fourgeeks.gha.domain.ess.ui.AppFormViewFunction;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.ejb.GHAEJBExceptionService;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * @author alacret
 * 
 */
@Stateless
public class AppFormViewFunctionService extends GHAEJBExceptionService implements
		AppFormViewFunctionServiceRemote {

	@PersistenceContext
	private EntityManager em;

	private final static Logger logger = Logger
			.getLogger(AppFormViewFunctionService.class.getName());

	@Override
	public List<AppFormViewFunction> getAll() throws GHAEJBException {
		try {
			TypedQuery<AppFormViewFunction> query = em.createNamedQuery(
					"AppFormViewFunction.getAll", AppFormViewFunction.class);
			return query.getResultList();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error obteniendo todos los function", e);
			throw super.generateGHAEJBException("function-getAll-fail",
					RuntimeParameters.getLang(), em);
		}
	}

}
