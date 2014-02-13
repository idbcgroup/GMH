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
import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.ejb.GHAEJBExceptionService;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * @author emiliot, vivi.torresg
 * 
 */

@Stateless
public class CitizenService extends GHAEJBExceptionService implements
		CitizenServiceRemote {
	@PersistenceContext
	private EntityManager em;

	private final static Logger logger = Logger.getLogger(CitizenService.class
			.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.mix.CitizenServiceRemote#delete(long)
	 */
	@Override
	public void delete(List<Citizen> citizens) throws GHAEJBException {
		try {
			for (final Citizen citizen : citizens) {
				final Citizen entity = em.find(Citizen.class, citizen.getId());
				em.remove(entity);
			}

		} catch (final Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete Citizen", e);
			throw super.generateGHAEJBException("citizen-delete-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.mix.CitizenServiceRemote#find(org.fourgeeks.gha
	 * .domain.mix.Citizen)
	 */
	@Override
	public List<Citizen> find(Citizen citizen) throws GHAEJBException {
		try {
			return em.createNamedQuery("Citizen.findByCitizen", Citizen.class)
					.setParameter("citizen", citizen).getResultList();
		} catch (final Exception ex) {
			logger.log(Level.SEVERE, "Error finding Citizen by citizen", ex);
			throw super.generateGHAEJBException("citizen-findByCitizen-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.mix.CitizenServiceRemote#find(long)
	 */
	@Override
	public Citizen find(long Id) throws GHAEJBException {
		try {
			return em.find(Citizen.class, Id);
		} catch (final Exception e) {
			logger.log(Level.INFO, "ERROR: finding Citizen", e);
			throw super.generateGHAEJBException("citizen-find-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.mix.CitizenServiceRemote#getAll()
	 */
	@Override
	public List<Citizen> getAll() throws GHAEJBException {
		try {
			return em.createNamedQuery("Citizen.getAll", Citizen.class)
					.getResultList();
		} catch (final Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all Citizen", ex);
			throw super.generateGHAEJBException("citizen-getAll-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.mix.CitizenServiceRemote#save(org.fourgeeks.gha
	 * .domain.mix.Citizen)
	 */
	@Override
	public Citizen save(Citizen citizen) throws GHAEJBException {
		try {
			em.persist(citizen);
			em.flush();
			return em.find(Citizen.class, citizen.getId());
		} catch (final Exception e) {
			logger.log(Level.INFO, "ERROR: saving Citizen ", e);
			throw super.generateGHAEJBException("citizen-save-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.mix.CitizenServiceRemote#update(org.fourgeeks.gha
	 * .domain.mix.Citizen)
	 */
	@Override
	public Citizen update(Citizen citizen) throws GHAEJBException {
		try {
			final Citizen res = em.merge(citizen);
			em.flush();
			return res;
		} catch (final Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update Citizen ", e);
			throw super.generateGHAEJBException("citizen-update-fail",
					RuntimeParameters.getLang(), em);
		}
	}
}