package org.fourgeeks.gha.ejb;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.TimerParams;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;

/**
 * Session Bean implementation class TimerParamsService
 */
@Stateless
public class TimerParamsService extends GHAEJBExceptionService implements
		TimerParamsServiceLocal {

	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger
			.getLogger(TimerParamsService.class.getName());

	@Override
	public void delete(String code) throws GHAEJBException {
		try {
			final TimerParams entity = em.find(TimerParams.class, code);
			em.remove(entity);

		} catch (final Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete TimerParams", e);
			throw super.generateGHAEJBException("timerParams-delete-fail", em);
		}
	}

	@Override
	public List<TimerParams> getAll() throws GHAEJBException {
		try {
			return em.createNamedQuery("TimerParams.getAll", TimerParams.class)
					.getResultList();

		} catch (final Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all eiatypes", ex);
			throw super.generateGHAEJBException("timerParams-getAll-fail", em);
		}
	}

	@Override
	public TimerParams save(TimerParams entity) throws GHAEJBException {
		try {
			em.persist(entity);
			em.flush();
			return em.find(TimerParams.class, entity.getCode());

		} catch (final Exception e) {
			logger.log(Level.INFO, "ERROR: saving TimerParams ", e);
			throw super.generateGHAEJBException("timerParams-save-fail", em);
		}
	}

	@Override
	public TimerParams update(TimerParams entity) throws GHAEJBException {
		try {
			final TimerParams res = em.merge(entity);
			return res;
		} catch (final Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update TimerParams ", e);
			throw super.generateGHAEJBException("timerParams-update-fail", em);
		}
	}

}
