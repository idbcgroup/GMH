/**
 * 
 */
package org.fourgeeks.gha.ejb.mix;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.mix.Bpa;
import org.fourgeeks.gha.ejb.GHAEJBExceptionService;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * @author emiliot, vivi.torresg
 * 
 */

@Stateless
public class BpaService extends GHAEJBExceptionService implements BpaServiceRemote {
	@PersistenceContext
	private EntityManager em;

	private final static Logger logger = Logger.getLogger(BpaService.class
			.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.mix.BpaServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		try {
			Bpa entity = em.find(Bpa.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete Bpa", e);
			throw super.generateGHAEJBException("bpa-delete-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.mix.BpaServiceRemote#find(org.fourgeeks.gha.domain
	 * .mix.Bpa)
	 */
	@Override
	public List<Bpa> find(Bpa bpa) throws GHAEJBException {
		try {
			return em.createNamedQuery("Bpa.findByBpa", Bpa.class)
					.setParameter("bpa", bpa).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error finding Bpa by bpa", ex);
			throw super.generateGHAEJBException("bpa-findByBpa-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.mix.BpaServiceRemote#find(long)
	 */
	@Override
	public Bpa find(long Id) throws GHAEJBException {
		try {
			return em.find(Bpa.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding Bpa", e);
			throw super.generateGHAEJBException("bpa-find-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.mix.BpaServiceRemote#getAll()
	 */
	@Override
	public List<Bpa> getAll() throws GHAEJBException {
		try {
			return em.createNamedQuery("Bpa.getAll", Bpa.class).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all Bpa", ex);
			throw super.generateGHAEJBException("bpa-getAll-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.mix.BpaServiceRemote#save(org.fourgeeks.gha.domain
	 * .mix.Bpa)
	 */
	@Override
	public Bpa save(Bpa bpa) throws GHAEJBException {
		try {
			em.persist(bpa);
			em.flush();
			return em.find(Bpa.class, bpa.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving Bpa ", e);
			throw super.generateGHAEJBException("bpa-save-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.mix.BpaServiceRemote#update(org.fourgeeks.gha.domain
	 * .mix.Bpa)
	 */
	@Override
	public Bpa update(Bpa bpa) throws GHAEJBException {
		try {
			Bpa res = em.merge(bpa);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update Bpa ", e);
			throw super.generateGHAEJBException("bpa-update-fail",
					RuntimeParameters.getLang(), em);
		}
	}
}