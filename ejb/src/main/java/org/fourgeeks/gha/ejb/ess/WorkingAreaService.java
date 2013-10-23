/**
 * 
 */
package org.fourgeeks.gha.ejb.ess;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.ess.WorkingArea;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.ejb.GHAEJBExceptionImpl;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * @author emiliot, vivi.torresg
 * 
 */

@Stateless(name = "ess.WorkingAreaService")
public class WorkingAreaService extends GHAEJBExceptionImpl implements
		WorkingAreaServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger
			.getLogger(WorkingAreaService.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.ess.WorkingAreaServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		try {
			WorkingArea entity = em.find(WorkingArea.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete WorkingArea", e);
			throw super.generateGHAEJBException("workingArea-delete-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.ess.WorkingAreaServiceRemote#find(org.fourgeeks
	 * .gha.domain.ess.WorkingArea)
	 */
	@Override
	public List<WorkingArea> find(WorkingArea workingArea)
			throws GHAEJBException {
		try {
			return em
					.createNamedQuery("WorkingArea.findByWorkingArea",
							WorkingArea.class)
					.setParameter("workingArea", workingArea).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE,
					"Error finding WorkingArea by workingArea", ex);
			throw super.generateGHAEJBException(
					"workingArea-findByWorkingArea-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.ess.WorkingAreaServiceRemote#find(long)
	 */
	@Override
	public WorkingArea find(long Id) throws GHAEJBException {
		try {
			return em.find(WorkingArea.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding WorkingArea", e);
			throw super.generateGHAEJBException("workingArea-find-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.ess.WorkingAreaServiceRemote#getAll()
	 */
	@Override
	public List<WorkingArea> getAll() throws GHAEJBException {
		try {
			return em.createNamedQuery("WorkingArea.getAll", WorkingArea.class)
					.getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all WorkingArea", ex);
			throw super.generateGHAEJBException("workingArea-getAll-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.ess.WorkingAreaServiceRemote#save(org.fourgeeks
	 * .gha.domain.ess.WorkingArea)
	 */
	@Override
	public WorkingArea save(WorkingArea entity) throws GHAEJBException {
		try {
			em.persist(entity);
			em.flush();
			return em.find(WorkingArea.class, entity.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving WorkingArea ", e);
			throw super.generateGHAEJBException("workingArea-save-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.ess.WorkingAreaServiceRemote#update(org.fourgeeks
	 * .gha.domain.ess.WorkingArea)
	 */
	@Override
	public WorkingArea update(WorkingArea entity) throws GHAEJBException {
		try {
			WorkingArea res = em.merge(entity);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update WorkingArea ", e);
			throw super.generateGHAEJBException("workingArea-update-fail",
					RuntimeParameters.getLang(), em);
		}
	}
}
