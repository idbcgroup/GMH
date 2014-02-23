package org.fourgeeks.gha.ejb.pdt;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.TransactionParams;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.ejb.GHAEJBExceptionService;

/**
 * Session Bean implementation class TransactionParamsService
 */
@Stateless
public class TransactionParamsService extends GHAEJBExceptionService implements
		TransactionParamsServiceLocal {

	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger
			.getLogger(TransactionParamsService.class.getName());

	@Override
	public TransactionParams find(String code) throws GHAEJBException {
		try {
			return em.find(TransactionParams.class, code);

		} catch (final Exception e) {
			logger.log(Level.INFO, "Error buscando TransactionParams por code",
					e);
			throw super.generateGHAEJBException("transactionParams-find-fail",
					em);
		}
	}
}
