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

/**
 * @author emiliot, vivi.torresg
 * 
 */

@Stateless(name = "mix.CitizenService")
public class CitizenService implements CitizenServiceRemote {
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
	public void delete(long Id) throws GHAEJBException {
		try {
			Citizen entity = em.find(Citizen.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete Citizen", e);
			throw new GHAEJBException("ERROR: unable to delete Citizen "
					+ e.getCause().getMessage());
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
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error finding Citizen by citizen", ex);
			throw new GHAEJBException("Error obteniendo Citizen por citizen"
					+ ex.getCause().getMessage());
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
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding Citizen", e);
			throw new GHAEJBException("ERROR: finding Citizen "
					+ e.getCause().getMessage());
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
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all Citizen", ex);
			throw new GHAEJBException("Error obteniendo todas las Citizen"
					+ ex.getCause().getMessage());
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
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving Citizen ", e);
			throw new GHAEJBException("ERROR: saving Citizen "
					+ e.getCause().getMessage());
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
			Citizen res = em.merge(citizen);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update Citizen ", e);
			throw new GHAEJBException(
					"ERROR: no se puede actualizar el Citizen "
							+ e.getCause().getMessage());
		}
	}

}
