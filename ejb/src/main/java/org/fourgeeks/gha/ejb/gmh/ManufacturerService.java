/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.Manufacturer;
import org.fourgeeks.gha.ejb.GHAEJBExceptionImpl;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * @author emiliot, vivi.torresg
 * 
 */

@Stateless(name = "gmh.ManufacturerService")
public class ManufacturerService extends GHAEJBExceptionImpl implements
		ManufacturerServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger.getLogger(Manufacturer.class
			.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.ManufacturerServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		try {
			Manufacturer entity = em.find(Manufacturer.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete manufacturer", e);
			throw super.generateGHAEJBException("manufacturer-delete-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.ManufacturerServiceRemote#find(long)
	 */
	@Override
	public Manufacturer find(long Id) throws GHAEJBException {
		try {
			return em.find(Manufacturer.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "Error buscando Manufacturer por Id ", e);
			throw super.generateGHAEJBException("manufacturer-find-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.ManufacturerServiceRemote#find(org.fourgeeks
	 * .gha.domain.gmh.Manufacturer)
	 */
	@Override
	public List<Manufacturer> find(Manufacturer manufacturer)
			throws GHAEJBException {
		try {
			return em
					.createNamedQuery("Manufacturer.findByManufacturer",
							Manufacturer.class)
					.setParameter("manufacturer", manufacturer).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO,
					"Error: finding manufacturer by manufacturer", e);
			throw super.generateGHAEJBException(
					"manufacturer-findByManufacturer-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.ManufacturerServiceRemote#getAll()
	 */
	@Override
	public List<Manufacturer> getAll() throws GHAEJBException {
		try {
			return em.createNamedQuery("Manufacturer.getAll",
					Manufacturer.class).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all manufacturers", ex);
			throw super.generateGHAEJBException("manufacturer-getAll-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.ManufacturerServiceRemote#save(org.fourgeeks
	 * .gha.domain.gmh.Manufacturer)
	 */
	@Override
	public Manufacturer save(Manufacturer manufacturer) throws GHAEJBException {
		try {
			em.persist(manufacturer);
			em.flush();
			return em.find(Manufacturer.class, manufacturer.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving manufacturer", e);
			throw super.generateGHAEJBException("manufacturer-save-fail",
					RuntimeParameters.getLang(), em);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.ManufacturerServiceRemote#update(org.fourgeeks
	 * .gha.domain.gmh.Manufacturer)
	 */
	@Override
	public Manufacturer update(Manufacturer manufacturer)
			throws GHAEJBException {
		try {
			Manufacturer res = em.merge(manufacturer);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update manufacturer", e);
			throw super.generateGHAEJBException("manufacturer-update-fail",
					RuntimeParameters.getLang(), em);
		}
	}
}