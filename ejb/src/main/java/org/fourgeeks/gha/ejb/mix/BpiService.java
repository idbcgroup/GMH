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
import org.fourgeeks.gha.domain.mix.Bpi;
import org.fourgeeks.gha.ejb.GHAEJBExceptionImpl;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * @author emiliot, vivi.torresg
 * 
 */

@Stateless(name = "mix.BpiService")
public class BpiService extends GHAEJBExceptionImpl implements BpiServiceRemote {
	@PersistenceContext
	private EntityManager em;

	private final static Logger logger = Logger.getLogger(BpiService.class
			.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.mix.BpiServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		try {
			Bpi entity = em.find(Bpi.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete Bpi", e);
			throw super.generateGHAEJBException("bpi-delete-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.mix.BpiServiceRemote#find(org.fourgeeks.gha.domain
	 * .mix.Bpi)
	 */
	@Override
	public List<Bpi> find(Bpi bpi) throws GHAEJBException {
		try {
			return em.createNamedQuery("Bpi.findByBpi", Bpi.class)
					.setParameter("bpi", bpi).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error finding Bpi by bpi", ex);
			throw super.generateGHAEJBException("bpi-findByBpi-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.mix.BpiServiceRemote#find(long)
	 */
	@Override
	public Bpi find(long Id) throws GHAEJBException {
		try {
			return em.find(Bpi.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding Bpi", e);
			throw super.generateGHAEJBException("bpi-find-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.mix.BpiServiceRemote#getAll()
	 */
	@Override
	public List<Bpi> getAll() throws GHAEJBException {
		try {
			return em.createNamedQuery("Bpi.getAll", Bpi.class).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all Bpi", ex);
			throw super.generateGHAEJBException("bpi-getAll-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.mix.BpiServiceRemote#save(org.fourgeeks.gha.domain
	 * .mix.Bpi)
	 */
	@Override
	public Bpi save(Bpi bpi) throws GHAEJBException {
		try {
			em.persist(bpi);
			em.flush();
			return em.find(Bpi.class, bpi.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving Bpi ", e);
			throw super.generateGHAEJBException("bpi-save-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.mix.BpiServiceRemote#update(org.fourgeeks.gha.domain
	 * .mix.Bpi)
	 */
	@Override
	public Bpi update(Bpi bpi) throws GHAEJBException {
		try {
			Bpi res = em.merge(bpi);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update Bpi ", e);
			throw super.generateGHAEJBException("bpi-update-fail",
					RuntimeParameters.getLang(), em);
		}
	}
}